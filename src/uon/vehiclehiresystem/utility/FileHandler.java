package uon.vehiclehiresystem.utility;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler<T> {

    private FileInputStream fileInputStream;
    private ObjectInputStream objectInputStream;
    private FileOutputStream fileOutputStream;
    private ObjectOutputStream objectOutputStream;

    public boolean writeObject(T obj, String fileName, boolean append) throws FileNotFoundException {

        String dataDir = Utility.DATA_DIR;

        // Check if data directory exists
        if(!Utility.doesDirExist(dataDir)) {
            // Create if does't exist
            if (!Utility.createDirectory(dataDir)) {
                // Throw exception if cannot create
                throw new FileNotFoundException("Directory Not Found! :" + dataDir);
            }
        }

        // Now check for file
        if(!Utility.doesDirExist(dataDir + fileName)){
            append = false;
        }

        try {
            fileOutputStream = new FileOutputStream(dataDir + fileName, append);

            if(append){
                objectOutputStream = new ObjectOutputStreamAppend(fileOutputStream);
            }else{
                objectOutputStream = new ObjectOutputStream(fileOutputStream);
            }

            objectOutputStream.writeObject(obj);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public List<T> readObjects(String fileName){
        List<T> objectList = new ArrayList<T>();

        try {
            fileInputStream = new FileInputStream(Utility.DATA_DIR + fileName);
            objectInputStream = new ObjectInputStream(fileInputStream);

            T obj = null;

            while((obj = (T) objectInputStream.readObject()) != null){
                objectList.add(obj);
            }

        } catch (EOFException e) {
            // Reached the end of file
            return objectList;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return objectList;
    }

    /** Returns the object as soon as objects match (improving optimization)
     *
     * Must override 'equals' method of the object to find the match otherwise does not work
     */
    public T findFirstMatchingObject(String fileName, T objComparing){
        try {
            fileInputStream = new FileInputStream(Utility.DATA_DIR + fileName);
            objectInputStream = new ObjectInputStream(fileInputStream);

            T obj = null;

            while((obj = (T) objectInputStream.readObject()) != null){
                if(obj.equals(objComparing)) return obj;
            }

        } catch (FileNotFoundException e) {
            // Not implemented
        } catch (IOException e) {
            // Not implemented
        } catch (ClassNotFoundException e) {
            // Not implemented
        }

        return null;
    }


    /** Discards the object while loading into the list as objects match, making it possible to
     * perform the delete operation in 2 loops instead of 3 (improving optimization)
     *
     * Must override 'equals' method of the object to find the match otherwise does not work
     */
    public boolean deleteMatchingObject(String fileName, T objComparing){
        List<T> objectList = new ArrayList<T>();

        try {
            fileInputStream = new FileInputStream(Utility.DATA_DIR + fileName);
            objectInputStream = new ObjectInputStream(fileInputStream);

            T obj = null;

            while((obj = (T) objectInputStream.readObject()) != null){
                // Only add to the new list if objects match
                if(!obj.equals(objComparing)) objectList.add(obj);
            }

        } catch (EOFException e){

            // Rewrite the file after reaching the end of file

            try {
                fileOutputStream = new FileOutputStream(Utility.DATA_DIR + fileName, false);
                objectOutputStream = new ObjectOutputStream(fileOutputStream);

                for (T objectItem: objectList) {
                    objectOutputStream.writeObject(objectItem);
                }

                objectOutputStream.close();
                return true;
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }


}

/** Must use this modified class to append objects to same file
*/
class ObjectOutputStreamAppend extends ObjectOutputStream{

    public ObjectOutputStreamAppend(OutputStream out) throws IOException {
        super(out);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        // Resets the header to allow write file appends
        reset();
    }
}
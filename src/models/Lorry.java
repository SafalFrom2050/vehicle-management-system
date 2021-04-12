package models;

import utility.FileHandler;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Lorry extends Vehicle implements Serializable {

    private static final long serialVersionUID = 144341031459673221L;

    private static final String FILE_NAME = "lorrys.dat";

    private int loadingCapacity;

    public Lorry(){
        setType(TYPE_LORRY);
    }

    public Lorry(int registrationNumber, int topSpeed, int dailyHireRate, String make, String model, boolean isHired, int loadingCapacity) {
        super(registrationNumber, topSpeed, dailyHireRate, make, model, isHired);
        this.loadingCapacity = loadingCapacity;

        setType(TYPE_LORRY);
    }

    public int getLoadingCapacity() {
        return loadingCapacity;
    }

    public void setLoadingCapacity(int loadingCapacity) {
        this.loadingCapacity = loadingCapacity;
    }

    public void create() throws FileNotFoundException {
        FileHandler<Lorry> fileHandler = new FileHandler<Lorry>();

        fileHandler.writeObject(this, FILE_NAME, true);
    }

    public void delete() {
        FileHandler<Lorry> fileHandler = new FileHandler<Lorry>();
        boolean delete = fileHandler.deleteFirstMatchingObject(FILE_NAME, this);

        if(delete){
            System.out.println("Delete Success!");
        }else{
            System.out.println("Delete Failed!");
        }
    }

    public List getLorrysList(){
        FileHandler<Vehicle> fileHandler = new FileHandler<Vehicle>();
        List lorrys = fileHandler.readObjects(FILE_NAME);

        return lorrys;
    }

    public static List<Lorry> getDummyList(){
        List<Lorry> lorryList = new ArrayList<>();
        lorryList.add(new Lorry(1, 180, 4, "Lorry 1", "2000", false,  40));

        return lorryList;
    }
}

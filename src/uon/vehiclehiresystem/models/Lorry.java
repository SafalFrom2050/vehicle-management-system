package uon.vehiclehiresystem.models;

import uon.vehiclehiresystem.utility.FileHandler;

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

    public Lorry(int registrationNumber){
        super(registrationNumber);
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

    public boolean delete() {
        FileHandler<Lorry> fileHandler = new FileHandler<Lorry>();
        return fileHandler.deleteMatchingObject(FILE_NAME, this);

    }

    public List getLorrysList(){
        FileHandler<Vehicle> fileHandler = new FileHandler<Vehicle>();
        List lorrys = fileHandler.readObjects(FILE_NAME);

        return lorrys;
    }

    public static Lorry getLorryWithRegNo(int registrationNumber){
        FileHandler<Lorry> fileHandler = new FileHandler<>();
        return fileHandler.findFirstMatchingObject(FILE_NAME, new Lorry(registrationNumber));
    }

    public static List<Lorry> getDummyList(){
        List<Lorry> lorryList = new ArrayList<>();
        lorryList.add(new Lorry(1, 180, 4, "Lorry 1", "2000", false,  40));

        return lorryList;
    }
}

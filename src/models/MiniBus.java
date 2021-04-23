package models;

import utility.FileHandler;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MiniBus extends Vehicle implements Serializable {
    private static final long serialVersionUID = 144341031459673221L;

    private static final String FILE_NAME = "mini_bus.dat";

    private int seatingCapacity;

    public MiniBus(){
        setType(TYPE_MINI_BUS);
    }

    public MiniBus(int registrationNumber){
        super(registrationNumber);
    }

    public MiniBus(int registrationNumber, int topSpeed, int dailyHireRate, String make, String model, boolean isHired, int seatingCapacity) {
        super(registrationNumber, topSpeed, dailyHireRate, make, model, isHired);
        this.seatingCapacity = seatingCapacity;
        setType(TYPE_MINI_BUS);
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }


    public void addToDB() throws FileNotFoundException {
        FileHandler<MiniBus> fileHandler = new FileHandler<>();
        Class classname = this.getClass();
        fileHandler.writeObject(this, FILE_NAME, true);
    }

    public List getMiniBusesList(){
        FileHandler<Vehicle> fileHandler = new FileHandler<Vehicle>();
        List miniBuses = fileHandler.readObjects(FILE_NAME);

        return miniBuses;
    }

    public static MiniBus getMiniBusWithRegNo(int registrationNumber){
        FileHandler<MiniBus> fileHandler = new FileHandler<>();
        return fileHandler.findFirstMatchingObject(FILE_NAME, new MiniBus(registrationNumber));
    }

    public void create() throws FileNotFoundException {
        FileHandler<MiniBus> fileHandler = new FileHandler<MiniBus>();

        fileHandler.writeObject(this, FILE_NAME, true);
    }

    public boolean delete() {
        FileHandler<MiniBus> fileHandler = new FileHandler<MiniBus>();
        return fileHandler.deleteMatchingObject(FILE_NAME, this);

    }

    public static List<MiniBus> getDummyList(){
        List<MiniBus> miniBusList = new ArrayList<>();
        miniBusList.add(new MiniBus(1, 180, 4, "Mini Bus 1", "2000", false,  4));

        return miniBusList;
    }

}

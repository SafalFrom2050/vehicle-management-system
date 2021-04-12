package models;

import utility.FileHandler;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Car extends Vehicle implements Serializable {

    private static final long serialVersionUID = 144341031459673221L;

    private static final String FILE_NAME = "cars.dat";

    private String fuelType;
    private int numberOfDoors;


    public Car(){
        setType(TYPE_CAR);
    }

    public Car(int registrationNumber, int topSpeed, int dailyHireRate, String make, String model, boolean isHired, String fuelType, int numberOfDoors) {
        super(registrationNumber, topSpeed, dailyHireRate, make, model, isHired);
        this.fuelType = fuelType;
        this.numberOfDoors = numberOfDoors;
        setType(TYPE_CAR);
    }


    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }


    public static List<Car> getDummyList(){
        List<Car> carList = new ArrayList<>();
        carList.add(new Car(1, 180, 4, "Toyota", "2020", false, "petrol", 4));
        carList.add(new Car(2, 170, 4, "Ford", "2010", false, "petrol", 4));
        carList.add(new Car(3, 160, 4, "Hyundai", "2017", false, "petrol", 4));
        carList.add(new Car(4, 140, 4, "Tata", "2019", false, "petrol", 4));
        carList.add(new Car(4, 140, 4, "Tata", "2019", false, "petrol", 4));
        carList.add(new Car(8, 140, 4, "Tata", "2019", false, "petrol", 4));
        carList.add(new Car(3, 140, 4, "Tata", "2019", false, "petrol", 4));
        carList.add(new Car(6, 140, 4, "Tata", "2019", false, "petrol", 4));
        carList.add(new Car(5, 140, 4, "Tata", "2019", false, "petrol", 4));

        return carList;
    }

    public void create() throws FileNotFoundException {
        FileHandler<Car> fileHandler = new FileHandler<Car>();

        fileHandler.writeObject(this, FILE_NAME, true);
    }

    public void delete() {
        FileHandler<Car> fileHandler = new FileHandler<Car>();
        boolean delete = fileHandler.deleteFirstMatchingObject(FILE_NAME, this);

        if(delete){
            System.out.println("Delete Success!");
        }else{
            System.out.println("Delete Failed!");
        }
    }

    public List getCarsList(){
        FileHandler<Vehicle> fileHandler = new FileHandler<Vehicle>();
        List cars = fileHandler.readObjects(FILE_NAME);

        return cars;
    }
}

package uon.vehiclehiresystem.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Vehicle implements Serializable {
    private static final long serialVersionUID = 6098736424535717790L;
    public static final int TYPE_VEHICLE = 1000, TYPE_CAR = 1001, TYPE_MINI_BUS = 1002, TYPE_LORRY = 1003;

    private int registrationNumber, topSpeed, dailyHireRate;
    private String make, model;
    private int type;

    private boolean isHired;


    public Vehicle(){

    }

    public Vehicle(int registrationNumber){
        this.registrationNumber = registrationNumber;
    }

    public Vehicle(int registrationNumber, int topSpeed, int dailyHireRate, String make, String model, boolean isHired) {
        this.registrationNumber = registrationNumber;
        this.topSpeed = topSpeed;
        this.dailyHireRate = dailyHireRate;
        this.make = make;
        this.model = model;
        this.isHired = isHired;
    }

    public int getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(int registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public int getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(int topSpeed) {
        this.topSpeed = topSpeed;
    }

    public int getDailyHireRate() {
        return dailyHireRate;
    }

    public void setDailyHireRate(int dailyHireRate) {
        this.dailyHireRate = dailyHireRate;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isHired() {
        return isHired;
    }

    public void setHired(boolean hired) {
        isHired = hired;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStringType(){
        if(type == TYPE_CAR){
            return "Car";
        }else if(type == TYPE_MINI_BUS){
            return "Mini Bus";
        }else if(type == TYPE_LORRY){
            return "Lorry";
        }else{
            return null;
        }
    }

    public List<Vehicle> getVehiclesList(){
        Car car = new Car();
        MiniBus miniBus = new MiniBus();
        Lorry lorry = new Lorry();

        List<Vehicle> vehicles = new ArrayList<>();

        vehicles.addAll(car.getCarsList());
        vehicles.addAll(miniBus.getMiniBusesList());
        vehicles.addAll(lorry.getLorrysList());

        return vehicles;
    }

    public static Vehicle getVehicleWithRegNo(int registrationNumber){
        Vehicle vehicle;

        if((vehicle = Car.getCarWithRegNo(registrationNumber)) != null){
            return vehicle;
        }else if((vehicle = MiniBus.getMiniBusWithRegNo(registrationNumber)) != null){
            return vehicle;
        }else if((vehicle = Lorry.getLorryWithRegNo(registrationNumber)) != null){
            return vehicle;
        }

        return null;
    }

    public boolean delete(){
        if(type == TYPE_CAR){
            Car car = new Car();
            car.setRegistrationNumber(registrationNumber);
            return car.delete();
        }else if(type == TYPE_MINI_BUS){
            MiniBus miniBus = new MiniBus();
            miniBus.setRegistrationNumber(registrationNumber);
            return miniBus.delete();
        }else if(type == TYPE_LORRY){
            Lorry lorry = new Lorry();
            lorry.setRegistrationNumber(registrationNumber);
            return lorry.delete();
        }

        return false;
    }

    @Override
    public boolean equals(Object obj) {
        Vehicle inVehicle = (Vehicle) obj;
        return (inVehicle.getRegistrationNumber() == this.getRegistrationNumber());
    }
}

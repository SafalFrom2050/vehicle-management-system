package controllers;

import models.Car;
import models.Lorry;
import models.MiniBus;
import models.Vehicle;
import views.FrameAddVehicle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;

import static views.miscellaneous.Messages.showErrorMessage;
import static views.miscellaneous.Messages.showMessage;

public class AddVehicleController implements ActionListener {

    private WindowListener windowListener;
    private FrameAddVehicle frameAddVehicle;
    private Vehicle vehicle;
    private Car car;
    private MiniBus miniBus;
    private Lorry lorry;

    private int type;

    public AddVehicleController(FrameAddVehicle frameAddVehicle, Vehicle vehicle){
        this.frameAddVehicle = frameAddVehicle;
        this.vehicle = vehicle;
        this.type = Vehicle.TYPE_VEHICLE;

        addEventListeners();
    }

    public AddVehicleController(FrameAddVehicle frameAddVehicle, Car car){
        this.frameAddVehicle = frameAddVehicle;
        this.car = car;
        this.type = Vehicle.TYPE_CAR;

        frameAddVehicle.getLblSpecial1().setText("Fuel Type");
        frameAddVehicle.getLblSpecial2().setText("Number Of Doors");

        addEventListeners();
    }

    public AddVehicleController(FrameAddVehicle frameAddVehicle, Lorry lorry){
        this.frameAddVehicle = frameAddVehicle;
        this.lorry = lorry;
        this.type = Vehicle.TYPE_LORRY;

        frameAddVehicle.getLblSpecial1().setText("Loading Capacity");
        frameAddVehicle.getLblSpecial2().setVisible(false);
        frameAddVehicle.getTxtSpecial2().setVisible(false);

        addEventListeners();
    }

    public AddVehicleController(FrameAddVehicle frameAddVehicle, MiniBus miniBus){
        this.frameAddVehicle = frameAddVehicle;
        this.miniBus = miniBus;
        this.type = Vehicle.TYPE_MINI_BUS;

        frameAddVehicle.getLblSpecial1().setText("Seating Capacity");
        frameAddVehicle.getLblSpecial2().setVisible(false);
        frameAddVehicle.getTxtSpecial2().setVisible(false);

        addEventListeners();
    }


    public void addWindowListener(WindowListener windowListener){
        this.windowListener = windowListener;
        frameAddVehicle.addWindowListener(windowListener);
    }

    public void addEventListeners(){
        frameAddVehicle.getBtnConfirm().addActionListener(this);
    }

    public void addToDB(){

        if(!validateForm()){
            showErrorMessage("Please input all fields", frameAddVehicle);
            return;
        }
//        int topSpeed, int dailyHireRate, String make, String model, boolean isHired

        int registrationNumber = Integer.parseInt(frameAddVehicle.getTxtRegNumber().getText());
        int topSpeed = Integer.parseInt(frameAddVehicle.getTxtTopSpeed().getText());
        int dailyHireRate = Integer.parseInt(frameAddVehicle.getTxtDailyHireRate().getText());
        String make = frameAddVehicle.getTxtMake().getText();
        String model = frameAddVehicle.getTxtModel().getText();


        if(type == Vehicle.TYPE_CAR){
            String fuelType = frameAddVehicle.getTxtSpecial1().getText();
            int numberOfDoors = Integer.parseInt(frameAddVehicle.getTxtSpecial2().getText());

            Car vehicle = new Car(registrationNumber, topSpeed, dailyHireRate, make, model, false, fuelType, numberOfDoors);
            vehicle.setType(Vehicle.TYPE_CAR);

            try {
                vehicle.create();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }else if(type == Vehicle.TYPE_MINI_BUS){

            int seatingCapacity = Integer.parseInt(frameAddVehicle.getTxtSpecial1().getText());

            MiniBus vehicle = new MiniBus(registrationNumber, topSpeed, dailyHireRate, make, model, false, seatingCapacity);
            vehicle.setType(Vehicle.TYPE_MINI_BUS);

            try {
                vehicle.create();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }else if(type == Vehicle.TYPE_LORRY){
            int loadingCapacity = Integer.parseInt(frameAddVehicle.getTxtSpecial1().getText());

            Lorry vehicle = new Lorry(registrationNumber, topSpeed, dailyHireRate, make, model, false, loadingCapacity);
            vehicle.setType(Vehicle.TYPE_LORRY);

            try {
                vehicle.create();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        showMessage("Added Successfully", frameAddVehicle);
        windowListener.windowClosing(null);
        frameAddVehicle.dispose();
    }

    public boolean validateForm(){
        boolean isUnfinished = false;

        if(frameAddVehicle.getTxtRegNumber().getText().equals("")) isUnfinished = true;
        if(frameAddVehicle.getTxtDailyHireRate().getText().equals("")) isUnfinished = true;
        if(frameAddVehicle.getTxtMake().getText().equals("")) isUnfinished = true;
        if(frameAddVehicle.getTxtModel().getText().equals("")) isUnfinished = true;
        if(frameAddVehicle.getTxtTopSpeed().getText().equals("")) isUnfinished = true;
        if(frameAddVehicle.getTxtSpecial1().getText().equals("")) isUnfinished = true;

        // Special 2 is only in car
        if(type == Vehicle.TYPE_CAR && frameAddVehicle.getTxtSpecial2().getText().equals("")) isUnfinished = true;

        return !isUnfinished;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == frameAddVehicle.getBtnConfirm()){
            System.out.println("Clicked!");
            addToDB();
        }
    }
}

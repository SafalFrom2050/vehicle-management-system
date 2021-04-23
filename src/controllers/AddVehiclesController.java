package controllers;

import models.Car;
import models.Lorry;
import models.MiniBus;
import models.Vehicle;
import utility.Validator;
import views.FrameAddVehicle;
import views.miscellaneous.Messages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;

import static views.miscellaneous.Messages.showErrorMessage;
import static views.miscellaneous.Messages.showMessage;

public class AddVehiclesController implements ActionListener {

    private WindowListener windowListener;
    private FrameAddVehicle frameAddVehicle;
    private Vehicle vehicle;
    private Car car;
    private MiniBus miniBus;
    private Lorry lorry;

    private int type;

    public AddVehiclesController(FrameAddVehicle frameAddVehicle, Vehicle vehicle){
        this.frameAddVehicle = frameAddVehicle;
        this.vehicle = vehicle;
        this.type = Vehicle.TYPE_VEHICLE;

        addEventListeners();
    }

    public AddVehiclesController(FrameAddVehicle frameAddVehicle, Car car){
        this.frameAddVehicle = frameAddVehicle;
        this.car = car;
        this.type = Vehicle.TYPE_CAR;

        frameAddVehicle.getLblSpecial1().setText("Fuel Type");
        frameAddVehicle.getLblSpecial2().setText("Number Of Doors");

        addEventListeners();
    }

    public AddVehiclesController(FrameAddVehicle frameAddVehicle, Lorry lorry){
        this.frameAddVehicle = frameAddVehicle;
        this.lorry = lorry;
        this.type = Vehicle.TYPE_LORRY;

        frameAddVehicle.getLblSpecial1().setText("Loading Capacity");
        frameAddVehicle.getLblSpecial2().setVisible(false);
        frameAddVehicle.getTxtSpecial2().setVisible(false);

        addEventListeners();
    }

    public AddVehiclesController(FrameAddVehicle frameAddVehicle, MiniBus miniBus){
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

    public void addToDB() throws FileNotFoundException{

        if(!validateForm()){
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

            vehicle.create();

        }else if(type == Vehicle.TYPE_MINI_BUS){

            int seatingCapacity = Integer.parseInt(frameAddVehicle.getTxtSpecial1().getText());

            MiniBus vehicle = new MiniBus(registrationNumber, topSpeed, dailyHireRate, make, model, false, seatingCapacity);
            vehicle.setType(Vehicle.TYPE_MINI_BUS);

            vehicle.create();

        }else if(type == Vehicle.TYPE_LORRY){
            int loadingCapacity = Integer.parseInt(frameAddVehicle.getTxtSpecial1().getText());

            Lorry vehicle = new Lorry(registrationNumber, topSpeed, dailyHireRate, make, model, false, loadingCapacity);
            vehicle.setType(Vehicle.TYPE_LORRY);

            vehicle.create();

        }

        showMessage("Added Successfully", frameAddVehicle);
        windowListener.windowClosing(null);
        frameAddVehicle.dispose();
    }

    private boolean validateForm(){
        boolean isUnfinished = false;

        if(!Validator.validateUniqueRegNo(frameAddVehicle.getTxtRegNumber())){
            showErrorMessage(frameAddVehicle.getLblRegNumber().getText() +
                    " must be unique!", frameAddVehicle);
            isUnfinished = true;
        }
        if(!Validator.validateIsNumber(frameAddVehicle.getTxtDailyHireRate())){
            showErrorMessage( frameAddVehicle.getLblDailyHireRate().getText() +
                    " must be a number!", frameAddVehicle);
            isUnfinished = true;
        }
        if(!Validator.validateNotNull(frameAddVehicle.getTxtMake())){
            showErrorMessage( frameAddVehicle.getLblMake().getText() +
                    " is missing!", frameAddVehicle);
            isUnfinished = true;
        }
        if(!Validator.validateNotNull(frameAddVehicle.getTxtModel())){
            showErrorMessage( frameAddVehicle.getLblModel().getText() +
                    " is missing!", frameAddVehicle);
            isUnfinished = true;
        }

        if(!Validator.validateIsNumber(frameAddVehicle.getTxtTopSpeed())){
            showErrorMessage( frameAddVehicle.getLblTopSpeed().getText() +
                    " must be a number!", frameAddVehicle);
            isUnfinished = true;
        }
        if(!Validator.validateNotNull(frameAddVehicle.getTxtSpecial1())){
            showErrorMessage( frameAddVehicle.getLblSpecial1().getText() +
                    " is missing!", frameAddVehicle);
            isUnfinished = true;
        }


        // Special 2 is only in car
        if(type == Vehicle.TYPE_CAR && !Validator.validateNotNull(frameAddVehicle.getTxtSpecial2())){
            showErrorMessage( frameAddVehicle.getLblSpecial2().getText() +
                    " is missing!", frameAddVehicle);
            isUnfinished = true;
        }

        return !isUnfinished;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == frameAddVehicle.getBtnConfirm()){
            try {
                addToDB();
            } catch (FileNotFoundException ex) {
                Messages.showErrorMessage("Couldn't create new vehicle!", frameAddVehicle);
            }
        }
    }
}

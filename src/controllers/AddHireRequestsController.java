package controllers;

import models.*;
import utility.Utility;
import views.FrameAddHireRequest;
import views.miscellaneous.Messages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.util.Date;

public class AddHireRequestsController implements ActionListener {

    private FrameAddHireRequest frameAddHireRequest;

    private HiredVehicle hiredVehicleModel;
    private Vehicle vehicle;

    private WindowListener windowListener;

    public AddHireRequestsController(FrameAddHireRequest frameAddHireRequest, HiredVehicle hiredVehicleModel){
        // Important to pass hiredVehicleModel with vehicle registration number

        this.frameAddHireRequest = frameAddHireRequest;
        this.hiredVehicleModel = hiredVehicleModel;

        loadDataIntoGUI();
        setEventListeners();
    }

    private void loadDataIntoGUI(){
        int regNo = hiredVehicleModel.getVehicleRegNo();
        vehicle = Vehicle.getVehicleWithRegNo(regNo);

        frameAddHireRequest.getLblVehicleRegNumber().setText("Vehicle Reg No. " + regNo);
        frameAddHireRequest.getLblVehicleName().setText("Vehicle Make: " + vehicle.getMake());
    }

    private void setEventListeners(){
        frameAddHireRequest.getBtnAddRequest().addActionListener(this);
    }

    public void addWindowListener(WindowListener windowListener){
        this.windowListener = windowListener;
        frameAddHireRequest.addWindowListener(windowListener);
    }


    private void addToDB() throws FileNotFoundException {

        // No need for validation as additional information is optional
        String additionalInfo = frameAddHireRequest.getTxtAdditionalInfo().getText();
        String username = Utility.username;

        int status = HiredVehicle.STATUS_PENDING;

        Date datetime = new Date();

        // Important to pass hiredVehicleModel with vehicle registration number
        int registrationNumber = hiredVehicleModel.getVehicleRegNo();

        hiredVehicleModel = new HiredVehicle(registrationNumber, username, additionalInfo, datetime, status);
        hiredVehicleModel.create();

        // Update the value of isHired property of vehicle as well
        vehicle.delete();
        vehicle.setHired(true);
        if(vehicle.getType() == Vehicle.TYPE_CAR){
            ((Car) vehicle).create();
        }else if(vehicle.getType() == Vehicle.TYPE_MINI_BUS){
            ((MiniBus) vehicle).create();
        }else if(vehicle.getType() == Vehicle.TYPE_LORRY){
            ((Lorry) vehicle).create();
        }

        Messages.showMessage("Your request has been sent. " +
                "Please check the approval status on 'My Rents' section.", frameAddHireRequest);

        frameAddHireRequest.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == frameAddHireRequest.getBtnAddRequest()){
            try {
                addToDB();
            } catch (FileNotFoundException ex) {
                Messages.showErrorMessage("Couldn't send request at the moment! Please try again later",
                        frameAddHireRequest);
            }
        }
    }
}

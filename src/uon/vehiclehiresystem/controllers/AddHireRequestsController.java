package uon.vehiclehiresystem.controllers;

import uon.vehiclehiresystem.models.*;
import uon.vehiclehiresystem.utility.Utility;
import uon.vehiclehiresystem.utility.Validator;
import uon.vehiclehiresystem.views.FrameAddHireRequest;
import uon.vehiclehiresystem.views.miscellaneous.Messages;

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

    // Set data into the form
    private void loadDataIntoGUI(){
        int regNo = hiredVehicleModel.getVehicleRegNo();
        vehicle = Vehicle.getVehicleWithRegNo(regNo);

        frameAddHireRequest.getLblVehicleRegNumber().setText("Vehicle Reg No. \t\t" + regNo);
        frameAddHireRequest.getLblVehicleName().setText("Vehicle Make: \t\t" + vehicle.getMake());

        // only ask for loading capacity for Lorry and seating capacity for Minibus
        if(vehicle.getType() == Vehicle.TYPE_MINI_BUS){
            frameAddHireRequest.removeLoadingCapacity();
        }else if(vehicle.getType() == Vehicle.TYPE_LORRY){
            frameAddHireRequest.removeSeatingCapacity();
        }else if(vehicle.getType() == Vehicle.TYPE_CAR){
            frameAddHireRequest.removeLoadingCapacity();
            frameAddHireRequest.removeSeatingCapacity();
        }

    }

    private void setEventListeners(){
        frameAddHireRequest.getBtnAddRequest().addActionListener(this);
    }

    public void addWindowListener(WindowListener windowListener){
        this.windowListener = windowListener;
        frameAddHireRequest.addWindowListener(windowListener);
    }


    private void addToDB() throws FileNotFoundException {

        if(!validate()) return;

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

        windowListener.windowClosing(null);
        frameAddHireRequest.dispose();
    }

    private boolean validate(){
        boolean isUnfinished = false;

        // Check 'Loading Capacity' for lorry type and 'Seating capacity' for minibus type
        if(vehicle.getType() == Vehicle.TYPE_LORRY){
            if(!Validator.validateIsNumber(frameAddHireRequest.getTxtLoadingCapacity())){
                isUnfinished = true;
                Messages.showErrorMessage(frameAddHireRequest.getLblLoadingCapacity().getText() +
                        " must be a number", frameAddHireRequest);
            }else{
                // Validate load capacity is under supported number
                int loads = Integer.parseInt(frameAddHireRequest.getTxtLoadingCapacity().getText());
                int supportedLoads = ((Lorry) vehicle).getLoadingCapacity();

                if(loads > supportedLoads){
                    isUnfinished = true;
                    Messages.showErrorMessage(frameAddHireRequest.getLblLoadingCapacity().getText() +
                            " for the selected vehicle can only be upto " + supportedLoads, frameAddHireRequest);
                }
            }

        }else if(vehicle.getType() == Vehicle.TYPE_MINI_BUS){
            if(!Validator.validateIsNumber(frameAddHireRequest.getTxtSeatingCapacity())){
                isUnfinished = true;
                Messages.showErrorMessage(frameAddHireRequest.getLblSeatingCapacity().getText() +
                        " must be a number", frameAddHireRequest);
            }else{
                // Validate if required seats are below the total number of seats
                int seats = Integer.parseInt(frameAddHireRequest.getTxtSeatingCapacity().getText());
                int supportedSeats = ((MiniBus) vehicle).getSeatingCapacity();

                if(seats > supportedSeats){
                    isUnfinished = true;
                    Messages.showErrorMessage(frameAddHireRequest.getLblLoadingCapacity().getText() +
                            " for the selected vehicle can only be upto " + supportedSeats, frameAddHireRequest);
                }
            }
        }




        return !isUnfinished;
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

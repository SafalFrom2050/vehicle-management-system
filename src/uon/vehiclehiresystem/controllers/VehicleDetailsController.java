package uon.vehiclehiresystem.controllers;

import uon.vehiclehiresystem.models.*;
import uon.vehiclehiresystem.views.FrameVehicleDetail;
import uon.vehiclehiresystem.views.PanelDescriptionBox;
import uon.vehiclehiresystem.views.miscellaneous.Messages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class VehicleDetailsController implements ActionListener {

    private FrameVehicleDetail frameVehicleDetail;
    private Vehicle vehicleModel;

    public VehicleDetailsController(FrameVehicleDetail frameVehicleDetail, Vehicle vehicleModel){
        this.frameVehicleDetail = frameVehicleDetail;
        this.vehicleModel = vehicleModel;

        loadDataIntoGUI();
        setEventListeners();
    }

    private void loadDataIntoGUI(){
        if(vehicleModel.getType() == Vehicle.TYPE_CAR){
            frameVehicleDetail.getPanelDescriptionBox().setCarModelData((Car) vehicleModel);
        }else if(vehicleModel.getType() == Vehicle.TYPE_MINI_BUS){
            frameVehicleDetail.getPanelDescriptionBox().setMiniBusModelData((MiniBus) vehicleModel);
        }else if(vehicleModel.getType() == Vehicle.TYPE_LORRY){
            frameVehicleDetail.getPanelDescriptionBox().setLorryModelData((Lorry) vehicleModel);
        }

    }


    private void setEventListeners(){
        frameVehicleDetail.getPanelDescriptionBox().getBtnEdit().addActionListener(this);
    }

    private void editVehicle() throws FileNotFoundException {
        PanelDescriptionBox descBox = frameVehicleDetail.getPanelDescriptionBox();

        int registrationNumber = Integer.parseInt(descBox.getTxtRegNumber().getText());
        int topSpeed = Integer.parseInt(descBox.getTxtTopSpeed().getText());
        int dailyHireRate = Integer.parseInt(descBox.getTxtDailyHireRate().getText());
        String make = descBox.getTxtMake().getText();
        String model = descBox.getTxtModel().getText();

        if(descBox.getType() == Vehicle.TYPE_CAR){
            String fuelType = descBox.getTxtSpecial1().getText();
            int numberOfDoors = Integer.parseInt(descBox.getTxtSpecial2().getText());

            Car vehicle = new Car(registrationNumber, topSpeed, dailyHireRate, make, model, false, fuelType, numberOfDoors);
            vehicle.setType(Vehicle.TYPE_CAR);

            // Edit Operation requires deletion and then recreation
            vehicle.delete();
            vehicle.create();
            //

            frameVehicleDetail.getPanelDescriptionBox().setCarModelData(vehicle);

        }else if(descBox.getType() == Vehicle.TYPE_MINI_BUS){
            int seatingCapacity = Integer.parseInt(descBox.getTxtSpecial1().getText());

            MiniBus vehicle = new MiniBus(registrationNumber, topSpeed, dailyHireRate, make, model, false, seatingCapacity);
            vehicle.setType(Vehicle.TYPE_MINI_BUS);

            // Edit Operation requires deletion and then recreation
            vehicle.delete();
            vehicle.create();
            //

            frameVehicleDetail.getPanelDescriptionBox().setMiniBusModelData(vehicle);

        }else if(descBox.getType() == Vehicle.TYPE_LORRY){
            int loadingCapacity = Integer.parseInt(descBox.getTxtSpecial1().getText());

            Lorry vehicle = new Lorry(registrationNumber, topSpeed, dailyHireRate, make, model, false, loadingCapacity);
            vehicle.setType(Vehicle.TYPE_LORRY);

            // Edit Operation requires deletion and then recreation
            vehicle.delete();
            vehicle.create();
            //

            frameVehicleDetail.getPanelDescriptionBox().setLorryModelData(vehicle);
        }else{
            return;
        }

        Messages.showMessage("Vehicle Details Changed!", frameVehicleDetail);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == frameVehicleDetail.getPanelDescriptionBox().getBtnEdit()){
            try {
                editVehicle();
            } catch (FileNotFoundException ex) {
                Messages.showErrorMessage("Vehicle detail cannot be edited due to unknown reason",
                        frameVehicleDetail);
            }
        }
    }
}

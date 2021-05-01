package uon.vehiclehiresystem.controllers;

import uon.vehiclehiresystem.models.ListModel;
import uon.vehiclehiresystem.views.FrameUserDetail;
import uon.vehiclehiresystem.models.*;
import uon.vehiclehiresystem.views.PanelDescriptionBox;
import uon.vehiclehiresystem.views.miscellaneous.Messages;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class UserDetailsController implements ListSelectionListener, ActionListener {

    private FrameUserDetail frameUserDetail;
    private User userModel;
    private HiredVehicle selectedHiredVehicle;

    public UserDetailsController(FrameUserDetail frameUserDetail, User userModel){
        this.frameUserDetail = frameUserDetail;
        this.userModel = userModel;

        loadDataIntoGUI();
        setEventListeners();
    }

    private void loadDataIntoGUI(){
        if(userModel.getUserType() == User.TYPE_CUSTOMER){

            frameUserDetail.getPanelDescriptionBox().setCustomerModelData((Customer) userModel);
            frameUserDetail.getPanelListView().setListModel(getHiredVehiclesList());
        }

        if(userModel.getUserType() == User.TYPE_STAFF)
            frameUserDetail.getPanelDescriptionBox().setStaffModelData((Staff) userModel);
    }

    private ListModel<HiredVehicle> getHiredVehiclesList(){
        List<HiredVehicle> hiredVehicles = new ArrayList<>();
        for (HiredVehicle hv:HiredVehicle.getHiredVehiclesList()) {
            // Only add hired vehicles with current username
            if(hv.getUsername().equalsIgnoreCase(userModel.getUsername()))
                hiredVehicles.add(hv);
        }

        return new ListModel<>(hiredVehicles);
    }

    private void setEventListeners(){
        frameUserDetail.getPanelListView().getBtnApprove().addActionListener(this);
        frameUserDetail.getPanelListView().getBtnDisapprove().addActionListener(this);
        frameUserDetail.getPanelListView().getBtnSetExpired().addActionListener(this);
        frameUserDetail.getPanelListView().getListView().addListSelectionListener(this);

        frameUserDetail.getPanelDescriptionBox().getBtnEdit().addActionListener(this);
    }

    private void editUser() throws FileNotFoundException{
        PanelDescriptionBox descBox = frameUserDetail.getPanelDescriptionBox();

        String username = descBox.getTxtUsername().getText();
        String name = descBox.getTxtName().getText();
        String password = descBox.getTxtPassword().getText();

        if(descBox.getType() == User.TYPE_STAFF){
            int staffID = Integer.parseInt(descBox.getTxtStaffID().getText());

            Staff staff = new Staff(staffID, name, username, password);

            // Edit operation requires deletion and recreation
            staff.delete();
            staff.create();
        }else if(descBox.getType() == User.TYPE_CUSTOMER){
            int customerID = Integer.parseInt(descBox.getTxtCustomerID().getText());
            String phoneNumber = descBox.getTxtPhoneNumber().getText();
            String email = descBox.getTxtEmail().getText();
            String address = descBox.getTxtAddress().getText();

            Customer customer = new Customer(customerID, name, username, password, phoneNumber, address, email);

            // Edit operation requires deletion and recreation
            customer.delete();
            customer.create();
        }else{
            return;
        }

        Messages.showMessage("User Details Changed!", frameUserDetail);
    }

    private void setHireRequestStatus(int status) {
        if(selectedHiredVehicle == null){
            Messages.showMessage("Please select one from the list!", frameUserDetail);
            return;
        }
        selectedHiredVehicle.setStatus(status);

        // Edit requires deletion and creation
        selectedHiredVehicle.delete();
        try {
            selectedHiredVehicle.create();
        } catch (FileNotFoundException e) {
            Messages.showErrorMessage("Couldn't change status!", frameUserDetail);
            return;
        }

        // Free up the vehicle for rehire if expired!
        if(status == HiredVehicle.STATUS_EXPIRED || status == HiredVehicle.STATUS_DISAPPROVED){
            setVehicleIsHired(selectedHiredVehicle, false);
        }else{
            setVehicleIsHired(selectedHiredVehicle, true);
        }


        // Refresh the list
        frameUserDetail.getPanelListView().setListModel(getHiredVehiclesList());
    }

    private void setVehicleIsHired(HiredVehicle hiredVehicle, boolean value){

        Vehicle vehicle = Vehicle.getVehicleWithRegNo(hiredVehicle.getVehicleRegNo());
        if (vehicle != null) {
            vehicle.delete();
            vehicle.setHired(value);
        }

        try {
            if (vehicle.getType() == Vehicle.TYPE_CAR) ((Car) vehicle).create();
            if (vehicle.getType() == Vehicle.TYPE_LORRY) ((Lorry) vehicle).create();
            if (vehicle.getType() == Vehicle.TYPE_MINI_BUS) ((MiniBus) vehicle).create();
        }catch (FileNotFoundException e){
            Messages.showErrorMessage("Couldn't change vehicle status!", frameUserDetail);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == frameUserDetail.getPanelListView().getBtnApprove()){
            setHireRequestStatus(HiredVehicle.STATUS_APPROVED);
        }else if(source == frameUserDetail.getPanelListView().getBtnDisapprove()){
            setHireRequestStatus(HiredVehicle.STATUS_DISAPPROVED);
        }else if(source == frameUserDetail.getPanelListView().getBtnSetExpired()){
            setHireRequestStatus(HiredVehicle.STATUS_EXPIRED);
        }else if(source == frameUserDetail.getPanelDescriptionBox().getBtnEdit()){
            try {
                editUser();
            } catch (FileNotFoundException ex) {
                Messages.showErrorMessage("User details cannot be edited!", frameUserDetail);
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()){
            Object obj = frameUserDetail.getPanelListView().getListView().getSelectedValue();

            // Only enable list item action buttons when a non-null item is selected
            if(obj == null){
                frameUserDetail.getPanelListView().setActionBtnEnabled(false);
            }else{
                frameUserDetail.getPanelListView().setActionBtnEnabled(true);
            }

            selectedHiredVehicle = (HiredVehicle) obj;
        }
    }
}

package uon.vehiclehiresystem.controllers;

import uon.vehiclehiresystem.models.ListModel;
import uon.vehiclehiresystem.views.FrameUserDetail;
import uon.vehiclehiresystem.models.*;
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
        vehicle.delete();
        vehicle.setHired(value);
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

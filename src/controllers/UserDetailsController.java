package controllers;

import models.*;
import views.FrameUserDetail;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsController {

    private FrameUserDetail frameUserDetail;
    private User userModel;

    public UserDetailsController(FrameUserDetail frameUserDetail, User userModel){
        this.frameUserDetail = frameUserDetail;
        this.userModel = userModel;

        loadDataIntoGUI();
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

    }
}

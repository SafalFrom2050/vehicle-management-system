package uon.vehiclehiresystem.controllers;

import uon.vehiclehiresystem.models.ListModel;
import uon.vehiclehiresystem.utility.Utility;
import uon.vehiclehiresystem.views.FrameAddHireRequest;
import uon.vehiclehiresystem.views.FrameCustomerPortal;
import uon.vehiclehiresystem.views.FrameLogin;
import uon.vehiclehiresystem.views.PanelDescriptionBox;
import uon.vehiclehiresystem.views.miscellaneous.Messages;
import uon.vehiclehiresystem.models.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CustomerPortalController implements ActionListener, ListSelectionListener {

    private final static String BUTTON_ACTION_VEHICLES = "VEHICLES",
            BUTTON_ACTION_RENTS = "RENTS",
            BUTTON_ACTION_ACCOUNT = "ACCOUNT";

    private FrameCustomerPortal frameCustomerPortal;
    private Customer customer;

    private int type;
    private Vehicle selectedVehicle;
    private HiredVehicle selectedHiredVehicle;

    public CustomerPortalController(FrameCustomerPortal frameCustomerPortal, Customer customer){
        this.frameCustomerPortal = frameCustomerPortal;
        this.customer = customer;
    }

    public void loadFrame(){
        frameCustomerPortal.setTitle("Vehicle Hiring System");
        frameCustomerPortal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameCustomerPortal.setLocationRelativeTo(null);
        frameCustomerPortal.setVisible(true);

        loadDefaultList();

        setEventListeners();
    }

    private void loadDefaultList(){
        loadVehicles();
        setSelectedMainMenuItem(BUTTON_ACTION_VEHICLES);
    }

    private void setSelectedMainMenuItem(String buttonAction){
        // Reset Colors
        frameCustomerPortal.getBtnMenuVehicles().setBackground(Utility.button_default_color);
        frameCustomerPortal.getBtnMenuMyRents().setBackground(Utility.button_default_color);
        frameCustomerPortal.getBtnMenuMyAccount().setBackground(Utility.button_default_color);

        if(buttonAction == BUTTON_ACTION_VEHICLES){
            frameCustomerPortal.getBtnMenuVehicles().setBackground(Utility.BUTTON_SELECTED_COLOR);
            type = Vehicle.TYPE_VEHICLE;
        }else if(buttonAction == BUTTON_ACTION_RENTS){
            frameCustomerPortal.getBtnMenuMyRents().setBackground(Utility.BUTTON_SELECTED_COLOR);
            type = HiredVehicle.TYPE_HIRED_VEHICLE;
        }else if(buttonAction == BUTTON_ACTION_ACCOUNT){
            frameCustomerPortal.getBtnMenuMyAccount().setBackground(Utility.BUTTON_SELECTED_COLOR);
            type = User.TYPE_USER;
        }

        // Hide description box
        frameCustomerPortal.getPanelDescriptionBox().setHidden();
    }

    private void loadVehicles(){
        Vehicle vehicle = new Vehicle();
        List<Vehicle> vehicles = new ArrayList<>();

        for (Vehicle v:vehicle.getVehiclesList()) {
            // Only show vehicles which aren't already hired out
            if(!v.isHired()) vehicles.add(v);
        }

        uon.vehiclehiresystem.models.ListModel<Vehicle> vehicleListModel = new uon.vehiclehiresystem.models.ListModel<Vehicle>(vehicles);

        frameCustomerPortal.getPanelListView().setVisible(true);
        frameCustomerPortal.getPanelListView().setListModel(vehicleListModel);
        frameCustomerPortal.getPanelListView().setListType(Vehicle.TYPE_VEHICLE);
    }

    private void loadHiredVehicles(){
        HiredVehicle hiredVehicle = new HiredVehicle();

        List<HiredVehicle> hiredVehicleList = new ArrayList<>();

        for (HiredVehicle hv:hiredVehicle.getHiredVehiclesList()) {
            if(hv.getUsername().equalsIgnoreCase(Utility.username)){
                hiredVehicleList.add(hv);
            }
        }
        uon.vehiclehiresystem.models.ListModel<HiredVehicle> hiredVehicleListModel = new ListModel<>(hiredVehicleList);

        frameCustomerPortal.getPanelListView().setVisible(true);
        frameCustomerPortal.getPanelListView().setListModel(hiredVehicleListModel);
        frameCustomerPortal.getPanelListView().setListType(HiredVehicle.TYPE_HIRED_VEHICLE);
    }

    private void setEventListeners(){
        frameCustomerPortal.getMenuLogout().addActionListener(this);

        frameCustomerPortal.getBtnMenuVehicles().addActionListener(this);
        frameCustomerPortal.getBtnMenuMyRents().addActionListener(this);
        frameCustomerPortal.getBtnMenuMyAccount().addActionListener(this);

        frameCustomerPortal.getPanelListView().getListView().addListSelectionListener(this);

        frameCustomerPortal.getPanelDescriptionBox().getBtnHireVehicle().addActionListener(this);

        frameCustomerPortal.getPanelDescriptionBox().getBtnEdit().addActionListener(this);
    }

    private void setVehicleDescription(Object obj){
        Vehicle vehicle = (Vehicle) obj;
        if(vehicle == null) return;

        selectedVehicle = vehicle;

        if(vehicle.getType() == Vehicle.TYPE_CAR) frameCustomerPortal.getPanelDescriptionBox().setCarModelData((Car) obj);
        if(vehicle.getType() == Vehicle.TYPE_MINI_BUS) frameCustomerPortal.getPanelDescriptionBox().setMiniBusModelData((MiniBus) obj);
        if(vehicle.getType() == Vehicle.TYPE_LORRY) frameCustomerPortal.getPanelDescriptionBox().setLorryModelData((Lorry) obj);
    }

    private void setUserDescription(){
        Customer user = Customer.getUserWithUsername(Utility.username);
        frameCustomerPortal.getPanelListView().setVisible(false);

        if(user.getUserType() == User.TYPE_CUSTOMER) frameCustomerPortal.getPanelDescriptionBox().setCustomerModelData(user);
    }

    private void setHiredVehicleDescription(Object obj){
        HiredVehicle hiredVehicle = (HiredVehicle) obj;

        selectedHiredVehicle = hiredVehicle;

        frameCustomerPortal.getPanelDescriptionBox().setHireVehicleModelData(selectedHiredVehicle);
    }

    private void openAddToHireRequestsForm(){
        HiredVehicle hiredVehicle = new HiredVehicle();

        // Important to pass model with registration number
        hiredVehicle.setVehicleRegNo(selectedVehicle.getRegistrationNumber());
        System.out.println(selectedVehicle.getMake());

        AddHireRequestsController hrc = new AddHireRequestsController(new FrameAddHireRequest(), hiredVehicle);

        // Load the list again when the form closes
        hrc.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(e != null) e.getWindow().dispose();
                loadVehicles();
            }
        });
    }

    private void editUser() throws FileNotFoundException {
        PanelDescriptionBox descBox = frameCustomerPortal.getPanelDescriptionBox();

        String username = descBox.getTxtUsername().getText();
        String name = descBox.getTxtName().getText();
        String password = descBox.getTxtPassword().getText();


        // The user can only be ot customer type

        int customerID = Integer.parseInt(descBox.getTxtCustomerID().getText());
        String phoneNumber = descBox.getTxtPhoneNumber().getText();
        String email = descBox.getTxtEmail().getText();
        String address = descBox.getTxtAddress().getText();

        Customer customer = new Customer(customerID, name, username, password, phoneNumber, address, email);

        // Edit operation requires deletion and recreation
        customer.delete();
        customer.create();

        Messages.showMessage("Your profile has been successfully edited.", frameCustomerPortal);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == frameCustomerPortal.getBtnMenuVehicles()){
            setSelectedMainMenuItem(BUTTON_ACTION_VEHICLES);
            loadVehicles();

        }else if(source == frameCustomerPortal.getBtnMenuMyRents()){
            setSelectedMainMenuItem(BUTTON_ACTION_RENTS);
            loadHiredVehicles();

        }else if(source == frameCustomerPortal.getBtnMenuMyAccount()){
            setSelectedMainMenuItem(BUTTON_ACTION_ACCOUNT);

            // TODO: Add account details page
            setUserDescription();
        }else if(source == frameCustomerPortal.getPanelDescriptionBox().getBtnHireVehicle()){
            openAddToHireRequestsForm();
        }else if(source == frameCustomerPortal.getPanelDescriptionBox().getBtnEdit()){
            try {
                editUser();
            } catch (FileNotFoundException ex) {
                Messages.showErrorMessage("Sorry, user detail cannot be updated!", frameCustomerPortal);
            }
        }else if(source == frameCustomerPortal.getMenuLogout()){
            new LoginsController(new FrameLogin(), new User(Utility.username));
            frameCustomerPortal.dispose();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()){
            Object obj = frameCustomerPortal.getPanelListView().getListView().getSelectedValue();

            if(frameCustomerPortal.getPanelListView().getListType() == Vehicle.TYPE_VEHICLE){
                setVehicleDescription(obj);
            }else if(frameCustomerPortal.getPanelListView().getListType() == HiredVehicle.TYPE_HIRED_VEHICLE){
                setHiredVehicleDescription(obj);
            }else {
                // Ensure remove/edit buttons has no effect on previously selected items
                selectedVehicle = null;
                selectedHiredVehicle = null;
            }
        }
    }
}

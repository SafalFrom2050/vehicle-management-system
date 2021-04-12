package controllers;

import models.*;
import models.ListModel;
import utility.Utility;
import views.FrameAddVehicle;
import views.FrameStaffPortal;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StaffPortalController implements ActionListener, ListSelectionListener {

    private final static String BUTTON_ACTION_VEHICLES = "VEHICLES",
            BUTTON_ACTION_CUSTOMERS = "CUSTOMERS",
            BUTTON_ACTION_HIRE_REQUESTS = "HIRE REQUESTS";

    private FrameStaffPortal frameStaffPortal;
    private Staff staff;

    private JPanel panelParent;

    private Vehicle selectedVehicle;

    public StaffPortalController(FrameStaffPortal frameStaffPortal, Staff staff){
        this.staff = staff;

        this.frameStaffPortal = frameStaffPortal;
    }

    public void loadFrame(){
        panelParent = frameStaffPortal.createNewPanel();
        frameStaffPortal.add(panelParent);

        frameStaffPortal.setTitle("Vehicle Hiring System");
        frameStaffPortal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameStaffPortal.setLocationRelativeTo(null);
        frameStaffPortal.setVisible(true);

        setEventListeners();
    }
     private void setEventListeners(){
        frameStaffPortal.getBtnMenuVehicles().addActionListener(this);
        frameStaffPortal.getBtnMenuCustomers().addActionListener(this);
        frameStaffPortal.getBtnMenuHireRequests().addActionListener(this);
        frameStaffPortal.getPanelListView().getListView().addListSelectionListener(this);

        frameStaffPortal.getPanelListView().getBtnRemove().addActionListener(this);
         frameStaffPortal.getPanelListView().getBtnAdd().addActionListener(this);
     }

     public void setSelectedMainMenuItem(String buttonAction){
        // Reset Colors
        frameStaffPortal.getBtnMenuVehicles().setBackground(Utility.button_default_color);
        frameStaffPortal.getBtnMenuCustomers().setBackground(Utility.button_default_color);
        frameStaffPortal.getBtnMenuHireRequests().setBackground(Utility.button_default_color);

        if(buttonAction == BUTTON_ACTION_VEHICLES){
            frameStaffPortal.getBtnMenuVehicles().setBackground(Utility.BUTTON_SELECTED_COLOR);
        }else if(buttonAction == BUTTON_ACTION_CUSTOMERS){
            frameStaffPortal.getBtnMenuCustomers().setBackground(Utility.BUTTON_SELECTED_COLOR);
        }else if(buttonAction == BUTTON_ACTION_HIRE_REQUESTS){
            frameStaffPortal.getBtnMenuHireRequests().setBackground(Utility.BUTTON_SELECTED_COLOR);
        }
     }


     public void loadVehicles(){
        Vehicle vehicle = new Vehicle();

         // TODO: Remove
//        Car car = Car.getDummyList().get(0);
//        MiniBus miniBus = MiniBus.getDummyList().get(0);
//        Lorry lorry = Lorry.getDummyList().get(0);
//
//        try {
//            car.create();
//            miniBus.create();
//            lorry.create();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }


        ListModel<Vehicle> vehicleListModel = new ListModel<Vehicle>(vehicle.getVehiclesList());

        frameStaffPortal.getPanelListView().setListModel(vehicleListModel);
        frameStaffPortal.getPanelListView().setListRenderer(new ListRenderer<Vehicle>(Utility.HEADINGS_VEHICLES));
     }

     public void loadUsers(){
        Staff staff = new Staff(1,"test", "test");

        ListModel<Staff> staffListModel = new ListModel<Staff>(staff.getUsersList());

        frameStaffPortal.getPanelListView().setListModel(staffListModel);
        frameStaffPortal.getPanelListView().setListRenderer(new ListRenderer<Staff>(Utility.HEADINGS_USERS));
     }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == frameStaffPortal.getBtnMenuVehicles()){
            setSelectedMainMenuItem(BUTTON_ACTION_VEHICLES);
            loadVehicles();

        }else if(source == frameStaffPortal.getBtnMenuCustomers()){
            setSelectedMainMenuItem(BUTTON_ACTION_CUSTOMERS);
            loadUsers();

        }else if(source == frameStaffPortal.getBtnMenuHireRequests()){
            setSelectedMainMenuItem(BUTTON_ACTION_HIRE_REQUESTS);
        }else if(source == frameStaffPortal.getPanelListView().getBtnRemove()){
            selectedVehicle.delete();
        }
        else if(source == frameStaffPortal.getPanelListView().getBtnAdd()){
            new FrameAddVehicle();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()){
            Object obj = frameStaffPortal.getPanelListView().getListView().getSelectedValue();
            Vehicle vehicle = (Vehicle) obj;
            if(vehicle == null) return;

            selectedVehicle = vehicle;

            if(vehicle.getType() == Vehicle.TYPE_CAR) frameStaffPortal.getPanelDescriptionBox().setCarModelData((Car) obj);
            if(vehicle.getType() == Vehicle.TYPE_MINI_BUS) frameStaffPortal.getPanelDescriptionBox().setMiniBusModelData((MiniBus) obj);
            if(vehicle.getType() == Vehicle.TYPE_LORRY) frameStaffPortal.getPanelDescriptionBox().setLorryModelData((Lorry) obj);
        }

    }
}

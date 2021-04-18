package controllers;

import models.*;
import models.ListModel;
import utility.Utility;
import views.FrameAddUser;
import views.FrameAddVehicle;
import views.FrameStaffPortal;
import views.PanelDescriptionBox;
import views.miscellaneous.Messages;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class StaffPortalController implements ActionListener, ListSelectionListener {

    private final static String BUTTON_ACTION_VEHICLES = "VEHICLES",
            BUTTON_ACTION_CUSTOMERS = "CUSTOMERS",
            BUTTON_ACTION_HIRE_REQUESTS = "HIRE REQUESTS";

    private FrameStaffPortal frameStaffPortal;
    private Staff staff;

    private JPanel panelParent;

    private Vehicle selectedVehicle;
    private User selectedUser;

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

        loadDefaultList();

        setEventListeners();
    }

    private void loadDefaultList(){
        loadVehicles();
        setSelectedMainMenuItem(BUTTON_ACTION_VEHICLES);
    }

     private void setEventListeners(){
        frameStaffPortal.getBtnMenuVehicles().addActionListener(this);
        frameStaffPortal.getBtnMenuCustomers().addActionListener(this);
        frameStaffPortal.getBtnMenuHireRequests().addActionListener(this);
        frameStaffPortal.getPanelListView().getListView().addListSelectionListener(this);

        frameStaffPortal.getPanelListView().getBtnRemove().addActionListener(this);
        frameStaffPortal.getPanelListView().getBtnAddCar().addActionListener(this);
        frameStaffPortal.getPanelListView().getBtnAddMiniBus().addActionListener(this);
        frameStaffPortal.getPanelListView().getBtnAddLorry().addActionListener(this);

        frameStaffPortal.getPanelListView().getBtnAddStaff().addActionListener(this);
        frameStaffPortal.getPanelListView().getBtnAddCustomer().addActionListener(this);

        frameStaffPortal.getPanelDescriptionBox().getBtnEdit().addActionListener(this);
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

        ListModel<Vehicle> vehicleListModel = new ListModel<Vehicle>(vehicle.getVehiclesList());

        frameStaffPortal.getPanelListView().setListModel(vehicleListModel);
        frameStaffPortal.getPanelListView().setListType(Vehicle.TYPE_VEHICLE);

     }

     public void loadUsers(){
        Staff staff = new Staff();
        Customer customer = new Customer();

        List usersList = new ArrayList();
        usersList.addAll(staff.getUsersList());
        usersList.addAll(customer.getUsersList());

        ListModel<Staff> staffListModel = new ListModel<Staff>(usersList);

        frameStaffPortal.getPanelListView().setListModel(staffListModel);
        frameStaffPortal.getPanelListView().setListType(User.TYPE_STAFF);

     }

     public void openAddCarForm(){
         AddVehicleController addVehicleController = new AddVehicleController(new FrameAddVehicle(), Car.getDummyList().get(0));

         addVehicleController.addWindowListener(new WindowAdapter() {
             @Override
             public void windowClosing(WindowEvent e)
             {
                 System.out.println("Closed");

                 if(e != null) e.getWindow().dispose();
                 loadVehicles();
             }
         });
     }

    public void openAddMiniBusForm(){
        AddVehicleController addVehicleController = new AddVehicleController(new FrameAddVehicle(), MiniBus.getDummyList().get(0));

        addVehicleController.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.out.println("Closed");

                if(e != null) e.getWindow().dispose();
                loadVehicles();
            }
        });
    }

    public void openAddLorryForm(){
        AddVehicleController addVehicleController = new AddVehicleController(new FrameAddVehicle(),
                Lorry.getDummyList().get(0));

        addVehicleController.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(e != null) e.getWindow().dispose();
                loadVehicles();
            }
        });
    }

    private void openAddStaffForm(){
        AddUserController addUserController = new AddUserController(new FrameAddUser(User.TYPE_STAFF),
                Staff.getDummyList().get(0));

        addUserController.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(e != null) e.getWindow().dispose();
                loadUsers();
            }
        });
    }

    private void openAddCustomerForm(){
        AddUserController addUserController = new AddUserController(new FrameAddUser(User.TYPE_CUSTOMER),
                new Customer("dummy"));

        addUserController.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(e != null) e.getWindow().dispose();
                loadUsers();
            }
        });
    }


    public void editVehicle() throws FileNotFoundException {
        PanelDescriptionBox descBox = frameStaffPortal.getPanelDescriptionBox();

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

            frameStaffPortal.getPanelDescriptionBox().setCarModelData(vehicle);

        }else if(descBox.getType() == Vehicle.TYPE_MINI_BUS){
            int seatingCapacity = Integer.parseInt(descBox.getTxtSpecial1().getText());

            MiniBus vehicle = new MiniBus(registrationNumber, topSpeed, dailyHireRate, make, model, false, seatingCapacity);
            vehicle.setType(Vehicle.TYPE_MINI_BUS);

            // Edit Operation requires deletion and then recreation
            vehicle.delete();
            vehicle.create();
            //

            frameStaffPortal.getPanelDescriptionBox().setMiniBusModelData(vehicle);

        }else if(descBox.getType() == Vehicle.TYPE_LORRY){
            int loadingCapacity = Integer.parseInt(descBox.getTxtSpecial1().getText());

            Lorry vehicle = new Lorry(registrationNumber, topSpeed, dailyHireRate, make, model, false, loadingCapacity);
            vehicle.setType(Vehicle.TYPE_LORRY);

            // Edit Operation requires deletion and then recreation
            vehicle.delete();
            vehicle.create();
            //

            frameStaffPortal.getPanelDescriptionBox().setLorryModelData(vehicle);
        }else{
            return;
        }

        Messages.showMessage("Vehicle Details Changed!", frameStaffPortal);
    }

    private void setVehicleDescription(Object obj){
        Vehicle vehicle = (Vehicle) obj;
        if(vehicle == null) return;

        selectedVehicle = vehicle;

        if(vehicle.getType() == Vehicle.TYPE_CAR) frameStaffPortal.getPanelDescriptionBox().setCarModelData((Car) obj);
        if(vehicle.getType() == Vehicle.TYPE_MINI_BUS) frameStaffPortal.getPanelDescriptionBox().setMiniBusModelData((MiniBus) obj);
        if(vehicle.getType() == Vehicle.TYPE_LORRY) frameStaffPortal.getPanelDescriptionBox().setLorryModelData((Lorry) obj);
    }

    private void setUserDescription(Object obj){
        User user = (User) obj;
        if(user == null) return;

        selectedUser = user;

        if(user.getUserType() == User.TYPE_STAFF) frameStaffPortal.getPanelDescriptionBox().setStaffModelData((Staff) obj);
        if(user.getUserType() == User.TYPE_CUSTOMER) frameStaffPortal.getPanelDescriptionBox().setCustomerModelData((Customer) obj);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        // Hide description box on any action
        frameStaffPortal.getPanelDescriptionBox().setHidden();

        if(source == frameStaffPortal.getBtnMenuVehicles()){
            setSelectedMainMenuItem(BUTTON_ACTION_VEHICLES);
            loadVehicles();

        }else if(source == frameStaffPortal.getBtnMenuCustomers()){
            setSelectedMainMenuItem(BUTTON_ACTION_CUSTOMERS);
            loadUsers();

        }else if(source == frameStaffPortal.getBtnMenuHireRequests()){
            setSelectedMainMenuItem(BUTTON_ACTION_HIRE_REQUESTS);
            loadVehicles();
        }else if(source == frameStaffPortal.getPanelListView().getBtnRemove()){
            selectedVehicle.delete();
            loadVehicles();

        }
        else if(source == frameStaffPortal.getPanelListView().getBtnAddCar()){
            openAddCarForm();
        }else if(source == frameStaffPortal.getPanelListView().getBtnAddMiniBus()){
            openAddMiniBusForm();
        }else if(source == frameStaffPortal.getPanelListView().getBtnAddLorry()){
            openAddLorryForm();
        }else if(source == frameStaffPortal.getPanelListView().getBtnAddStaff()){
            openAddStaffForm();
        }else if(source == frameStaffPortal.getPanelListView().getBtnAddCustomer()){
            openAddCustomerForm();
        }else if(source == frameStaffPortal.getPanelDescriptionBox().getBtnEdit()){
            try {
                editVehicle();
                loadVehicles();
            } catch (FileNotFoundException ex) {
                Messages.showErrorMessage("Could not edit changes", frameStaffPortal);
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()){
            Object obj = frameStaffPortal.getPanelListView().getListView().getSelectedValue();

            if(frameStaffPortal.getPanelListView().getListType() == User.TYPE_USER){
                setUserDescription(obj);
            }else if(frameStaffPortal.getPanelListView().getListType() == Vehicle.TYPE_VEHICLE){
                setVehicleDescription(obj);
            }
        }
    }
}

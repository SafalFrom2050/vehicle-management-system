package controllers;

import models.*;
import models.ListModel;
import utility.Utility;
import views.*;
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
            BUTTON_ACTION_USERS = "USERS",
            BUTTON_ACTION_HIRE_REQUESTS = "HIRE REQUESTS";

    private FrameStaffPortal frameStaffPortal;
    private Staff staff;

    private int type;
    private Vehicle selectedVehicle;
    private User selectedUser;
    private HiredVehicle selectedHiredVehicle;

    public StaffPortalController(FrameStaffPortal frameStaffPortal, Staff staff){
        this.staff = staff;

        this.frameStaffPortal = frameStaffPortal;
    }

    public void loadFrame(){
        frameStaffPortal.setTitle("Vehicle Hiring System | Staff Portal");
        frameStaffPortal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        frameStaffPortal.getMenuLogout().addActionListener(this);
        frameStaffPortal.getMenuQueryUser().addActionListener(this);
        frameStaffPortal.getMenuQueryVehicle().addActionListener(this);

        frameStaffPortal.getBtnMenuVehicles().addActionListener(this);
        frameStaffPortal.getBtnMenuUsers().addActionListener(this);
        frameStaffPortal.getBtnMenuHireRequests().addActionListener(this);
        frameStaffPortal.getPanelListView().getListView().addListSelectionListener(this);

        frameStaffPortal.getPanelListView().getBtnRemove().addActionListener(this);
        frameStaffPortal.getPanelListView().getBtnAddCar().addActionListener(this);
        frameStaffPortal.getPanelListView().getBtnAddMiniBus().addActionListener(this);
        frameStaffPortal.getPanelListView().getBtnAddLorry().addActionListener(this);

        frameStaffPortal.getPanelListView().getBtnAddStaff().addActionListener(this);
        frameStaffPortal.getPanelListView().getBtnAddCustomer().addActionListener(this);

        frameStaffPortal.getPanelListView().getBtnApprove().addActionListener(this);
        frameStaffPortal.getPanelListView().getBtnDisapprove().addActionListener(this);
        frameStaffPortal.getPanelListView().getBtnSetExpired().addActionListener(this);

        frameStaffPortal.getPanelDescriptionBox().getBtnEdit().addActionListener(this);
     }

     private void setSelectedMainMenuItem(String buttonAction){
        // Reset Colors
        frameStaffPortal.getBtnMenuVehicles().setBackground(Utility.button_default_color);
        frameStaffPortal.getBtnMenuUsers().setBackground(Utility.button_default_color);
        frameStaffPortal.getBtnMenuHireRequests().setBackground(Utility.button_default_color);

        if(buttonAction == BUTTON_ACTION_VEHICLES){
            frameStaffPortal.getBtnMenuVehicles().setBackground(Utility.BUTTON_SELECTED_COLOR);
            type = Vehicle.TYPE_VEHICLE;
        }else if(buttonAction == BUTTON_ACTION_USERS){
            frameStaffPortal.getBtnMenuUsers().setBackground(Utility.BUTTON_SELECTED_COLOR);
            type = User.TYPE_USER;
        }else if(buttonAction == BUTTON_ACTION_HIRE_REQUESTS){
            frameStaffPortal.getBtnMenuHireRequests().setBackground(Utility.BUTTON_SELECTED_COLOR);
            type = HiredVehicle.TYPE_HIRED_VEHICLE;
        }

         // Hide description box
         frameStaffPortal.getPanelDescriptionBox().setHidden();
     }


     private void loadVehicles(){
        Vehicle vehicle = new Vehicle();

        ListModel<Vehicle> vehicleListModel = new ListModel<Vehicle>(vehicle.getVehiclesList());

        frameStaffPortal.getPanelListView().setListModel(vehicleListModel);
        frameStaffPortal.getPanelListView().setListType(Vehicle.TYPE_VEHICLE);
     }

     private void loadHiredVehicles(){
        HiredVehicle hiredVehicle = new HiredVehicle();

        ListModel<HiredVehicle>  hiredVehicleListModel = new ListModel<>(hiredVehicle.getHiredVehiclesList());

        frameStaffPortal.getPanelListView().setListModel(hiredVehicleListModel);
        frameStaffPortal.getPanelListView().setListType(HiredVehicle.TYPE_HIRED_VEHICLE);
     }

     private void loadUsers(){
        Staff staff = new Staff();
        Customer customer = new Customer();

        List usersList = new ArrayList();
        usersList.addAll(staff.getUsersList());
        usersList.addAll(customer.getUsersList());

        ListModel<Staff> staffListModel = new ListModel<Staff>(usersList);

        frameStaffPortal.getPanelListView().setListModel(staffListModel);
        frameStaffPortal.getPanelListView().setListType(User.TYPE_STAFF);
     }

     private void openAddCarForm(){
         AddVehiclesController addVehiclesController = new AddVehiclesController(new FrameAddVehicle(), Car.getDummyList().get(0));

         addVehiclesController.addWindowListener(new WindowAdapter() {
             @Override
             public void windowClosing(WindowEvent e)
             {
                 System.out.println("Closed");

                 if(e != null) e.getWindow().dispose();
                 loadVehicles();
             }
         });
     }

    private void openAddMiniBusForm(){
        AddVehiclesController addVehiclesController = new AddVehiclesController(new FrameAddVehicle(), MiniBus.getDummyList().get(0));

        addVehiclesController.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.out.println("Closed");

                if(e != null) e.getWindow().dispose();
                loadVehicles();
            }
        });
    }

    private void openAddLorryForm(){
        AddVehiclesController addVehiclesController = new AddVehiclesController(new FrameAddVehicle(),
                Lorry.getDummyList().get(0));

        addVehiclesController.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(e != null) e.getWindow().dispose();
                loadVehicles();
            }
        });
    }

    private void openAddStaffForm(){
        AddUsersController addUsersController = new AddUsersController(new FrameAddUser(User.TYPE_STAFF),
                Staff.getDummyList().get(0));

        addUsersController.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(e != null) e.getWindow().dispose();
                loadUsers();
            }
        });
    }

    private void openAddCustomerForm(){
        AddUsersController addUsersController = new AddUsersController(new FrameAddUser(User.TYPE_CUSTOMER),
                new Customer("dummy"));

        addUsersController.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                loadUsers();
                if(e != null) e.getWindow().dispose();
            }
        });
    }


    private void editVehicle() throws FileNotFoundException {
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

    private void editUser() throws FileNotFoundException{
        PanelDescriptionBox descBox = frameStaffPortal.getPanelDescriptionBox();

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

        Messages.showMessage("User Details Changed!", frameStaffPortal);
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

    private void setHiredVehicleDescription(Object obj){

        selectedHiredVehicle = (HiredVehicle) obj;

        frameStaffPortal.getPanelDescriptionBox().setHireVehicleModelData(selectedHiredVehicle);
    }

    private void setHireRequestStatus(int status) {
        if(selectedHiredVehicle == null){
            Messages.showMessage("Please select one from the list!", frameStaffPortal);
            return;
        }
        selectedHiredVehicle.setStatus(status);

        // Edit requires deletion and creation
        selectedHiredVehicle.delete();
        try {
            selectedHiredVehicle.create();
        } catch (FileNotFoundException e) {
            Messages.showErrorMessage("Couldn't change status!", frameStaffPortal);
        }


        // Free up the vehicle for rehire if expired!
        if(status == HiredVehicle.STATUS_EXPIRED || status == HiredVehicle.STATUS_DISAPPROVED){
            freeHiredVehicle(selectedHiredVehicle);
        }


        // Update description box as well
        frameStaffPortal.getPanelDescriptionBox().setHireVehicleModelData(selectedHiredVehicle);

        loadHiredVehicles();
    }

    private void freeHiredVehicle(HiredVehicle hiredVehicle){

        Vehicle vehicle = Vehicle.getVehicleWithRegNo(hiredVehicle.getVehicleRegNo());
        vehicle.delete();
        vehicle.setHired(false);
        try {
            if (vehicle.getType() == Vehicle.TYPE_CAR) ((Car) vehicle).create();
            if (vehicle.getType() == Vehicle.TYPE_LORRY) ((Lorry) vehicle).create();
            if (vehicle.getType() == Vehicle.TYPE_MINI_BUS) ((MiniBus) vehicle).create();
        }catch (FileNotFoundException e){
            Messages.showErrorMessage("Couldn't change vehicle status!", frameStaffPortal);
        }

    }

    private void remove(){

        if(selectedHiredVehicle == null && selectedUser == null && selectedVehicle == null){
            Messages.showMessage("Please select one from the list!", frameStaffPortal);
            return;
        }

        // Show confirmation message
        int confirmation = Messages.showConfirmationMessage("Confirm Delete",
                "Are you sure you want to delete?", frameStaffPortal);

        // value 0 is option 'Yes'
        if(confirmation > 0) return;

        if(type == Vehicle.TYPE_VEHICLE){
            selectedVehicle.delete();
            loadVehicles();
        }else if(type == User.TYPE_USER){
            selectedUser.delete();
            loadUsers();
        }else if(type == HiredVehicle.TYPE_HIRED_VEHICLE){
            selectedHiredVehicle.delete();
            freeHiredVehicle(selectedHiredVehicle);
            loadHiredVehicles();
        }
        frameStaffPortal.getPanelDescriptionBox().setHidden();
    }

    private void edit(){
        try {
            if (type == Vehicle.TYPE_VEHICLE) {
                editVehicle();
                loadVehicles();
            } else if (type == User.TYPE_USER) {
                editUser();
                loadUsers();
            }
        }catch (FileNotFoundException e){
            Messages.showErrorMessage("Operation couldn't be completed!", frameStaffPortal);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == frameStaffPortal.getBtnMenuVehicles()){
            setSelectedMainMenuItem(BUTTON_ACTION_VEHICLES);
            loadVehicles();

        }else if(source == frameStaffPortal.getBtnMenuUsers()){
            setSelectedMainMenuItem(BUTTON_ACTION_USERS);
            loadUsers();

        }else if(source == frameStaffPortal.getBtnMenuHireRequests()){
            setSelectedMainMenuItem(BUTTON_ACTION_HIRE_REQUESTS);
            loadHiredVehicles();
        }else if(source == frameStaffPortal.getPanelListView().getBtnRemove()){
            remove();
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
        }else if(source == frameStaffPortal.getPanelListView().getBtnApprove()){
            setHireRequestStatus(HiredVehicle.STATUS_APPROVED);
        }else if(source == frameStaffPortal.getPanelListView().getBtnDisapprove()){
            setHireRequestStatus(HiredVehicle.STATUS_DISAPPROVED);
        }else if(source == frameStaffPortal.getPanelListView().getBtnSetExpired()){
            setHireRequestStatus(HiredVehicle.STATUS_EXPIRED);
        }else if(source == frameStaffPortal.getPanelDescriptionBox().getBtnEdit()){
           edit();
        }else if(source == frameStaffPortal.getMenuLogout()){
            // Open login page
            new LoginsController(new FrameLogin(), new User(Utility.username));
            // Close Staff Panel
            frameStaffPortal.dispose();
        }else if(source == frameStaffPortal.getMenuQueryUser()){
            new UserDetailsController(new FrameUserDetail(), new Customer("safalfrom2050"));
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()){
            Object obj = frameStaffPortal.getPanelListView().getListView().getSelectedValue();

            // Only enable list item action buttons when a non-null item is selected
            if(obj == null){
                frameStaffPortal.getPanelListView().setActionBtnEnabled(false);
            }else{
                frameStaffPortal.getPanelListView().setActionBtnEnabled(true);
            }

            // Load the list item details according to its type
            if(frameStaffPortal.getPanelListView().getListType() == User.TYPE_USER){
                setUserDescription(obj);
            }else if(frameStaffPortal.getPanelListView().getListType() == Vehicle.TYPE_VEHICLE){
                setVehicleDescription(obj);
            }else if(frameStaffPortal.getPanelListView().getListType() == HiredVehicle.TYPE_HIRED_VEHICLE){
                setHiredVehicleDescription(obj);
            }else {
                // Ensure remove/edit buttons has no effect on previously selected items
                selectedVehicle = null;
                selectedUser = null;
                selectedHiredVehicle = null;
            }
        }
    }

    public static String getButtonActionVehicles() {
        return BUTTON_ACTION_VEHICLES;
    }

    public static String getButtonActionCustomers() {
        return BUTTON_ACTION_USERS;
    }

    public static String getButtonActionHireRequests() {
        return BUTTON_ACTION_HIRE_REQUESTS;
    }

    public FrameStaffPortal getFrameStaffPortal() {
        return frameStaffPortal;
    }

    public void setFrameStaffPortal(FrameStaffPortal frameStaffPortal) {
        this.frameStaffPortal = frameStaffPortal;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Vehicle getSelectedVehicle() {
        return selectedVehicle;
    }

    public void setSelectedVehicle(Vehicle selectedVehicle) {
        this.selectedVehicle = selectedVehicle;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }
}

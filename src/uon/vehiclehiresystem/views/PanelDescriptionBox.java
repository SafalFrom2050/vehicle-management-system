package uon.vehiclehiresystem.views;

import uon.vehiclehiresystem.views.miscellaneous.LabelDescriptionBox;
import uon.vehiclehiresystem.views.miscellaneous.PasswordFieldDescriptionBox;
import uon.vehiclehiresystem.views.miscellaneous.TextFieldDescriptionBox;
import uon.vehiclehiresystem.models.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class PanelDescriptionBox extends JPanel {

    private Font fontHeading, fontBig, fontMedium, fontSmall;

    private JLabel lblHeading = new JLabel();

    // Car Views
    private JLabel lblRegNumber = new LabelDescriptionBox("Reg No.: "),
            lblTopSpeed = new LabelDescriptionBox("Top Speed: "),
            lblDailyHireRate = new LabelDescriptionBox("Daily Hire Rate: "),
            lblMake = new LabelDescriptionBox("Make: "),
            lblModel = new LabelDescriptionBox("Model: "),
            lblIsHired = new LabelDescriptionBox("Is Hired: "),
            lblSpecial1 = new LabelDescriptionBox(),
            lblSpecial2 = new LabelDescriptionBox(),

    // User Views
            lblStaffID = new LabelDescriptionBox("Staff ID: "),
            lblCustomerID = new LabelDescriptionBox("Customer ID: "),
            lblUsername = new LabelDescriptionBox("Username: "),
            lblPassword = new LabelDescriptionBox("Password: "),
            lblName = new LabelDescriptionBox("Name: "),
            lblAddress = new LabelDescriptionBox("Address: "),
            lblPhoneNumber = new LabelDescriptionBox("Phone Number: "),
            lblEmail = new LabelDescriptionBox("Email: "),

    // Hire Vehicles View
            lblHireStatus = new LabelDescriptionBox("Status: "),
            lblHireAdditionalInfo = new LabelDescriptionBox("Additional Info: "),
            lblHireRequestDate = new LabelDescriptionBox("Request Date: ");


    // Car Views
    private JTextField txtRegNumber = new TextFieldDescriptionBox(),
            txtTopSpeed = new TextFieldDescriptionBox(),
            txtDailyHireRate = new TextFieldDescriptionBox(),
            txtMake = new TextFieldDescriptionBox(),
            txtModel = new TextFieldDescriptionBox(),
            txtIsHired = new TextFieldDescriptionBox(),
            txtSpecial1 = new TextFieldDescriptionBox(),
            txtSpecial2 = new TextFieldDescriptionBox(),

    // User Views
            txtStaffID = new TextFieldDescriptionBox(),
            txtCustomerID = new TextFieldDescriptionBox(),
            txtUsername = new TextFieldDescriptionBox(),
            txtPassword = new PasswordFieldDescriptionBox("Unchanged"),
            txtName = new TextFieldDescriptionBox(),
            txtAddress = new TextFieldDescriptionBox(),
            txtPhoneNumber = new TextFieldDescriptionBox(),
            txtEmail = new TextFieldDescriptionBox(),

    // Hire Vehicles View
            txtHireStatus = new TextFieldDescriptionBox(),
            txtHireAdditionalInfo = new TextFieldDescriptionBox(),
            txtHireRequestDate = new TextFieldDescriptionBox();


    private JButton btnEdit = new JButton("Save Edit"),
            btnHireVehicle = new JButton("Hire Vehicle");

    private int height, width;

    private Vehicle vehicle;
    private Car carModel;
    private MiniBus miniBusModel;
    private Lorry lorryModel;

    private Staff staffModel;
    private Customer customerModel;

    private HiredVehicle hiredVehicleModel;

    private int type = Vehicle.TYPE_VEHICLE;

    private int accessLevelType = User.TYPE_CUSTOMER;


    public PanelDescriptionBox(int height, int width, int accessLevelType){
        this.height = height;
        this.width = width;
        this.accessLevelType = accessLevelType;

        createDetailPanel();
        setHidden();
    }

    public PanelDescriptionBox(Car carModel, int height, int width, int accessLevelType){
        this.height = height;
        this.width = width;
        this.carModel = carModel;
        this.type = Vehicle.TYPE_CAR;
        this.accessLevelType = accessLevelType;

        createDetailPanel();
        setCarModelData(carModel);
    }

    public PanelDescriptionBox(MiniBus miniBusModel, int height, int width, int accessLevelType){
        this.height = height;
        this.width = width;
        this.miniBusModel = miniBusModel;
        this.type = Vehicle.TYPE_MINI_BUS;
        this.accessLevelType = accessLevelType;

        createDetailPanel();
        setMiniBusModelData(miniBusModel);
    }

    public PanelDescriptionBox(Lorry lorryModel, int height, int width, int accessLevelType){
        this.height = height;
        this.width = width;
        this.lorryModel = lorryModel;
        this.type = Vehicle.TYPE_LORRY;

        createDetailPanel();
        setLorryModelData(lorryModel);
    }

    public PanelDescriptionBox(Staff staffModel, int height, int width, int accessLevelType){
        this.height = height;
        this.width = width;
        this.staffModel = staffModel;
        this.type = User.TYPE_STAFF;
        this.accessLevelType = accessLevelType;

        createDetailPanel();
        setStaffModelData(staffModel);
    }

    public PanelDescriptionBox(Customer customerModel, int height, int width, int accessLevelType){
        this.height = height;
        this.width = width;
        this.customerModel = customerModel;
        this.type = User.TYPE_CUSTOMER;
        this.accessLevelType = accessLevelType;

        createDetailPanel();
        setCustomerModelData(customerModel);
    }

    public PanelDescriptionBox(HiredVehicle hiredVehicleModel, int height, int width, int accessLevelType){
        this.height = height;
        this.width = width;
        this.hiredVehicleModel = hiredVehicleModel;
        this.type = HiredVehicle.TYPE_HIRED_VEHICLE;
        this.accessLevelType = accessLevelType;

        createDetailPanel();
        setHireVehicleModelData(hiredVehicleModel);
    }

    private void createDetailPanel(){
        this.setLayout(new GridBagLayout());

        setPreferredSize(new Dimension(width, height));

        fontHeading = new Font(null, Font.BOLD, 18);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY),
                        "Description", TitledBorder.CENTER,
                        TitledBorder.DEFAULT_POSITION, fontHeading),
                BorderFactory.createEmptyBorder(1, 1, 1, 1)));

        setViews();
    }

    public void setStaffModelData(Staff staffModel){
        // Show Staff User Views Only
        setHidden();
        setStaffViewsVisibility(true);
        insertEditBtn();

        this.staffModel = staffModel;
        this.type = User.TYPE_STAFF;

        txtStaffID.setText(Integer.toString(staffModel.getStaffID()));
        txtUsername.setText(staffModel.getUsername());
        txtName.setText(staffModel.getName());

        // Password is set, but is hidden due to JPasswordType
        txtPassword.setText(staffModel.getPassword());
    }

    public void setCustomerModelData(Customer customerModel){
        // Show Customer User Views Only
        setHidden();
        setCustomerViewsVisibility(true);
        insertEditBtn();

        this.customerModel = customerModel;
        this.type = User.TYPE_CUSTOMER;

        txtCustomerID.setText(Integer.toString(customerModel.getIdentificationNumber()));
        txtUsername.setText(customerModel.getUsername());
        txtName.setText(customerModel.getName());
        txtPhoneNumber.setText(customerModel.getPhoneNumber());
        txtAddress.setText(customerModel.getAddress());
        txtEmail.setText(customerModel.getEmail());

        // Password is set, but is hidden due to JPasswordType
        txtPassword.setText(customerModel.getPassword());
    }

    private void setCommonVehicleData(){
        // Show Vehicle Views Only
        setHidden();
        setVehicleViewsVisibility(true);

        if(accessLevelType == User.TYPE_STAFF) insertEditBtn();
        if(accessLevelType == User.TYPE_CUSTOMER) insertHireVehicleBtn();

        txtMake.setText(vehicle.getMake());

        txtRegNumber.setText(Integer.toString(vehicle.getRegistrationNumber()));

        txtTopSpeed.setText(Integer.toString(vehicle.getTopSpeed()));

        txtModel.setText(vehicle.getModel());

        txtIsHired.setText(Boolean.toString(vehicle.isHired()));

        txtDailyHireRate.setText(Integer.toString(vehicle.getDailyHireRate()));
    }

    public void setCarModelData(Car carModel){
        this.carModel = carModel;
        this.vehicle = (Vehicle) carModel;
        this.type = Vehicle.TYPE_CAR;
        setCommonVehicleData();

        lblSpecial1.setText("Fuel Type: ");
        txtSpecial1.setText(carModel.getFuelType());

        lblSpecial2.setText("No. of Doors: " );
        txtSpecial2.setText(Integer.toString(carModel.getNumberOfDoors()));
    }

    public void setMiniBusModelData(MiniBus miniBusModel){
        this.miniBusModel = miniBusModel;
        this.vehicle = (Vehicle) miniBusModel;
        this.type = Vehicle.TYPE_MINI_BUS;

        setCommonVehicleData();

        lblSpecial1.setText("Seating Capacity: ");
        txtSpecial1.setText(Integer.toString(miniBusModel.getSeatingCapacity()));

        lblSpecial2.setText("---" );
        txtSpecial2.setText("---");
    }

    public void setLorryModelData(Lorry lorryModel){
        this.lorryModel = lorryModel;
        this.vehicle = (Vehicle) lorryModel;
        this.type = Vehicle.TYPE_LORRY;

        setCommonVehicleData();

        lblSpecial1.setText("Loading Capacity: ");
        txtSpecial1.setText(Integer.toString(lorryModel.getLoadingCapacity()));

        lblSpecial2.setText("---" );
        txtSpecial2.setText("---");
    }

    public void setHireVehicleModelData(HiredVehicle hireVehicleModel){
        this.hiredVehicleModel = hireVehicleModel;
        this.type = HiredVehicle.TYPE_HIRED_VEHICLE;

        if(hireVehicleModel == null) return;

        setHireVehicleViewsVisibility(true);

        Vehicle vehicle = Vehicle.getVehicleWithRegNo(hireVehicleModel.getVehicleRegNo());
        Customer customer = Customer.getUserWithUsername(hireVehicleModel.getUsername());


        txtCustomerID.setText(Integer.toString(customer.getIdentificationNumber()));
        txtUsername.setText(customer.getUsername());

        if(vehicle != null){
            txtRegNumber.setText(Integer.toString(vehicle.getRegistrationNumber()));
            txtMake.setText(vehicle.getMake());
            txtModel.setText(vehicle.getModel());
        }else {
            txtRegNumber.setText("VEHICLE REMOVED!");
            txtMake.setText("VEHICLE REMOVED!");
            txtModel.setText("VEHICLE REMOVED!");
        }


        txtHireStatus.setText(hireVehicleModel.getStringStatus());
        txtHireAdditionalInfo.setText(hireVehicleModel.getAdditionalInfo());
        txtHireRequestDate.setText(hireVehicleModel.getRequestDate().toString());
    }


    private void setViews(){
        setChildPadding();

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        //add(lblHeading, gbc);
        add(lblRegNumber, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblStaffID, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblCustomerID, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lblMake, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(lblUsername, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(lblModel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(lblPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        add(lblTopSpeed, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        add(lblName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        add(lblIsHired, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        add(lblAddress, gbc);

        gbc.gridx = 0;
        gbc.gridy = 11;
        add(lblDailyHireRate, gbc);

        gbc.gridx = 0;
        gbc.gridy = 12;
        add(lblPhoneNumber, gbc);


        gbc.gridx = 0;
        gbc.gridy = 13;
        add(lblSpecial1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 14;
        add(lblEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 15;
        add(lblSpecial2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 16;
        add(lblHireAdditionalInfo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 17;
        add(lblHireRequestDate, gbc);

        gbc.gridx = 0;
        gbc.gridy = 18;
        add(lblHireStatus, gbc);


       // Text Fields
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(txtRegNumber, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtStaffID, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(txtCustomerID, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(txtMake, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(txtUsername, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        add(txtModel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        add(txtPassword, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        add(txtTopSpeed, gbc);

        gbc.gridx = 1;
        gbc.gridy = 8;
        add(txtName, gbc);

        gbc.gridx = 1;
        gbc.gridy = 9;
        add(txtIsHired, gbc);

        gbc.gridx = 1;
        gbc.gridy = 10;
        add(txtAddress, gbc);

        gbc.gridx = 1;
        gbc.gridy = 11;
        add(txtDailyHireRate, gbc);

        gbc.gridx = 1;
        gbc.gridy = 12;
        add(txtPhoneNumber, gbc);


        gbc.gridx = 1;
        gbc.gridy = 13;
        add(txtSpecial1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 14;
        add(txtEmail, gbc);

        gbc.gridx = 1;
        gbc.gridy = 15;
        add(txtSpecial2, gbc);

        gbc.gridx = 1;
        gbc.gridy = 16;
        add(txtHireAdditionalInfo, gbc);

        gbc.gridx = 1;
        gbc.gridy = 17;
        add(txtHireRequestDate, gbc);

        gbc.gridx = 1;
        gbc.gridy = 18;
        add(txtHireStatus, gbc);
    }

    private void setChildPadding(){
        txtRegNumber.setColumns(4);
        txtStaffID.setColumns(4);
        txtCustomerID.setColumns(4);
    }

    private void insertEditBtn(){
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(20, 2, 0, 2);

        // Add in the last position
        gbc.gridx = 1;
        gbc.gridy = 101;

        this.add(btnEdit, gbc);
    }

    private void insertHireVehicleBtn(){
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(20, 2, 0, 2);

        // Add in the last position
        gbc.gridx = 1;
        gbc.gridy = 100;

        this.add(btnHireVehicle, gbc);
    }

    private void setVehicleViewsVisibility(boolean v){
        lblMake.setVisible(v);
        lblModel.setVisible(v);
        lblRegNumber.setVisible(v);
        lblDailyHireRate.setVisible(v);
        lblIsHired.setVisible(v);
        lblTopSpeed.setVisible(v);
        lblSpecial1.setVisible(v);
        lblSpecial2.setVisible(v);

        txtMake.setVisible(v);
        txtModel.setVisible(v);
        txtRegNumber.setVisible(v);
        txtDailyHireRate.setVisible(v);
        txtIsHired.setVisible(v);
        txtTopSpeed.setVisible(v);
        txtSpecial1.setVisible(v);
        txtSpecial2.setVisible(v);

        if(accessLevelType == User.TYPE_STAFF) btnEdit.setVisible(true);
        btnHireVehicle.setVisible(true);
    }

    private void setCustomerViewsVisibility(boolean v){
        //lblStaffID.setVisible(v);
        lblCustomerID.setVisible(v);
        lblUsername.setVisible(v);
        lblPassword.setVisible(v);
        lblName.setVisible(v);
        lblPhoneNumber.setVisible(v);
        lblAddress.setVisible(v);
        lblEmail.setVisible(v);

        //txtStaffID.setVisible(v);
        txtCustomerID.setVisible(v);
        txtUsername.setVisible(v);
        txtPassword.setVisible(v);
        txtName.setVisible(v);
        txtPhoneNumber.setVisible(v);
        txtAddress.setVisible(v);
        txtEmail.setVisible(v);

        btnEdit.setVisible(true);
    }

    private void setStaffViewsVisibility(boolean v){
        lblStaffID.setVisible(v);
        lblUsername.setVisible(v);
        lblPassword.setVisible(v);
        lblName.setVisible(v);


        txtStaffID.setVisible(v);
        txtUsername.setVisible(v);
        txtPassword.setVisible(v);
        txtName.setVisible(v);

        btnEdit.setVisible(true);
    }

    private void setHireVehicleViewsVisibility(boolean v){
        lblRegNumber.setVisible(v);
        lblCustomerID.setVisible(v);
        lblUsername.setVisible(v);
        lblMake.setVisible(v);
        lblModel.setVisible(v);
        lblHireAdditionalInfo.setVisible(v);
        lblHireRequestDate.setVisible(v);
        lblHireStatus.setVisible(v);

        txtRegNumber.setVisible(v);
        txtCustomerID.setVisible(v);
        txtUsername.setVisible(v);
        txtMake.setVisible(v);
        txtModel.setVisible(v);
        txtHireAdditionalInfo.setVisible(v);
        txtHireRequestDate.setVisible(v);
        txtHireStatus.setVisible(v);
    }

    public void setHidden(){
        for (Component c: this.getComponents()) {
            c.setVisible(false);
        }
    }

    public Font getFontHeading() {
        return fontHeading;
    }

    public void setFontHeading(Font fontHeading) {
        this.fontHeading = fontHeading;
    }

    public Font getFontBig() {
        return fontBig;
    }

    public void setFontBig(Font fontBig) {
        this.fontBig = fontBig;
    }

    public Font getFontMedium() {
        return fontMedium;
    }

    public void setFontMedium(Font fontMedium) {
        this.fontMedium = fontMedium;
    }

    public Font getFontSmall() {
        return fontSmall;
    }

    public void setFontSmall(Font fontSmall) {
        this.fontSmall = fontSmall;
    }

    public JLabel getLblHeading() {
        return lblHeading;
    }

    public void setLblHeading(JLabel lblHeading) {
        this.lblHeading = lblHeading;
    }

    public JLabel getLblRegNumber() {
        return lblRegNumber;
    }

    public void setLblRegNumber(JLabel lblRegNumber) {
        this.lblRegNumber = lblRegNumber;
    }

    public JLabel getLblTopSpeed() {
        return lblTopSpeed;
    }

    public void setLblTopSpeed(JLabel lblTopSpeed) {
        this.lblTopSpeed = lblTopSpeed;
    }

    public JLabel getLblDailyHireRate() {
        return lblDailyHireRate;
    }

    public void setLblDailyHireRate(JLabel lblDailyHireRate) {
        this.lblDailyHireRate = lblDailyHireRate;
    }

    public JLabel getLblMake() {
        return lblMake;
    }

    public void setLblMake(JLabel lblMake) {
        this.lblMake = lblMake;
    }

    public JLabel getLblModel() {
        return lblModel;
    }

    public void setLblModel(JLabel lblModel) {
        this.lblModel = lblModel;
    }

    public JLabel getLblIsHired() {
        return lblIsHired;
    }

    public void setLblIsHired(JLabel lblIsHired) {
        this.lblIsHired = lblIsHired;
    }

    public JLabel getLblSpecial1() {
        return lblSpecial1;
    }

    public void setLblSpecial1(JLabel lblSpecial1) {
        this.lblSpecial1 = lblSpecial1;
    }

    public JLabel getLblSpecial2() {
        return lblSpecial2;
    }

    public void setLblSpecial2(JLabel lblSpecial2) {
        this.lblSpecial2 = lblSpecial2;
    }

    public JTextField getTxtSpecial1() {
        return txtSpecial1;
    }

    public void setTxtSpecial1(JTextField txtSpecial1) {
        this.txtSpecial1 = txtSpecial1;
    }

    public JTextField getTxtSpecial2() {
        return txtSpecial2;
    }

    public void setTxtSpecial2(JTextField txtSpecial2) {
        this.txtSpecial2 = txtSpecial2;
    }

    public JTextField getTxtRegNumber() {
        return txtRegNumber;
    }

    public void setTxtRegNumber(JTextField txtRegNumber) {
        this.txtRegNumber = txtRegNumber;
    }

    public JTextField getTxtTopSpeed() {
        return txtTopSpeed;
    }

    public void setTxtTopSpeed(JTextField txtTopSpeed) {
        this.txtTopSpeed = txtTopSpeed;
    }

    public JTextField getTxtDailyHireRate() {
        return txtDailyHireRate;
    }

    public void setTxtDailyHireRate(JTextField txtDailyHireRate) {
        this.txtDailyHireRate = txtDailyHireRate;
    }

    public JTextField getTxtMake() {
        return txtMake;
    }

    public void setTxtMake(JTextField txtMake) {
        this.txtMake = txtMake;
    }

    public JTextField getTxtModel() {
        return txtModel;
    }

    public void setTxtModel(JTextField txtModel) {
        this.txtModel = txtModel;
    }

    public JTextField getTxtIsHired() {
        return txtIsHired;
    }

    public void setTxtIsHired(JTextField txtIsHired) {
        this.txtIsHired = txtIsHired;
    }

    public JButton getBtnEdit() {
        return btnEdit;
    }

    public void setBtnEdit(JButton btnEdit) {
        this.btnEdit = btnEdit;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Car getCarModel() {
        return carModel;
    }

    public void setCarModel(Car carModel) {
        this.carModel = carModel;
    }

    public MiniBus getMiniBusModel() {
        return miniBusModel;
    }

    public void setMiniBusModel(MiniBus miniBusModel) {
        this.miniBusModel = miniBusModel;
    }

    public Lorry getLorryModel() {
        return lorryModel;
    }

    public void setLorryModel(Lorry lorryModel) {
        this.lorryModel = lorryModel;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public JLabel getLblStaffID() {
        return lblStaffID;
    }

    public void setLblStaffID(JLabel lblStaffID) {
        this.lblStaffID = lblStaffID;
    }

    public JLabel getLblCustomerID() {
        return lblCustomerID;
    }

    public void setLblCustomerID(JLabel lblCustomerID) {
        this.lblCustomerID = lblCustomerID;
    }

    public JLabel getLblUsername() {
        return lblUsername;
    }

    public void setLblUsername(JLabel lblUsername) {
        this.lblUsername = lblUsername;
    }

    public JLabel getLblPassword() {
        return lblPassword;
    }

    public void setLblPassword(JLabel lblPassword) {
        this.lblPassword = lblPassword;
    }

    public JLabel getLblName() {
        return lblName;
    }

    public void setLblName(JLabel lblName) {
        this.lblName = lblName;
    }

    public JLabel getLblAddress() {
        return lblAddress;
    }

    public void setLblAddress(JLabel lblAddress) {
        this.lblAddress = lblAddress;
    }

    public JLabel getLblPhoneNumber() {
        return lblPhoneNumber;
    }

    public void setLblPhoneNumber(JLabel lblPhoneNumber) {
        this.lblPhoneNumber = lblPhoneNumber;
    }

    public JLabel getLblEmail() {
        return lblEmail;
    }

    public void setLblEmail(JLabel lblEmail) {
        this.lblEmail = lblEmail;
    }

    public JLabel getLblHireStatus() {
        return lblHireStatus;
    }

    public void setLblHireStatus(JLabel lblHireStatus) {
        this.lblHireStatus = lblHireStatus;
    }

    public JLabel getLblHireAdditionalInfo() {
        return lblHireAdditionalInfo;
    }

    public void setLblHireAdditionalInfo(JLabel lblHireAdditionalInfo) {
        this.lblHireAdditionalInfo = lblHireAdditionalInfo;
    }

    public JLabel getLblHireRequestDate() {
        return lblHireRequestDate;
    }

    public void setLblHireRequestDate(JLabel lblHireRequestDate) {
        this.lblHireRequestDate = lblHireRequestDate;
    }

    public JTextField getTxtStaffID() {
        return txtStaffID;
    }

    public void setTxtStaffID(JTextField txtStaffID) {
        this.txtStaffID = txtStaffID;
    }

    public JTextField getTxtCustomerID() {
        return txtCustomerID;
    }

    public void setTxtCustomerID(JTextField txtCustomerID) {
        this.txtCustomerID = txtCustomerID;
    }

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public void setTxtUsername(JTextField txtUsername) {
        this.txtUsername = txtUsername;
    }

    public JTextField getTxtPassword() {
        return txtPassword;
    }

    public void setTxtPassword(JTextField txtPassword) {
        this.txtPassword = txtPassword;
    }

    public JTextField getTxtName() {
        return txtName;
    }

    public void setTxtName(JTextField txtName) {
        this.txtName = txtName;
    }

    public JTextField getTxtAddress() {
        return txtAddress;
    }

    public void setTxtAddress(JTextField txtAddress) {
        this.txtAddress = txtAddress;
    }

    public JTextField getTxtPhoneNumber() {
        return txtPhoneNumber;
    }

    public void setTxtPhoneNumber(JTextField txtPhoneNumber) {
        this.txtPhoneNumber = txtPhoneNumber;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(JTextField txtEmail) {
        this.txtEmail = txtEmail;
    }

    public JTextField getTxtHireStatus() {
        return txtHireStatus;
    }

    public void setTxtHireStatus(JTextField txtHireStatus) {
        this.txtHireStatus = txtHireStatus;
    }

    public JTextField getTxtHireAdditionalInfo() {
        return txtHireAdditionalInfo;
    }

    public void setTxtHireAdditionalInfo(JTextField txtHireAdditionalInfo) {
        this.txtHireAdditionalInfo = txtHireAdditionalInfo;
    }

    public JTextField getTxtHireRequestDate() {
        return txtHireRequestDate;
    }

    public void setTxtHireRequestDate(JTextField txtHireRequestDate) {
        this.txtHireRequestDate = txtHireRequestDate;
    }

    public Staff getStaffModel() {
        return staffModel;
    }

    public void setStaffModel(Staff staffModel) {
        this.staffModel = staffModel;
    }

    public Customer getCustomerModel() {
        return customerModel;
    }

    public void setCustomerModel(Customer customerModel) {
        this.customerModel = customerModel;
    }

    public HiredVehicle getHiredVehicleModel() {
        return hiredVehicleModel;
    }

    public void setHiredVehicleModel(HiredVehicle hiredVehicleModel) {
        this.hiredVehicleModel = hiredVehicleModel;
    }

    public JButton getBtnHireVehicle() {
        return btnHireVehicle;
    }

    public void setBtnHireVehicle(JButton btnHireVehicle) {
        this.btnHireVehicle = btnHireVehicle;
    }

    public int getAccessLevelType() {
        return accessLevelType;
    }

    public void setAccessLevelType(int accessLevelType) {
        this.accessLevelType = accessLevelType;
    }
}

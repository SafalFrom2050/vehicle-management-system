package views;

import models.*;
import views.miscellaneous.LabelDescriptionBox;
import views.miscellaneous.TextFieldDescriptionBox;

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
            lblEmail = new LabelDescriptionBox("Email: ");


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
            txtPassword = new TextFieldDescriptionBox(),
            txtName = new TextFieldDescriptionBox(),
            txtAddress = new TextFieldDescriptionBox(),
            txtPhoneNumber = new TextFieldDescriptionBox(),
            txtEmail = new TextFieldDescriptionBox();

    private JButton btnEdit = new JButton("Save Edit");

    private int height, width;

    private Vehicle vehicle;
    private Car carModel;
    private MiniBus miniBusModel;
    private Lorry lorryModel;

    private Staff staffModel;
    private Customer customerModel;

    private int type = Vehicle.TYPE_VEHICLE;


    public PanelDescriptionBox(int height, int width){
        this.height = height;
        this.width = width;

        createDetailPanel();
        setHidden();
    }

    public PanelDescriptionBox(Car carModel, int height, int width){
        this.height = height;
        this.width = width;
        this.carModel = carModel;

        createDetailPanel();
        setCarModelData(carModel);
    }

    public PanelDescriptionBox(MiniBus miniBusModel, int height, int width){
        this.height = height;
        this.width = width;
        this.miniBusModel = miniBusModel;

        createDetailPanel();
        setMiniBusModelData(miniBusModel);
    }

    public PanelDescriptionBox(Lorry lorryModel, int height, int width){
        this.height = height;
        this.width = width;
        this.lorryModel = lorryModel;

        createDetailPanel();
        setLorryModelData(lorryModel);
    }

    public PanelDescriptionBox(Staff staffModel, int height, int width){
        this.height = height;
        this.width = width;
        this.staffModel = staffModel;

        createDetailPanel();
        // TODO: setLorryModelData(lorryModel);
    }

    public PanelDescriptionBox(Customer customerModel, int height, int width){
        this.height = height;
        this.width = width;
        this.customerModel = customerModel;

        createDetailPanel();
        // TODO: setLorryModelData(lorryModel);
    }

    private JPanel createDetailPanel(){
        this.setLayout(new GridBagLayout());

        setPreferredSize(new Dimension(width, height));

        fontHeading = new Font(null, Font.BOLD, 18);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY),
                        "Description", TitledBorder.CENTER,
                        TitledBorder.DEFAULT_POSITION, fontHeading),
                BorderFactory.createEmptyBorder(1, 1, 1, 1)));

        setViews();
        return this;
    }

    private void setCustomerViewsVisibility(boolean v){
        lblCustomerID.setVisible(v);
        txtCustomerID.setVisible(v);

        lblAddress.setVisible(v);
        txtAddress.setVisible(v);

        lblPhoneNumber.setVisible(v);
        txtPhoneNumber.setVisible(v);

        lblEmail.setVisible(v);
        txtEmail.setVisible(v);
    }

    public void setStaffModelData(Staff staffModel){
        // Show Staff User Views Only
        setVehicleViewsVisibility(false);
        setUserViewsVisibility(true);
        btnEdit.setVisible(true);

        setCustomerViewsVisibility(false);


        this.staffModel = staffModel;
        this.type = User.TYPE_STAFF;

        txtStaffID.setText(Integer.toString(staffModel.getStaffID()));
        txtUsername.setText(staffModel.getUsername());

        // Do not show password
        txtPassword.setText("");
    }

    public void setCustomerModelData(Customer customerModel){
        // Show Customer User Views Only
        setVehicleViewsVisibility(false);
        setUserViewsVisibility(true);
        setCustomerViewsVisibility(true);
        btnEdit.setVisible(true);
        lblStaffID.setVisible(false);
        txtStaffID.setVisible(false);

        this.customerModel = customerModel;
        this.type = User.TYPE_CUSTOMER;

        txtCustomerID.setText(Integer.toString(customerModel.getIdentificationNumber()));
        txtUsername.setText(customerModel.getUsername());
        txtName.setText(customerModel.getName());
        txtPhoneNumber.setText(customerModel.getPhoneNumber());
        txtAddress.setText(customerModel.getAddress());
        txtEmail.setText(customerModel.getEmail());

        // Do not show password
        txtPassword.setText("");
    }

    private void setCommonVehicleData(){
        // Show Vehicle Views Only
        setVehicleViewsVisibility(true);
        setUserViewsVisibility(false);
        btnEdit.setVisible(true);

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


    public void setViews(){
        setChildPadding();

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        //add(lblHeading, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblRegNumber, gbc);
        add(lblStaffID, gbc);
        add(lblCustomerID, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblMake, gbc);
        add(lblUsername, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lblModel, gbc);
        add(lblPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(lblTopSpeed, gbc);
        add(lblName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(lblIsHired, gbc);
        add(lblAddress, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(lblDailyHireRate, gbc);
        add(lblPhoneNumber, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        add(lblSpecial1, gbc);
        add(lblEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        add(lblSpecial2, gbc);


       // Text Fields

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtRegNumber, gbc);
        add(txtCustomerID, gbc);
        add(txtStaffID, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(txtMake, gbc);
        add(txtUsername, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(txtModel, gbc);
        add(txtPassword, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(txtTopSpeed, gbc);
        add(txtName, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        add(txtIsHired, gbc);
        add(txtAddress, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        add(txtDailyHireRate, gbc);
        add(txtPhoneNumber, gbc);


        gbc.gridx = 1;
        gbc.gridy = 7;
        add(txtSpecial1, gbc);
        add(txtEmail, gbc);

        gbc.gridx = 1;
        gbc.gridy = 8;
        add(txtSpecial2, gbc);
    }

    private void setChildPadding(){
        txtRegNumber.setColumns(4);
        txtStaffID.setColumns(4);
        txtCustomerID.setColumns(4);
    }

    public void insertEditBtn(){
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(20, 2, 0, 2);

        // Add in the last position
        gbc.gridx = 1;
        gbc.gridy = 100;

        this.add(btnEdit, gbc);
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
    }

    private void setUserViewsVisibility(boolean v){
        lblStaffID.setVisible(v);
        lblCustomerID.setVisible(v);
        lblUsername.setVisible(v);
        lblPassword.setVisible(v);
        lblName.setVisible(v);
        lblPhoneNumber.setVisible(v);
        lblAddress.setVisible(v);
        lblEmail.setVisible(v);

        txtStaffID.setVisible(v);
        txtCustomerID.setVisible(v);
        txtUsername.setVisible(v);
        txtPassword.setVisible(v);
        txtName.setVisible(v);
        txtPhoneNumber.setVisible(v);
        txtAddress.setVisible(v);
        txtEmail.setVisible(v);
    }

    public void setHidden(){

        setVehicleViewsVisibility(false);
        setUserViewsVisibility(false);
        btnEdit.setVisible(false);
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
}

package views;

import models.Car;
import models.Lorry;
import models.MiniBus;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class PanelDescriptionBox extends JPanel {

    private Font fontHeading, fontBig, fontMedium, fontSmall;

    private JLabel lblHeading = new JLabel();

    private JLabel lblRegNumber = new JLabel(),
            lblTopSpeed = new JLabel(),
            lblDailyHireRate = new JLabel(),
            lblMake = new JLabel(),
            lblModel = new JLabel(),
            lblIsHired = new JLabel(),
            lblSpecial1 = new JLabel(),
            lblSpecial2 = new JLabel();

    private JTextField txtRegNumber = new JTextField(),
            txtTopSpeed = new JTextField(),
            txtDailyHireRate = new JTextField(),
            txtMake = new JTextField(),
            txtModel = new JTextField(),
            txtIsHired = new JTextField(),
            txtSpecial1 = new JTextField(),
            txtSpecial2 = new JTextField();

    private JButton btnEdit;

    private int height, width;

    private Car carModel;
    private MiniBus miniBusModel;
    private Lorry lorryModel;

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


    public JPanel createDetailPanel(){
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

    public void setCommonData(){
        lblMake.setText("Make: ");
        txtMake.setText(carModel.getMake());

        lblRegNumber.setText("Reg No: ");
        txtRegNumber.setText(Integer.toString(carModel.getRegistrationNumber()));

        lblTopSpeed.setText("Top Speed: ");
        txtTopSpeed.setText(Integer.toString(carModel.getTopSpeed()));

        lblModel.setText("Model: ");
        txtModel.setText(carModel.getModel());

        lblIsHired.setText("Is Hired: ");
        txtIsHired.setText(Boolean.toString(carModel.isHired()));

        lblDailyHireRate.setText("Daily Hire Rate: ");
        txtDailyHireRate.setText(Integer.toString(carModel.getDailyHireRate()));
    }

    public void setCarModelData(Car carModel){
        this.carModel = carModel;
        setCommonData();

        lblSpecial1.setText("Fuel Type: ");
        txtSpecial1.setText(carModel.getFuelType());

        lblSpecial2.setText("No. of Doors: " );
        txtSpecial2.setText(Integer.toString(carModel.getNumberOfDoors()));
    }

    public void setMiniBusModelData(MiniBus miniBusModel){
        this.miniBusModel = miniBusModel;
        setCommonData();

        lblSpecial1.setText("Seating Capacity: ");
        txtSpecial1.setText(Integer.toString(miniBusModel.getSeatingCapacity()));

        lblSpecial2.setText("---" );
        txtSpecial2.setText("---");
    }

    public void setLorryModelData(Lorry lorryModel){
        this.lorryModel = lorryModel;
        setCommonData();

        lblSpecial1.setText("Loading Capacity: ");
        txtSpecial1.setText(Integer.toString(lorryModel.getLoadingCapacity()));

        lblSpecial2.setText("---" );
        txtSpecial2.setText("---");
    }


    public void setViews(){
        setFonts();
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

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblMake, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lblModel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(lblTopSpeed, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(lblIsHired, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(lblDailyHireRate, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        add(lblSpecial1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        add(lblSpecial2, gbc);

       // Buttons

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtRegNumber, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(txtMake, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(txtModel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(txtTopSpeed, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        add(txtIsHired, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        add(txtDailyHireRate, gbc);


        gbc.gridx = 1;
        gbc.gridy = 7;
        add(txtSpecial1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 8;
        add(txtSpecial2, gbc);
    }

    private void setFonts(){
        fontBig = new Font(null, Font.PLAIN, 16);
        fontMedium = new Font(null, Font.PLAIN, 14);

        lblHeading.setFont(fontHeading);

        lblMake.setFont(fontMedium);
        lblModel.setFont(fontMedium);
        lblTopSpeed.setFont(fontMedium);
        lblRegNumber.setFont(fontMedium);
        lblDailyHireRate.setFont(fontMedium);
        lblIsHired.setFont(fontMedium);
        lblSpecial1.setFont(fontMedium);
        lblSpecial2.setFont(fontMedium);

        txtMake.setFont(fontMedium);
        txtModel.setFont(fontMedium);
        txtTopSpeed.setFont(fontMedium);
        txtRegNumber.setFont(fontMedium);
        txtDailyHireRate.setFont(fontMedium);
        txtIsHired.setFont(fontMedium);
        txtSpecial1.setFont(fontMedium);
        txtSpecial2.setFont(fontMedium);
    }

    private void setChildPadding(){

        txtRegNumber.setColumns(4);
        txtRegNumber.setHorizontalAlignment(SwingConstants.CENTER);

        txtMake.setColumns(10);
        txtMake.setHorizontalAlignment(SwingConstants.CENTER);

        txtModel.setColumns(10);
        txtModel.setHorizontalAlignment(SwingConstants.CENTER);

        txtDailyHireRate.setColumns(10);
        txtDailyHireRate.setHorizontalAlignment(SwingConstants.CENTER);

        txtSpecial1.setColumns(10);
        txtSpecial1.setHorizontalAlignment(SwingConstants.CENTER);

        txtSpecial2.setColumns(10);
        txtSpecial2.setHorizontalAlignment(SwingConstants.CENTER);

        txtIsHired.setColumns(10);
        txtIsHired.setHorizontalAlignment(SwingConstants.CENTER);

        txtTopSpeed.setColumns(10);
        txtTopSpeed.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void insertEditBtn(){
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(20, 2, 0, 2);

        // Add in the last position
        gbc.gridx = 1;
        gbc.gridy = 100;


        btnEdit = new JButton("Save Edit");
        this.add(btnEdit, gbc);
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
}

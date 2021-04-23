package views;

import controllers.ListRenderer;
import models.*;
import models.ListModel;
import utility.Utility;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class PanelListView extends JPanel {

    private ListModel listModel;
    private int height, width;

    private JButton btnAddCar = new JButton("Add Car"),
            btnAddMiniBus = new JButton("Add Minibus"),
            btnAddLorry = new JButton("Add Lorry"),
            btnApprove = new JButton("Approve"),
            btnDisapprove = new JButton("Disapprove"),
            btnSetExpired = new JButton("Set Expired"),
            btnRemove;

    private JButton btnAddStaff = new JButton("Add Staff"),
            btnAddCustomer = new JButton("Add Customer");

    private JList listView = new JList();

    private int listType = -1;
    private int userType = User.TYPE_CUSTOMER;

    PanelListView(int width, int height, int userType){
        this.height = height;
        this.width = width;
        this.userType = userType;

        setTitle("Vehicles");
        setLayout(new FlowLayout());
        addListPanel();
    }

    private void setTitle(String title){
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(),
                        title, TitledBorder.DEFAULT_JUSTIFICATION,
                        TitledBorder.DEFAULT_POSITION, new Font(null, Font.BOLD, 16)),
                BorderFactory.createEmptyBorder(0, 1, 0, 1)));
    }

    public PanelListView(ListModel listModel, int width, int height, int userType){
        this.listModel = listModel;
        this.height = height;
        this.width = width;

        setTitle("Vehicles");

        setLayout(new FlowLayout());
        addListPanel();
    }

    private void addListPanel(){

        setListModel(listModel);

        setPreferredSize(new Dimension(width, height));

        listView.setVisibleRowCount(6);
        JScrollPane scrollPane = new JScrollPane(listView);
        listView.setFixedCellWidth(width - 20);

        listView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(scrollPane);
    }

    private void insertVehicleAddBtn(){
        removeActionButtons();

        this.add(btnAddCar);
        this.add(btnAddMiniBus);
        this.add(btnAddLorry);
    }

    private void insertUserAddBtn(){
        removeActionButtons();

        this.add(btnAddCustomer);
        this.add(btnAddStaff);
    }

    private void insertHireRequestBtn(){
        removeActionButtons();

        this.add(btnApprove);
        this.add(btnDisapprove);
        this.add(btnSetExpired);

    }

    private void removeActionButtons(){
        this.remove(btnAddStaff);
        this.remove(btnAddCustomer);
        this.remove(btnAddCar);
        this.remove(btnAddMiniBus);
        this.remove(btnAddLorry);
        this.remove(btnApprove);
        this.remove(btnDisapprove);
        this.remove(btnSetExpired);

        revalidate();
        repaint();
    }

    void insertRemoveBtn(){
        btnRemove = new JButton("Remove");
        this.add(btnRemove);
    }

    public void setListModel(ListModel listModel) {
        this.listModel = listModel;

        if(listModel != null) listView.setModel(listModel);
    }

    private void setListRenderer(ListRenderer listRenderer){
        listView.setCellRenderer(listRenderer);
    }

    public void setListType(int listType){

        if(listType == Vehicle.TYPE_CAR ||
                listType == Vehicle.TYPE_MINI_BUS  ||
                listType == Vehicle.TYPE_LORRY  ||
                listType == Vehicle.TYPE_VEHICLE){
            this.listType = Vehicle.TYPE_VEHICLE;
            setTitle("Vehicles");
            setListRenderer(new ListRenderer<Vehicle>(Utility.HEADINGS_VEHICLES));

            // Only add controls for staff user type
            if(userType == User.TYPE_STAFF) insertVehicleAddBtn();
        }else if(listType == User.TYPE_USER ||
                listType == User.TYPE_CUSTOMER ||
                listType == User.TYPE_STAFF){

            this.listType = User.TYPE_USER;
            setTitle("All Users");
            setListRenderer(new ListRenderer<Staff>(Utility.HEADINGS_USERS));
            // Only add controls for staff user type
            if(userType == User.TYPE_STAFF) insertUserAddBtn();
        }else if(listType == HiredVehicle.TYPE_HIRED_VEHICLE){
            this.listType = HiredVehicle.TYPE_HIRED_VEHICLE;
            setTitle("Hired Vehicles");
            setListRenderer(new ListRenderer<HiredVehicle>(Utility.HEADINGS_HIRED_VEHICLES));
            // Only add controls for staff user type
            if(userType == User.TYPE_STAFF) insertHireRequestBtn();
        }
    }

    public JButton getBtnAddCar() {
        return btnAddCar;
    }

    public void setBtnAddCar(JButton btnAddCar) {
        this.btnAddCar = btnAddCar;
    }

    public JButton getBtnAddMiniBus() {
        return btnAddMiniBus;
    }

    public void setBtnAddMiniBus(JButton btnAddMiniBus) {
        this.btnAddMiniBus = btnAddMiniBus;
    }

    public JButton getBtnAddLorry() {
        return btnAddLorry;
    }

    public void setBtnAddLorry(JButton btnAddLorry) {
        this.btnAddLorry = btnAddLorry;
    }

    public JButton getBtnRemove() {
        return btnRemove;
    }

    public void setBtnRemove(JButton btnRemove) {
        this.btnRemove = btnRemove;
    }

    public JList getListView() {
        return listView;
    }

    public void setListView(JList listView) {
        this.listView = listView;
    }

    public JButton getBtnAddStaff() {
        return btnAddStaff;
    }

    public void setBtnAddStaff(JButton btnAddStaff) {
        this.btnAddStaff = btnAddStaff;
    }

    public JButton getBtnAddCustomer() {
        return btnAddCustomer;
    }

    public void setBtnAddCustomer(JButton btnAddCustomer) {
        this.btnAddCustomer = btnAddCustomer;
    }

    public ListModel getListModel() {
        return listModel;
    }

    public int getListType(){
        return listType;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }


    public JButton getBtnApprove() {
        return btnApprove;
    }

    public void setBtnApprove(JButton btnApprove) {
        this.btnApprove = btnApprove;
    }

    public JButton getBtnDisapprove() {
        return btnDisapprove;
    }

    public void setBtnDisapprove(JButton btnDisapprove) {
        this.btnDisapprove = btnDisapprove;
    }

    public JButton getBtnSetExpired() {
        return btnSetExpired;
    }

    public void setBtnSetExpired(JButton btnSetExpired) {
        this.btnSetExpired = btnSetExpired;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}

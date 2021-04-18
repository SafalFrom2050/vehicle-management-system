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
            btnRemove;

    private JButton btnAddStaff = new JButton("Add Staff"),
            btnAddCustomer = new JButton("Add Customer");

    private JList listView = new JList();

    private int listType = -1;

    public PanelListView(int width, int height){
        this.height = height;
        this.width = width;

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(),
                        "Vehicles", TitledBorder.DEFAULT_JUSTIFICATION,
                        TitledBorder.DEFAULT_POSITION, new Font(null, Font.BOLD, 16)),
                BorderFactory.createEmptyBorder(0, 1, 0, 1)));

        setLayout(new FlowLayout());
        getListPanel();
    }

    public PanelListView(ListModel listModel, int width, int height){
        this.listModel = listModel;
        this.height = height;
        this.width = width;

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(),
                        "Vehicles", TitledBorder.DEFAULT_JUSTIFICATION,
                        TitledBorder.DEFAULT_POSITION, new Font(null, Font.BOLD, 16)),
                BorderFactory.createEmptyBorder(0, 1, 0, 1)));

        setLayout(new FlowLayout());
        getListPanel();
    }

    public JPanel getListPanel(){

        setListModel(listModel);

        // TODO: Remove

        listView.setCellRenderer(new ListRenderer<Car>(Utility.HEADINGS_VEHICLES));
        //

        setPreferredSize(new Dimension(width, height));

        listView.setVisibleRowCount(6);
        JScrollPane scrollPane = new JScrollPane(listView);
        listView.setFixedCellWidth(width - 20);

        listView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(scrollPane);
        return this;
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

    private void removeActionButtons(){
        this.remove(btnAddStaff);
        this.remove(btnAddCustomer);
        this.remove(btnAddCar);
        this.remove(btnAddMiniBus);
        this.remove(btnAddLorry);

        revalidate();
        repaint();
    }

    public void insertRemoveBtn(){
        btnRemove = new JButton("Remove");
        this.add(btnRemove);
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

    public void setListModel(ListModel listModel) {
        this.listModel = listModel;

        if(listModel != null) listView.setModel(listModel);
    }

    public void setListRenderer(ListRenderer listRenderer){
        listView.setCellRenderer(listRenderer);
    }

    public void setListType(int listType){

        if(listType == Vehicle.TYPE_CAR ||
                listType == Vehicle.TYPE_MINI_BUS  ||
                listType == Vehicle.TYPE_LORRY  ||
                listType == Vehicle.TYPE_VEHICLE){

            this.listType = Vehicle.TYPE_VEHICLE;
            setListRenderer(new ListRenderer<Vehicle>(Utility.HEADINGS_VEHICLES));
            insertVehicleAddBtn();
        }else if(listType == User.TYPE_USER ||
                listType == User.TYPE_CUSTOMER ||
                listType == User.TYPE_STAFF){

            this.listType = User.TYPE_USER;
            setListRenderer(new ListRenderer<Staff>(Utility.HEADINGS_USERS));
            insertUserAddBtn();
        }
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
}

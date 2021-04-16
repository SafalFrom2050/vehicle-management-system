package views;

import controllers.ListRenderer;
import models.Car;
import models.ListModel;
import utility.Utility;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class PanelListView extends JPanel {

    private ListModel listModel;
    private int height, width;

    private JButton btnAddCar, btnAddMiniBus, btnAddLorry, btnRemove;
    private JList listView = new JList();

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

    public void insertAddBtn(){
        btnAddCar = new JButton("Add Car");
        btnAddMiniBus = new JButton("Add Minibus");
        btnAddLorry = new JButton("Add Lorry");

        this.add(btnAddCar);
        this.add(btnAddMiniBus);
        this.add(btnAddLorry);
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

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}

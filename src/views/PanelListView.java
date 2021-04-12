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

    private JButton btnAdd, btnRemove, btnEdit;
    private JList listView = new JList();

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
        btnAdd = new JButton("Add");
        this.add(btnAdd);
    }
    public void insertEditBtn(){
        btnEdit = new JButton("Edit");
        this.add(btnEdit);
    }
    public void insertRemoveBtn(){
        btnRemove = new JButton("Remove");
        this.add(btnRemove);
    }

    public JButton getBtnAdd() {
        return btnAdd;
    }

    public void setBtnAdd(JButton btnAdd) {
        this.btnAdd = btnAdd;
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

    public JButton getBtnEdit() {
        return btnEdit;
    }

    public void setBtnEdit(JButton btnEdit) {
        this.btnEdit = btnEdit;
    }

    public ListModel getListModel() {
        return listModel;
    }

    public void setListModel(ListModel listModel) {
        listView.setModel(listModel);
        this.listModel = listModel;
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

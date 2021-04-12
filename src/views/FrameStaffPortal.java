package views;

import models.Car;
import models.ListModel;
import utility.Utility;

import javax.swing.*;
import java.awt.*;

public class FrameStaffPortal extends JFrame {

    private Font fontMenu = new Font(null, Font.BOLD, 28);
    private JPanel panelMenu = new JPanel();
    private JButton btnMenuVehicles = new JButton("Vehicles");
    private JButton btnMenuCustomers = new JButton("Customers");
    private JButton btnMenuHireRequests = new JButton("Hire Requests");

    private PanelListView panelListView;

    private PanelDescriptionBox panelDescriptionBox;

    public FrameStaffPortal(){

        setSize(800, 600);          // 4:3 aspect ratio (required on Top to create panel)

    }

    public JPanel createNewPanel(){
        // Parent Panel
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Testing!");

        panel.add(getMenuPanel(), BorderLayout.NORTH);
        panel.add(label, BorderLayout.SOUTH);

        panel.add(loadCarsListPanel(), BorderLayout.WEST);
        panel.add(loadDetailsPanel(), BorderLayout.EAST);

        return panel;
    }

    public JPanel getMenuPanel(){
        btnMenuCustomers.setFont(fontMenu);
        btnMenuHireRequests.setFont(fontMenu);
        btnMenuVehicles.setFont(fontMenu);

        // Keep default color of the button
        Utility.button_default_color = btnMenuCustomers.getBackground();

        btnMenuCustomers.setPreferredSize(new Dimension(250, 140));
        btnMenuHireRequests.setPreferredSize(new Dimension(250, 140));
        btnMenuVehicles.setPreferredSize(new Dimension(250, 140));

        panelMenu.setLayout(new FlowLayout());
        panelMenu.setPreferredSize(new Dimension(this.getWidth(), 150));
        panelMenu.add(btnMenuVehicles);
        panelMenu.add(btnMenuCustomers);
        panelMenu.add(btnMenuHireRequests);

        return panelMenu;
    }

    public JPanel loadDetailsPanel(){
        JPanel parent = new JPanel(new GridBagLayout());
        parent.setPreferredSize(new Dimension(340, 360));

        Car car = new Car(1, 180, 4, "Toyota", "2020", false, "petrol", 4);
        panelDescriptionBox = new PanelDescriptionBox(car, 360, 300);
        panelDescriptionBox.insertEditBtn();
        parent.add(panelDescriptionBox);
        return parent;
    }


    public JPanel loadCarsListPanel(){
        JPanel parent = new JPanel(new GridBagLayout());
        parent.setPreferredSize(new Dimension(this.getWidth()/2+40, 400));

        ListModel<Car> listModelCar = new ListModel(Car.getDummyList());

        panelListView = new PanelListView(listModelCar, this.getWidth()/2, 360);
        panelListView.insertAddBtn();
        panelListView.insertRemoveBtn();

        parent.add(panelListView);
        return parent;
    }


    public Font getFontMenu() {
        return fontMenu;
    }

    public void setFontMenu(Font fontMenu) {
        this.fontMenu = fontMenu;
    }

    public JPanel getPanelMenu() {
        return panelMenu;
    }

    public void setPanelMenu(JPanel panelMenu) {
        this.panelMenu = panelMenu;
    }

    public JButton getBtnMenuVehicles() {
        return btnMenuVehicles;
    }

    public void setBtnMenuVehicles(JButton btnMenuVehicles) {
        this.btnMenuVehicles = btnMenuVehicles;
    }

    public JButton getBtnMenuCustomers() {
        return btnMenuCustomers;
    }

    public void setBtnMenuCustomers(JButton btnMenuCustomers) {
        this.btnMenuCustomers = btnMenuCustomers;
    }

    public JButton getBtnMenuHireRequests() {
        return btnMenuHireRequests;
    }

    public void setBtnMenuHireRequests(JButton btnMenuHireRequests) {
        this.btnMenuHireRequests = btnMenuHireRequests;
    }

    public PanelListView getPanelListView() {
        return panelListView;
    }

    public void setPanelListView(PanelListView panelListView) {
        this.panelListView = panelListView;
    }

    public PanelDescriptionBox getPanelDescriptionBox() {
        return panelDescriptionBox;
    }

    public void setPanelDescriptionBox(PanelDescriptionBox panelDescriptionBox) {
        this.panelDescriptionBox = panelDescriptionBox;
    }
}

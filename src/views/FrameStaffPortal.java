package views;

import models.User;
import utility.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class FrameStaffPortal extends JFrame {

    private Font fontMenu = new Font(null, Font.BOLD, 28);
    private JPanel panelMenu = new JPanel();
    private JButton btnMenuVehicles = new JButton("Vehicles");
    private JButton btnMenuCustomers = new JButton("Customers");
    private JButton btnMenuHireRequests = new JButton("Hire Requests");

    private PanelListView panelListView;

    private PanelDescriptionBox panelDescriptionBox;

    private JMenuItem menuLogout = new JMenuItem("Logout");

    public FrameStaffPortal(){

        setSize(800, 600);          // 4:3 aspect ratio (required on Top to create panel)

        // Set menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Account");
        menu.add(menuLogout);
        menuBar.add(menu);

        this.setJMenuBar(menuBar);

        createNewPanel();
    }

    private void createNewPanel(){
        // Parent Panel
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Testing!");

        panel.add(getMenuPanel(), BorderLayout.NORTH);
        panel.add(label, BorderLayout.SOUTH);

        panel.add(createListPanel(), BorderLayout.WEST);
        panel.add(createDetailsPanel(), BorderLayout.EAST);

        add(panel);

    }

    private JPanel getMenuPanel(){
        btnMenuVehicles.setMnemonic(KeyEvent.VK_V);
        btnMenuCustomers.setMnemonic(KeyEvent.VK_C);
        btnMenuHireRequests.setMnemonic(KeyEvent.VK_H);

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

    private JPanel createDetailsPanel(){
        JPanel parent = new JPanel(new GridBagLayout());
        parent.setPreferredSize(new Dimension(340, 360));

        panelDescriptionBox = new PanelDescriptionBox(360, 300, User.TYPE_STAFF);
        panelDescriptionBox.setHidden();
        parent.add(panelDescriptionBox);
        return parent;
    }


    private JPanel createListPanel(){
        JPanel parent = new JPanel(new GridBagLayout());
        parent.setPreferredSize(new Dimension(this.getWidth()/2+40, 400));

        panelListView = new PanelListView( this.getWidth()/2, 360, User.TYPE_STAFF);
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

    public JMenuItem getMenuLogout() {
        return menuLogout;
    }

    public void setMenuLogout(JMenuItem menuLogout) {
        this.menuLogout = menuLogout;
    }
}

package uon.vehiclehiresystem.views;

import uon.vehiclehiresystem.models.User;
import uon.vehiclehiresystem.utility.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class FrameCustomerPortal extends JFrame {

    private Font fontMenu = new Font(null, Font.BOLD, 28);
    private JPanel panelMenu = new JPanel();
    private JButton btnMenuVehicles = new JButton("All Vehicles");
    private JButton btnMenuMyRents = new JButton("My Rents");
    private JButton btnMenuMyAccount = new JButton("My Account");

    private PanelListView panelListView;

    private PanelDescriptionBox panelDescriptionBox;

    private JMenuItem menuLogout = new JMenuItem("Logout");

    public FrameCustomerPortal(){
        setSize(800, 600);          // 4:3 aspect ratio (required on Top to create panel)

        // Set menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Account");
        menu.add(menuLogout);
        menuBar.add(menu);
        setJMenuBar(menuBar);

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
        btnMenuMyRents.setMnemonic(KeyEvent.VK_R);
        btnMenuMyAccount.setMnemonic(KeyEvent.VK_A);

        btnMenuVehicles.setFont(fontMenu);
        btnMenuMyRents.setFont(fontMenu);
        btnMenuMyAccount.setFont(fontMenu);

        // Keep default color of the button
        Utility.button_default_color = btnMenuVehicles.getBackground();

        btnMenuMyAccount.setPreferredSize(new Dimension(250, 140));
        btnMenuMyRents.setPreferredSize(new Dimension(250, 140));
        btnMenuVehicles.setPreferredSize(new Dimension(250, 140));

        panelMenu.setLayout(new FlowLayout());
        panelMenu.setPreferredSize(new Dimension(this.getWidth(), 150));
        panelMenu.add(btnMenuVehicles);
        panelMenu.add(btnMenuMyRents);
        panelMenu.add(btnMenuMyAccount);

        return panelMenu;
    }


    private JPanel createDetailsPanel(){
        JPanel parent = new JPanel(new GridBagLayout());
        parent.setPreferredSize(new Dimension(340, 360));

        panelDescriptionBox = new PanelDescriptionBox(360, 300, User.TYPE_CUSTOMER);
        panelDescriptionBox.setHidden();
        parent.add(panelDescriptionBox);
        return parent;
    }


    private JPanel createListPanel(){
        JPanel parent = new JPanel(new GridBagLayout());
        parent.setPreferredSize(new Dimension(this.getWidth()/2+40, 400));

        panelListView = new PanelListView( this.getWidth()/2, 360, User.TYPE_CUSTOMER);

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

    public JButton getBtnMenuMyRents() {
        return btnMenuMyRents;
    }

    public void setBtnMenuMyRents(JButton btnMenuMyRents) {
        this.btnMenuMyRents = btnMenuMyRents;
    }

    public JButton getBtnMenuMyAccount() {
        return btnMenuMyAccount;
    }

    public void setBtnMenuMyAccount(JButton btnMenuMyAccount) {
        this.btnMenuMyAccount = btnMenuMyAccount;
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

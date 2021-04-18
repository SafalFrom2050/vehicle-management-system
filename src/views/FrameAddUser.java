package views;

import models.User;

import javax.swing.*;
import java.awt.*;

public class FrameAddUser extends JFrame {

    private JLabel lblStaffID = new JLabel("Staff ID: "),
            lblCustomerID = new JLabel("Customer ID: "),
            lblUsername = new JLabel("Username: "),
            lblPassword = new JLabel("Password: "),
            lblName = new JLabel("Name: "),
            lblPhoneNumber = new JLabel("Phone Number: "),
            lblAddress = new JLabel("Address: "),
            lblEmail = new JLabel("Email: ");

    private JTextField txtStaffID = new JTextField(),
            txtCustomerID = new JTextField(),
            txtUsername = new JTextField(),
            txtPassword = new JTextField(),
            txtName = new JTextField(),
            txtPhoneNumber = new JTextField(),
            txtAddress = new JTextField(),
            txtEmail = new JTextField();

    private JButton btnSubmit = new JButton("Confirm");

    private JPanel panel = new JPanel(new GridLayout(10,2));

    public FrameAddUser(int type){
        setSize(400, 300);          // 4:3 aspect ratio (required on Top to create panel)

        if(type == User.TYPE_STAFF){
            setTitle("Add New Staff");

            panel = createAddStaffPanel();
            add(panel);

        }else if(type == User.TYPE_CUSTOMER){
            setTitle("Add New Customer");

            panel = createAddCustomerPanel();
            add(panel);
        }

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createAddStaffPanel(){

        JPanel tempPanel = new JPanel(new GridBagLayout());

        panel.add(lblStaffID);
        panel.add(txtStaffID);

        panel.add(lblName);
        panel.add(txtName);

        panel.add(lblUsername);
        panel.add(txtUsername);

        panel.add(lblPassword);
        panel.add(txtPassword);

        // Placeholder
        panel.add(new JLabel());
        panel.add(btnSubmit);

        tempPanel.add(panel);
        return tempPanel;
    }

    private JPanel createAddCustomerPanel(){

        JPanel tempPanel = new JPanel(new GridBagLayout());

        panel.add(lblCustomerID);
        panel.add(txtCustomerID);

        panel.add(lblName);
        panel.add(txtName);

        panel.add(lblUsername);
        panel.add(txtUsername);

        panel.add(lblPassword);
        panel.add(txtPassword);

        panel.add(lblPhoneNumber);
        panel.add(txtPhoneNumber);

        panel.add(lblAddress);
        panel.add(txtAddress);

        panel.add(lblEmail);
        panel.add(txtEmail);

        // Placeholder
        panel.add(new JLabel());
        panel.add(btnSubmit);

        tempPanel.add(panel);
        return tempPanel;
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

    public JLabel getLblPhoneNumber() {
        return lblPhoneNumber;
    }

    public void setLblPhoneNumber(JLabel lblPhoneNumber) {
        this.lblPhoneNumber = lblPhoneNumber;
    }

    public JLabel getLblAddress() {
        return lblAddress;
    }

    public void setLblAddress(JLabel lblAddress) {
        this.lblAddress = lblAddress;
    }

    public JLabel getLblEmail() {
        return lblEmail;
    }

    public void setLblEmail(JLabel lblEmail) {
        this.lblEmail = lblEmail;
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

    public JTextField getTxtPhoneNumber() {
        return txtPhoneNumber;
    }

    public void setTxtPhoneNumber(JTextField txtPhoneNumber) {
        this.txtPhoneNumber = txtPhoneNumber;
    }

    public JTextField getTxtAddress() {
        return txtAddress;
    }

    public void setTxtAddress(JTextField txtAddress) {
        this.txtAddress = txtAddress;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(JTextField txtEmail) {
        this.txtEmail = txtEmail;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JButton getBtnSubmit() {
        return btnSubmit;
    }

    public void setBtnSubmit(JButton btnSubmit) {
        this.btnSubmit = btnSubmit;
    }
}

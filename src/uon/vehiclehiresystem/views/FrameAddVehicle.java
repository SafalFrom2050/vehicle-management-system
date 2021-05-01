package uon.vehiclehiresystem.views;

import javax.swing.*;
import java.awt.*;

public class FrameAddVehicle extends JFrame {

    private JPanel panel = new JPanel(new GridLayout(10,2));

    private JLabel lblRegNumber = new JLabel("Registration Number"),
            lblTopSpeed = new JLabel("Top Speed"),
            lblDailyHireRate = new JLabel("Daily Hire Rate"),
            lblMake = new JLabel("Make"),
            lblModel = new JLabel("Model"),
            lblIsHired = new JLabel("Is Hired"),
            lblSpecial1 = new JLabel("Special"),
            lblSpecial2 = new JLabel("Special");

    private JTextField txtRegNumber = new JTextField(),
            txtTopSpeed = new JTextField(),
            txtDailyHireRate = new JTextField(),
            txtMake = new JTextField(),
            txtModel = new JTextField(),
            txtIsHired = new JTextField(),
            txtSpecial1 = new JTextField(),
            txtSpecial2 = new JTextField();

    private JButton btnConfirm = new JButton("Confirm");

    public FrameAddVehicle(){
        setSize(400, 300);          // 4:3 aspect ratio (required on Top to create panel)
        panel = createPanel();
        add(panel);

        setTitle("Add New Vehicle");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createPanel(){

        JPanel tempPanel = new JPanel(new GridBagLayout());


        panel.add(lblRegNumber);
        panel.add(txtRegNumber);

        panel.add(lblMake);
        panel.add(txtMake);

        panel.add(lblModel);
        panel.add(txtModel);

        panel.add(lblTopSpeed);
        panel.add(txtTopSpeed);

        panel.add(lblDailyHireRate);
        panel.add(txtDailyHireRate);

        panel.add(lblSpecial1);
        panel.add(txtSpecial1);

        panel.add(lblSpecial2);
        panel.add(txtSpecial2);

        panel.add(new JLabel());  // Placeholder
        panel.add(new JLabel());  // Placeholder
        panel.add(new JLabel());  // Placeholder
        panel.add(btnConfirm);

        tempPanel.add(this.panel);

        return tempPanel;
    }


    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
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

    public JButton getBtnConfirm() {
        return btnConfirm;
    }

    public void setBtnConfirm(JButton btnConfirm) {
        this.btnConfirm = btnConfirm;
    }
}

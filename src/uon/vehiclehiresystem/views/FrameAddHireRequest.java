package uon.vehiclehiresystem.views;

import uon.vehiclehiresystem.views.miscellaneous.LabelDescriptionBox;
import uon.vehiclehiresystem.views.miscellaneous.TextFieldDescriptionBox;

import javax.swing.*;
import java.awt.*;

public class FrameAddHireRequest extends JFrame {

    private JPanel panel = new JPanel(new GridLayout(10,2));

    private JLabel lblVehicleName = new LabelDescriptionBox("Vehicle Name"),
            lblVehicleRegNumber = new LabelDescriptionBox("Vehicle Registration Number"),
            lblAdditionalInfo = new LabelDescriptionBox("Additional Info"),
            lblSeatingCapacity = new LabelDescriptionBox("Number of Seats"),
            lblLoadingCapacity = new LabelDescriptionBox("Loading Capacity");

    private JTextField txtAdditionalInfo = new TextFieldDescriptionBox(),
            txtSeatingCapacity = new TextFieldDescriptionBox(),
            txtLoadingCapacity = new TextFieldDescriptionBox();

    private JButton btnAddRequest = new JButton("Add Request");


    public FrameAddHireRequest(){
        setSize(400, 300);          // 4:3 aspect ratio (required on Top to create panel)

        panel = createPanel();
        add(panel);

        setTitle("Request Vehicle Hire");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createPanel(){

        JPanel tempPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(lblVehicleName, gbc);

        // Placeholder
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(new JLabel(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblVehicleRegNumber, gbc);

        // Placeholder
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(new JLabel(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(lblAdditionalInfo, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(txtAdditionalInfo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lblLoadingCapacity, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(txtLoadingCapacity, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lblSeatingCapacity, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(txtSeatingCapacity, gbc);

        // Placeholder
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel(), gbc);
        // Placeholder
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(new JLabel(), gbc);

        // Placeholder
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel(), gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(btnAddRequest, gbc);

        tempPanel.add(this.panel);

        return tempPanel;
    }

    public void removeSeatingCapacity(){
        lblSeatingCapacity.getParent().remove(lblSeatingCapacity);
        txtSeatingCapacity.getParent().remove(txtSeatingCapacity);

        revalidate();
        repaint();
    }

    public void removeLoadingCapacity(){

        lblLoadingCapacity.getParent().remove(lblLoadingCapacity);
        txtLoadingCapacity.getParent().remove(txtLoadingCapacity);

        revalidate();
        repaint();
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JLabel getLblVehicleName() {
        return lblVehicleName;
    }

    public void setLblVehicleName(JLabel lblVehicleName) {
        this.lblVehicleName = lblVehicleName;
    }

    public JLabel getLblAdditionalInfo() {
        return lblAdditionalInfo;
    }

    public void setLblAdditionalInfo(JLabel lblAdditionalInfo) {
        this.lblAdditionalInfo = lblAdditionalInfo;
    }

    public JTextField getTxtAdditionalInfo() {
        return txtAdditionalInfo;
    }

    public void setTxtAdditionalInfo(JTextField txtAdditionalInfo) {
        this.txtAdditionalInfo = txtAdditionalInfo;
    }

    public JButton getBtnAddRequest() {
        return btnAddRequest;
    }

    public void setBtnAddRequest(JButton btnAddRequest) {
        this.btnAddRequest = btnAddRequest;
    }

    public JLabel getLblVehicleRegNumber() {
        return lblVehicleRegNumber;
    }

    public void setLblVehicleRegNumber(JLabel lblVehicleRegNumber) {
        this.lblVehicleRegNumber = lblVehicleRegNumber;
    }

    public JLabel getLblSeatingCapacity() {
        return lblSeatingCapacity;
    }

    public void setLblSeatingCapacity(JLabel lblSeatingCapacity) {
        this.lblSeatingCapacity = lblSeatingCapacity;
    }

    public JLabel getLblLoadingCapacity() {
        return lblLoadingCapacity;
    }

    public void setLblLoadingCapacity(JLabel lblLoadingCapacity) {
        this.lblLoadingCapacity = lblLoadingCapacity;
    }

    public JTextField getTxtSeatingCapacity() {
        return txtSeatingCapacity;
    }

    public void setTxtSeatingCapacity(JTextField txtSeatingCapacity) {
        this.txtSeatingCapacity = txtSeatingCapacity;
    }

    public JTextField getTxtLoadingCapacity() {
        return txtLoadingCapacity;
    }

    public void setTxtLoadingCapacity(JTextField txtLoadingCapacity) {
        this.txtLoadingCapacity = txtLoadingCapacity;
    }
}

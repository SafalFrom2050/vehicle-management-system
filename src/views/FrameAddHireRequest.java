package views;

import views.miscellaneous.LabelDescriptionBox;
import views.miscellaneous.TextFieldDescriptionBox;

import javax.swing.*;
import java.awt.*;

public class FrameAddHireRequest extends JFrame {

    private JPanel panel = new JPanel(new GridLayout(10,2));

    private JLabel lblVehicleName = new LabelDescriptionBox("Vehicle Name"),
            lblVehicleRegNumber = new LabelDescriptionBox("Vehicle Registration Number"),
            lblAdditionalInfo = new LabelDescriptionBox("Additional Info");

    private JTextField txtAdditionalInfo = new TextFieldDescriptionBox();

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
        panel.add(lblAdditionalInfo, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(txtAdditionalInfo, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(btnAddRequest, gbc);

        tempPanel.add(this.panel);

        return tempPanel;
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
}

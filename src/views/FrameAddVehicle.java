package views;

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
        panel = createAddVehiclePanel();
        add(panel);

        setTitle("Add New Vehicle");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JPanel createAddVehiclePanel(){

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

}

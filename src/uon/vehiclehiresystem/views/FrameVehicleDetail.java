package uon.vehiclehiresystem.views;

import uon.vehiclehiresystem.models.User;
import uon.vehiclehiresystem.models.Vehicle;
import uon.vehiclehiresystem.utility.Utility;
import uon.vehiclehiresystem.views.miscellaneous.LabelDescriptionBox;

import javax.swing.*;
import java.awt.*;

public class FrameVehicleDetail extends JFrame{

    private PanelDescriptionBox panelDescriptionBox;
    private JLabel lblVehicleDetails = new LabelDescriptionBox("Vehicle Details");

    private JPanel panel = new JPanel(new GridBagLayout());

    public FrameVehicleDetail(){
        setSize(600, 800);  // Portrait view

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JScrollPane scrollPane = new JScrollPane(createNewPanel());
        add(scrollPane);
        setVisible(true);
    }

    private JPanel createNewPanel(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10 , 0 );

        lblVehicleDetails.setFont(Utility.FONT_BIG_HEADING);
        lblVehicleDetails.setSize(new Dimension(getWidth(), 20));
        panel.add(lblVehicleDetails, gbc);

        gbc.gridy = 1;

        // Last argument is access level i.e. Staff
        panelDescriptionBox = new PanelDescriptionBox(getHeight()/2 - 20, getWidth() - 50, User.TYPE_STAFF);
        panel.add(panelDescriptionBox, gbc);


        return panel;
    }

    public PanelDescriptionBox getPanelDescriptionBox() {
        return panelDescriptionBox;
    }

    public void setPanelDescriptionBox(PanelDescriptionBox panelDescriptionBox) {
        this.panelDescriptionBox = panelDescriptionBox;
    }

    public JLabel getLblVehicleDetails() {
        return lblVehicleDetails;
    }

    public void setLblVehicleDetails(JLabel lblVehicleDetails) {
        this.lblVehicleDetails = lblVehicleDetails;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}

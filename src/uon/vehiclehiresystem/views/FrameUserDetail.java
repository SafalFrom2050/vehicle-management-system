package uon.vehiclehiresystem.views;

import uon.vehiclehiresystem.models.HiredVehicle;
import uon.vehiclehiresystem.models.User;
import uon.vehiclehiresystem.utility.Utility;
import uon.vehiclehiresystem.views.miscellaneous.LabelDescriptionBox;

import javax.swing.*;
import java.awt.*;

public class FrameUserDetail extends JFrame {
    private PanelDescriptionBox panelDescriptionBox;

    private PanelListView panelListView;

    private JLabel lblAccountDetails = new LabelDescriptionBox("Account Details");

    private JPanel panel = new JPanel(new GridBagLayout());

    public FrameUserDetail(){
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

        lblAccountDetails.setFont(Utility.FONT_BIG_HEADING);
        lblAccountDetails.setSize(new Dimension(getWidth(), 20));
        panel.add(lblAccountDetails, gbc);

        gbc.gridy = 1;
        panelDescriptionBox = new PanelDescriptionBox(getHeight()/2 - 20, getWidth() - 50, User.TYPE_STAFF);
        panel.add(panelDescriptionBox, gbc);

        gbc.gridy = 2;
        panelListView = new PanelListView(getWidth() - 50, getHeight()/2, User.TYPE_STAFF);
        panelListView.setListType(HiredVehicle.TYPE_HIRED_VEHICLE);
        panel.add(panelListView, gbc);

        return panel;
    }


    public PanelDescriptionBox getPanelDescriptionBox() {
        return panelDescriptionBox;
    }

    public void setPanelDescriptionBox(PanelDescriptionBox panelDescriptionBox) {
        this.panelDescriptionBox = panelDescriptionBox;
    }

    public PanelListView getPanelListView() {
        return panelListView;
    }

    public void setPanelListView(PanelListView panelListView) {
        this.panelListView = panelListView;
    }

    public JLabel getLblAccountDetails() {
        return lblAccountDetails;
    }

    public void setLblAccountDetails(JLabel lblAccountDetails) {
        this.lblAccountDetails = lblAccountDetails;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}

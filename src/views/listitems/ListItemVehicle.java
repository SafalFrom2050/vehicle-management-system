package views.listitems;

import models.Vehicle;
import utility.Utility;

import javax.swing.*;
import java.awt.*;

public class ListItemVehicle extends JPanel {

    private Vehicle vehicle;

    private JLabel lblRegNumber = new JLabel(),
            lblMake = new JLabel(),
            lblModel = new JLabel();

    public ListItemVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
        createView();
    }

    public JPanel createView(){
        lblRegNumber.setText(Integer.toString(vehicle.getRegistrationNumber()));
        lblMake.setText(vehicle.getMake());
        lblModel.setText(vehicle.getModel());

        Font fontListItem = Utility.FONT_LIST_ITEM;

        lblRegNumber.setFont(fontListItem);
        lblMake.setFont(fontListItem);
        lblModel.setFont(fontListItem);

        setLayout(new GridLayout(1,10));
        add(lblRegNumber);
        add(lblMake);
        add(lblModel);
        this.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        return this;
    }
}

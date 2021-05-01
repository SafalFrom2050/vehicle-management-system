package uon.vehiclehiresystem.views.listitems;

import uon.vehiclehiresystem.models.Vehicle;
import uon.vehiclehiresystem.utility.Utility;

import javax.swing.*;
import java.awt.*;

public class ListItemVehicle extends JPanel {

    private Vehicle vehicle;

    private JLabel lblType = new JLabel(),
            lblMake = new JLabel(),
            lblModel = new JLabel();

    public ListItemVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
        createView();
    }

    public JPanel createView(){
        lblType.setText(vehicle.getStringType());
        lblMake.setText(vehicle.getMake());
        lblModel.setText(vehicle.getModel());

        Font fontListItem = Utility.FONT_LIST_ITEM;

        lblType.setFont(fontListItem);
        lblMake.setFont(fontListItem);
        lblModel.setFont(fontListItem);

        setLayout(new GridLayout(1,10));
        add(lblType);
        add(lblMake);
        add(lblModel);
        this.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        return this;
    }
}

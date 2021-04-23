package views.listitems;

import models.Customer;
import models.HiredVehicle;
import models.Vehicle;
import utility.Utility;

import javax.swing.*;
import java.awt.*;

public class ListItemHiredVehicle extends JPanel {

    private HiredVehicle hiredVehicle;


    private JLabel lblVehicleName = new JLabel(),
            lblCustomerName = new JLabel(),
            lblVehicleType = new JLabel(),
            lblStatus = new JLabel();

    public ListItemHiredVehicle(HiredVehicle hiredVehicle){
        this.hiredVehicle = hiredVehicle;

        createView();
    }

    public JPanel createView(){
        Vehicle vehicle = Vehicle.getVehicleWithRegNo(hiredVehicle.getVehicleRegNo());
        Customer customer = Customer.getUserWithUsername(hiredVehicle.getUsername());

        lblVehicleType.setText(vehicle.getStringType());
        lblVehicleName.setText(vehicle.getMake());
        lblCustomerName.setText(customer.getName());
        lblStatus.setText(hiredVehicle.getStringStatus());

        Font fontListItem = Utility.FONT_LIST_ITEM;

        lblVehicleType.setFont(fontListItem);
        lblVehicleName.setFont(fontListItem);
        lblCustomerName.setFont(fontListItem);
        lblStatus.setFont(fontListItem);

        setLayout(new GridLayout(1,10));
        add(lblCustomerName);
        add(lblVehicleType);
        add(lblVehicleName);
        add(lblStatus);
        this.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        return this;
    }


    public HiredVehicle getHiredVehicle() {
        return hiredVehicle;
    }

    public void setHiredVehicle(HiredVehicle hiredVehicle) {
        this.hiredVehicle = hiredVehicle;
    }

    public JLabel getLblVehicleName() {
        return lblVehicleName;
    }

    public void setLblVehicleName(JLabel lblVehicleName) {
        this.lblVehicleName = lblVehicleName;
    }

    public JLabel getLblCustomerName() {
        return lblCustomerName;
    }

    public void setLblCustomerName(JLabel lblCustomerName) {
        this.lblCustomerName = lblCustomerName;
    }

    public JLabel getLblVehicleType() {
        return lblVehicleType;
    }

    public void setLblVehicleType(JLabel lblVehicleType) {
        this.lblVehicleType = lblVehicleType;
    }
}

package uon.vehiclehiresystem.views.listitems;

import uon.vehiclehiresystem.models.User;
import uon.vehiclehiresystem.utility.Utility;

import javax.swing.*;
import java.awt.*;

public class ListItemUser extends JPanel {

    private User user;


    private JLabel lblUsername = new JLabel(),
            lblName = new JLabel(),
            lblType = new JLabel();

    public ListItemUser(User user){
        this.user = user;
        createView();
    }

    public JPanel createView(){

        lblUsername.setText(user.getUsername());
        lblName.setText(user.getName());
        if(user.getUserType() == User.TYPE_STAFF){
            lblType.setText("Staff");
        }else if(user.getUserType() == User.TYPE_CUSTOMER){
            lblType.setText("Customer");
        }

        Font fontListItem = Utility.FONT_LIST_ITEM;

        lblUsername.setFont(fontListItem);
        lblName.setFont(fontListItem);
        lblType.setFont(fontListItem);

        setLayout(new GridLayout(1,10));
        add(lblUsername);
        add(lblName);
        add(lblType);
        this.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        return this;
    }


}

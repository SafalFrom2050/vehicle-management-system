package uon.vehiclehiresystem.controllers;

import uon.vehiclehiresystem.models.Vehicle;
import uon.vehiclehiresystem.utility.Utility;
import uon.vehiclehiresystem.views.listitems.ListItemHiredVehicle;
import uon.vehiclehiresystem.views.listitems.ListItemUser;
import uon.vehiclehiresystem.views.listitems.ListItemVehicle;
import uon.vehiclehiresystem.models.HiredVehicle;
import uon.vehiclehiresystem.models.User;

import javax.swing.*;
import java.awt.*;

public class ListRenderer<T> extends JPanel implements ListCellRenderer {

    private String[] headings;
    public ListRenderer(String[] headings){
        this.headings = headings;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        removeAll();

        // Adding Heading If Start Of The ListView
        if(index == 0){
            setEnabled(false);
            JPanel panelHeading = getHeadingPanel(list.getWidth());
            this.add(panelHeading);
            this.setBackground(panelHeading.getBackground());
            return this;
        }

        T model = (T) value;
        if(model instanceof Vehicle){
            ListItemVehicle listItemVehicle;
            listItemVehicle = new ListItemVehicle((Vehicle) model);
            this.add(listItemVehicle);
        }else if(model instanceof User){
            ListItemUser listItemUser = new ListItemUser((User) model);
            this.add(listItemUser);
        }else if(model instanceof HiredVehicle) {
            ListItemHiredVehicle listItemHiredVehicle = new ListItemHiredVehicle((HiredVehicle) model);
            this.add(listItemHiredVehicle);
        }

        this.getComponent(0).setPreferredSize(new Dimension(list.getWidth(), 35));

        if(isSelected){
            setBackground(list.getSelectionBackground());
            this.getComponent(0).setBackground(list.getSelectionBackground());

            setForeground(list.getSelectionForeground());
            this.getComponent(0).setForeground(list.getSelectionForeground());
        }else{
            setBackground(list.getBackground());
            this.getComponent(0).setBackground(list.getBackground());

            setForeground(list.getForeground());
            this.getComponent(0).setForeground(list.getForeground());
        }

        setEnabled(true);
        setFont(list.getFont());

        return this;
    }

    private JPanel getHeadingPanel(int width){
        JPanel panelHeading = new JPanel(new GridLayout(1,headings.length));
        Font fontListHeading = Utility.FONT_LIST_HEADING;

        for (String heading:headings) {
            JLabel lbl = new JLabel(heading);
            lbl.setFont(fontListHeading);
            panelHeading.add(lbl);
        }

        panelHeading.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        panelHeading.setPreferredSize(new Dimension(width, 35));
        return panelHeading;
    }
}

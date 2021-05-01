package uon.vehiclehiresystem.views.miscellaneous;

import uon.vehiclehiresystem.utility.Utility;

import javax.swing.*;

public class LabelDescriptionBox extends JLabel {

    public LabelDescriptionBox(String s){
        super(s);
        loadProperties();
    }

    public LabelDescriptionBox(){
        loadProperties();
    }

    private void loadProperties(){
        this.setFont(Utility.FONT_DESCRIPTION_LABEL);
    }
}

package views.miscellaneous;

import utility.Utility;

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

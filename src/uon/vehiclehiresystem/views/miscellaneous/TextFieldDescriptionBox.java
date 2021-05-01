package uon.vehiclehiresystem.views.miscellaneous;

import uon.vehiclehiresystem.utility.Utility;

import javax.swing.*;

public class TextFieldDescriptionBox extends JTextField {

    public TextFieldDescriptionBox(String s){
        super(s);
        loadProperties();
    }

    public TextFieldDescriptionBox(){
        loadProperties();
    }

    private void loadProperties(){
        this.setFont(Utility.FONT_DESCRIPTION_TEXTFIELD);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setColumns(10);

        // Set tooltip text to textfield text
        this.setToolTipText(getText());
    }

    @Override
    public void setText(String t) {
        super.setText(t);

        // Set tooltip text to textfield text
        this.setToolTipText(getText());
    }
}

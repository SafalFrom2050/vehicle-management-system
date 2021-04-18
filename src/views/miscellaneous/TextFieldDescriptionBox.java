package views.miscellaneous;

import utility.Utility;

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
    }
}

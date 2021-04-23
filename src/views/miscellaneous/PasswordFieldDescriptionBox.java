package views.miscellaneous;

import utility.Utility;

import javax.swing.*;

public class PasswordFieldDescriptionBox extends JPasswordField {
    public PasswordFieldDescriptionBox(String s){
        super(s);
        loadProperties();
    }

    public PasswordFieldDescriptionBox(){
        loadProperties();
    }

    private void loadProperties(){
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setColumns(10);
    }
}

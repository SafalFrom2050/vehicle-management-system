package utility;

import models.*;

import javax.swing.*;

public class Validator {

    /**
     * Validate if textfield contains some text and is not null
     *
     * @param textField: Textfield containing text
     * @return Validation result(true:pass / false:fail)
     */
    public static boolean validateNotNull(JTextField textField){

        // Check if null
        return !textField.getText().equals("");
    }

    /**
     * Validate if textfield contains only number
     *
     * @param textField: Textfield containing text
     * @return Validation result(true:pass / false:fail)
     */
    public static boolean validateIsNumber(JTextField textField){
        // Check if text not null
        if(!validateNotNull(textField)) return false;

        String text = textField.getText();
        try{
            int num = Integer.parseInt(text);
        }catch (NumberFormatException e){
            return false;
        }

        // Validation Complete!
        return true;
    }


    /**
     * Validate if textfield contains unique username
     *
     * @param textField: Textfield containing text
     * @return Validation result(true:pass / false:fail)
     */
    public static boolean validateUniqueUsername(JTextField textField){
        // Check if text not null
        if(!validateNotNull(textField)) return false;

        String username = textField.getText();

        // Check if any customer with same username exists
        if(Customer.getUserWithUsername(username) != null) return false;

        // If not found, check if any staff with the username exists
        if(Staff.getUserWithUsername(username) != null) return false;

        // Validation Complete!
        return true;
    }


    /**
     * Validate if textfield contains unique registration number
     *
     * @param textField: Textfield containing text
     * @return Validation result(true:pass / false:fail)
     */
    public static boolean validateUniqueRegNo(JTextField textField){

        // Check if text not null
        if(!validateNotNull(textField)) return false;
        // Check if contains number
        if(!validateIsNumber(textField)) return false;

        int regNo = Integer.parseInt(textField.getText());

        // Finally check if vehicle with same registration number exists
        if(Vehicle.getVehicleWithRegNo(regNo) != null) return false;


        // Validation Complete!
        return true;
    }

}

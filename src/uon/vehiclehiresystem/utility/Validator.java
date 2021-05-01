package uon.vehiclehiresystem.utility;

import uon.vehiclehiresystem.models.Customer;
import uon.vehiclehiresystem.models.Staff;
import uon.vehiclehiresystem.models.Vehicle;

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
        return validateNotNull(textField.getText());
    }
    public static boolean validateNotNull(String s){

        // Check if null
        if(s==null) return false;

        // Check if empty string
        return !s.equals("");
    }

    /**
     * Validate if textfield contains only number
     *
     * @param textField: Textfield containing text
     * @return Validation result(true:pass / false:fail)
     */
    public static boolean validateIsNumber(JTextField textField){

        return validateIsNumber(textField.getText());
    }
    public static boolean validateIsNumber(String s){
        // Check if text not null
        if(!validateNotNull(s)) return false;

        try{
            int num = Integer.parseInt(s);
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
        return Staff.getUserWithUsername(username) == null;
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
        return Vehicle.getVehicleWithRegNo(regNo) == null;

        // Validation Complete!
    }

}

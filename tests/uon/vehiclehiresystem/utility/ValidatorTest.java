package uon.vehiclehiresystem.utility;

import org.junit.jupiter.api.Test;
import uon.vehiclehiresystem.models.Customer;
import uon.vehiclehiresystem.models.Staff;
import uon.vehiclehiresystem.models.User;
import uon.vehiclehiresystem.models.Vehicle;

import javax.swing.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    private JTextField txtTest = new JTextField();

    @Test
    void validateNotNull() {
        // Check for true
        txtTest.setText("132");
        boolean result = Validator.validateNotNull(txtTest);
        assertTrue(result);

        // Check for false
        txtTest.setText("");
        result = Validator.validateNotNull(txtTest);
        assertFalse(result);
    }

    @Test
    void validateIsNumber() {
        // Check for true
        txtTest.setText("1232");
        boolean result = Validator.validateIsNumber(txtTest);
        assertTrue(result);

        // Check for false
        txtTest.setText("aa1232dsd");
        result = Validator.validateIsNumber(txtTest);
        assertFalse(result);
    }

    @Test
    void validateUniqueUsername() throws FileNotFoundException {
        // Check for true

        // Staff id of largest integer value to prevent clash with the system data
        Staff testStaff = new Staff(2147483647, "testName", "testUsername", "testPassword");
        testStaff.create();

        txtTest.setText("random");
        boolean result = Validator.validateUniqueUsername(txtTest);
        assertTrue(result);

        // Check for false
        txtTest.setText("testUsername");
        result = Validator.validateUniqueUsername(txtTest);
        assertFalse(result);


        // Clear test staff from data
        testStaff.delete();
    }

    @Test
    void validateUniqueRegNo() {
        // Check for true

        // Reg no. of largest integer value to prevent clash with the system data
        Vehicle vehicle = new Vehicle();
        vehicle.setRegistrationNumber(2147483647);
        vehicle.setType(Vehicle.TYPE_CAR);

        txtTest.setText("2147483647");
        boolean result = Validator.validateUniqueRegNo(txtTest);
        assertTrue(result);

        // Check for false
        txtTest.setText("random");
        result = Validator.validateUniqueRegNo(txtTest);
        assertFalse(result);


        // Clear test vehicle from data
        vehicle.delete();
    }
}
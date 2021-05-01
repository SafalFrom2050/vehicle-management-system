package uon.vehiclehiresystem.models;

import uon.vehiclehiresystem.utility.FileHandler;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Staff extends User implements Serializable {
    private static final long serialVersionUID = 144341031459673221L;

    private static final String FILE_NAME = "staff.dat";

    private int staffID;

    public Staff(){
    }

    public Staff(String username){
        super(username);
    }

    public Staff(int staffID, String name, String username, String password){
        super(name, username, password);
        this.staffID = staffID;
        setUserType(TYPE_STAFF);
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public static List<Staff> getDummyList(){
        List<Staff> staffList = new ArrayList<>();

        staffList.add(new Staff(1, "Safal", "safal", "password"));
        staffList.add(new Staff(1, "Test", "Test 1", "password"));
        staffList.add(new Staff(1, "Test", "Test 2", "password"));
        staffList.add(new Staff(1, "Test", "Test 3", "password"));

        return staffList;

    }

    public void create() throws FileNotFoundException {
        FileHandler<Staff> fileHandler = new FileHandler<Staff>();

        fileHandler.writeObject(this, FILE_NAME, true);
    }

    public List getUsersList(){
        FileHandler<Staff> fileHandler = new FileHandler<Staff>();
        List users = fileHandler.readObjects(FILE_NAME);

        return users;
    }

    public boolean delete() {
        FileHandler<Staff> fileHandler = new FileHandler<Staff>();
       return fileHandler.deleteMatchingObject(FILE_NAME, this);
    }

    public static Staff getUserWithUsername(String username){
        FileHandler<Staff> fileHandler = new FileHandler<Staff>();
        Staff user = fileHandler.findFirstMatchingObject(FILE_NAME, new Staff(-1,"dummyName", username,"dummyPassword"));

        return user;
    }
}

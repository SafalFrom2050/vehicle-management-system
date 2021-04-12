package models;

import utility.FileHandler;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Staff extends User implements Serializable {
    private static final long serialVersionUID = 144341031459673221L;

    private static final String FILE_NAME = "staff.dat";

    private int staffID;

    public Staff(int staffID, String username, String password){
        super(username, password);
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public static List<Staff> getDummyList(){
        List<Staff> staffList = new ArrayList<>();

        staffList.add(new Staff(1, "Safal", "password"));
        staffList.add(new Staff(2, "Mathew", "password"));
        staffList.add(new Staff(3, "Harry", "password"));
        staffList.add(new Staff(4, "Yoda", "password"));
        staffList.add(new Staff(5, "Batman", "password"));

        return staffList;

    }

    public void createUser() throws FileNotFoundException {
        FileHandler<Staff> fileHandler = new FileHandler<Staff>();

        fileHandler.writeObject(this, FILE_NAME, true);
    }

    public List getUsersList(){
        FileHandler<Staff> fileHandler = new FileHandler<Staff>();
        List users = fileHandler.readObjects(FILE_NAME);

        return users;
    }

    public void delete() {
        FileHandler<Staff> fileHandler = new FileHandler<Staff>();
        boolean delete = fileHandler.deleteFirstMatchingObject(FILE_NAME, this);

        if(delete){
            System.out.println("Delete Success!");
        }else{
            System.out.println("Delete Failed!");
        }
    }

    public User getUserWithUsername(String username){
        FileHandler<Staff> fileHandler = new FileHandler<Staff>();
        Staff user = fileHandler.findFirstMatchingObject(FILE_NAME, new Staff(-1, username, "dummy"));

        System.out.println(user.getUsername());

        return user;
    }
}

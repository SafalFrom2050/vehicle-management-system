package models;

import utility.FileHandler;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.List;

public class Customer extends User implements Serializable {

    private static final long serialVersionUID = 144341031459673221L;

    private static final String FILE_NAME = "customers.dat";

    private String name, address, phoneNumber, email;

    private int identificationNumber;

    public Customer(String username){
        super(username);
    }
    public Customer(String username, String password){
        super(username, password);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(int identificationNumber) {
        this.identificationNumber = identificationNumber;
    }


    public void createUser() throws FileNotFoundException {
        FileHandler<Customer> fileHandler = new FileHandler<Customer>();

        fileHandler.writeObject(this, FILE_NAME, true);
    }

    public List getUsersList(){
        FileHandler<Customer> fileHandler = new FileHandler<Customer>();
        List users = fileHandler.readObjects(FILE_NAME);

        return users;
    }

    public void delete() {
        FileHandler<Customer> fileHandler = new FileHandler<Customer>();
        boolean delete = fileHandler.deleteFirstMatchingObject(FILE_NAME, this);

        if(delete){
            System.out.println("Delete Success!");
        }else{
            System.out.println("Delete Failed!");
        }
    }

    public User getUserWithUsername(String username){
        FileHandler<Customer> fileHandler = new FileHandler<Customer>();
        Customer user = fileHandler.findFirstMatchingObject(FILE_NAME, new Customer(username));

        System.out.println(user.getUsername());

        return user;
    }
}

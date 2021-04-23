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

    public Customer(){

    }

    public Customer(String username){
        super(username);
        setUserType(TYPE_CUSTOMER);
    }

    public Customer(String name, String username, String password){
        super(name, username, password);
        setUserType(TYPE_CUSTOMER);
    }

    public Customer(int identificationNumber, String name, String username, String password, String phoneNumber,
                    String address, String email){
        super(name, username, password);

        this.identificationNumber = identificationNumber;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        setUserType(TYPE_CUSTOMER);
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


    public void create() throws FileNotFoundException {
        FileHandler<Customer> fileHandler = new FileHandler<Customer>();

        fileHandler.writeObject(this, FILE_NAME, true);
    }

    public List getUsersList(){
        FileHandler<Customer> fileHandler = new FileHandler<Customer>();
        List users = fileHandler.readObjects(FILE_NAME);

        return users;
    }

    public boolean delete() {
        FileHandler<Customer> fileHandler = new FileHandler<Customer>();
        return fileHandler.deleteMatchingObject(FILE_NAME, this);
    }

    public static Customer getUserWithUsername(String username){
        FileHandler<Customer> fileHandler = new FileHandler<Customer>();
        Customer user = fileHandler.findFirstMatchingObject(FILE_NAME, new Customer(username));

        return user;
    }
}

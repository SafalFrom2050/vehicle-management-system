package models;

import java.io.*;

public class User implements Serializable {

    public static final int TYPE_USER = 2000, TYPE_STAFF = 2001, TYPE_CUSTOMER = 2002;

    private static final long serialVersionUID = 7079889566089012613L;

    private String name, username, password;

    private int userType;

    public User(){

    }

    public User(String name, String username, String password){
        this.name = name;
        this.username = username.toLowerCase();
        this.password = password;
    }
    public User(String username){
        this.username = username.toLowerCase();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username.toLowerCase();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getStringUserType(){
        if(userType == TYPE_STAFF){
            return "Staff";
        }else if(userType == TYPE_CUSTOMER){
            return "Customer";
        }else{
            return null;
        }
    }

    public boolean delete(){
        if((new Customer(this.username)).delete()){
           return true;
        }else if((new Staff(this.username)).delete()){
            return true;
        }

        return false;
    }

    // Overriding 'equals' method to compare username
    @Override
    public boolean equals(Object obj) {
        User user = (User) obj;
        return (username.equalsIgnoreCase(user.getUsername()));
    }
}

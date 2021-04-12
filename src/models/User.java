package models;

import java.io.*;

public class User implements Serializable {

    public static final int TYPE_STAFF = 2001, TYPE_CUSTOMER = 2002;

    private static final long serialVersionUID = 7079889566089012613L;

    private String username, password;

    private int userType;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
    public User(String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    // Overriding 'equals' method to compare username
    @Override
    public boolean equals(Object obj) {
        User user = (User) obj;
        return (username.equals(user.username));
    }
}

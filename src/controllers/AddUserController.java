package controllers;

import models.Customer;
import models.Staff;
import models.User;
import views.FrameAddUser;
import views.miscellaneous.Messages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;

public class AddUserController implements ActionListener {

    private FrameAddUser frameAddUser;

    private Staff staff;
    private Customer customer;

    private int type;

    private WindowListener windowListener;

    // TODO: Usage of model to load default data

    public AddUserController(FrameAddUser frameAddUser, Customer customer){
        this.frameAddUser = frameAddUser;
        this.customer = customer;

        this.type = User.TYPE_CUSTOMER;

        setListeners();
    }

    public AddUserController(FrameAddUser frameAddUser, Staff staff){
        this.frameAddUser = frameAddUser;
        this.staff = staff;

        this.type = User.TYPE_STAFF;

        setListeners();
    }

    public void addWindowListener(WindowListener windowListener){
        this.windowListener = windowListener;
        frameAddUser.addWindowListener(windowListener);
    }

    private void setListeners(){
        frameAddUser.getBtnSubmit().addActionListener(this);
    }

    private void addToDB() throws FileNotFoundException {
        if(type == User.TYPE_STAFF){
            int staffID = Integer.parseInt(frameAddUser.getTxtStaffID().getText());
            String name = frameAddUser.getTxtName().getText();
            String username = frameAddUser.getTxtUsername().getText();
            String passsword = frameAddUser.getTxtPassword().getText();

            staff = new Staff(staffID, name, username, passsword);

            // TODO: Validate
            staff.create();
        }else if(type == User.TYPE_CUSTOMER){
            int customerID = Integer.parseInt(frameAddUser.getTxtCustomerID().getText());
            String name = frameAddUser.getTxtName().getText();
            String username = frameAddUser.getTxtUsername().getText();
            String password = frameAddUser.getTxtPassword().getText();
            String phoneNumber = frameAddUser.getTxtPhoneNumber().getText();
            String address = frameAddUser.getTxtAddress().getText();
            String email = frameAddUser.getTxtEmail().getText();

            customer = new Customer(customerID, name, username, password, phoneNumber, address, email);

            // TODO: Validate
            customer.create();
        }else{
            return;
        }

        Messages.showMessage("Added Successfully!", frameAddUser);
        frameAddUser.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        if(source == frameAddUser.getBtnSubmit()){
            try {
                addToDB();
            } catch (FileNotFoundException ex) {
                Messages.showErrorMessage("Couldn't create user!", frameAddUser);
            }
        }

    }
}

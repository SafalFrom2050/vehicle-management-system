package uon.vehiclehiresystem.controllers;

import uon.vehiclehiresystem.models.Customer;
import uon.vehiclehiresystem.models.Staff;
import uon.vehiclehiresystem.models.User;
import uon.vehiclehiresystem.utility.Validator;
import uon.vehiclehiresystem.views.FrameAddUser;
import uon.vehiclehiresystem.views.miscellaneous.Messages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;

public class AddUsersController implements ActionListener {

    private FrameAddUser frameAddUser;

    private Staff staff;
    private Customer customer;

    private int type;

    private WindowListener windowListener;


    public AddUsersController(FrameAddUser frameAddUser, Customer customer){
        this.frameAddUser = frameAddUser;
        this.customer = customer;

        this.type = User.TYPE_CUSTOMER;

        setListeners();
    }

    public AddUsersController(FrameAddUser frameAddUser, Staff staff){
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

        if(!validateForm()){
            return;
        }

        if(type == User.TYPE_STAFF){
            int staffID = Integer.parseInt(frameAddUser.getTxtStaffID().getText());
            String name = frameAddUser.getTxtName().getText();
            String username = frameAddUser.getTxtUsername().getText();
            String passsword = frameAddUser.getTxtPassword().getText();

            staff = new Staff(staffID, name, username, passsword);

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

            customer.create();
        }else{
            return;
        }

        Messages.showMessage("Added Successfully!", frameAddUser);
        windowListener.windowClosing(null);
        frameAddUser.dispose();
    }

    private boolean validateForm(){
        boolean isUnfinished = false;

        if(!Validator.validateUniqueUsername(frameAddUser.getTxtUsername())){
            Messages.showErrorMessage(frameAddUser.getLblUsername().getText() +
                    " must be unique!", frameAddUser);
            isUnfinished = true;
        }

        if(!Validator.validateNotNull(frameAddUser.getTxtPassword())){
            Messages.showErrorMessage(frameAddUser.getLblPassword().getText() +
                    " is missing!", frameAddUser);
            isUnfinished = true;
        }

        if(!Validator.validateNotNull(frameAddUser.getTxtName())){
            Messages.showErrorMessage(frameAddUser.getLblName().getText() +
                    " is missing!", frameAddUser);
            isUnfinished = true;
        }

        // Staff specific properties
        if(type == User.TYPE_STAFF && !Validator.validateIsNumber(frameAddUser.getTxtStaffID())){
            Messages.showErrorMessage(frameAddUser.getLblStaffID().getText() +
                    " is missing!", frameAddUser);
            isUnfinished = true;
        }

        // Validation steps completed for staff
        if(type == User.TYPE_STAFF) return !isUnfinished;

        // Customer specific properties

        if(!Validator.validateIsNumber(frameAddUser.getTxtCustomerID())){
            Messages.showErrorMessage(frameAddUser.getLblCustomerID().getText() +
                    " is missing!", frameAddUser);
            isUnfinished = true;
        }

        if(!Validator.validateNotNull(frameAddUser.getTxtAddress())){
            Messages.showErrorMessage(frameAddUser.getLblAddress().getText() +
                    " is missing!", frameAddUser);
            isUnfinished = true;
        }

        if(!Validator.validateNotNull(frameAddUser.getTxtPhoneNumber())){
            Messages.showErrorMessage(frameAddUser.getLblPhoneNumber().getText() +
                    " is missing!", frameAddUser);
            isUnfinished = true;
        }

        if(!Validator.validateNotNull(frameAddUser.getTxtEmail())){
            Messages.showErrorMessage(frameAddUser.getLblEmail().getText() +
                    " is missing!", frameAddUser);
            isUnfinished = true;
        }

        return !isUnfinished;
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

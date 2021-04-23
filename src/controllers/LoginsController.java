package controllers;

import models.Customer;
import models.Staff;
import models.User;
import utility.Utility;
import views.FrameCustomerPortal;
import views.FrameLogin;
import views.FrameStaffPortal;
import views.miscellaneous.Messages;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginsController implements ActionListener {

    private FrameLogin frameLogin;

    private User user;

    public LoginsController(FrameLogin frameLogin, User user){
        this.frameLogin = frameLogin;
        this.user = user;

        setDataToGUI();
        setActionListeners();
    }

    private void setDataToGUI(){
        frameLogin.getTxtUsername().setText(user.getUsername());
    }

    private void setActionListeners(){
        frameLogin.getBtnLogin().addActionListener(this);

        setKeyBindings();
    }

    private void setKeyBindings(){
        frameLogin.getBtnLogin().getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "ENTER");
        frameLogin.getBtnLogin().getActionMap().put("ENTER", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkLogin();
            }
        });
    }

    private void checkLogin(){
        String username = frameLogin.getTxtUsername().getText();
        String password = frameLogin.getTxtPassword().getText();

        // Check in customer list
        if((user = Customer.getUserWithUsername(username)) != null){
            if(user.getPassword().equals(password)){
                CustomerPortalController cpc = new CustomerPortalController(new FrameCustomerPortal(), new Customer());
                cpc.loadFrame();

                Utility.username = username;
                frameLogin.dispose();
                return;
            }

            // Display error message if password do not match
            Messages.showErrorMessage("Password incorrect!", frameLogin);
            return;
        }else if((user = Staff.getUserWithUsername(username)) != null){
            // Check in staff list
            if(user.getPassword().equals(password)){
                StaffPortalController staffPortalController = new StaffPortalController(new FrameStaffPortal(), new Staff());
                staffPortalController.loadFrame();

                Utility.username = username;
                frameLogin.dispose();
                return;
            }
            // Display error message if password do not match
            Messages.showErrorMessage("Password incorrect!", frameLogin);
            return;
        }

        // Display error message if username not found
        Messages.showErrorMessage("No user found!", frameLogin);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == frameLogin.getBtnLogin()){
            checkLogin();
        }
    }
}

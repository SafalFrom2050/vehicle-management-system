package uon.vehiclehiresystem;

import uon.vehiclehiresystem.controllers.LoginsController;
import uon.vehiclehiresystem.models.*;
import uon.vehiclehiresystem.views.FrameLogin;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        new LoginsController(new FrameLogin(), new User());
    }
}

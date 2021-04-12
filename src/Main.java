import controllers.StaffPortalController;
import views.FrameLogin;
import views.FrameStaffPortal;

public class Main {

    public static void main(String[] args) {

        StaffPortalController staffPortalController = new StaffPortalController(new FrameStaffPortal(),null);
        staffPortalController.loadFrame();
    }
}

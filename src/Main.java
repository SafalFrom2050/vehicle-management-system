import controllers.LoginsController;
import models.User;
import views.FrameLogin;

public class Main {

    public static void main(String[] args) {
        LoginsController loginsController = new LoginsController(new FrameLogin(), new User());
    }
}

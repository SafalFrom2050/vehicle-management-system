package uon.vehiclehiresystem.utility;

import org.junit.jupiter.api.Test;
import uon.vehiclehiresystem.models.Car;
import uon.vehiclehiresystem.models.User;
import uon.vehiclehiresystem.models.Vehicle;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {

    private final String TEST_DATA_FILE_NAME = "tests.dat";

    @Test
    void writeObject() throws FileNotFoundException {
        User user = new User("test");
        FileHandler<User> fileHandler = new FileHandler<>();
        fileHandler.writeObject(user, TEST_DATA_FILE_NAME, false);
    }

    @Test
    void readObjects() {
        FileHandler<User> fileHandler = new FileHandler<>();
        User user = fileHandler.readObjects(TEST_DATA_FILE_NAME).get(0);

        assertEquals("test", user.getUsername());
    }

    @Test
    void findFirstMatchingObject() {
        FileHandler<User> fileHandler = new FileHandler<>();
        User user1 = new User("test");
        User user2 = fileHandler.findFirstMatchingObject(TEST_DATA_FILE_NAME, user1);

        assertEquals("test", user2.getUsername());
    }

    @Test
    void deleteMatchingObject() {
        User user = new User("test");
        FileHandler<User> fileHandler = new FileHandler<>();
        fileHandler.deleteMatchingObject(TEST_DATA_FILE_NAME, user);
    }

    @Test
    void checkIfDeletedObjectExists(){
        User user = new User("test");
        FileHandler<User> fileHandler = new FileHandler<>();
        fileHandler.deleteMatchingObject(TEST_DATA_FILE_NAME, user);

        // Now check if object exists
        fileHandler = new FileHandler<>();
        User user1 = new User("test");
        User user2 = fileHandler.findFirstMatchingObject(TEST_DATA_FILE_NAME, user1);

        assertNull(user2);
    }
}
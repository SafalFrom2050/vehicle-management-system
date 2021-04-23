package utility;

import java.awt.*;
import java.io.File;

public class Utility {

    public final static String DATA_DIR = "data/";
    public final static Color BUTTON_SELECTED_COLOR = Color.decode("#F1F7ED");

    public final static String[] HEADINGS_VEHICLES = {"Reg. No:", "Make", "Model"};
    public final static String[] HEADINGS_USERS = {"Username", "Name", "Type"};
    public final static String[] HEADINGS_HIRED_VEHICLES = {"Customer", "Type", "Make", "Status"};

    public static Color button_default_color;

    public static Font FONT_LIST_HEADING = new Font(null, Font.BOLD, 12);
    public static Font FONT_LIST_ITEM = new Font(null, Font.PLAIN, 16);
    public static Font FONT_DESCRIPTION_LABEL = new Font(null, Font.PLAIN, 14);
    public static Font FONT_DESCRIPTION_TEXTFIELD = new Font(null, Font.PLAIN, 14);

    public static String username;

    public static boolean createDirectory(String dir){
        File file = new File(dir);
        return file.mkdir();
    }

    public static boolean doesDirExist(String dir){
        File file = new File(dir);
        return file.exists();
    }
}

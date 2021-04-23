package views.miscellaneous;

import javax.swing.*;

public class Messages {

    public static void showMessage(String s, JFrame frame){
        JOptionPane.showMessageDialog(frame, s);
    }

    public static void showErrorMessage(String s, JFrame frame){
        JOptionPane optionPane = new JOptionPane(s, JOptionPane.ERROR_MESSAGE);

        JDialog dialog = optionPane.createDialog("Error!");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

    public static int showConfirmationMessage(String title, String body, JFrame frame){
        // Show confirmation message
        return JOptionPane.showConfirmDialog(frame,
                body, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    }
}

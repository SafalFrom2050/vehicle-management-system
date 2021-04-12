package views;

import javax.swing.*;
import java.awt.*;

public class FrameLogin extends JFrame {

    private JPanel panel;

    private JTextField username;
    private JPasswordField password;

    private JLabel labelTitle, labelUsername, labelPassword;

    private JButton btnLogin;


    public FrameLogin(){

        setSize(400, 300);          // 4:3 aspect ratio (required on Top to create panel)
        panel = createNewLoginPanel();
        add(panel);

        setTitle("Vehicle Hiring System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
        setVisible(true);

        new FrameStaffPortal();
    }

    public JPanel createNewLoginPanel(){
        JPanel parentPanel = new JPanel();
        JPanel panel = new JPanel(new GridBagLayout());

        username = new JTextField();
        username.setPreferredSize(new Dimension(100, 25));
        password = new JPasswordField();
        password.setPreferredSize(new Dimension(100, 25));

        labelTitle = new JLabel("Staff Login");
        labelTitle.setPreferredSize(new Dimension(this.getWidth(), 80));
        labelTitle.setHorizontalAlignment(SwingConstants.CENTER);

        labelTitle.setFont(new Font(null, Font.BOLD, 26));
        labelPassword = new JLabel("Password");
        labelUsername = new JLabel("Username");

        btnLogin = new JButton("Login");

        parentPanel.add(labelTitle);



        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(8,8,8,8);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(labelUsername, gbc);
        gbc.gridx = 1;
        panel.add(username, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(labelPassword, gbc);
        gbc.gridx = 1;
        panel.add(password, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(20, 8, 8, 8);
        panel.add(btnLogin, gbc);

        parentPanel.add(panel);

        return parentPanel;
    }
}

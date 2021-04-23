package views;

import javax.swing.*;
import java.awt.*;

public class FrameLogin extends JFrame {

    private JPanel panel;

    private JTextField txtUsername;
    private JPasswordField txtPassword;

    private JLabel lblTitle, lblUsername, lblPassword;

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

    private JPanel createNewLoginPanel(){
        JPanel parentPanel = new JPanel();
        JPanel panel = new JPanel(new GridBagLayout());

        txtUsername = new JTextField();
        txtUsername.setPreferredSize(new Dimension(100, 25));
        txtPassword = new JPasswordField();
        txtPassword.setPreferredSize(new Dimension(100, 25));

        lblTitle = new JLabel("Login To Your Account");
        lblTitle.setPreferredSize(new Dimension(this.getWidth(), 80));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

        lblTitle.setFont(new Font(null, Font.BOLD, 26));
        lblPassword = new JLabel("Password");
        lblUsername = new JLabel("Username");

        btnLogin = new JButton("Login");

        parentPanel.add(lblTitle);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(8,8,8,8);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblUsername, gbc);
        gbc.gridx = 1;
        panel.add(txtUsername, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblPassword, gbc);
        gbc.gridx = 1;
        panel.add(txtPassword, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(20, 8, 8, 8);
        panel.add(btnLogin, gbc);

        parentPanel.add(panel);

        return parentPanel;
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public void setBtnLogin(JButton btnLogin) {
        this.btnLogin = btnLogin;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public void setTxtUsername(JTextField txtUsername) {
        this.txtUsername = txtUsername;
    }

    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    public void setTxtPassword(JPasswordField txtPassword) {
        this.txtPassword = txtPassword;
    }

    public JLabel getLblTitle() {
        return lblTitle;
    }

    public void setLblTitle(JLabel lblTitle) {
        this.lblTitle = lblTitle;
    }

    public JLabel getLblUsername() {
        return lblUsername;
    }

    public void setLblUsername(JLabel lblUsername) {
        this.lblUsername = lblUsername;
    }

    public JLabel getLblPassword() {
        return lblPassword;
    }

    public void setLblPassword(JLabel lblPassword) {
        this.lblPassword = lblPassword;
    }
}

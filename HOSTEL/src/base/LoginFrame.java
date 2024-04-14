package base;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class LoginFrame extends JFrame implements ActionListener {

    private JLabel userLabel;
    private JLabel passLabel;
    private JTextField userTextField;
    private JPasswordField passTextField;
    private JButton loginButton;

    public LoginFrame() {
        setTitle("Hostel Management System - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);

        userLabel = new JLabel("Username: ");
        passLabel = new JLabel("Password: ");
        userTextField = new JTextField(20);
        passTextField = new JPasswordField(20);
        loginButton = new JButton("Login");

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(10, 10, 10, 10);
        add(userLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        add(userTextField, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        add(passLabel, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        add(passTextField, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        add(loginButton, gc);

        loginButton.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = userTextField.getText();
            String password = new String(passTextField.getPassword());

            if (isValidUser(username, password)) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                dispose();
                // close the login frame
                // create and display the mainframe
//                MainFrame mainFrame = new MainFrame();
//                mainFrame.setVisible(true);
                UserMenu userMenu = new UserMenu();
                userMenu.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.");
            }
        }
    }

    private boolean isValidUser(String username, String password) {
        try {
            // Get a connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");

            // Create a statement object
            Statement statement = connection.createStatement();

            // Execute a query to check if the username and password exist
            String query = "SELECT * FROM `login` WHERE username='" + username + "' AND Password='" + password + "'";
            ResultSet resultSet = statement.executeQuery(query);

            // Check if the result set is not empty
            if (resultSet.next()) {

                return true;
            }

            // User is not valid, return false
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        LoginFrame loginFrame = new LoginFrame();
    }

}
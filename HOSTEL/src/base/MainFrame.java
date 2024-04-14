package base;
import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Hostel Management System - Main");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Create a JPanel for the main content and add it to the content pane
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false); // Set the panel as transparent

        // Create a JLabel with the background image and add the main panel to it
        ImageIcon backgroundImage = new ImageIcon("src/Images/mainbg.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.insets = new Insets(0, 0, 100, 0); // Add some padding to the button
        backgroundLabel.add(mainPanel, gbc);

        // Create a label for the welcome message and add it to the main panel
        JLabel welcomeLabel = new JLabel("Welcome to Hostel Management System");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Set the font and size
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER); // Center the label text
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);

        // Create the login button and add it to the main panel
        JButton loginButton = new JButton("LOGIN");
        loginButton.setPreferredSize(new Dimension(200, 100)); // Set the size of the button
        loginButton.addActionListener(e -> {
            // When the login button is clicked, open the LoginFrame
            dispose();
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
        mainPanel.add(loginButton, BorderLayout.CENTER);

        // Add the background label to the content pane
        getContentPane().add(backgroundLabel);

        // Set the visibility of the frame to true
        setVisible(true);
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
    }

}


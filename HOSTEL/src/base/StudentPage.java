package base;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentPage extends JFrame {
    private JButton insertButton;
    private JButton viewButton;
    private JButton deleteButton;
    private JButton homeButton;

    public StudentPage() {
        // set frame properties
        setTitle("Student Page");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create panel for buttons
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 10, 10, 10); // add padding

        // create buttons
        insertButton = new JButton("Insert");
        viewButton = new JButton("View All");
        deleteButton = new JButton("Delete");
        homeButton = new JButton("Home");

        // add buttons to panel
        buttonPanel.add(insertButton, c);
        c.gridx++;
        buttonPanel.add(viewButton, c);
        c.gridy++;
        c.gridx = 0;
        buttonPanel.add(deleteButton, c);
        c.gridx++;
        buttonPanel.add(homeButton, c);

        // add panel to frame
        add(buttonPanel);

        // add action listeners to buttons
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                StudentReg studentreg = new StudentReg();
                studentreg.setVisible(true);
                dispose();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                StudentInfoFrame stf = new StudentInfoFrame();
                stf.setVisible(true);
                dispose();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                StudentDeletionForm sd = new StudentDeletionForm();
                sd.setVisible(true);
                dispose();
            }
        });

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                UserMenu us = new UserMenu();
                us.setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        StudentPage studentPage = new StudentPage();
        studentPage.setVisible(true);
    }
}
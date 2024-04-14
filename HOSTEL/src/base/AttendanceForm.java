package base;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;

public class AttendanceForm extends JFrame implements ActionListener {
    JLabel studentIdLabel, totalDaysLabel, presentDaysLabel, absentDaysLabel;
    JComboBox<String> studentIdComboBox;
    JTextField totalDaysTextField, presentDaysTextField, absentDaysTextField;
    JButton saveButton, clearButton, cancelButton;
    Connection con;
    Statement st;
    public AttendanceForm() {
        super("Student Attendance Form");

        // Labels
        studentIdLabel = new JLabel("Student ID:");
        studentIdLabel.setBounds(50, 50, 100, 25);
        totalDaysLabel = new JLabel("Total No. of Days:");
        totalDaysLabel.setBounds(50, 100, 150, 25);
        presentDaysLabel = new JLabel("Present Days:");
        presentDaysLabel.setBounds(50, 150, 100, 25);
        absentDaysLabel = new JLabel("Absent Days:");
        absentDaysLabel.setBounds(50, 200, 100, 25);

        // Dropdown Menu
        studentIdComboBox = new JComboBox<String>();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select stuid from student");
            while(rs.next())
            {
                int a=rs.getInt(1);
                studentIdComboBox.addItem(""+a);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Sql error occured");
        }
        studentIdComboBox.setBounds(200, 50, 100, 25);

        // TextFields
        totalDaysTextField = new JTextField();
        totalDaysTextField.setBounds(200, 100, 100, 25);
        presentDaysTextField = new JTextField();
        presentDaysTextField.setBounds(200, 150, 100, 25);
        absentDaysTextField = new JTextField();
        absentDaysTextField.setBounds(200, 200, 100, 25);

        // Buttons
        saveButton = new JButton("Save");
        saveButton.setBounds(50, 250, 80, 25);
        saveButton.addActionListener(this);
        clearButton = new JButton("Clear");
        clearButton.setBounds(150, 250, 80, 25);
        clearButton.addActionListener(this);
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(250, 250, 80, 25);
        cancelButton.addActionListener(this);

        // Add components to JFrame
        add(studentIdLabel);
        add(totalDaysLabel);
        add(presentDaysLabel);
        add(absentDaysLabel);
        add(studentIdComboBox);
        add(totalDaysTextField);
        add(presentDaysTextField);
        add(absentDaysTextField);
        add(saveButton);
        add(clearButton);
        add(cancelButton);

        // JFrame settings
        setLayout(null);
        setSize(400, 350);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            String id = (String) studentIdComboBox.getSelectedItem();
            String tot = totalDaysTextField.getText();
            String pre = presentDaysTextField.getText();
            String abs = absentDaysTextField.getText();
            try
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
                Statement st = con.createStatement();
                st.executeUpdate("INSERT INTO attend VALUES (" + id + ",'" + tot + "','" + pre + "','" + abs + "')");
                JOptionPane.showMessageDialog(null," Record insert sucessfully");
                con.close();
                st.close();
                dispose();
                new UserMenu();
            }
            catch(Exception ex)
            {
                System.out.println("SqlException Caught:"+ex);
                JOptionPane.showMessageDialog(null," sql Error is occured:   "+ex);
            }
        } else if (e.getSource() == clearButton) {
            totalDaysTextField.setText("");
            presentDaysTextField.setText("");
            absentDaysTextField.setText("");
        } else if (e.getSource() == cancelButton) {
            dispose();
        }
    }

    public static void main(String[] args) {
        new AttendanceForm();
    }
}

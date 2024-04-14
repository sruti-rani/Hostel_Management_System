package base;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;

public class StudentDeletionForm extends JFrame implements ActionListener {
    JLabel studentIdLabel, studentNameLabel;
    JComboBox<String> studentIdComboBox;
    JTextField studentNameTextField;
    JButton deleteButton, cancelButton;
    Connection con;
    Statement st;
    public StudentDeletionForm() {
        super("Student Deletion Form");

        // Labels
        studentIdLabel = new JLabel("Student ID:");
        studentIdLabel.setBounds(50, 50, 100, 25);
        studentNameLabel = new JLabel("Student Name:");
        studentNameLabel.setBounds(50, 100, 100, 25);

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
        studentIdComboBox.setBounds(150, 50, 100, 25);

        // TextFields
        studentNameTextField = new JTextField();
        // Add ActionListener to studentIdComboBox
        studentIdComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Retrieve selected student id from the JComboBox
                String id = (String) studentIdComboBox.getSelectedItem();
                try {
                    // Query database for student name
                    ResultSet rs = st.executeQuery("SELECT * FROM student WHERE Stuid = " + id);
                    String name = "";
                    while (rs.next()) {
                        name = rs.getString("Stuname");
                    }
                    // Update studentNameTextField with retrieved student name
                    studentNameTextField.setText(name);
                } catch (Exception ex) {
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(null, "Sql error occurred");
                }
            }
        });
        studentNameTextField.setEditable(false);
        studentNameTextField.setBounds(150, 100, 200, 25);

        // Buttons
        deleteButton = new JButton("Delete");
        deleteButton.setBounds(100, 150, 80, 25);
        deleteButton.addActionListener(this);
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(200, 150, 80, 25);
        cancelButton.addActionListener(this);

        // Add components to JFrame
        add(studentIdLabel);
        add(studentNameLabel);
        add(studentIdComboBox);
        add(studentNameTextField);
        add(deleteButton);
        add(cancelButton);

        // JFrame settings
        setLayout(null);
        setSize(400, 250);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton) {
            int response = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete this student?", "Confirmation",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                // Delete student from database here
                String id = (String) studentIdComboBox.getSelectedItem();
                try
                {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
                    Statement st = con.createStatement();
                    st.executeUpdate("DELETE FROM `student` WHERE `Stuid`="+id+"");
                    JOptionPane.showMessageDialog(null," Record deleted sucessfully");
                    con.close();
                    st.close();
                    dispose();
                    new UserMenu();
                }
                catch(Exception ex)
                {
                    //System.out.println("SqlException Caught:"+ex);
                    //JOptionPane.showMessageDialog(null," sql Error is occured:   "+ex);
                }
                JOptionPane.showMessageDialog(this, "Student deleted successfully!");
                dispose();
            }
        } else if (e.getSource() == cancelButton) {
            dispose();
            new UserMenu().setVisible(true);
            dispose();
        }
    }

    public static void main(String[] args) {
        new StudentDeletionForm();
    }
}

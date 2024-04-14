package base;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class RoomAllotment extends JFrame implements ActionListener {

    private JLabel studentIdLbl, courseLbl, roomLbl, floorLbl;
    private JComboBox<String> studentIdCb, courseCb, roomCb, floorCb;
    private JButton saveBtn, clearBtn, cancelBtn;

    public RoomAllotment() {
        super("Room Allotment");

        // initialize labels
        studentIdLbl = new JLabel("Student ID");
        studentIdLbl.setBounds(50, 50, 100, 25);
        courseLbl = new JLabel("Course");
        courseLbl.setBounds(50, 100, 100, 25);
        roomLbl = new JLabel("Room No");
        roomLbl.setBounds(50, 150, 100, 25);
        floorLbl = new JLabel("Floor No");
        floorLbl.setBounds(50, 200, 100, 25);

        // initialize combo boxes
        studentIdCb = new JComboBox<String>();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select stuid from student");
            while(rs.next())
            {
                int a=rs.getInt(1);
                studentIdCb.addItem(""+a);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Sql error occured");
        }
        studentIdCb.setBounds(150, 50, 150, 25);
        courseCb = new JComboBox<String>();
        courseCb.addItem("Computer Science");
        courseCb.addItem("Electrical Engineering");
        courseCb.addItem("Mechanical Engineering");
        courseCb.setBounds(150, 100, 150, 25);
        roomCb = new JComboBox<String>();
        roomCb.addItem("101");
        roomCb.addItem("102");
        roomCb.addItem("103");
        roomCb.setBounds(150, 150, 150, 25);
        floorCb = new JComboBox<String>();
        floorCb.addItem("1");
        floorCb.addItem("2");
        floorCb.addItem("3");
        floorCb.setBounds(150, 200, 150, 25);

        // initialize buttons
        saveBtn = new JButton("Save");
        saveBtn.setBounds(50, 250, 80, 25);
        saveBtn.addActionListener(this);
        clearBtn = new JButton("Clear");
        clearBtn.setBounds(150, 250, 80, 25);
        clearBtn.addActionListener(this);
        cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(250, 250, 80, 25);
        cancelBtn.addActionListener(this);

        // add components to frame
        add(studentIdLbl);
        add(courseLbl);
        add(roomLbl);
        add(floorLbl);
        add(studentIdCb);
        add(courseCb);
        add(roomCb);
        add(floorCb);
        add(saveBtn);
        add(clearBtn);
        add(cancelBtn);

        // set frame properties
        setSize(400, 350);
        setResizable(false);
        setLayout(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveBtn) {
            String id = (String) studentIdCb.getSelectedItem();
            String course = (String) courseCb.getSelectedItem();
            String rno = (String) roomCb.getSelectedItem();
            String fno = (String) floorCb.getSelectedItem();
            try
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
                Statement st = con.createStatement();
                st.executeUpdate("INSERT INTO room VALUES (" + id + ",'" + course + "','" + rno + "','" + fno + "')");
                JOptionPane.showMessageDialog(null," Record insert sucessfully");
                con.close();
                st.close();
                dispose();
                new UserMenu().setVisible(true);
            }
            catch(Exception ex)
            {
                System.out.println("SqlException Caught:"+ex);
                JOptionPane.showMessageDialog(null," sql Error is occured:   "+ex);
            }
        } else if (e.getSource() == clearBtn) {
            // clear form fields
            studentIdCb.setSelectedIndex(0);
            courseCb.setSelectedIndex(0);
            roomCb.setSelectedIndex(0);
            floorCb.setSelectedIndex(0);
        } else if (e.getSource() == cancelBtn) {
            dispose();
            new UserMenu().setVisible(true);
            dispose();
        }
    }

    public static void main(String[] args) {
        new RoomAllotment();
    }
}

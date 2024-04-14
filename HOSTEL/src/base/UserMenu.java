package base;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserMenu extends JFrame {
    private JButton studentButton, feeButton, roomButton, attendanceButton, refundButton, reportButton, exitButton;

    public UserMenu() {
        super("User Menu");

        // create components
        JLabel backgroundLabel = new JLabel(new ImageIcon("src/Images/2188.jpg"));
        JLabel titleLabel = new JLabel("Hostel Management System");
        titleLabel.setFont(new Font("Monotype Corsiva",Font.ITALIC,25));
        titleLabel.setForeground(Color.red);
        studentButton = new JButton("Student info");
        feeButton = new JButton("Fee Details");
        roomButton = new JButton("Room Allotment");
        attendanceButton = new JButton("Attendance");
//        refundButton = new JButton("Refunds");
//        reportButton = new JButton("Reports");
        exitButton = new JButton("Exit");

        // set layout
        backgroundLabel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(10, 10, 10, 10);
        gc.gridx = 0;
        gc.gridy = 0;
        backgroundLabel.add(titleLabel, gc);

        gc.gridy = 1;
        backgroundLabel.add(studentButton, gc);

        gc.gridy = 2;
        backgroundLabel.add(feeButton, gc);

        gc.gridy = 3;
        backgroundLabel.add(roomButton, gc);

        gc.gridy = 4;
        backgroundLabel.add(attendanceButton, gc);

//        gc.gridy = 5;
//        backgroundLabel.add(refundButton, gc);
//
//        gc.gridy = 6;
//        backgroundLabel.add(reportButton, gc);

        gc.gridy = 5;
        backgroundLabel.add(exitButton, gc);

        // set frame properties
        setContentPane(backgroundLabel);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add button listeners
        studentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                StudentPage studentPage = new StudentPage();
                    studentPage.setVisible(true);
                dispose();
            }
        });

        feeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Fee f = new Fee();
                f.setVisible(true);
                dispose();
            }
        });

        roomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                RoomAllotment rm = new RoomAllotment();
                rm.setVisible(true);
                dispose();
            }
        });

        attendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AttendanceForm af = new AttendanceForm();
                af.setVisible(true);
                dispose();
            }
        });

//        refundButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // TODO: open refunds page
//            }
//        });
//
//        reportButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // TODO: open reports page
//            }
//        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                UserMenu menu = new UserMenu();
                menu.setVisible(true);
            }
        });
    }
}

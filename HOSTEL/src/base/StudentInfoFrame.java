package base;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class StudentInfoFrame extends JFrame {
    private JTable table;

    public StudentInfoFrame() {
        setTitle("Student Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);

        // Create a table to display student information
        String[] columns = {"StuID", "StuName", "FName", "Age", "Hostel", "Gender", "Mobile", "Date"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        // Retrieve data from the database and add to the table
        try {
            // Replace "jdbc:mysql://localhost:3306/yourdatabase" with your database URL
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM student");

            while (resultSet.next()) {
                String stuID = resultSet.getString("StuID");
                String stuName = resultSet.getString("StuName");
                String fName = resultSet.getString("FName");
                String age = resultSet.getString("Age");
                String hostel = resultSet.getString("Hostel");
                String gender = resultSet.getString("Gender");
                String mobile = resultSet.getString("Mob");
                String date = resultSet.getString("Dat");

                String[] rowData = {stuID, stuName, fName, age, hostel, gender, mobile, date};
                tableModel.addRow(rowData);
            }

            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Add the table to a JScrollPane and set it as the content pane of the JFrame
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane);

        setVisible(true);
    }

    public static void main(String[] args) {
        new StudentInfoFrame();
    }
}

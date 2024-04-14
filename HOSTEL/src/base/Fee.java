package base;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.sql.*;

public class Fee extends JFrame implements ActionListener,FocusListener
{
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,ld;
    JTextField t2,t3,t4,t5,t6,t7,t8,t9,t10,td;
    JTextArea ta1;
    JButton b1,b2,b3;
    Choice ch;
    Choice ch2,ch1;
    Connection con;
    Statement st;
    ResultSet rs;

    public Fee()
    {
        super("Fee Information");
        setup();
        addWindowListener(new WindowEventHandler());
        setVisible(true);
    }
    public void setup()
    {
        setLayout(null);
        l1=new JLabel("Fee Information");
        l1.setFont(new Font("Monotype Corsiva",Font.ITALIC,25));
        l1.setBounds(250,50,400,25);
        l1.setForeground(Color.red);

        setFont(new Font("Courier",Font.BOLD,15));
        l2=new JLabel("Student ID");
        l2.setBounds(70,120,100,25);

        ch2=new Choice();
        ch2.setBounds(240,120,180,25);
        ch2.add("");


        l3=new JLabel("Room Rent");
        l3.setBounds(70,160,100,25);
        t3=new JTextField();
        t3.setBounds(240,160,60,25);
        t3.setText("9000");
        t3.setEditable(false);

        l4=new JLabel("Mess");
        l4.setBounds(360,160,60,25);
        t4=new JTextField();
        t4.setBounds(430,160,60,25);
        t4.setText("6000");
        t4.setEditable(false);

        l5=new JLabel("Hostel Management");
        l5.setBounds(70,200,160,25);
        t5=new JTextField();
        t5.setBounds(240,200,60,25);
        t5.setText("2000");
        t5.setEditable(false);

        l6=new JLabel("No of Months");
        l6.setBounds(360,200,100,25);
        t6=new JTextField();
        t6.setBounds(520,200,40,25);

        l10=new JLabel("Others");
        l10.setBounds(70,240,100,25);
        t10=new JTextField();
        t10.setBounds(240,240,60,25);
        t10.setText("9000");
        t10.setEditable(false);


        l7=new JLabel("Total Amount");
        l7.setBounds(70,280,120,25);
        t7=new JTextField();
        t7.setBounds(240,280,80,25);


        l8=new JLabel("Amount Pay");
        l8.setBounds(70,330,120,25);
        t8=new JTextField();
        t8.setBounds(240,330,80,25);

        l9=new JLabel("Balance");
        l9.setBounds(360,320,80,25);
        t9=new JTextField();
        t9.setBounds(450,320,80,25);


        ld=new JLabel("Date");
        ld.setBounds(500,120,100,25);
        td=new JTextField();
        td.setBounds(550,120,120,25);

        Calendar cal=Calendar.getInstance();
        String dat=cal.get(Calendar.YEAR)+"-"+cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.DATE);
        td.setText(""+dat);

        b1=new JButton("Save");
        b1.setBounds(200,450,100,25);
        b2=new JButton("Cancel");
        b2.setBounds(340,450,100,25);
        b3=new JButton("Clear");
        b3.setBounds(480,450,100,25);

        add(l1);add(l2);add(l3);add(l4);add(l5);add(l6);add(l7);add(l9);add(l8);add(l10);
        add(ch2);add(t3);add(t4);add(t5);add(t10);add(t6);add(t7);add(t9);add(t8);
        add(b1);add(b2);add(b3);
        add(ld);add(td);
        td.setEditable(false);
        t8.addFocusListener(this);
        t6.addFocusListener(this);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        setBackground(Color.cyan);
        setSize(800,600);
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
            st = con.createStatement();
            rs=st.executeQuery("select stuid from student");
            while(rs.next())
            {
                int a=rs.getInt(1);
                ch2.add(""+a);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Sql error occured");
        }

    }

    public void focusLost(FocusEvent f)
    {
        if(f.getSource()==t8)
        {
            int b=Integer.parseInt(t7.getText())-Integer.parseInt(t8.getText());
            t9.setText(""+b);
        }
        else if(f.getSource()==t6)
        { int res=Integer.parseInt(t3.getText())+Integer.parseInt(t4.getText())+Integer.parseInt(t5.getText())+Integer.parseInt(t10.getText());
            int mon=Integer.parseInt(t6.getText());
            res=(res*mon)/6;
            t7.setText(""+res);

        }
    }
    public void focusGained(FocusEvent fe)
    {

    }



    public void actionPerformed(ActionEvent ae)
    {
        String sst=ae.getActionCommand();
        if(sst.equals("Save"))
        {
            String id=ch2.getSelectedItem();
            String mon=t6.getText();
            String total=t7.getText();
            String pay=t8.getText();
            String bal=t9.getText();
            String dat=td.getText();

            if(id.length()==0){
                JOptionPane.showMessageDialog(null,"invlaid ID");}
            else if(mon.length()==0){
                JOptionPane.showMessageDialog(null,"invlaid Months");}
            else if(pay.length()==0){
                JOptionPane.showMessageDialog(null,"invlaid amount pay");}
            else
            {
                int n=0;
                if(n==0)
                {
                    try
                    {
                        int an=Integer.parseInt(mon);
                        n=1;
                    }
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(null,"Enter numbers only in month field");
                    }
                }
                if(n==1)
                {
                    try
                    {
                        int an=Integer.parseInt(pay);
                        n=2;
                    }
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(null,"Enter numbers only in Paying amt");
                    }
                }
                if(n==2)
                {
                    try
                    {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
                        st = con.createStatement();
                        st.executeUpdate("insert into fee values("+id+","+mon+","+total+",'"+dat+"',"+pay+","+bal+")");
                        JOptionPane.showMessageDialog(null," Record insert sucessfully");
                        con.close();
                        st.close();
                        dispose();
                        new UserMenu();
                    }
                    catch(Exception e)
                    {
                        System.out.println("SqlException Caught:"+e);
                        JOptionPane.showMessageDialog(null," sql Error is occured:   "+e);
                    }
                }
            }
        }
        else if(sst.equals("Cancel"))
        {
            dispose();
            new UserMenu();
        }
        else if(sst.equals("Clear"))
        {
            t6.setText("");
            t7.setText("");
            t8.setText("");
            t9.setText("");
            ch.select(0);
        }
    }

    public static void main(String s[])
    {
        Fee f=new Fee();
    }

    class WindowEventHandler extends WindowAdapter
    {
        public void windowClosing(WindowEvent we)
        {
            System.exit(0);
        }
    }
}

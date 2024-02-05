import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.lang.*;

public class Del_Co extends JFrame implements ActionListener {

    JLabel l1,L,topic;
    JTextField tf7;
    JButton b1,b2,b3;
	Connection con;
    public Del_Co() 
	{
        setup();
        setVisible(true);
    }

    public void setup() {
        setLayout(null);
        L = new JLabel();
        try {
            
            ClassLoader classLoader = getClass().getClassLoader();
            ImageIcon icon = new ImageIcon(classLoader.getResource("g.jpg"));
            L.setIcon(icon);
        } catch (Exception e) {
            System.err.println("Error loading image: " + e.getMessage());
        }
        L.setBounds(0, 0, 800, 600);
		 

        l1 = new JLabel("Co Curricular Activity Record Deletion Form");
        l1.setFont(new Font("Monotype Corsiva", Font.BOLD, 30));
        l1.setBounds(50, 50, 650, 25);
        l1.setForeground(Color.yellow);
        
		
        
		
        topic = new JLabel("Name of Topic:");
        topic.setBounds(150, 200, 250, 25);
		topic.setFont(new Font("Courier", Font.BOLD, 20));
        tf7 = new JTextField();
        tf7.setBounds(400, 200, 160, 25);
		
        b1 = new JButton("Delete");
        b1.setBounds(200, 450, 100, 25);
        b2 = new JButton("Cancel");
        b2.setBounds(340, 450, 100, 25);
        b3 = new JButton("Clear");
        b3.setBounds(480, 450, 100, 25);
		L.add(b1);L.add(b2);L.add(b3);
		
        add(L);
        L.add(l1);
		L.add(topic);L.add(tf7);;
    
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        setBackground(Color.cyan);
        setSize(800, 600);
    }

    public void actionPerformed(ActionEvent ae)
	{
       String sst = ae.getActionCommand();
        if (sst.equals("Delete")) 
		{
		  String activityTopic = tf7.getText();
		  try 
					{
                        Class.forName("com.mysql.jdbc.Driver");  
  
	                 	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/microp","root","");  
						String deleteSQL = "Delete from co_activities where topic=?";

            // Create a PreparedStatement
            PreparedStatement preparedStatement = con.prepareStatement(deleteSQL);
            preparedStatement.setString(1, activityTopic);
			preparedStatement.executeUpdate();

            // Close the database connection


						JOptionPane.showMessageDialog(null," Record deleted sucessfully");
						con.close();
                        dispose();
                        new Activity();
                    } 
					catch (Exception e) 
					{
                        System.out.println("SqlException Caught:" + e);
                        JOptionPane.showMessageDialog(null, "SQL Error is occurred");
                    }
		}
		else if (sst.equals("Cancel")) 
		{
            dispose();
            Co_Info st = new Co_Info();
        } 
		else if (sst.equals("Clear"))
		{
         
        tf7.setText("");
         
        }
    }
    public static void main(String s[]) {
       Del_Co  stt = new Del_Co();
    }

    class WindowEventHandler extends WindowAdapter {
        public void windowClosing(WindowEvent we) {
            dispose();
            new Activity();
        }
    }
}
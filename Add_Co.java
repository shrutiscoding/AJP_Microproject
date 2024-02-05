import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.lang.*;

public class Add_Co extends JFrame implements ActionListener {

    JLabel l1, L,year, date, time, place, tea_co, hod, stud_co, topic, name, lresult,lb;
    JTextField tf1, tf2, tf4, tf5, tf6, tf7,lben;
    JComboBox<String> cb1;
	JCheckBox c1,c2,c3;
	JButton b1,b2,b3;
    JTextArea tf3,detail;
	Connection con;
    public Add_Co() {
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
		    String activity1[] = {"Essay_Writing", "Story_Writing", "Debate_Competition", "Exhibition", "Industry Visit", "Guest Lectures", "PPT_Presentation", "Evocation_Competition", "Quiz_Competition"};


        l1 = new JLabel("Co-Curricular Activity Record Insertion Form");
        l1.setFont(new Font("Monotype Corsiva", Font.BOLD, 30));
        l1.setBounds(50, 50, 650, 25);
        l1.setForeground(Color.yellow);
        setFont(new Font("Courier", Font.BOLD, 15));
		
        year = new JLabel("Organized for Year:");
        year.setBounds(55, 120, 150, 25);
		
        c1 = new JCheckBox("FY");
        c2 = new JCheckBox("SY");
        c3 = new JCheckBox("TY");
        c1.setBounds(210, 120, 40, 25);c2.setBounds(260, 120, 40, 25);c3.setBounds(310, 120, 40, 25);
		
        date = new JLabel("Date of activity organized:");
        date.setBounds(55, 170, 150, 25);
		
        tf1 = new JTextField();
        tf1.setBounds(210, 170, 180, 25);
		
        time = new JLabel("Time:");
        time.setBounds(55, 220, 140, 25);
		
        tf2 = new JTextField();
        tf2.setBounds(210, 220, 180, 25);
        
        
        place = new JLabel("Place/Venue:");
        place.setBounds(55, 270, 120, 25);
		
        tf3 = new JTextArea();
        tf3.setBounds(210, 270, 180, 25);

        tea_co = new JLabel("Teacher Co-ordinator:");
        tea_co.setBounds(55, 320, 160, 25);
		
		tf4 = new JTextField();
        tf4.setBounds(210, 320, 180, 25);
		
        hod = new JLabel("HOD:");
        hod.setBounds(55, 370, 160, 25);
		
		tf5 = new JTextField();
        tf5.setBounds(210, 370, 160, 25);
		
        stud_co = new JLabel("Student Co-ordinator:");
        stud_co.setBounds(450, 120, 100, 25);
		
        tf6 = new JTextField();
        tf6.setBounds(570, 120, 160, 25);
		
        topic = new JLabel("Name of Topic:");
        topic.setBounds(450, 170, 150, 25);
		
        tf7 = new JTextField();
        tf7.setBounds(570, 170, 160, 25);
		
        name = new JLabel("Activity Name:");
        name.setBounds(450,220, 100, 25);
       
		cb1 = new JComboBox<String>();
        cb1=new JComboBox(activity1);cb1.setBounds(570,220,100,25);
        
		lresult = new JLabel("About Activity:");
		lresult.setBounds(450,270,100,25);
		
		detail = new JTextArea();
		detail.setBounds(570,270,150,35);
        JScrollPane j1 = new JScrollPane(detail);
		detail.setAlignmentX(Component.LEFT_ALIGNMENT);
        j1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        j1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		j1.setBounds(0,30,40,200);
		
        lb = new JLabel("Beneficiaries:");
        lb.setBounds(450, 370, 100, 25);
		lben = new JTextField();
        lben.setBounds(570,370,100,25);
		
        b1 = new JButton("Save");
        b1.setBounds(200, 450, 100, 25);
        b2 = new JButton("Cancel");
        b2.setBounds(340, 450, 100, 25);
        b3 = new JButton("Clear");
        b3.setBounds(480, 450, 100, 25);
     
        add(L);
        L.add(l1);
		L.add(year);
		L.add(c1);L.add(c2);L.add(c3);L.add(date);L.add(time);L.add(place);L.add (tea_co);L.add( hod);L.add(stud_co);L.add(topic);L.add(name);
		L.add(lresult);L.add(lb);
		L.add(tf1);L.add(tf2); L.add(tf4);L.add(tf5);L.add(tf6);L.add(tf7);L.add(lben);
    L.add(cb1);
    L.add(tf3);L.add(detail);
		L.add(b1);L.add(b2);L.add(b3);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        setBackground(Color.cyan);
        setSize(800, 600);
    }

    public void actionPerformed(ActionEvent ae)
	{
       String sst = ae.getActionCommand();
        if (sst.equals("Save")) 
		{
            String selectedYear = "";
        if (c1.isSelected()) {
            selectedYear += "FY ";
        }
        if (c2.isSelected()) {
            selectedYear += "SY ";
        }
        if (c3.isSelected()) {
            selectedYear += "TY ";
        }
        String activityYear = tf1.getText();
        String activityTime = tf2.getText();
        String activityPlace = tf3.getText();
        String teacherCoordinator = tf4.getText();
        String hodName = tf5.getText();
        String studentCoordinator = tf6.getText();
        String activityTopic = tf7.getText();
        String activityName = (String) cb1.getSelectedItem();
        String activityDetails = detail.getText();
        String numberOfBeneficiaries = lben.getText();
            
                    try 
					{
                        Class.forName("com.mysql.jdbc.Driver");  
  
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/microp","root","");  
						String insertSQL = "INSERT INTO co_activities (year, datel, timel, place, t_coordinator, hod, s_coordinator, topic, name, details, beneficiaries) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // Create a PreparedStatement
            PreparedStatement preparedStatement = con.prepareStatement(insertSQL);
            preparedStatement.setString(1, selectedYear);
            preparedStatement.setString(2, activityYear);
            preparedStatement.setString(3, activityTime);
            preparedStatement.setString(4, activityPlace);
            preparedStatement.setString(5, teacherCoordinator);
            preparedStatement.setString(6, hodName);
            preparedStatement.setString(7, studentCoordinator);
            preparedStatement.setString(8, activityTopic);
            preparedStatement.setString(9, activityName);
            preparedStatement.setString(10, activityDetails);
            preparedStatement.setString(11, numberOfBeneficiaries);

            // Execute the INSERT statement
            preparedStatement.executeUpdate();

            // Close the database connection


						JOptionPane.showMessageDialog(null," Record inserted sucessfully");
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
         tf1.setText("");
		 tf2.setText("");
        tf3.setText("");
        tf4.setText("");
        tf5.setText("");
        tf6.setText("");
        tf7.setText("");
        cb1.setSelectedItem(0);
        detail.setText("");
        lben.setText("");
		 
        }
    }

    public static void main(String s[]) {
       Add_Co  stt = new Add_Co();
    }

    class WindowEventHandler extends WindowAdapter {
        public void windowClosing(WindowEvent we) {
            dispose();
            new Activity();
        }
    }
}
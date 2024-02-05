import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.util.*;
import java.lang.*;

public class Show_Co extends JFrame implements ActionListener {

    JLabel l1, L,year,l;
    JTextField tf1,tf2,tf3,tf4;
	JCheckBox c1,c2,c3;
	JButton b1,b2;
    DefaultTableModel model;
    JTable table;
	Connection con;
    public Show_Co() {
        setup();
        setVisible(true);
    }

    public void setup() {
        setLayout(null);
        L = new JLabel();
		String[] columns = {"year", "datel", "timel", "place", "t_coordinator", "hod", "s_coordinator", "topic", "name"," details", "beneficiaries"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
		
		JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 250, 700, 150);
        try {
            
            ClassLoader classLoader = getClass().getClassLoader();
            ImageIcon icon = new ImageIcon(classLoader.getResource("g.jpg"));
            L.setIcon(icon);
        } catch (Exception e) {
            System.err.println("Error loading image: " + e.getMessage());
        }
        L.setBounds(0, 0, 800, 600);
		  

        l1 = new JLabel("Co-Curricular Activity Record Showing");
        l1.setFont(new Font("Monotype Corsiva", Font.BOLD, 30));
        l1.setBounds(50, 50, 650, 25);
        l1.setForeground(Color.yellow);
        setFont(new Font("Courier", Font.BOLD, 15));
		
        year = new JLabel("Organized for Year:");
        year.setBounds(55, 120, 150, 25);
		
        c1 = new JCheckBox("Date");
		tf1=new JTextField();
		tf2=new JTextField();
		l=new JLabel("to");
		tf3=new JTextField();
        c2 = new JCheckBox("Teacher Co-ordinater");
        c3 = new JCheckBox("Activity Type");
			tf4=new JTextField();
        c1.setBounds(210, 120, 100, 25);tf1.setBounds(420,120,50,25);l.setBounds(480,120,20,25);tf2.setBounds(510,120,50,25);
		
		c2.setBounds(210, 170, 170, 25);tf3.setBounds(420,170,100,25);
		
		c3.setBounds(210, 220, 100, 25);tf4.setBounds(420,220,100,25);
		
        b1 = new JButton("Show table");
        b1.setBounds(200, 450, 100, 25);
        b2 = new JButton("Cancel");
        b2.setBounds(340, 450, 100, 25);
        
     
        add(L);
        L.add(l1);L.add(tf1);L.add(tf2);L.add(tf3);L.add(tf4);L.add(l);
		L.add(year);
		L.add(c1);L.add(c2);L.add(c3);
		L.add(b1);L.add(b2);;
        b1.addActionListener(this);
		add(scrollPane);
        b2.addActionListener(this);
       
        setBackground(Color.cyan);
        setSize(800, 600);
    }

    public void actionPerformed(ActionEvent ae)
	{
       String sst = ae.getActionCommand();
	  
        if (sst.equals("Show table")) 
		{
                    try 
					{
                        Class.forName("com.mysql.jdbc.Driver");  
  
						Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/microp","root","");  
						
						String selectSQL = "SELECT * FROM co_activities WHERE ";

							// Check which checkboxes are selected and add conditions to the query
							if (c1.isSelected()) {
								String fromDate = tf1.getText();
								String toDate = tf2.getText();
								selectSQL += "datel BETWEEN '" + fromDate + "' AND '" + toDate + "' AND ";
							}
							if (c2.isSelected()) {
								String teacherCoordinator = tf3.getText();
								selectSQL += "t_coordinator = '" + teacherCoordinator + "' AND ";
							}
							if (c3.isSelected()) {
								String activityType = tf4.getText();
								selectSQL += "name = '" + activityType + "' AND ";
							}

							// Remove the trailing "AND" if present
							if (selectSQL.endsWith("AND ")) {
								selectSQL = selectSQL.substring(0, selectSQL.length() - 5);
							}

							// Create a PreparedStatement for the SELECT query
							PreparedStatement preparedStatement = con.prepareStatement(selectSQL);

							// Execute the query and retrieve the results
							ResultSet resultSet = preparedStatement.executeQuery();

							// Clear the existing data in the JTable
							model.setRowCount(0);

							// Iterate through the result set and add rows to the JTable
						while (resultSet.next()) {
    String year = resultSet.getString("year");
    String datel = resultSet.getString("datel");
    String timel = resultSet.getString("timel");
    String place = resultSet.getString("place");
    String t_coordinator = resultSet.getString("t_coordinator");
    String hod = resultSet.getString("hod");
    String s_coordinator = resultSet.getString("s_coordinator");
    String topic = resultSet.getString("topic");
    String name = resultSet.getString("name");
    String details = resultSet.getString("details");
    String beneficiaries = resultSet.getString("beneficiaries");

    model.addRow(new Object[]{year, datel, timel, place, t_coordinator, hod, s_coordinator, topic, name, details, beneficiaries});
}


                con.close();
                
            } 
			catch (Exception e) {
                System.out.println("SqlException Caught: " + e);
                JOptionPane.showMessageDialog(null, "SQL Error occurred");
            }
        } else if (sst.equals("Cancel")) {
            dispose();
            Co_Info st = new Co_Info();
        }
    }

    public static void main(String s[]) {
        Show_Co stt = new Show_Co();
    }

    class WindowEventHandler extends WindowAdapter {
        public void windowClosing(WindowEvent we) {
            dispose();
            new Activity();
        }
    }
}
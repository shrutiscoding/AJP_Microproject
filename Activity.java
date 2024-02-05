import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Activity extends JFrame implements ActionListener {

    JButton b1, b2, b3;
    JLabel l1;

    public Activity() {
        super("Extra and Co-Curricular Activity Management System");
        setup();
        addWindowListener(new WindowEventHandler());
        setVisible(true);
    }

    public void setup() {
        setLayout(null);
	JLabel imageLabel = new JLabel(new ImageIcon("h.jpg"));
        imageLabel.setBounds(0,0, 700, 500); 
        
        setContentPane(imageLabel);

        l1 = new JLabel("Extra and Co-Curricular Activity Management System");
        l1.setFont(new Font("Algerian", Font.BOLD, 20));
        l1.setBounds(25, 50, 700, 40);
        l1.setForeground(Color.black);

        b1 = new JButton("Extra Curricular Activity");
        b1.setBounds(50, 200, 200, 25);

        b2 = new JButton("Co-Curricular Activity");
        b2.setBounds(400, 200, 200, 25);

        b3 = new JButton("Exit");
        b3.setBounds(250, 300, 150, 25);

       

        add(l1);
        add(b1);
        add(b2);
        add(b3);

        //getContentPane().setBackground(Color.pink);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        setSize(700, 500);
    }

    public static void main(String s[]) {
        Activity h = new Activity();
    }

    public void actionPerformed(ActionEvent ae) {
        String sst = ae.getActionCommand();
        if (sst.equals("Extra Curricular Activity")) 
		{
            
             Extra_Info e = new Extra_Info();
            
        }
		else if (sst.equals("Co-Curricular Activity")) 
		{
            
            Co_Info c=new Co_Info();
            c.setVisible(true);
        }
        else if (sst.equals("EXIT")) 
		{
            System.exit(-1);
			dispose();
        }
    }

    class WindowEventHandler extends WindowAdapter {
        public void windowClosing(WindowEvent we) {
            System.exit(0);
        }
    }
}
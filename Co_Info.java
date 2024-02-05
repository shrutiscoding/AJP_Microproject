import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Co_Info extends JFrame implements ActionListener {

    JButton b1, b2, b3,b4,b5;
    JLabel l1;

    public Co_Info() {
        super("Co-Curricular Activity Management System");
        setup();
        addWindowListener(new WindowEventHandler());
        setVisible(true);
    }

    public void setup() {
        setLayout(null);
	JLabel imageLabel = new JLabel(new ImageIcon("f.jpg"));
        imageLabel.setBounds(0,0, 1000, 800); 

        
        setContentPane(imageLabel);

        l1 = new JLabel("Co-Curricular Activity Management System");
        l1.setFont(new Font("Algerian", Font.BOLD, 20));
        l1.setBounds(75, 50, 700, 40);
        l1.setForeground(Color.black);

        b1 = new JButton("Add Activity");
        b1.setBounds(50, 200, 200, 25);

        b2 = new JButton("Modify Activity");
        b2.setBounds(400, 200, 200, 25);

        b4= new JButton("Delete Activity");
        b4.setBounds(50, 300, 200, 25);
		
		b5 = new JButton("Show Activity");
        b5.setBounds(400, 300, 200, 25);
		
        b3 = new JButton("Cancel");
        b3.setBounds(250, 400, 150, 25);
       

        add(l1);
        add(b1);
        add(b2);
        add(b3);add(b4);add(b5);

        //getContentPane().setBackground(Color.pink);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);b4.addActionListener(this);b5.addActionListener(this);

        setSize(700, 500);
    }

    public static void main(String s[]) {
        Co_Info c = new Co_Info();
    }

    public void actionPerformed(ActionEvent ae) {
        String sst = ae.getActionCommand();
        if (sst.equals("Add Activity")) 
		{
            
             Add_Co e = new Add_Co();
            
        }
		else if (sst.equals("Modify Activity")) 
		{
            
            Mod_Co c=new Mod_Co();
            c.setVisible(true);
        }
		
		else if (sst.equals("Delete Activity")) 
		{
            
            Del_Co c=new Del_Co();
            c.setVisible(true);
        }
		else if (sst.equals("Show Activity")) 
		{
            
            Show_Co c=new Show_Co();
            c.setVisible(true);
        }
        else if (sst.equals("Cancel")) 
		{
          		 new Activity();
        }
    }

    class WindowEventHandler extends WindowAdapter {
        public void windowClosing(WindowEvent we) {
            System.exit(0);
        }
    }
}
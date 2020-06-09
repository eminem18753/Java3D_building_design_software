import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public  class Welcome extends JFrame{
    String name;
	JLabel lab1;
	JLabel lab2;
	//JPanel button;
	JButton start;
	//JLabel lab3;
	 
	public Welcome()
		{
		super("Welcome to SketchUp3D");
		setSize(850,720);
		
		setLayout(new BorderLayout());
		Icon Wel=new ImageIcon(getClass().getResource("Welcome.jpg"));
		lab1=new JLabel("",Wel,SwingConstants.CENTER);
		
		//lab1=new JLabel("Welcome to simple SketchUp",Wel,SwingConstants.CENTER);

		//lab2=new JLabel("This is a simple 3D program designed for architecture designers and others who need easy 3D process in work\n\n\n\n\nBy Eminem FANG & Kevin WU");
		//lab3=new JLabel("");
		
		
		add(lab1,BorderLayout.CENTER);
		//add(lab2);
		//add(lab3);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setResizable(false);    // fixed size display
		setVisible(true);
		Icon image=new ImageIcon(getClass().getResource("Start.png"));
		start=new JButton("",image);
		add(start,BorderLayout.SOUTH);
		ButtonHandler handler=new ButtonHandler();
		start.addActionListener(handler);
	}
	private class ButtonHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			name=JOptionPane.showInputDialog("Please regsist your name");
			dispose();
			new Checkers3D();
		}
	}
}
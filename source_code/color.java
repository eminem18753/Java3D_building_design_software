import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class color extends JFrame implements ActionListener
{
	private JColorChooser d1;
	private JButton finish;
	Color appearanceColor;
	public float red;
	public float green;
	public float blue;
	public color(WrapCheckers3D r)
	{
		super("Change color");
		setLayout(new BorderLayout());
		JLabel j1=new JLabel("Choose the color please",JLabel.CENTER);
		d1=new JColorChooser(j1.getForeground());
		JScrollPane sp=new JScrollPane(d1);
		finish=new JButton("finish");
		finish.addActionListener(this);
		add(sp,BorderLayout.CENTER);
		add(finish,BorderLayout.SOUTH);
		setSize(700,500);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setResizable(false);    // fixed size display
		setVisible(true);
		appearanceColor=d1.getColor();
		//r.redPart=(float)appearanceColor.getRed()/256;
		//r.greenPart=(float)appearanceColor.getGreen()/256;
		//r.bluePart=(float)appearanceColor.getBlue()/256;
		//System.out.print(r.redPart+"\n\n"+r.greenPart+"\n\n"+r.bluePart+"\n\n");
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==finish)
		{
			//System.out.print(appearanceColor.getRed()+"\n\n"+appearanceColor.getGreen()+"\n\n"+appearanceColor.getBlue()+"\n\n");
			//r=redPart;
			//g=greenPart;
			//b=bluePart;
			//System.out.print(redPart+"\n\n"+greenPart+"\n\n"+bluePart+"\n\n");
			appearanceColor=d1.getColor();
			red=(float)appearanceColor.getRed()/256;
			green=(float)appearanceColor.getGreen()/256;
			blue=(float)appearanceColor.getBlue()/256;
			System.out.print(red+"\n"+green+'\n'+blue+"\n\n");
			try
			{
				File file=new File("color.txt");
				FileWriter fw=new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw=new BufferedWriter(fw);
				bw.write(Double.toString(red)+"\n");
				bw.write(Double.toString(green)+"\n");
				bw.write(Double.toString(blue)+"\n");
				bw.close();
			}
			catch(IOException ex)
			{}
			//writer.println(green);
			//writer.println(blue);
			dispose();
		}
	}
	
}
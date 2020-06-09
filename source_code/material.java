import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
public class material extends JFrame implements ActionListener
{
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JButton b4;
	private JButton b5;
	private JButton b6;
	private JButton b7;
	private JButton b8;
	private JButton b9;
	private JButton b10;
	private JButton b11;
	private JButton b12;
	private JButton b13;
	private JButton b14;
	private JButton b15;
	private JButton b16;
	private JButton b17;
	private JButton b18;
	private JButton b19;
	private JButton b20;
	private JButton b21;
	private JButton b22;
	private JButton b23;
	private JButton b24;
	private JButton b25;
	private JButton b26;
	private JButton b27;
	public material()
	{
		super("Set the material you want");
		setLayout(new GridLayout(3,9));
		Icon c1=new ImageIcon(getClass().getResource("r1.jpg"));
		Icon c2=new ImageIcon(getClass().getResource("r2.jpg"));
		Icon c3=new ImageIcon(getClass().getResource("r3.jpg"));
		Icon c4=new ImageIcon(getClass().getResource("r4.jpg"));
		Icon c5=new ImageIcon(getClass().getResource("r5.jpg"));
		Icon c6=new ImageIcon(getClass().getResource("r6.jpg"));
		Icon c7=new ImageIcon(getClass().getResource("r7.jpg"));
		Icon c8=new ImageIcon(getClass().getResource("r8.jpg"));
		Icon c9=new ImageIcon(getClass().getResource("r9.jpg"));
		Icon c10=new ImageIcon(getClass().getResource("r10.jpg"));
		Icon c11=new ImageIcon(getClass().getResource("r11.jpg"));
		Icon c12=new ImageIcon(getClass().getResource("r12.jpg"));
		Icon c13=new ImageIcon(getClass().getResource("r13.jpg"));
		Icon c14=new ImageIcon(getClass().getResource("r14.jpg"));
		Icon c15=new ImageIcon(getClass().getResource("r15.jpg"));
		Icon c16=new ImageIcon(getClass().getResource("r16.jpg"));
		Icon c17=new ImageIcon(getClass().getResource("r17.jpg"));
		Icon c18=new ImageIcon(getClass().getResource("r18.jpg"));
		Icon c19=new ImageIcon(getClass().getResource("r19.jpg"));
		Icon c20=new ImageIcon(getClass().getResource("r20.jpg"));
		Icon c21=new ImageIcon(getClass().getResource("r21.jpg"));
		Icon c22=new ImageIcon(getClass().getResource("r22.jpg"));
		Icon c23=new ImageIcon(getClass().getResource("r23.jpg"));
		Icon c24=new ImageIcon(getClass().getResource("r24.jpg"));
		Icon c25=new ImageIcon(getClass().getResource("r25.jpg"));
		Icon c26=new ImageIcon(getClass().getResource("r26.jpg"));
		Icon c27=new ImageIcon(getClass().getResource("r27.jpg"));
		
		b1=new JButton("",c1);
		b2=new JButton("",c2);
		b3=new JButton("",c3);
		b4=new JButton("",c4);
		b5=new JButton("",c5);
		b6=new JButton("",c6);
		b7=new JButton("",c7);
		b8=new JButton("",c8);
		b9=new JButton("",c9);
		b10=new JButton("",c10);
		b11=new JButton("",c11);
		b12=new JButton("",c12);
		b13=new JButton("",c13);
		b14=new JButton("",c14);
		b15=new JButton("",c15);
		b16=new JButton("",c16);
		b17=new JButton("",c17);
		b18=new JButton("",c18);
		b19=new JButton("",c19);
		b20=new JButton("",c20);
		b21=new JButton("",c21);
		b22=new JButton("",c22);
		b23=new JButton("",c23);
		b24=new JButton("",c24);
		b25=new JButton("",c25);
		b26=new JButton("",c26);
		b27=new JButton("",c27);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		b8.addActionListener(this);
		b9.addActionListener(this);
		b10.addActionListener(this);
		b11.addActionListener(this);
		b12.addActionListener(this);
		b13.addActionListener(this);
		b14.addActionListener(this);
		b15.addActionListener(this);
		b16.addActionListener(this);
		b17.addActionListener(this);
		b18.addActionListener(this);
		b19.addActionListener(this);
		b20.addActionListener(this);
		b21.addActionListener(this);
		b22.addActionListener(this);
		b23.addActionListener(this);
		b24.addActionListener(this);
		b25.addActionListener(this);
		b26.addActionListener(this);
		b27.addActionListener(this);
		
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		add(b5);
		add(b6);
		add(b7);
		add(b8);
		add(b9);
		add(b10);
		add(b11);
		add(b12);
		add(b13);
		add(b14);
		add(b15);
		add(b16);
		add(b17);
		add(b18);
		add(b19);
		add(b20);
		add(b21);
		add(b22);
		add(b23);
		add(b24);
		add(b25);
		add(b26);
		add(b27);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,500);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)
		{
			try
			{
				File file=new File("material.txt");
				FileWriter fw=new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw=new BufferedWriter(fw);
				bw.write("w1");
				bw.close();
			}
			catch(IOException ex)
			{}
			dispose();
		}
		
		if(e.getSource()==b2)
		{
			try
			{
				File file=new File("material.txt");
				FileWriter fw=new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw=new BufferedWriter(fw);
				bw.write("w2");
				bw.close();
			}
			catch(IOException ex)
			{}
			dispose();
		}
		
		if(e.getSource()==b3)
		{
			try
			{
				File file=new File("material.txt");
				FileWriter fw=new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw=new BufferedWriter(fw);
				bw.write("w3");
				bw.close();
			}
			catch(IOException ex)
			{}
			dispose();
		}
		if(e.getSource()==b4)
		{
			try
			{
				File file=new File("material.txt");
				FileWriter fw=new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw=new BufferedWriter(fw);
				bw.write("w4");
				bw.close();
			}
			catch(IOException ex)
			{}
			dispose();
		}
		if(e.getSource()==b5)
		{
			try
			{
				File file=new File("material.txt");
				FileWriter fw=new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw=new BufferedWriter(fw);
				bw.write("w5");
				bw.close();
			}
			catch(IOException ex)
			{}
			dispose();
		}
		if(e.getSource()==b6)
		{
			try
			{
				File file=new File("material.txt");
				FileWriter fw=new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw=new BufferedWriter(fw);
				bw.write("w6");
				bw.close();
			}
			catch(IOException ex)
			{}
			dispose();
		}
		if(e.getSource()==b7)
		{
			try
			{
				File file=new File("material.txt");
				FileWriter fw=new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw=new BufferedWriter(fw);
				bw.write("w7");
				bw.close();
			}
			catch(IOException ex)
			{}
			dispose();
		}
		if(e.getSource()==b8)
		{
			try
			{
				File file=new File("material.txt");
				FileWriter fw=new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw=new BufferedWriter(fw);
				bw.write("w8");
				bw.close();
			}
			catch(IOException ex)
			{}
			dispose();
		}
		if(e.getSource()==b9)
		{
			try
			{
				File file=new File("material.txt");
				FileWriter fw=new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw=new BufferedWriter(fw);
				bw.write("w9");
				bw.close();
			}
			catch(IOException ex)
			{}
			dispose();
		}
		if(e.getSource()==b10)
		{
			try
			{
				File file=new File("material.txt");
				FileWriter fw=new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw=new BufferedWriter(fw);
				bw.write("w10");
				bw.close();
			}
			catch(IOException ex)
			{}
			dispose();
		}
		if(e.getSource()==b11)
		{
			try
			{
				File file=new File("material.txt");
				FileWriter fw=new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw=new BufferedWriter(fw);
				bw.write("w11");
				bw.close();
			}
			catch(IOException ex)
			{}
			dispose();
		}
		if(e.getSource()==b12)
		{
			try
			{
				File file=new File("material.txt");
				FileWriter fw=new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw=new BufferedWriter(fw);
				bw.write("w12");
				bw.close();
			}
			catch(IOException ex)
			{}
			dispose();
		}
		if(e.getSource()==b13)
		{
			try
			{
				File file=new File("material.txt");
				FileWriter fw=new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw=new BufferedWriter(fw);
				bw.write("w13");
				bw.close();
			}
			catch(IOException ex)
			{}
			dispose();
		}
		if(e.getSource()==b14)
		{
			try
			{
				File file=new File("material.txt");
				FileWriter fw=new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw=new BufferedWriter(fw);
				bw.write("w14");
				bw.close();
			}
			catch(IOException ex)
			{}
			dispose();
		}
		if(e.getSource()==b15)
		{
			try
			{
				File file=new File("material.txt");
				FileWriter fw=new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw=new BufferedWriter(fw);
				bw.write("w15");
				bw.close();
			}
			catch(IOException ex)
			{}
			dispose();
		}
		if(e.getSource()==b16)
		{
			try
			{
				File file=new File("material.txt");
				FileWriter fw=new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw=new BufferedWriter(fw);
				bw.write("w16");
				bw.close();
			}
			catch(IOException ex)
			{}
			dispose();
		}
		if(e.getSource()==b17)
		{
			try
			{
				File file=new File("material.txt");
				FileWriter fw=new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw=new BufferedWriter(fw);
				bw.write("w17");
				bw.close();
			}
			catch(IOException ex)
			{}
			dispose();
		}
		if(e.getSource()==b18)
		{
			try
			{
				File file=new File("material.txt");
				FileWriter fw=new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw=new BufferedWriter(fw);
				bw.write("w18");
				bw.close();
			}
			catch(IOException ex)
			{}
			dispose();
		}
		if(e.getSource()==b19)
		{
			try
			{
				File file=new File("material.txt");
				FileWriter fw=new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw=new BufferedWriter(fw);
				bw.write("w19");
				bw.close();
			}
			catch(IOException ex)
			{}
			dispose();
		}
		if(e.getSource()==b20)
		{
			try
			{
				File file=new File("material.txt");
				FileWriter fw=new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw=new BufferedWriter(fw);
				bw.write("w20");
				bw.close();
			}
			catch(IOException ex)
			{}
			dispose();
		}
		if(e.getSource()==b21)
		{
			try
			{
				File file=new File("material.txt");
				FileWriter fw=new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw=new BufferedWriter(fw);
				bw.write("w21");
				bw.close();
			}
			catch(IOException ex)
			{}
			dispose();
		}
		if(e.getSource()==b22)
		{
			try
			{
				File file=new File("material.txt");
				FileWriter fw=new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw=new BufferedWriter(fw);
				bw.write("w22");
				bw.close();
			}
			catch(IOException ex)
			{}
			dispose();
		}
		if(e.getSource()==b23)
		{
			try
			{
				File file=new File("material.txt");
				FileWriter fw=new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw=new BufferedWriter(fw);
				bw.write("w23");
				bw.close();
			}
			catch(IOException ex)
			{}
			dispose();
		}
		if(e.getSource()==b24)
		{
			try
			{
				File file=new File("material.txt");
				FileWriter fw=new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw=new BufferedWriter(fw);
				bw.write("w24");
				bw.close();
			}
			catch(IOException ex)
			{}
			dispose();
		}
		if(e.getSource()==b25)
		{
			try
			{
				File file=new File("material.txt");
				FileWriter fw=new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw=new BufferedWriter(fw);
				bw.write("w25");
				bw.close();
			}
			catch(IOException ex)
			{}
			dispose();
		}
		if(e.getSource()==b26)
		{
			try
			{
				File file=new File("material.txt");
				FileWriter fw=new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw=new BufferedWriter(fw);
				bw.write("w26");
				bw.close();
			}
			catch(IOException ex)
			{}
			dispose();
		}
		if(e.getSource()==b27)
		{
			try
			{
				File file=new File("material.txt");
				FileWriter fw=new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw=new BufferedWriter(fw);
				bw.write("w27");
				bw.close();
			}
			catch(IOException ex)
			{}
			dispose();
		}
		
	}
}
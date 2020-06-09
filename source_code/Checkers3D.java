import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Checkers3D extends JFrame
{
  

  public Checkers3D() 
  {
    super("SketchUp3D");
		
	Container c = getContentPane();
    c.setLayout( new BorderLayout());
	
    WrapCheckers3D w3d = new WrapCheckers3D();     // panel holding the 3D canvas
    c.add(w3d, BorderLayout.CENTER);
	setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    pack();
	setSize(1366,768);
    setResizable(true);    // fixed size display
    setVisible(true);
  } // end of Checkers3D()
  
// -----------------------------------------

  
}


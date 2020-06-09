
// CheckerFloor.java
// Andrew Davison, April 2005, ad@fivedots.coe.psu.ac.th

/* The floor is a blue and green chessboard, with a small red square
   at the (0,0) position on the (X,Z) plane, and with numbers along
   the X- and Z- axes.
*/

import java.awt.*;
import javax.media.j3d.*;
import com.sun.j3d.utils.geometry.Text2D;
import javax.vecmath.*;
import java.util.ArrayList;


public class CheckerFloor
{
  private final static int FLOOR_LEN = 50;  // should be even

  // colours for floor, etc
  private final static Color3f floorgray1 = new Color3f(0.7f, 0.7f, 0.7f);
  private final static Color3f floorgray2 = new Color3f(0.67f, 0.67f, 0.67f);
  private final static Color3f medRed = new Color3f(0.0f, 0.0f, 0.0f);
  private final static Color3f white = new Color3f(1.0f, 1.0f, 1.0f);

  private BranchGroup floorBG;


  public CheckerFloor()
  // create tiles, add origin marker, then the axes labels
  {
    ArrayList floorgray1Coords = new ArrayList();//can change the array size automatically, to save each blue brick's four point
    ArrayList floorgray2Coords = new ArrayList();//can change the array size automatically, to save each green brick's four point 
    floorBG = new BranchGroup();

    boolean isBlue;//to check if the current is blue
    for(int z=-FLOOR_LEN/2; z <= (FLOOR_LEN/2)-1; z++) {
      isBlue = (z%2 == 0)? true : false;    // set color for new row
      for(int x=-FLOOR_LEN/2; x <= (FLOOR_LEN/2)-1; x++) {
        if (isBlue)
          createCoords(x, z, floorgray1Coords,1);//put the four point according to the x,z and the color in the arraylist
        else 
          createCoords(x, z, floorgray2Coords,0);//put the four point according to the x,z and the color in the arraylist
        isBlue = !isBlue;//for each row element, change the color after each brick is created
      }
    }
    floorBG.addChild( new ColouredTiles(floorgray1Coords, floorgray1) );//the odd number for blue bricks
    floorBG.addChild( new ColouredTiles(floorgray2Coords, floorgray2) );//the even number for green bricks

    addOriginMarker();
    labelAxes();
  }  // end of CheckerFloor()


  private void createCoords(int x, int z, ArrayList coords,int line)
  // Coords for a single blue or green square, 
  // its left hand corner at (x,0,z)
  {
    // points created in counter-clockwise order
	if(line==0){
		Point3f p1 = new Point3f(x, 0.0f, z+0.02f);//four number have been modifyed
		Point3f p2 = new Point3f(x+2.0f, 0.0f, z+0.02f);
		Point3f p3 = new Point3f(x+2.0f, 0.0f, z);
		Point3f p4 = new Point3f(x, 0.0f, z);   
		coords.add(p1); coords.add(p2); 
		coords.add(p3); coords.add(p4);
		Point3f p5 = new Point3f(x, 0.0f, z+2.0f);//four number have been modifyed
		Point3f p6 = new Point3f(x+0.02f, 0.0f, z+2.0f);
		Point3f p7 = new Point3f(x+0.02f, 0.0f, z);
		Point3f p8 = new Point3f(x, 0.1f, z);   
		coords.add(p5); coords.add(p6); 
		coords.add(p7); coords.add(p8);
		
	}
    else{
		Point3f p1 = new Point3f(x+0.02f, 0.0f, z+2.0f);//four number have been modifyed
		Point3f p2 = new Point3f(x+2.0f, 0.0f, z+2.0f);
		Point3f p3 = new Point3f(x+2.0f, 0.0f, z+0.02f);
		Point3f p4 = new Point3f(x+0.02f, 0.0f, z+0.02f);   
		coords.add(p1); coords.add(p2); 
		coords.add(p3); coords.add(p4);
	}
  }  // end of createCoords()


  private void addOriginMarker()
  // A red square centered at (0,0,0), of length 0.5
  {  // points created counter-clockwise, a bit above the floor for us to see more clearly
    Point3f p1 = new Point3f(-0.25f, 0.01f, 0.25f);
    Point3f p2 = new Point3f(0.25f, 0.01f, 0.25f);
    Point3f p3 = new Point3f(0.25f, 0.01f, -0.25f);    
    Point3f p4 = new Point3f(-0.25f, 0.01f, -0.25f);

    ArrayList oCoords = new ArrayList();//create new arraylist to point out the origin
    oCoords.add(p1); oCoords.add(p2);
    oCoords.add(p3); oCoords.add(p4);

    floorBG.addChild( new ColouredTiles(oCoords, medRed) );//create the red brick for the origin
  } // end of addOriginMarker();


  private void labelAxes()//draw the labels
  // Place numbers along the X- and Z-axes at the integer positions
  {
    Vector3d pt = new Vector3d();//create a Vecto3d object: pt (0,0,0)
    for (int i=-FLOOR_LEN/2; i <= FLOOR_LEN/2; i++) {
      pt.x = i;
      floorBG.addChild( makeText(pt,""+i) );//draw the label with the string ""+i at the vector pt 
	  // along x-axis
    }

    pt.x = 0;//set the Vector3d object: pt again to (0,0,0)
    for (int i=-FLOOR_LEN/2; i <= FLOOR_LEN/2; i++) {
      pt.z = i;
      floorBG.addChild( makeText(pt,""+i) );//draw the label with the string ""+i at the vector pt
	  // along z-axis
    }
  }  // end of labelAxes()


  private TransformGroup makeText(Vector3d vertex, String text)
  // Create a Text2D object at the specified vertex
  {
    Text2D message = new Text2D(text, white, "SansSerif", 36, Font.BOLD );
       // 36 point bold Sans Serif

    TransformGroup tg = new TransformGroup();
    Transform3D t3d = new Transform3D();
    t3d.setTranslation(vertex);//set the displacement
    tg.setTransform(t3d);
    tg.addChild(message);//displace the Text2D object:message
    return tg;
  } // end of getTG()


  public BranchGroup getBG()
  {
	return floorBG;//return this class' BranchGroup object:floorBG
  }


}  // end of CheckerFloor class


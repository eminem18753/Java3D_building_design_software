
// ColouredTiles.java
// Andrew Davison, April 2005, ad@fivedots.coe.psu.ac.th

// ColouredTiles creates a coloured quad array of tiles.
// No lighting since no normals or Material used

import javax.media.j3d.*;
import javax.vecmath.*;
import java.util.ArrayList;


public class ColouredTiles extends Shape3D 
{
  private QuadArray plane;


  public ColouredTiles(ArrayList coords, Color3f col)//coords: the point arraylist, colof3f:the color of the brick
  {
    plane = new QuadArray(coords.size(),GeometryArray.COORDINATES | GeometryArray.COLOR_3 );
	//the first parameter of QuadArray is the number of vertexes
	//GeometryArray.COORDINATES is a constant that specifies if this array contains an array of coordinates
	//GeometryArray.COLOR_3 is a constant that specifies if this array contains an array of colors without alpha(transparency)
    createGeometry(coords, col);
    createAppearance();
  }    


  private void createGeometry(ArrayList coords, Color3f col)//coords: the point arraylist, colof3f:the color of the brick
  { 
    int numPoints = coords.size();//numPoints: the size of the point arraylist

    Point3f[] points = new Point3f[numPoints];//create a point array with its size according to the size of the point arraylist
    coords.toArray( points );//returns an array containing all of the elements in this list in proper sequence
    plane.setCoordinates(0, points);
	//0:the starting destination vertex index in this geometry array
	//points:source array of points containing new coordinates

    Color3f cols[] = new Color3f[numPoints];//create an array of Color3f with the size according to the number of points
    for(int i=0; i < numPoints; i++)
      cols[i] = col;
    plane.setColors(0, cols);
	//0:the starting destination vertex index in this geometry array
	//colors:the source array of Colorf objects containing new colors
    setGeometry(plane);//draw it out
  }  // end of createGeometry()


  private void createAppearance()
  {
    Appearance app = new Appearance();

    PolygonAttributes pa = new PolygonAttributes();//defines attributes for rendering polygon primitives
    pa.setCullFace(PolygonAttributes.CULL_NONE); //don't perform any face culling 
      // so can see the ColouredTiles from both sides
    app.setPolygonAttributes(pa);//sets the polygonAttributes object to the specified object

    setAppearance(app);//set the main appearance of the primitive(all subparts) to same appearance
  }  // end of createAppearance()


} // end of ColouredTiles class

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.behaviors.vp.*;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.picking.*; 
import com.sun.j3d.utils.picking.behaviors.*;
import com.sun.j3d.utils.picking.PickIntersection;
import com.sun.j3d.utils.picking.PickResult;
import com.sun.j3d.utils.picking.PickTool;
import javax.vecmath.Point3d;
// import com.tornadolabs.j3dtree.*;    // for displaying the scene graph




public class WrapCheckers3D extends JPanel implements ActionListener,MouseListener

{
  private static final int PWIDTH = 512;   // size of panel
  private static final int PHEIGHT = 512; 

  private static final int BOUNDSIZE = 1000000;  // larger than world

  private static final Point3d USERPOSN = new Point3d(0,5,20);	// initial user position

  private ExamplePickBehavior behavior;
  private SimpleUniverse su;
  private BranchGroup sceneBG;
  private BoundingSphere bounds;   // for environment nodes

  private com.sun.j3d.utils.geometry.Box visual;//0531
  private Vector3f visualVector;//0531
  private BranchGroup visualGroup;//0531
  private Canvas3D canvas3D;
  private JLabel picture;
  private JButton b1;//for cylinder
  private JTextField t1;//
  private JButton b2;
  private JButton b3;
  private JButton b5;//for stretch
  private JButton b6;
  private JButton b7;
  private JButton b8;
  private JPanel button_panel;
  private JPanel explain_panel;
  // private Java3dTree j3dTree;   // frame to hold tree display
  private float radius;
  private float height;
  private float x;
  private float y;
  private float z;
  private Color3f blue;
  private Appearance a;
  private com.sun.j3d.utils.geometry.Box[] box;
  private Cylinder[] cylinder;
  private Sphere[] sphere;
  private com.sun.j3d.utils.geometry.Box[] boxn;
  private Cylinder[] cylindern;
  private Sphere[] spheren;
  private BranchGroup[] boxGroup;//to set capability detach
  private BranchGroup[] cylinderGroup;//to set capability detach
  private BranchGroup[] sphereGroup;//to set capability detach
  private BranchGroup[] boxGroupn;
  private BranchGroup[] cylinderGroupn;
  private BranchGroup[] sphereGroupn;
  private Vector3f[] boxVector;//to store the same vector
  private Vector3f[] cylinderVector;//to store the same vector
  private Vector3f[] sphereVector;//to store the same vector
  private Vector3f[] boxVectorn;
  private Vector3f[] cylinderVectorn;
  private Vector3f[] sphereVectorn;
  private float[] boxX;
  private float[] boxY;
  private float[] boxZ;
  private float[] boxXn;
  private float[] boxYn;
  private float[] boxZn;
  private Color3f[] boxColor;
  private Color3f[] cylinderColor;
  private Color3f[] sphereColor;
  private float[] cylinderRadius;
  private float[] cylinderRadiusn;
  private float[] cylinderHeight;
  private float[] cylinderHeightn;
  private float[] sphereRadius;
  private float[] sphereRadiusn;
  private int boxSelect=-1;
  private int cylinderSelect=-1;
  private int cylinderSelectn=-1;
  private int sphereSelect=-1;
  private int sphereSelectn=-1;
  private int boxSelectn=-1;
  private int boxCount=0;
  private int cylinderCount=0;
  private int cylinderCountn=0;
  private int sphereCount=0;
  private int sphereCountn=0;
  private int boxCountn=0;
  private String[] boxMaterial;
  private String[] cylinderMaterial;
  private String[] sphereMaterial;
  private int checker=0;
  private int choose=0;
  private int remove=0;
  private int distanceX1=0;
  private int distanceX2=0;
  private int distanceY1=0;
  private int distanceY2=0;
  private double distance=0;
  private int c1=0;
  private int m1=0;
  private int main1=0;
  private int main2=0;
  private String h1;

  //private PickCanvas pickCanvas;
  
  public WrapCheckers3D()
  // A panel holding a 3D canvas: the usual way of linking Java 3D to Swing
  {
	int i=0;
    setLayout( new BorderLayout() );
    setOpaque( false );//set the panel transparent
    setPreferredSize( new Dimension(PWIDTH, PHEIGHT));//set the size of the panel

    GraphicsConfiguration config =	SimpleUniverse.getPreferredConfiguration();//get the characteristics of the current platform
    canvas3D = new Canvas3D(config);//create a place that you can put your 3D object on
    add("Center", canvas3D);//put the place that you put your 3D object on in the center
    canvas3D.setFocusable(true);     // give focus to the canvas 
    canvas3D.requestFocus();//requests that this Component gets the input focus
	
    su = new SimpleUniverse(canvas3D);//create a new SimpleUniverse and put it on canvas3D

	blue = new Color3f(0.3f, 0.3f, 0.3f);
	button_panel=new JPanel();
	button_panel.setLayout(new FlowLayout(FlowLayout.LEADING,20,20));//number of buttons
	explain_panel=new JPanel();
	explain_panel.setLayout(new BorderLayout());
	Icon c1=new ImageIcon(getClass().getResource("b1.png"));
	Icon c2=new ImageIcon(getClass().getResource("b2.png"));
	Icon c3=new ImageIcon(getClass().getResource("b3.png"));
	Icon c5=new ImageIcon(getClass().getResource("b5.png"));
	Icon c6=new ImageIcon(getClass().getResource("b6.png"));
	Icon c7=new ImageIcon(getClass().getResource("b7.png"));
	Icon c8=new ImageIcon(getClass().getResource("b8.jpg"));
	b1=new JButton("Cylinder",c1);
	b2=new JButton("Box",c2);
	b3=new JButton("Sphere",c3);
	b5=new JButton("Stretch",c5);
	b6=new JButton("Delete",c6);
	b7=new JButton("Color",c7);
	b8=new JButton("Material",c8);
	b1.addActionListener(this);
	b2.addActionListener(this);
	b3.addActionListener(this);
	b5.addActionListener(this);
	b6.addActionListener(this);
	b7.addActionListener(this);
	b8.addActionListener(this);
	canvas3D.addMouseListener(this);
	addMouseListener(this);
	Icon p1=new ImageIcon(getClass().getResource("explain.jpg"));
	picture=new JLabel("",p1,SwingConstants.CENTER);
	JScrollPane jsp=new JScrollPane(picture);
	button_panel.add(b1);
	button_panel.add(b2);
	button_panel.add(b3);
	button_panel.add(b5);
	button_panel.add(b6);
	button_panel.add(b7);
	button_panel.add(b8);
	
	explain_panel.add(jsp);
	add(button_panel,BorderLayout.NORTH);
	add(explain_panel,BorderLayout.EAST);
	//add(sp,BorderLayout.SOUTH);
	
	box=new com.sun.j3d.utils.geometry.Box[100];
	boxn=new com.sun.j3d.utils.geometry.Box[100];
	cylinder=new Cylinder[100];
	cylindern=new Cylinder[100];
	sphere=new Sphere[100];
	spheren=new Sphere[100];

	boxVector=new Vector3f[100];
	boxVectorn=new Vector3f[100];
	cylinderVector=new Vector3f[100];
	cylinderVectorn=new Vector3f[100];
	sphereVector=new Vector3f[100];
	sphereVectorn=new Vector3f[100];
	
	boxGroup=new BranchGroup[100];
	boxGroupn=new BranchGroup[100];
	cylinderGroup=new BranchGroup[100];
	cylinderGroupn=new BranchGroup[100];
	sphereGroup=new BranchGroup[100];
	sphereGroupn=new BranchGroup[100];
	
	boxX=new float[100];
	boxY=new float[100];
	boxZ=new float[100];
	boxXn=new float[100];
	boxYn=new float[100];
	boxZn=new float[100];
	cylinderRadius=new float[100];
	cylinderRadiusn=new float[100];
	cylinderHeight=new float[100];
	cylinderHeightn=new float[100];
	sphereRadius=new float[100];
	sphereRadiusn=new float[100];
	boxColor=new Color3f[100];
	cylinderColor=new Color3f[100];
	sphereColor=new Color3f[100];
    
	boxMaterial=new String[100];
	cylinderMaterial=new String[100];
	sphereMaterial=new String[100];
	createSceneGraph();		   // initilize the scene
    initUserPosition();        // set user's viewpoint
    orbitControls(canvas3D);   // controls for moving the viewpoint
    
    su.addBranchGraph( sceneBG );
	
  } // end of WrapCheckers3D()



  private void createSceneGraph() 
  // initilize the scene
  { 
    sceneBG = new BranchGroup();//create a new BranchGroup for sceneBG
    bounds = new BoundingSphere(new Point3d(0,0,0), BOUNDSIZE);//create a new BoundingSphere for bounds where objects take effect
	sceneBG.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
	sceneBG.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
    sceneBG.setCapability(BranchGroup.ALLOW_DETACH);
	lightScene();         // add the lights
    addBackground();      // add the sky
    sceneBG.addChild( new CheckerFloor().getBG() );  // add the floor

  	//System.out.println(sceneBG.getChild(4));
	//sceneBG.removeChild(4);
	behavior=new ExamplePickBehavior(canvas3D,sceneBG,bounds);
	sceneBG.addChild(behavior);
	
    sceneBG.compile();   // compiles the source BranchGroup associated with this object and creates and caches a compiled scene graph
	// fix the scene
  } // end of createSceneGraph()


  private void lightScene()
  /* One ambient light, 2 directional lights */
  {
    Color3f white = new Color3f(1.0f, 1.0f, 1.0f);

    // Set up the ambient light
    AmbientLight ambientLightNode = new AmbientLight(white);//create white ambientLight(environmental)
    ambientLightNode.setInfluencingBounds(bounds);//set the bounding sphere where the light takes effect
    sceneBG.addChild(ambientLightNode);//add the ambientLight to the sceneBG

    // Set up the directional lights
    Vector3f light1Direction  = new Vector3f(-1.0f, -1.0f, -1.0f);//set the direction of light1
       // left, down, backwards 
    Vector3f light2Direction  = new Vector3f(1.0f, -1.0f, 1.0f);//set the direction of light2
       // right, down, forwards

    DirectionalLight light1 = new DirectionalLight(white, light1Direction);//set light1 with color white and corresponding direction
    light1.setInfluencingBounds(bounds);//set the bounding sphere where light1 takes effect
    sceneBG.addChild(light1);//add light1 to the sceneBG

    DirectionalLight light2 = new DirectionalLight(white, light2Direction);//set light2 with color white and corresponding direction
    light2.setInfluencingBounds(bounds);//set the bounding sphere where light2 takes effect
    sceneBG.addChild(light2);//add light2 to the sceneBG
  }  // end of lightScene()



  private void addBackground()
  // A blue sky
  { 
	Background back = new Background();//constructs a background node with default color
    back.setApplicationBounds( bounds );//set the Background's application region to the specified bounds
    back.setColor(0.9f, 0.9f, 0.9f);    // sky color
    sceneBG.addChild( back );//add the Background node:back to the sceneBG
  }  // end of addBackground()



  private void orbitControls(Canvas3D c)
  /* OrbitBehaviour allows the user to rotate around the scene, and to
     zoom in and out.  */
  {
    OrbitBehavior orbit = new OrbitBehavior(c, OrbitBehavior.REVERSE_ALL);
	//OrbitBehavior moves the view around a point of interest when the
	//mouse is dragged with a mouse button pressed, user can rotate,
	//zoom in, and zoom out
    orbit.setSchedulingBounds(bounds);
	//set the behavior's scheduling region to the specified bound
    ViewingPlatform vp = su.getViewingPlatform();
	//The ViewingPlatform class is used to set up the "view" side of a Java 3D
	//scene graph. The ViewingPlatform object contains a MultiTransformGroup
	//node to allow for a series of transforms to be linked together. To this
	//structure the ViewPlatform is added as well as any geometry to associate
	//with this view platform
    vp.setViewPlatformBehavior(orbit);	    
	//set the ViewPlatformBehavior which will operate on the ViewPlatform transform
  }  // end of orbitControls()

  private void initUserPosition(){
  // Set the user's initial viewpoint using lookAt()
    ViewingPlatform vp = su.getViewingPlatform();
	//The ViewingPlatform class is used to set up the "view" side of a Java 3D
	//scene graph. The ViewingPlatform object contains a MultiTransformGroup
	//node to allow for a series of transforms to be linked together. To this
	//structure the ViewPlatform is added as well as any geometry to associate
	//with this view platform
    TransformGroup steerTG = vp.getViewPlatformTransform();
	//returns a reference to the "bottom most" transfrom in the MultiTansformGroup
	//that is above the ViewPlatform node
    Transform3D t3d = new Transform3D();//create a new Transform3D object for t3d
    steerTG.getTransform(t3d);//copies the transform component of the TransformGroup
	//object:steer TG into the passed Transform3D object:t3d

    // args are: viewer posn, where looking, up direction
    t3d.lookAt( USERPOSN, new Point3d(0,0,0), new Vector3d(0,1,0));
	//USERPOSN:Point3d:eye,  new Point3d(0,0,0):center, new Vector3d(0,1,0):up
    t3d.invert();//inverts the transform in place

    steerTG.setTransform(t3d);//set the transform component of the TransformGroup object:
	//steerTG to the value of the passed Transform3D object:t3d
  }  // end of initUserPosition()
 
  public void Cylinders(float r,float h,Vector3f v)
  {
	setRadius(r);
	setHeight(h);
	setAppearance2();
	
	cylinderGroup[cylinderCount]=new BranchGroup();
	cylinderGroup[cylinderCount].setCapability(BranchGroup.ALLOW_DETACH);//to let the cylinder be able to disappear
	
	Transform3D t3d=new Transform3D();
	//t3d.set(new Vector3f(5,0,0));
	t3d.set(v);
	TransformGroup tg=new TransformGroup(t3d);
	tg.addChild(cylinder[cylinderCount]=new Cylinder(r,h,a));
	cylinderRadius[cylinderCount]=r;
	cylinderHeight[cylinderCount]=h;
	cylinderVector[cylinderCount]=v;
	cylinderColor[cylinderCount]=blue;
	cylinderGroup[cylinderCount].addChild(tg);
	cylinderGroup[cylinderCount].compile();
	sceneBG.addChild(cylinderGroup[cylinderCount]);
	cylinderCount++;
}
  public void Cylindersn(float r,float h,Vector3f v)
  {
	setRadius(r);
	setHeight(h);
	setAppearance2();
	
	cylinderGroupn[cylinderCountn]=new BranchGroup();
	cylinderGroupn[cylinderCountn].setCapability(BranchGroup.ALLOW_DETACH);//to let the cylinder be able to disappear
	
	Transform3D t3d=new Transform3D();
	//t3d.set(new Vector3f(5,0,0));
	t3d.set(v);
	TransformGroup tg=new TransformGroup(t3d);
	tg.addChild(cylindern[cylinderCountn]=new Cylinder(r,h,Cylinder.GENERATE_TEXTURE_COORDS,a));
	if(m1==1)
	{
		File file=new File("material.txt");
		if(main2==0)
		{
		try
		{
			Scanner sc=new Scanner(file);
			h1=sc.nextLine();
			sc.close();
		}
		catch(FileNotFoundException ex)
		{}
		}
		if(h1.equals("w1"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r1.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			cylindern[cylinderCountn].setAppearance(Cylinder.TOP,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BOTTOM,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BODY,app1);
		}
		if(h1.equals("w2"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r2.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			cylindern[cylinderCountn].setAppearance(Cylinder.TOP,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BOTTOM,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BODY,app1);
		}
		if(h1.equals("w3"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r3.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			cylindern[cylinderCountn].setAppearance(Cylinder.TOP,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BOTTOM,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BODY,app1);
		}
		if(h1.equals("w4"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r4.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			cylindern[cylinderCountn].setAppearance(Cylinder.TOP,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BOTTOM,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BODY,app1);
		}
		if(h1.equals("w5"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r5.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			cylindern[cylinderCountn].setAppearance(Cylinder.TOP,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BOTTOM,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BODY,app1);
		}
		if(h1.equals("w6"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r6.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			cylindern[cylinderCountn].setAppearance(Cylinder.TOP,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BOTTOM,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BODY,app1);
		}
		if(h1.equals("w7"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r7.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			cylindern[cylinderCountn].setAppearance(Cylinder.TOP,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BOTTOM,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BODY,app1);
		}
		if(h1.equals("w8"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r9.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			cylindern[cylinderCountn].setAppearance(Cylinder.TOP,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BOTTOM,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BODY,app1);
		}
		if(h1.equals("w9"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r9.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			cylindern[cylinderCountn].setAppearance(Cylinder.TOP,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BOTTOM,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BODY,app1);
		}
		if(h1.equals("w10"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r10.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			cylindern[cylinderCountn].setAppearance(Cylinder.TOP,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BOTTOM,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BODY,app1);
		}
		if(h1.equals("w11"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r11.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			cylindern[cylinderCountn].setAppearance(Cylinder.TOP,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BOTTOM,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BODY,app1);
		}
		if(h1.equals("w12"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r12.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			cylindern[cylinderCountn].setAppearance(Cylinder.TOP,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BOTTOM,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BODY,app1);
		}
		if(h1.equals("w13"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r13.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			cylindern[cylinderCountn].setAppearance(Cylinder.TOP,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BOTTOM,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BODY,app1);
		}
		if(h1.equals("w14"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r14.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			cylindern[cylinderCountn].setAppearance(Cylinder.TOP,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BOTTOM,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BODY,app1);
		}
		if(h1.equals("w15"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r15.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			cylindern[cylinderCountn].setAppearance(Cylinder.TOP,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BOTTOM,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BODY,app1);
		}
		if(h1.equals("w16"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r16.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			cylindern[cylinderCountn].setAppearance(Cylinder.TOP,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BOTTOM,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BODY,app1);
		}
		if(h1.equals("w17"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r17.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			cylindern[cylinderCountn].setAppearance(Cylinder.TOP,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BOTTOM,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BODY,app1);
		}
		if(h1.equals("w18"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r18.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			cylindern[cylinderCountn].setAppearance(Cylinder.TOP,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BOTTOM,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BODY,app1);
		}
		if(h1.equals("w19"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r19.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			cylindern[cylinderCountn].setAppearance(Cylinder.TOP,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BOTTOM,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BODY,app1);
		}
		if(h1.equals("w20"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r20.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			cylindern[cylinderCountn].setAppearance(Cylinder.TOP,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BOTTOM,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BODY,app1);
		}
		if(h1.equals("w21"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r21.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			cylindern[cylinderCountn].setAppearance(Cylinder.TOP,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BOTTOM,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BODY,app1);
		}
		if(h1.equals("w22"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r22.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			cylindern[cylinderCountn].setAppearance(Cylinder.TOP,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BOTTOM,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BODY,app1);
		}
		if(h1.equals("w23"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r23.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			cylindern[cylinderCountn].setAppearance(Cylinder.TOP,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BOTTOM,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BODY,app1);
		}
		if(h1.equals("w24"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r24.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			cylindern[cylinderCountn].setAppearance(Cylinder.TOP,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BOTTOM,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BODY,app1);
		}
		if(h1.equals("w25"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r25.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			cylindern[cylinderCountn].setAppearance(Cylinder.TOP,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BOTTOM,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BODY,app1);
		}
		if(h1.equals("w26"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r26.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			cylindern[cylinderCountn].setAppearance(Cylinder.TOP,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BOTTOM,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BODY,app1);
		}
		if(h1.equals("w27"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r27.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			cylindern[cylinderCountn].setAppearance(Cylinder.TOP,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BOTTOM,app1);
			cylindern[cylinderCountn].setAppearance(Cylinder.BODY,app1);
		}
	}
	main2=0;
	cylinderRadiusn[cylinderCountn]=r;
	cylinderHeightn[cylinderCountn]=h;
	cylinderVectorn[cylinderCountn]=v;
	cylinderMaterial[cylinderCountn]=h1;
	cylinderGroupn[cylinderCountn].addChild(tg);
	cylinderGroupn[cylinderCountn].compile();
	sceneBG.addChild(cylinderGroupn[cylinderCountn]);
	cylinderCountn++;
}
  public void Boxes(float x, float y, float z,Vector3f v)
  {
	setX(x);
	setY(y);
	setZ(z);
	setAppearance2();
	
	boxGroup[boxCount]=new BranchGroup();
	boxGroup[boxCount].setCapability(BranchGroup.ALLOW_DETACH);//to let the box be able to disappear

	Transform3D t3d=new Transform3D();
	t3d.set(v);
	TransformGroup tg=new TransformGroup(t3d);
	tg.addChild(box[boxCount]=new com.sun.j3d.utils.geometry.Box(x,y,z,a));
	/*
	TextureLoader loader;
	Texture texture;
	loader=new TextureLoader("Explain.jpg",this);
	texture=loader.getTexture();
	Appearance app1=new Appearance();
	app1.setTexture(texture);
	box[boxCount].setAppearance(com.sun.j3d.utils.geometry.Box.TOP,app1);
	*/
	boxX[boxCount]=x;
	boxY[boxCount]=y;
	boxZ[boxCount]=z;
	boxColor[boxCount]=blue;
	boxVector[boxCount]=v;
	boxGroup[boxCount].addChild(tg);
	boxGroup[boxCount].compile();
	sceneBG.addChild(boxGroup[boxCount]);

    boxCount++;
}
	public void Boxesn(float x, float y, float z,Vector3f v)
  {
	setX(x);
	setY(y);
	setZ(z);
	setAppearance2();
	
	boxGroupn[boxCountn]=new BranchGroup();
	boxGroupn[boxCountn].setCapability(BranchGroup.ALLOW_DETACH);//to let the box be able to disappear

	Transform3D t3d=new Transform3D();
	t3d.set(v);
	TransformGroup tg=new TransformGroup(t3d);
	tg.addChild(boxn[boxCountn]=new com.sun.j3d.utils.geometry.Box(x,y,z,com.sun.j3d.utils.geometry.Box.GENERATE_TEXTURE_COORDS,a));
	if(m1==1)
	{
		File file=new File("material.txt");
		if(main2==0)
		{
		try
		{
			Scanner sc=new Scanner(file);
			h1=sc.nextLine();
			sc.close();
		}
		catch(FileNotFoundException ex)
		{}
		}
		if(h1.equals("w1"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r1.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.TOP,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BOTTOM,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.FRONT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BACK,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.LEFT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.RIGHT,app1);
		}
		if(h1.equals("w2"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r2.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.TOP,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BOTTOM,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.FRONT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BACK,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.LEFT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.RIGHT,app1);
		}
		if(h1.equals("w3"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r3.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.TOP,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BOTTOM,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.FRONT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BACK,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.LEFT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.RIGHT,app1);
		}
		if(h1.equals("w4"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r4.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.TOP,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BOTTOM,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.FRONT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BACK,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.LEFT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.RIGHT,app1);
		}
		if(h1.equals("w5"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r5.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.TOP,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BOTTOM,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.FRONT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BACK,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.LEFT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.RIGHT,app1);
		}
		if(h1.equals("w6"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r6.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.TOP,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BOTTOM,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.FRONT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BACK,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.LEFT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.RIGHT,app1);
		}
		if(h1.equals("w7"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r7.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.TOP,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BOTTOM,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.FRONT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BACK,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.LEFT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.RIGHT,app1);
		}
		if(h1.equals("w8"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r8.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.TOP,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BOTTOM,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.FRONT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BACK,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.LEFT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.RIGHT,app1);
		}
		if(h1.equals("w9"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r9.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.TOP,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BOTTOM,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.FRONT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BACK,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.LEFT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.RIGHT,app1);
		}
		if(h1.equals("w10"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r10.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.TOP,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BOTTOM,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.FRONT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BACK,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.LEFT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.RIGHT,app1);
		}
		if(h1.equals("w11"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r11.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.TOP,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BOTTOM,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.FRONT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BACK,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.LEFT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.RIGHT,app1);
		}
		if(h1.equals("w12"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r12.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.TOP,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BOTTOM,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.FRONT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BACK,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.LEFT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.RIGHT,app1);
		}
		if(h1.equals("w13"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r13.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.TOP,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BOTTOM,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.FRONT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BACK,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.LEFT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.RIGHT,app1);
		}
		if(h1.equals("w14"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r14.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.TOP,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BOTTOM,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.FRONT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BACK,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.LEFT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.RIGHT,app1);
		}
		if(h1.equals("w15"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r15.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.TOP,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BOTTOM,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.FRONT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BACK,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.LEFT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.RIGHT,app1);
		}
		if(h1.equals("w16"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r16.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.TOP,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BOTTOM,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.FRONT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BACK,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.LEFT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.RIGHT,app1);
		}
		if(h1.equals("w17"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r17.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.TOP,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BOTTOM,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.FRONT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BACK,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.LEFT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.RIGHT,app1);
		}
		if(h1.equals("w18"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r18.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.TOP,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BOTTOM,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.FRONT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BACK,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.LEFT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.RIGHT,app1);
		}
		if(h1.equals("w19"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r19.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.TOP,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BOTTOM,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.FRONT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BACK,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.LEFT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.RIGHT,app1);
		}
		if(h1.equals("w20"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r20.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.TOP,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BOTTOM,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.FRONT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BACK,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.LEFT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.RIGHT,app1);
		}
		if(h1.equals("w21"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r21.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.TOP,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BOTTOM,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.FRONT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BACK,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.LEFT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.RIGHT,app1);
		}
		if(h1.equals("w22"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r22.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.TOP,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BOTTOM,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.FRONT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BACK,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.LEFT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.RIGHT,app1);
		}
		if(h1.equals("w23"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r23.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.TOP,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BOTTOM,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.FRONT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BACK,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.LEFT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.RIGHT,app1);
		}
		if(h1.equals("w24"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r24.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.TOP,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BOTTOM,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.FRONT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BACK,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.LEFT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.RIGHT,app1);
		}
		if(h1.equals("w25"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r25.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.TOP,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BOTTOM,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.FRONT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BACK,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.LEFT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.RIGHT,app1);
		}
		if(h1.equals("w26"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r26.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.TOP,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BOTTOM,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.FRONT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BACK,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.LEFT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.RIGHT,app1);
		}
		if(h1.equals("w27"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r27.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.TOP,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BOTTOM,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.FRONT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.BACK,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.LEFT,app1);
			boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.RIGHT,app1);
		}

	}
	main2=0;
	boxXn[boxCountn]=x;
	boxYn[boxCountn]=y;
	boxZn[boxCountn]=z;
	boxVectorn[boxCountn]=v;
	boxMaterial[boxCountn]=h1;
	boxGroupn[boxCountn].addChild(tg);
	boxGroupn[boxCountn].compile();
	sceneBG.addChild(boxGroupn[boxCountn]);

    boxCountn++;
}
   public void Visual(float x, float y, float z,Vector3f v)//0531
  {
	setX(x);
	setY(y);
	setZ(z);
	setAppearancevisual();
	
	visualGroup=new BranchGroup();
	visualGroup.setCapability(BranchGroup.ALLOW_DETACH);//to let the box be able to disappear

	Transform3D t3d=new Transform3D();
	t3d.set(v);
	TransformGroup tg=new TransformGroup(t3d);
	tg.addChild(visual=new com.sun.j3d.utils.geometry.Box(x,y,z,a));
	
	visualVector=v;
	visualGroup.addChild(tg);
	visualGroup.compile();
	sceneBG.addChild(visualGroup);//0531
  }
  
  public void SVisual(float r,float h,Vector3f v)
  {
	setRadius(r);
	setHeight(h);
	setAppearancevisual();
	
	visualGroup=new BranchGroup();
	visualGroup.setCapability(BranchGroup.ALLOW_DETACH);//to let the box be able to disappear

	Transform3D t3d=new Transform3D();
	t3d.set(v);
	TransformGroup tg=new TransformGroup(t3d);
	tg.addChild(cylinder[cylinderCount]=new Cylinder(r,h,a));
	
	visualVector=v;
	visualGroup.addChild(tg);
	visualGroup.compile();
	sceneBG.addChild(visualGroup);//0531
	
  }

  public void Spheres(float radius,Vector3f v)
  {
	setRadius(radius);
	setAppearance2();
	
	sphereGroup[sphereCount]=new BranchGroup();
	sphereGroup[sphereCount].setCapability(BranchGroup.ALLOW_DETACH);//to let the sphere be able to disappear

	Transform3D t3d=new Transform3D();
	t3d.set(v);
	TransformGroup tg=new TransformGroup(t3d);
	tg.addChild(sphere[sphereCount]=new Sphere(radius,a));
	sphereRadius[sphereCount]=radius;
	sphereVector[sphereCount]=v;
	sphereColor[sphereCount]=blue;
	sphereGroup[sphereCount].addChild(tg);
	sphereGroup[sphereCount].compile();
	sceneBG.addChild(sphereGroup[sphereCount]);

    sphereCount++;
  }
  public void Spheresn(float radius,Vector3f v)
  {
	setRadius(radius);
	setAppearance2();
	
	sphereGroupn[sphereCountn]=new BranchGroup();
	sphereGroupn[sphereCountn].setCapability(BranchGroup.ALLOW_DETACH);//to let the sphere be able to disappear
	
	Transform3D t3d=new Transform3D();
	t3d.set(v);
	TransformGroup tg=new TransformGroup(t3d);
	tg.addChild(spheren[sphereCountn]=new Sphere(radius,Sphere.GENERATE_TEXTURE_COORDS,a));
	if(m1==1)
	{
		File file=new File("material.txt");
		if(main2==0)
		{
		try
		{
			Scanner sc=new Scanner(file);
			h1=sc.nextLine();
			sc.close();
		}
		catch(FileNotFoundException ex)
		{}
		}
		if(h1.equals("w1"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r1.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			spheren[sphereCountn].setAppearance(Sphere.BODY,app1);
		}
		if(h1.equals("w2"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r2.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			spheren[sphereCountn].setAppearance(Sphere.BODY,app1);
		}
		if(h1.equals("w3"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r3.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			spheren[sphereCountn].setAppearance(Sphere.BODY,app1);
		}
		if(h1.equals("w4"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r4.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			spheren[sphereCountn].setAppearance(Sphere.BODY,app1);
		}
		if(h1.equals("w5"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r5.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			spheren[sphereCountn].setAppearance(Sphere.BODY,app1);
		}
		if(h1.equals("w6"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r6.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			spheren[sphereCountn].setAppearance(Sphere.BODY,app1);
		}
		if(h1.equals("w7"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r7.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			spheren[sphereCountn].setAppearance(Sphere.BODY,app1);
		}
		if(h1.equals("w8"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r8.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			spheren[sphereCountn].setAppearance(Sphere.BODY,app1);
		}
		if(h1.equals("w9"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r9.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			spheren[sphereCountn].setAppearance(Sphere.BODY,app1);
		}
		if(h1.equals("w10"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r10.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			spheren[sphereCountn].setAppearance(Sphere.BODY,app1);
		}
		if(h1.equals("w11"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r11.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			spheren[sphereCountn].setAppearance(Sphere.BODY,app1);
		}
		if(h1.equals("w12"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r12.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			spheren[sphereCountn].setAppearance(Sphere.BODY,app1);
		}
		if(h1.equals("w13"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r13.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			spheren[sphereCountn].setAppearance(Sphere.BODY,app1);
		}
		if(h1.equals("w14"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r14.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			spheren[sphereCountn].setAppearance(Sphere.BODY,app1);
		}
		if(h1.equals("w15"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r15.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			spheren[sphereCountn].setAppearance(Sphere.BODY,app1);
		}
		if(h1.equals("w16"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r16.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			spheren[sphereCountn].setAppearance(Sphere.BODY,app1);
		}
		if(h1.equals("w17"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r17.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			spheren[sphereCountn].setAppearance(Sphere.BODY,app1);
		}
		if(h1.equals("w18"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r18.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			spheren[sphereCountn].setAppearance(Sphere.BODY,app1);
		}
		if(h1.equals("w19"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r19.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			spheren[sphereCountn].setAppearance(Sphere.BODY,app1);
		}
		if(h1.equals("w20"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r20.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			spheren[sphereCountn].setAppearance(Sphere.BODY,app1);
		}
		if(h1.equals("w21"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r21.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			spheren[sphereCountn].setAppearance(Sphere.BODY,app1);
		}
		if(h1.equals("w22"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r22.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			spheren[sphereCountn].setAppearance(Sphere.BODY,app1);
		}
		if(h1.equals("w23"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r23.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			spheren[sphereCountn].setAppearance(Sphere.BODY,app1);
		}
		if(h1.equals("w24"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r24.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			spheren[sphereCountn].setAppearance(Sphere.BODY,app1);
		}
		if(h1.equals("w25"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r25.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			spheren[sphereCountn].setAppearance(Sphere.BODY,app1);
		}
		if(h1.equals("w26"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r26.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			spheren[sphereCountn].setAppearance(Sphere.BODY,app1);
		}
		if(h1.equals("w27"))
		{
			TextureLoader loader;
			Texture texture;
			loader=new TextureLoader("r27.jpg",this);
			texture=loader.getTexture();
			Appearance app1=new Appearance();
			app1.setTexture(texture);
			spheren[sphereCountn].setAppearance(Sphere.BODY,app1);
		}

	}
	main2=0;
	sphereRadiusn[sphereCountn]=radius;
	sphereVectorn[sphereCountn]=v;
	sphereMaterial[sphereCountn]=h1;
	sphereGroupn[sphereCountn].addChild(tg);
	sphereGroupn[sphereCountn].compile();
	sceneBG.addChild(sphereGroupn[sphereCountn]);

    sphereCountn++;
  }
 
  public void setRadius(float radius)
  {
  	this.radius=radius;
  }
  public void setHeight(float height)
  {
  	this.height=height;
  }
  public void setX(float x)
  {
	this.x=x;
  }
  public void setY(float y)
  {
	this.y=y;
  }
  public void setZ(float z)
  {
	this.z=z;
  }
  public void setAppearance1(Appearance app)
  {
	this.a=app;
  }
  public void setAppearance2()
  {
  	this.a=createAppearance();
  }
  public void setAppearancevisual(){//0531
	  this.a=createAppearance2();
  }
  public Appearance createAppearance()
  {
  	Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
  	Color3f specular = new Color3f(0.3f, 0.3f, 0.3f);
  	
  	Material blueMat= new Material(blue, black, blue, specular, 30.0f);
	blueMat.setLightingEnable(true);
	
  	Appearance blueApp = new Appearance();
	blueApp.setMaterial(blueMat);
	return blueApp;
  }
  public Appearance createAppearance2()//0531
  {
  	Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
  	Color3f blue = new Color3f(0.1f, 0.1f, 0.6f);
  	Color3f specular = new Color3f(0.9f, 0.9f, 0.9f);
  	
  	Material blueMat= new Material(blue, black, blue, specular, 60.0f);
	blueMat.setLightingEnable(true);
	
  	Appearance blueApp = new Appearance();
	blueApp.setMaterial(blueMat);
	
	return blueApp;
  }
  
  public void actionPerformed(ActionEvent e)
  {
	if(e.getSource()==b1)
	{
		behavior.dir=1;
	}
	if(e.getSource()==b2)
	{
		behavior.dir=2;
	}
	if(e.getSource()==b3)
	{
		behavior.dir=3;
	}
	if(e.getSource()==b5)
	{
		behavior.dir=5;
		checker=1;
	}
	if(e.getSource()==b6)
	{
		//System.out.printf("b6 is pressed\n");
		behavior.dir=6;
		remove=1;
	}
	if(e.getSource()==b7)
	{
		color change=new color(this);
		main1=0;
		c1=1;
	}
	if(e.getSource()==b8)
	{
		material mat=new material();
		main1=1;
		m1=1;
	}
  }
  	public void mouseReleased(MouseEvent event){}	
   	public void mouseEntered(MouseEvent event){}
    public void mouseExited(MouseEvent event){}
	public void mousePressed(MouseEvent event){}
	public void mouseDragged( MouseEvent event ){}
    public void mouseClicked(MouseEvent event){
		int k=0;
		if(c1==1)
		{
			double r1=0.3f;
			double g1=0.3f;
			double b1=0.3f;
			File file=new File("color.txt");
			try
			{
				Scanner sc=new Scanner(file);
				r1=sc.nextDouble();
				g1=sc.nextDouble();
				b1=sc.nextDouble();
				sc.close();
			}
			catch(FileNotFoundException ex)
			{}
			System.out.print(r1+"\n"+g1+"\n"+b1+"\n\n");
			blue= new Color3f((float)r1,(float)g1,(float)b1);
			c1=0;
		}
		
		/*if(m1==1)
		{
			File file=new File("material.txt");
			try
			{
				Scanner sc=new Scanner(file);
				h1=sc.nextLine();
				sc.close();
			}
			catch(FileNotFoundException ex)
			{}
			
			if(h1=="w1")
			{
				TextureLoader loader;
				Texture texture;
				loader=new TextureLoader("r1.jpg",this);
				texture=loader.getTexture();
				Appearance app1=new Appearance();
				app1.setTexture(texture);
				boxn[boxCountn].setAppearance(com.sun.j3d.utils.geometry.Box.TOP,app1);
			}
		}*/
		if(behavior.count1==true)//count1 for cylinder
		{
			if(main1==0)
			{
				double x=behavior.buffer2.getX()-behavior.buffer1.getX();
				float y=0.0f;
				double z=behavior.buffer2.getZ()-behavior.buffer1.getZ();
				if(x<0)
				x=-x;
				if(z<0)
				z=-z;
				double r=x*x+z*z;
				Cylinders((float)Math.sqrt(r),
						0.0f,
						new Vector3f((float)behavior.buffer1.getX(),
						0.06f,
						(float)behavior.buffer1.getZ()));
				behavior.count1=false;
			}
			else if(main1==1)
			{
				double x=behavior.buffer2.getX()-behavior.buffer1.getX();
				float y=0.0f;
				double z=behavior.buffer2.getZ()-behavior.buffer1.getZ();
				if(x<0)
				x=-x;
				if(z<0)
				z=-z;
				double r=x*x+z*z;
				Cylindersn((float)Math.sqrt(r),
						0.0f,
						new Vector3f((float)behavior.buffer1.getX(),
						0.06f,
						(float)behavior.buffer1.getZ()));
				behavior.count1=false;			
			}
		}
		
		if(behavior.count2==true)//count2 for box
		{
			if(main1==0)
			{
				float x=(float)behavior.buffer2.getX()-(float)behavior.buffer1.getX();
				float y=0.0f;
				float z=(float)behavior.buffer2.getZ()-(float)behavior.buffer1.getZ();
				if(x<0)
				x=-x;
				if(z<0)
				z=-z;
				Boxes(x/2,y,z/2,
				new Vector3f(((float)behavior.buffer1.getX()+(float)behavior.buffer2.getX())/2,
					0.06f,
					((float)behavior.buffer1.getZ()+(float)behavior.buffer2.getZ())/2));
				//Boxes(3.0f,1.0f,2.0f,new Vector3f(6.0f,7.0f,5.0f));
				behavior.count2=false;
			}
			else if(main1==1)
			{
				float x=(float)behavior.buffer2.getX()-(float)behavior.buffer1.getX();
				float y=0.0f;
				float z=(float)behavior.buffer2.getZ()-(float)behavior.buffer1.getZ();
				if(x<0)
				x=-x;
				if(z<0)
				z=-z;
				Boxesn(x/2,y,z/2,
				new Vector3f(((float)behavior.buffer1.getX()+(float)behavior.buffer2.getX())/2,
					0.06f,
					((float)behavior.buffer1.getZ()+(float)behavior.buffer2.getZ())/2));
				//Boxes(3.0f,1.0f,2.0f,new Vector3f(6.0f,7.0f,5.0f));
				behavior.count2=false;
			}
		}
		
		if(behavior.count3==true)//count3 for sphere
		{
			if(main1==0)
			{
				double x=behavior.buffer2.getX()-behavior.buffer1.getX();
				double y=behavior.buffer2.getY()-behavior.buffer1.getY();
				double z=behavior.buffer2.getZ()-behavior.buffer1.getZ();
				if(x<0)
				x=-x;
				if(z<0)
				z=-z;
				if(y<0)
				y=-y;
				sphereVector[sphereCount]=new Vector3f((float)behavior.buffer1.getX(),
					(float)behavior.buffer1.getY(),
					(float)behavior.buffer1.getZ());			
				double r=x*x+z*z+y*y;
				double rad=Math.sqrt(r);
				Spheres((float)Math.sqrt(r),sphereVector[sphereCount]);
				behavior.count3=false;
			}
			else if(main1==1)
			{
				double x=behavior.buffer2.getX()-behavior.buffer1.getX();
				double y=behavior.buffer2.getY()-behavior.buffer1.getY();
				double z=behavior.buffer2.getZ()-behavior.buffer1.getZ();
				if(x<0)
				x=-x;
				if(z<0)
				z=-z;
				if(y<0)
				y=-y;
				sphereVectorn[sphereCountn]=new Vector3f((float)behavior.buffer1.getX(),
					(float)behavior.buffer1.getY(),
					(float)behavior.buffer1.getZ());			
				double r=x*x+z*z+y*y;
				double rad=Math.sqrt(r);
				Spheresn((float)Math.sqrt(r),sphereVectorn[sphereCountn]);
				behavior.count3=false;
			}
		}
		if(behavior.count5==true)
		{
			behavior.count5=false;
		}
		if(checker==1)
		{
			int i=0;
			checker++;
			distanceX1=event.getX();
			distanceY1=event.getY();
			
			float tempx;
			float tempz;
			float orboxx;
			float orboxz;
			float orcylinderx;
			float orcylinderz;
			float orspherex;
			float orspherez;
			tempx=(float)behavior.intercept.getX();
			tempz=(float)behavior.intercept.getZ();
			for(i=0;i<boxCount;i++)
			{
				orboxx=boxVector[i].getX();
				orboxz=boxVector[i].getZ();
				if((tempx<(orboxx+boxX[i]/2))&&(tempx>(orboxx-boxX[i]/2))
					&&(tempz<(orboxz+boxZ[i]/2))&&(tempz>(orboxz-boxZ[i]/2))){
					boxSelect=i;
					choose=1;
					System.out.printf("#################\nboxSelect: %d\n#################\n",boxSelect);
					Visual(boxX[i],boxY[i]+0.1f,boxZ[i],boxVector[i]);//0531
					i=200;
				}
			}
			for(i=0;i<boxCountn;i++)
			{
				orboxx=boxVectorn[i].getX();
				orboxz=boxVectorn[i].getZ();
				if((tempx<(orboxx+boxXn[i]/2))&&(tempx>(orboxx-boxXn[i]/2))
					&&(tempz<(orboxz+boxZn[i]/2))&&(tempz>(orboxz-boxZn[i]/2))){
					boxSelectn=i;
					choose=1;
					System.out.printf("\n\n\nboxSelect %d\n\n\n",boxSelectn);
					i=200;
				}	
			}			
			for(i=0;i<cylinderCount;i++)
			{
				orcylinderx=cylinderVector[i].getX();
				orcylinderz=cylinderVector[i].getZ();
				
				if(((Math.pow((tempx-orcylinderx),2))+(Math.pow((tempz-orcylinderz),2)))<Math.pow(cylinderRadius[i],2)){
					cylinderSelect=i;
					choose=2;
					System.out.printf("#################\ncylinderSelect: %d\n#################\n",cylinderSelect);
					SVisual(cylinderRadius[i]-0.05f,0.1f,cylinderVector[i]);
					i=200;
				}
			}
			for(i=0;i<cylinderCountn;i++)
			{
				orcylinderx=cylinderVectorn[i].getX();
				orcylinderz=cylinderVectorn[i].getZ();
				
				if(((Math.pow((tempx-orcylinderx),2))+(Math.pow((tempz-orcylinderz),2)))<Math.pow(cylinderRadiusn[i],2)){
					cylinderSelectn=i;
					choose=2;
					System.out.printf("#################\ncylinderSelect: %d\n#################\n",cylinderSelectn);
					i=200;
				}
			}

			for(i=0;i<sphereCount;i++)
			{
				orspherex=sphereVector[i].getX();
				orspherez=sphereVector[i].getZ();
				if(((Math.pow((tempx-orspherex),2))+(Math.pow((tempz-orspherez),2)))<Math.pow(sphereRadius[i],2)){
					sphereSelect=i;
					choose=3;
					System.out.printf("#################\nsphereSelect: %d\n#################\n",sphereSelect);
					
					SVisual(sphereRadius[i]-0.05f,0.1f,sphereVector[i]);
					i=200;
				}
			}
			for(i=0;i<sphereCountn;i++)
			{
				orspherex=sphereVectorn[i].getX();
				orspherez=sphereVectorn[i].getZ();
				if(((Math.pow((tempx-orspherex),2))+(Math.pow((tempz-orspherez),2)))<Math.pow(sphereRadiusn[i],2)){
					sphereSelectn=i;
					choose=3;
					System.out.printf("#################\nsphereSelect: %d\n#################\n",sphereSelectn);
					i=200;
				}
			}
		}
		else if(checker==2)
		{
			int x=0;
			int y=0;
			int d=0;
			checker=0;
			distanceX2=event.getX();
			distanceY2=event.getY();
			x=distanceX2-distanceX1;
			y=distanceY2-distanceY1;
			d=x*x+y*y;
			distance=Math.sqrt(d)/100;

			
			if(choose==1&&boxSelect!=-1)
			{
				Color3f temp=new Color3f();
				temp=blue;
				blue=boxColor[boxSelect];
				Boxes(boxX[boxSelect],(float)distance,boxZ[boxSelect],new Vector3f(boxVector[boxSelect].getX(),(float)distance,boxVector[boxSelect].getZ()));
				blue=temp;
				boxGroup[boxSelect].detach();
				boxX[boxSelect]=0;
				boxZ[boxSelect]=0;
				visualGroup.detach();//0531
				//boxCount--;
			}
			if(choose==1&&boxSelectn!=-1)
			{
				String temp;
				temp=h1;
				h1=boxMaterial[boxSelectn];
				main2=1;
				Boxesn(boxXn[boxSelectn],(float)distance,boxZn[boxSelectn],new Vector3f(boxVectorn[boxSelectn].getX(),(float)distance,boxVectorn[boxSelectn].getZ()));
				h1=temp;
				boxGroupn[boxSelectn].detach();
				boxXn[boxSelectn]=0;
				boxZn[boxSelectn]=0;
				//boxCount--;
			}

			//Boxes(boxX[boxCount],(float)distance,boxZ[boxCount],boxVector[boxCount]);
			
			//if(boxCount!=0)
			//boxGroup[boxSelect].detach();//make the ball to disappear
			
			if(choose==2&&cylinderSelect!=-1)
			{
				Color3f temp=new Color3f();
				temp=blue;
				blue=cylinderColor[cylinderSelect];
				Cylinders(cylinderRadius[cylinderSelect],(float)distance,new Vector3f(cylinderVector[cylinderSelect].getX(),(float)distance/2,cylinderVector[cylinderSelect].getZ()));
				blue=temp;
				cylinderGroup[cylinderSelect].detach();
				cylinderRadius[cylinderSelect]=0;
				visualGroup.detach();
				//cylinderRadius[cylinderSelect]=-10000;
				//cylinderCount--;
			}
			if(choose==2&&cylinderSelectn!=-1)
			{
				String temp;
				temp=h1;
				h1=cylinderMaterial[cylinderSelectn];
				main2=1;
				Cylindersn(cylinderRadiusn[cylinderSelectn],(float)distance,new Vector3f(cylinderVectorn[cylinderSelectn].getX(),(float)distance/2,cylinderVectorn[cylinderSelectn].getZ()));
				h1=temp;
				cylinderGroupn[cylinderSelectn].detach();
				cylinderRadiusn[cylinderSelectn]=0;
				//cylinderRadius[cylinderSelect]=-10000;
				//cylinderCount--;
			}
			//if(cylinderCount!=0)
			//cylinderGroup[cylinderCount-1].detach();//make the ball to disappear

			if(choose==3&&sphereSelect!=-1)
			{
				Color3f temp=new Color3f();
				temp=blue;
				blue=sphereColor[sphereSelect];
				Spheres((float)Math.sqrt(distance),sphereVector[sphereSelect]);
				blue=temp;
				sphereGroup[sphereSelect].detach();
				sphereRadius[sphereSelect]=0; 
				//sphereRadius[sphereSelect]=-10000;
				//sphereCount--;
			}
			if(choose==3&&sphereSelectn!=-1)
			{
				String temp;
				temp=h1;
				h1=sphereMaterial[sphereSelectn];
				main2=1;
				Spheresn((float)Math.sqrt(distance),sphereVectorn[sphereSelectn]);
				h1=temp;
				sphereGroupn[sphereSelectn].detach();
				sphereRadiusn[sphereSelectn]=0; 
				//sphereRadius[sphereSelect]=-10000;
				//sphereCount--;
			}
			//if(sphereCount!=0)
			//sphereGroup[sphereCount-1].detach();
			
			//make the ball to disappear
			//sceneBG.removeChild(4);
			//sceneBG.compile();
			//System.out.printf("%d\n%d\n%d\n%d\n%f\n",distanceX1,distanceY1,distanceX2,distanceY2,distance);
		}

		System.out.printf("******************\nboxCount: %d\ncylinderCount: %d\nsphereCount: %d\n******************\n",boxCount,cylinderCount,sphereCount);
		
		if(sphereCount!=0)
		{
			//System.out.printf("%f\n\n",sphere[sphereCount-1].getRadius());
			//for(k=0;k<sphereCount-1;k++)
			//if()
		}
		
		if(behavior.count6==true)
		{
			behavior.count6=false;
		}
		if(remove==1)
		{
			int i=0;
			distanceX1=event.getX();
			distanceY1=event.getY();
			remove=0;
			float tempx;
			float tempz;
			float orboxx;
			float orboxz;
			float orcylinderx;
			float orcylinderz;
			float orspherex;
			float orspherez;
			tempx=(float)behavior.intercept.getX();
			tempz=(float)behavior.intercept.getZ();
			System.out.printf("\n\n\n%f******\n\n\n",tempx);
			
			for(i=0;i<boxCount;i++)
			{
				orboxx=boxVector[i].getX();
				orboxz=boxVector[i].getZ();
				if((tempx<(orboxx+boxX[i]/2))&&(tempx>(orboxx-boxX[i]/2))
					&&(tempz<(orboxz+boxZ[i]/2))&&(tempz>(orboxz-boxZ[i]/2))){
					boxSelect=i;
					choose=1;
					System.out.printf("\n\n\nboxSelect %d\n\n\n",boxSelect);
					i=200;
				}	
			}
			for(i=0;i<boxCountn;i++)
			{
				orboxx=boxVectorn[i].getX();
				orboxz=boxVectorn[i].getZ();
				if((tempx<(orboxx+boxXn[i]/2))&&(tempx>(orboxx-boxXn[i]/2))
					&&(tempz<(orboxz+boxZn[i]/2))&&(tempz>(orboxz-boxZn[i]/2))){
					boxSelectn=i;
					choose=1;
					System.out.printf("\n\n\nboxSelect %d\n\n\n",boxSelectn);
					i=200;
				}	
			}		
			
			for(i=0;i<cylinderCount;i++)
			{
				orcylinderx=cylinderVector[i].getX();
				orcylinderz=cylinderVector[i].getZ();
				
				if(((Math.pow((tempx-orcylinderx),2))+(Math.pow((tempz-orcylinderz),2)))<Math.pow(cylinderRadius[i],2)){
					cylinderSelect=i;
					choose=2;
					System.out.printf("\n\n\ncylinderSelect %d\n\n\n",cylinderSelect);
					i=200;
				}
			}
			for(i=0;i<cylinderCountn;i++)
			{
				orcylinderx=cylinderVectorn[i].getX();
				orcylinderz=cylinderVectorn[i].getZ();
				
				if(((Math.pow((tempx-orcylinderx),2))+(Math.pow((tempz-orcylinderz),2)))<Math.pow(cylinderRadiusn[i],2)){
					cylinderSelectn=i;
					choose=2;
					System.out.printf("\n\n\ncylinderSelect %d\n\n\n",cylinderSelectn);
					i=200;
				}
			}
			for(i=0;i<sphereCount;i++)
			{
				orspherex=sphereVector[i].getX();
				orspherez=sphereVector[i].getZ();
				if(((Math.pow((tempx-orspherex),2))+(Math.pow((tempz-orspherez),2)))<Math.pow(sphereRadius[i],2)){
					sphereSelect=i;
					choose=3;
					System.out.printf("\n\n\nsphereSelect %d\n\n\n",sphereSelect);
					i=200;
				}
			}
			for(i=0;i<sphereCountn;i++)
			{
				orspherex=sphereVectorn[i].getX();
				orspherez=sphereVectorn[i].getZ();
				if(((Math.pow((tempx-orspherex),2))+(Math.pow((tempz-orspherez),2)))<Math.pow(sphereRadiusn[i],2)){
					sphereSelectn=i;
					choose=3;
					System.out.printf("\n\n\nsphereSelect %d\n\n\n",sphereSelectn);
					i=200;
				}
			}
			if(choose==1&&boxSelect!=-1)
			{
				boxGroup[boxSelect].detach();
				boxX[boxSelect]=0;
				boxZ[boxSelect]=0;
				//boxCount--;
			}
			if(choose==1&&boxSelectn!=-1)
			{
				boxGroupn[boxSelectn].detach();
				boxXn[boxSelectn]=0;
				boxZn[boxSelectn]=0;
				//boxCount--;
			}
			if(choose==2&&cylinderSelect!=-1)
			{
				cylinderGroup[cylinderSelect].detach();
				cylinderRadius[cylinderSelect]=0;
				//cylinderRadius[cylinderSelect]=-10000;
				//cylinderCount--;
			}
			if(choose==2&&cylinderSelectn!=-1)
			{
				cylinderGroupn[cylinderSelectn].detach();
				cylinderRadiusn[cylinderSelectn]=0;
				//cylinderRadius[cylinderSelect]=-10000;
				//cylinderCount--;
			}
			if(choose==3&&sphereSelect!=-1)
			{
				sphereGroup[sphereSelect].detach();
				sphereRadius[sphereSelect]=0; 
				//sphereRadius[sphereSelect]=-10000;
				//sphereCount--;
			}
			if(choose==3&&sphereSelectn!=-1)
			{
				sphereGroupn[sphereSelectn].detach();
				sphereRadiusn[sphereSelectn]=0; 
				//sphereRadius[sphereSelect]=-10000;
				//sphereCount--;
			}
		}
		//System.out.print(blue.getRed()+"\n\n"+blue.getGreen()+"\n\n"+blue.getBlue()+"\n\n");
	}
} // end of WrapCheckers3D class
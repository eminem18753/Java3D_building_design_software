import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.behaviors.vp.*;

import com.sun.j3d.utils.picking.behaviors.*;
import com.sun.j3d.utils.picking.PickIntersection;
import com.sun.j3d.utils.picking.PickResult;
import com.sun.j3d.utils.picking.PickTool;
import javax.vecmath.Point3d;
public class ExamplePickBehavior extends PickMouseBehavior
    {
		public PickIntersection pi;
		PickResult pickResult;
		private Point3d point;
		Point3d intercept;
		Point3d buffer1=new Point3d();
		Point3d buffer2=new Point3d();
		int dir=-1;
		int times=0;
		boolean count1=false;
		boolean count2=false;
		boolean count3=false;
		boolean count4=false;
		boolean count5=false;
		boolean count6=false;
        public ExamplePickBehavior(Canvas3D canvas, BranchGroup bg, Bounds bounds)
        {
            super(canvas, bg, bounds);
            setSchedulingBounds(bounds);

			point=new Point3d(0,0,0);
            pickCanvas.setMode(PickTool.GEOMETRY_INTERSECT_INFO);
            // allows PickIntersection objects to be returned
        }

        public void updateScene(int xpos, int ypos)
        {
            pickCanvas.setShapeLocation(xpos, ypos);
            // register mouse pointer location on the screen (canvas)

            Point3d eyePos = pickCanvas.getStartPosition();
            // get the viewer's eye location

            pickResult = null;
            pickResult = pickCanvas.pickClosest();
            // get the intersected shape closest to the viewer

            if (pickResult != null) {
                pi = pickResult.getClosestIntersection(eyePos);
                // get the closest intersect to the eyePos point
                intercept = pi.getPointCoordinatesVW();
				if(dir==1&&times==1){
					buffer2=intercept;
					dir=-1;
					times=0;
					count1=true;
				}
				if(dir==1&&times==0){
					buffer1=intercept;
					times=1;
				}
				
				if(dir==2&&times==1){
					buffer2=intercept;
					dir=-1;
					times=0;
					count2=true;
				}
				if(dir==2&&times==0){
					buffer1=intercept;
					times=1;
				}
				
				if(dir==3&&times==1){
					buffer2=intercept;
					dir=-1;
					times=0;
					count3=true;
				}
				if(dir==3&&times==0){
					buffer1=intercept;
					times=1;
				}
				if(dir==6){
					buffer1=intercept;
					dir=-1;
					count6=true;
				}
				
                point=intercept;
                // extract the intersection pt in scene coords space
                // use the intersection pt in some way...
            }
        } 		
 } // end of ExamplePickBehavior class

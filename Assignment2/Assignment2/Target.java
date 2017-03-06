/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {
	
	public static final double SCREEN_WIDTH = 1280;
	public static final double SCREEN_HEIGHT = 680;
	public static final double BIG_CIRCLE_RADIUS = 72;
	public static final double MIDDLE_CIRCLE_RADIUS = 72*0.65;
	public static final double SMALL_CIRCLE_RADIUS = 72*0.3;
	
	/*Variable for x and y coordinates at center of screen*/
	int xCenter = 0;
	int yCenter = 0;

/** Draw three circles in the center of the screen all sharing the same
 * center but different radiuses and different colors and all filled in.
 */
	public void run() {
		calculateCenter();
		drawBigRedCircle();
		drawMiddleWhiteCircle();
		drawCenterRedCircle();
	}
	
/** Find the coordinates of the center of the screen*/
	public void calculateCenter(){
		xCenter = 1280/2;
		yCenter = 680/2;
	}
	
/** Precondition : having the coordinates of center and the radius for the big red outer circle
 *  postcondition: drawing the big red outer circle.	
 */
	public void drawBigRedCircle(){
		
		double xBig = xCenter - BIG_CIRCLE_RADIUS;
		double yBig = yCenter - BIG_CIRCLE_RADIUS;
		GOval bigRed = new GOval(xBig, yBig, BIG_CIRCLE_RADIUS * 2, BIG_CIRCLE_RADIUS * 2);
		bigRed.setFillColor(Color.RED);
		bigRed.setFilled(true);
		add(bigRed);
	}
	
/** Draw a white circle inside the red one*/	
	public void drawMiddleWhiteCircle(){
		
		double xMiddle = xCenter - MIDDLE_CIRCLE_RADIUS;
		double yMiddle = yCenter - MIDDLE_CIRCLE_RADIUS;		
		GOval middleWhite = new GOval(xMiddle, yMiddle, MIDDLE_CIRCLE_RADIUS * 2, MIDDLE_CIRCLE_RADIUS * 2);
		middleWhite.setFillColor(Color.WHITE);
		middleWhite.setFilled(true);
		add(middleWhite);			
	}
	
/** Draw a small red circle in the middle */	
	public void drawCenterRedCircle(){
		double xSmall = xCenter - SMALL_CIRCLE_RADIUS;
		double ySmall = yCenter - SMALL_CIRCLE_RADIUS;		
		GOval smallRed = new GOval(xSmall, ySmall, SMALL_CIRCLE_RADIUS * 2, SMALL_CIRCLE_RADIUS * 2);
		smallRed.setFillColor(Color.RED);
		smallRed.setFilled(true);
		add(smallRed);					
	}
}

import java.awt.Color;

import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;


public class RobotFace extends GraphicsProgram{
	public final double HEAD_WIDTH = 200;
	public final double HEAD_HEIGHT = 400;
	public final double SCREEN_HEIGHT = 695;
	public final double SCREEN_WIDTH = 1280;
	public final double EYE_RADIUS = 30;
	public double xFace;
	public double yFace;
	
	public void run(){
		drawHead();
		//drawMouth();
		drawEyes();
	}
	
	public void drawHead(){
		println(getWidth()+"and"+getHeight());
		xFace = ((SCREEN_WIDTH - HEAD_WIDTH)/2);
		yFace = ((SCREEN_HEIGHT - HEAD_HEIGHT)/2);
		GRect face =  new GRect(xFace, yFace, HEAD_WIDTH, HEAD_HEIGHT);
		face.setFillColor(Color.gray);
		face.setFilled(true);
		add(face);		
	}
	
	public void drawEyes(){
		double leftEyeX = xFace + HEAD_WIDTH/4 - EYE_RADIUS;
		double leftEyeY = yFace + HEAD_HEIGHT/4 - EYE_RADIUS;
		GOval leftEye = new GOval(leftEyeX, leftEyeY, EYE_RADIUS * 2, EYE_RADIUS * 2);
		leftEye.setColor(Color.YELLOW);
		leftEye.setFilled(true);
		add(leftEye);
	}
}

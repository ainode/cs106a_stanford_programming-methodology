import java.awt.Color;

import acm.graphics.GCanvas;
import acm.graphics.GOval;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

/** Draws 10 circles of different colors with different diameters*/
public class RandomCircles extends GraphicsProgram{

	private static final int NUM_OF_CIRCLES = 10;
	private static final int MIN_RADIUS = 5;
	private static final int MAX_RADIUS = 50;
	
	public void run(){
		makeCircles();
	}
	
	public void makeCircles(){
	
		for(int i = 0; i < NUM_OF_CIRCLES; i++){
			int radius = rGen.nextInt(MIN_RADIUS, MAX_RADIUS);
			int maxWidth = getWidth() - radius;
			int maxHeight = getHeight() - radius;
			double coordinateX = rGen.nextInt(radius, maxWidth);
			double coordinateY = rGen.nextInt(radius, maxHeight);
			Color color = rGen.nextColor();
			GOval ball = new GOval(coordinateX, coordinateY, radius, radius);
			ball.setFillColor(color);
			ball.setFilled(true);
			add(ball);
		}
	}
	
	private RandomGenerator rGen = RandomGenerator.getInstance();
}

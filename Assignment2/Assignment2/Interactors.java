import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

import acm.graphics.GCompound;
import acm.graphics.GLine;
import acm.program.GraphicsProgram;
import java.awt.event.*;

/**Creates interactors on the top of the screen and a
 * cross in the middle. when each button is pressed, 
 * depending on which direction it says on it the cross
 * will move in the same direction and a red line will be
 * drawn, showing the path of the movement of the cross.
 * @author User
 *
 */
public class Interactors extends GraphicsProgram{
	private final static double X_WIDTH = 10;
	private final static double Y_WIDTH = 10;
	private final static double MOVE = 20;
	public void run(){
		createButtons();
		createX();
		addActionListeners();
	}
	
	private void createButtons(){
		add(new JButton("NORTH"), NORTH);
		add(new JButton("SOUTH"), NORTH);
		add(new JButton("EAST"), NORTH);
		add(new JButton("WEST"), NORTH);
	}
	
	private void createX(){	
		centerX = initialX;
		centerY = initialY;
		cross.add(new GLine(initialX - X_WIDTH/2, initialY - Y_WIDTH/2, initialX + X_WIDTH/2, initialY + Y_WIDTH/2));
		cross.add(new GLine(initialX - X_WIDTH/2, initialY + Y_WIDTH/2, initialX + X_WIDTH/2, initialY - Y_WIDTH/2));
		add(cross);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("NORTH")){
			cross.move(0, -MOVE); 
			redLine = new GLine(centerX, centerY, centerX, centerY - MOVE);
			centerY -= MOVE;
		}
		if(e.getActionCommand().equals("EAST")){
			cross.move(MOVE, 0); 
			redLine = new GLine(centerX, centerY, centerX + MOVE, centerY);
			centerX += MOVE;
		}
		redLine.setColor(Color.RED);
		add(redLine);		
	}
	
	GCompound cross = new GCompound();	
	double centerX;
	double centerY;
	double initialX = 1100/2;
	double initialY = 800/2;
	GLine redLine;
}

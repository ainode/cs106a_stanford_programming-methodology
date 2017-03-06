/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
		
/** height of the screen*/
	private static final int HIGHT_OF_SCREEN = 680;
	
/** width of the screen*/
	private static final int WIDTH_OF_SCREEN = 1240;
	
	int numberOfBricksInCurrentRow = 0;
	
	int positionOfCurrentBrickInRow = 0;
	
	int currentX = 0;
	
	int currentY = 0;
	
	int currentRow = 0;
	
/** Add new rows on the top of the previous ones
 * until there is only one brick on the top row
 */
	
	public void run() {
		
		for(int i = BRICKS_IN_BASE; i >= 0; i --){
			numberOfBricksInCurrentRow = i;
			currentRow = BRICKS_IN_BASE - i + 1;
			addNewRow();
		}		
	}
	
/** Precondition : The row number that we will add is determined
 * 	Postcondition: A row of bricks is made.			   	 
 */
	public void addNewRow(){
		for(int i = 0; i < numberOfBricksInCurrentRow; i ++){
			positionOfCurrentBrickInRow = i;
			makeBrick();
			addNewBrick();
		}
	}
	
/** Precondition : We know the number of bricks in a row and the row number
 *  Postcondition: Coordinates of the current brick are decided.	
 */
	public void makeBrick(){
		
		currentX  = BRICK_WIDTH * positionOfCurrentBrickInRow +  (WIDTH_OF_SCREEN - BRICK_WIDTH * numberOfBricksInCurrentRow)/2;
		currentY = HIGHT_OF_SCREEN - BRICK_HEIGHT * currentRow;
	
	}
	
/** Precondition : We know the coordinates of the brick.
 *  Postcondition: We add a new brick to the row	
 */
	public void addNewBrick(){
		add(new GRect(currentX, currentY, BRICK_WIDTH, BRICK_HEIGHT));
	}	
}



/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		pause(1000);
/** Sets up the bricks and the paddle for the game*/		
		setUp();
	
/** Implements methods for ball moving and other dynamic functions of the game.*/		
		play();
	}
	
	private void setUp(){
		makeBrickRows();
		setUpPaddle();
	}

	private void makeBrickRows(){
		for(int i = 0; i < NBRICK_ROWS; i++){
			addOneRow(i);
		}
	}
	
/** Adds one row to the brick row*/	
	private void addOneRow(int i){
		for(int j = 0; j < NBRICKS_PER_ROW; j++){
			Color color = getColor(i);
			int x = (j) * BRICK_WIDTH + j * BRICK_SEP;
			int y = (i) * BRICK_HEIGHT + i * BRICK_SEP + BRICK_Y_OFFSET;
			GRect brick = new GRect (x, y, BRICK_WIDTH, BRICK_HEIGHT);
			add(brick);
			brick.setFillColor(color);
			brick.setFilled(true);
		}
	}
	
/** Get color of the row depending on the row number
 *  Precondition: get the row numbers from 0 - 9
 *  Postcondition : return the color of Color type*/	
	private Color getColor(int rowNum){
		Color color = null ;
		if(rowNum == 0 || rowNum == 1)
			color = Color.RED;
			else if(rowNum == 2 || rowNum == 3)
				color = Color.ORANGE;
			else if(rowNum == 4 || rowNum == 5)
				color = Color.GREEN;
			else if(rowNum == 6 || rowNum == 7)
				color = Color.YELLOW;
			else if(rowNum == 8 || rowNum == 9)
				color = Color.CYAN;
		return color;
	}
	
	public void setUpPaddle(){
		paddle = makePaddle();
/** Add event listener. Make the mouse able to move the paddle*/
		addMouseListeners();		
	}

	public void mousePressed(MouseEvent e){
		last = new GPoint(e.getPoint());
	}
	
/** Get the location of the mouse.*/	
	public void mouseDragged(MouseEvent e) {
		//GPoint point = paddle.getLocation();
		//double oldX = point.getX();
		double newX = e.getX();
		//newX = keepPaddleWithinScreen(newX);
	    paddle.move(newX - last.getX() , 0);
	    last = new GPoint(e.getPoint());
	}
	
/** Create paddle*/	
	public GRect makePaddle(){
		GRect paddle = new GRect((APPLICATION_WIDTH - PADDLE_WIDTH)/2, (APPLICATION_HEIGHT - PADDLE_Y_OFFSET), PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFillColor(Color.BLACK);
		paddle.setFilled(true);
		add(paddle);
		return paddle;
	}
	
/** Precondition : get the new x coordinate from the mouseDragged method
 *  Postcondition : If paddle is out of the screen return it back.*/
	private double keepPaddleWithinScreen(double x){
		if (x > APPLICATION_WIDTH - PADDLE_WIDTH)
			x = APPLICATION_WIDTH - PADDLE_WIDTH/2;
		else if (x < PADDLE_WIDTH)
			x = PADDLE_WIDTH/2;
		return x;
	}
	
	private void play(){
		createBall();
		for(int i = 0; i < 3; i++){
			while(ball.getY() < APPLICATION_HEIGHT - BALL_RADIUS){
				pause(20);	
				moveBall();
				checkForCollision();
			}
			showMessage((2 - i)+"turn left");
			remove(ball);
			createBall();
		}
	}

/** Creates a ball with a specific y velocity and a random x velocity*/	
	private void createBall(){
		ball = new GOval((APPLICATION_WIDTH - BALL_RADIUS)/2, (APPLICATION_HEIGHT - BALL_RADIUS)/2, BALL_RADIUS, BALL_RADIUS);
		ball.setFillColor(Color.BLACK);
		ball.setFilled(true);
		add(ball);
		vx = rGen.nextDouble(1.0, 3.0);
		if (rGen.nextBoolean(0.5))
			vx = -vx;
		vy = 3.0;
	}
	
	private void moveBall(){
		ball.move(vx, vy);
	}
	
/** Precondition: setup the brick rows and ball and paddle
 *  Postcondition: check for collision with walls first and then
 *  the paddle and bricks*/	
	private void checkForCollision(){
		checkForCollisionWithWalls();
		checkForCollisionWithObjects();
	}
	
	private void checkForCollisionWithWalls(){
		if (ball.getY() > APPLICATION_HEIGHT - BALL_RADIUS || ball.getY() < 1 )
			vy = -vy;
		if (ball.getX() > APPLICATION_WIDTH - BALL_RADIUS || ball.getX() < 1)
			vx = -vx;
	}
	
	private void checkForCollisionWithObjects(){
		GObject collider = getCollidingObject();
		if (collider != null){
			if(collider == paddle)
				vy = -vy;
			else{
				bounceClip.play();
				remove(collider);
				vy = -vy;
			}
		}
	}
	
/** Checks for collision with any objects at four angels and
 *  return the object that ball collides with.*/	
	private GObject getCollidingObject(){
		double x = ball.getX();
		double y = ball.getY();
		GObject collider = null;
		if (checkAngel(x, y) != null)
			collider = checkAngel(x, y);
		else if (checkAngel(x + BALL_RADIUS, y) != null)
			collider = checkAngel(x + BALL_RADIUS, y);
		else if (checkAngel(x, y + BALL_RADIUS) != null)
			collider = checkAngel(x, y + BALL_RADIUS);
		else if (checkAngel(x + BALL_RADIUS, y + BALL_RADIUS) != null)
			collider = checkAngel(x + BALL_RADIUS, y + BALL_RADIUS);
		return collider;
	}
	
	private GObject checkAngel(double x, double y){
		GObject collider = getElementAt(x, y);
		return collider;
	}
	
	public void showMessage(String message){
		GLabel label = new GLabel(message, APPLICATION_WIDTH/2, APPLICATION_HEIGHT/2);
		add(label);
		pause(500);
		remove(label);
	}	
	
/** declare instance variables.*/	
	private GRect paddle;
	private GOval ball;
	private double vx, vy;
	private RandomGenerator rGen = RandomGenerator.getInstance();
	AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
	private GPoint last;
	
}

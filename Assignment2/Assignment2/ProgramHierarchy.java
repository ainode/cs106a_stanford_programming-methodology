/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;
/** Draw the diagram of the hierarchy of programs in java*/
public class ProgramHierarchy extends GraphicsProgram {
	
/** Giving the measurement of class boxes*/	
	public static double boxWidth = 150;
	public static double boxHeight = 35;
	
/** Set some constants*/	
	public static final double screenWidth = 1280;
	public static final double screenHeight = 680;
	public static final double DISTANCE_BETWEEN_SECOND_ROW_BOXES = 50;
	public static final double DISTANCE_BETWEEN_ROWS = 75;

/** Initialize variables that will be storing the
 *  the outer most dimensions of the whole diagram
 *  so that we can place it in the center of the frame*/	
	public static double xTop = 0;
	public static double yTop = 0;
	public static double xBottom = 0;
	public static double yBottom = 0;
	
/** Draw the four boxes containing the name of the classes and 
 * the lines connecting them and displaying their hierarchy.*/	
	public void run() {
		findCoordinates();
		drawBoxes();
		writeLabels();
		drawLines();
	}
	
/** Find coordinates of top box and the first bottom box*/	
	public void findCoordinates(){
		double frameHeight = boxHeight * 2 + DISTANCE_BETWEEN_ROWS;
		yTop = (screenHeight - frameHeight)/2;
		xTop = (screenWidth - boxWidth)/2;
		yBottom = yTop + boxHeight + DISTANCE_BETWEEN_ROWS;
		xBottom = (screenWidth - boxWidth * 3 - 2 * DISTANCE_BETWEEN_SECOND_ROW_BOXES)/2; 
	}
/** Draw the four boxes through two separate functions*/	
	public void drawBoxes(){
		drawTopBox();
		drawThreeBoxes();
	}
	
/** Draw the top box for Program*/
	public void drawTopBox(){
		add(new GRect(xTop, yTop, boxWidth, boxHeight));
	}

/** Precondition : must know yBottom and xBottom from findCoordinates() function.
 *  PostCondition : find x coordinates of second row of boxes one by one and draw them.*/	
	public void drawThreeBoxes(){
		for(int i = 0; i < 3; i++){
			double xOfSecondRowBox = xBottom + i * (boxWidth + DISTANCE_BETWEEN_SECOND_ROW_BOXES);
			add(new GRect(xOfSecondRowBox, yBottom, boxWidth, boxHeight));
		}
	}
	
	public void writeLabels(){
		writeTopLabel();
		writeBottomLabels();
	}
	
/** Write the label inside the top box*/	
	public void writeTopLabel(){
		GLabel programLabel = new GLabel("Program");
		double programLabelLength = programLabel.getWidth();
		double programLabelHeight = programLabel.getAscent();
		double xProgramLabel = xTop + (boxWidth - programLabelLength)/2; 
		double yProgramLabel = yTop + (boxHeight + programLabelHeight)/2;
		add(new GLabel("Program", xProgramLabel, yProgramLabel));		
	}
	
/** Write the bottom labels*/	
	public void writeBottomLabels(){
		writeFirstBottomLabel();
		writeSecondBottomLabel();
		writeThirdBottomLabel();
	}

	public void writeFirstBottomLabel(){
		writeEachBottomLabel("GraphicsProgram", 1);
	}
	
	public void writeSecondBottomLabel(){
		writeEachBottomLabel("ConsolProgram", 2);
	}
	
	public void writeThirdBottomLabel(){
		writeEachBottomLabel("DialogProgram", 3);
	}

/** Precondition : have to pass label name and its number (1 for first, 2 for second ....) to the method
 *  Post condition : label names will be written in the box.*/	
	public void writeEachBottomLabel(String labelName,int bottomRowBoxNumber){
		GLabel programLabel = new GLabel(labelName);
		double programLabelLength = programLabel.getWidth();
		double programLabelHeight = programLabel.getAscent();
		double xBox = xBottom + (DISTANCE_BETWEEN_SECOND_ROW_BOXES + boxWidth) * (bottomRowBoxNumber - 1);
		double yBox = yBottom;
		double xProgramLabel = xBox + (boxWidth - programLabelLength)/2; 
		double yProgramLabel = yBox + (boxHeight + programLabelHeight)/2;
		add(new GLabel(labelName, xProgramLabel, yProgramLabel));		
	}
	
/** Draw lines from the top box to the three bottom boxes*/
	public void drawLines(){
		double xTopBox = xTop + boxWidth/2;
		double yTopBox = yTop + boxHeight;
		double xFirstBottomBox = xBottom + boxWidth/2;
		//find x coordinate of the middle of top side of second bottom box were the line ends
		double xSecondBottomBox = xBottom + boxWidth + DISTANCE_BETWEEN_SECOND_ROW_BOXES + boxWidth/2;
		double xThirdBottomBox = xBottom + 2 * boxWidth + 2 * DISTANCE_BETWEEN_SECOND_ROW_BOXES + boxWidth/2;
		double yAllBottomBoxes = yBottom;
		add(new GLine(xTopBox, yTopBox, xFirstBottomBox, yAllBottomBoxes));
		add(new GLine(xTopBox, yTopBox, xSecondBottomBox, yAllBottomBoxes));
		add(new GLine(xTopBox, yTopBox, xThirdBottomBox, yAllBottomBoxes));
	}
}


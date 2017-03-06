/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		incorrectGuessStr = "";
		removeAll();
		hangMan.removeAll();
		incorrectGuessNum = 0;
		hangMan.setLocation(getWidth()/2 - HEAD_RADIUS/2, (getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH - SCAFFOLD_UP);
		createScafold();
	}
	
	private void createScafold(){
		GCompound scaffold = new GCompound();
		GLine scaffoldStack = new GLine(0, 0, 0, SCAFFOLD_HEIGHT);
		GLine scaffoldBeam = new GLine(0, 0, BEAM_LENGTH, 0);
		GLine rope = new GLine(BEAM_LENGTH, 0, BEAM_LENGTH, ROPE_LENGTH);
		scaffold.add(scaffoldStack);
		scaffold.add(scaffoldBeam);
		scaffold.add(rope);
		add(scaffold, getWidth()/2 - BEAM_LENGTH, (getHeight() - SCAFFOLD_HEIGHT)/2 - SCAFFOLD_UP );
	}
	
/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		correctGuessLabel.setLabel(word);
		correctGuessLabel.setFont("Times New Roman-36");
		//correctGuessLabel.setLocation(30, getHeight()/2 + SCAFFOLD_HEIGHT + 20);		
		add(correctGuessLabel, 30, (getHeight() + SCAFFOLD_HEIGHT)/2 + 40);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		incorrectGuessNum += 1;
		incorrectGuessStr += letter;
		incorrectGuessLabel.setLabel(incorrectGuessStr);
		//incorrectGuessLabel.setLocation(30, getHeight()/2 + SCAFFOLD_HEIGHT + 20);		
		add(incorrectGuessLabel, 30, (getHeight() + SCAFFOLD_HEIGHT)/2 + 80);		
		addBodyParts();
	}
	
	private void addBodyParts(){
		if (incorrectGuessNum == 1)
			addPart(head);
		if (incorrectGuessNum == 2)
			addPart(torso);
		if (incorrectGuessNum == 3){
			leftArm = makeArm(HEAD_RADIUS/2 - UPPER_ARM_LENGTH, HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH );			
			addPart(leftArm);
		}
		if (incorrectGuessNum == 4){
			rightArm = makeArm(HEAD_RADIUS/2 + UPPER_ARM_LENGTH, HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH );			
			addPart(rightArm);
			addPart(hip);
		}
		if (incorrectGuessNum == 5){
			addPart(leftLeg);
		}
		if (incorrectGuessNum == 6){
			addPart(rightLeg);
		}		
		if (incorrectGuessNum == 7){
			addPart(leftFoot);
		}
		if (incorrectGuessNum == 8){
			addPart(rightFoot);
		}
	}	
	private void addPart(GObject part){
		hangMan.add(part);
		add(hangMan);
	}
	
	private GCompound makeArm(double xUpperArm, double yLowerArm){
		GCompound arm = new GCompound();
		GLine upperArm = new GLine(HEAD_RADIUS/2, HEAD_RADIUS + ARM_OFFSET_FROM_HEAD, xUpperArm, HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);
		GLine lowerArm = new GLine(xUpperArm, HEAD_RADIUS + ARM_OFFSET_FROM_HEAD, xUpperArm, yLowerArm);
		arm.add(upperArm);
		arm.add(lowerArm);
		return arm;
	}
	
	GCompound hangMan = new GCompound();
	GOval head = new GOval(0, 0, HEAD_RADIUS, HEAD_RADIUS);
	GLine torso = new GLine(HEAD_RADIUS/2, HEAD_RADIUS, HEAD_RADIUS/2, HEAD_RADIUS + BODY_LENGTH);
	GCompound leftArm = new GCompound();
	GCompound rightArm = new GCompound();
	GLine hip = new GLine(HEAD_RADIUS/2 - HIP_WIDTH/2, HEAD_RADIUS + BODY_LENGTH, HEAD_RADIUS/2 + HIP_WIDTH/2, HEAD_RADIUS + BODY_LENGTH);
	GLine leftLeg = new GLine(HEAD_RADIUS/2 - HIP_WIDTH/2, HEAD_RADIUS + BODY_LENGTH, HEAD_RADIUS/2 - HIP_WIDTH/2, HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
	GLine rightLeg = new GLine(HEAD_RADIUS/2 + HIP_WIDTH/2, HEAD_RADIUS + BODY_LENGTH, HEAD_RADIUS/2 + HIP_WIDTH/2, HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
	GLine leftFoot = new GLine(HEAD_RADIUS/2 - HIP_WIDTH/2, HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH,HEAD_RADIUS/2 - HIP_WIDTH/2 - FOOT_LENGTH, HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH );
	GLine rightFoot = new GLine(HEAD_RADIUS/2 + HIP_WIDTH/2, HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH,HEAD_RADIUS/2 + HIP_WIDTH/2 + FOOT_LENGTH, HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH );

	GLabel correctGuessLabel = new GLabel("");
	GLabel incorrectGuessLabel = new GLabel("");
	String incorrectGuessStr;
	
/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_UP = 60;
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	//private static final int INCORRECT_LABEL_HEIGHT; 
	
	private int incorrectGuessNum;

}

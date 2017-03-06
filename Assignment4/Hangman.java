/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

/** Chooses random word and gets inputs from user, compares the response
 *  keeps track of responses and if within 9 turns the answer is found user 
 *  is the winner */
public class Hangman extends ConsoleProgram {

	public static void main(String[] args) { 
		 new Hangman().start(args); 
		 }
	
    public void run() {
    	do{
        	randomWord = chooseRandomWord();
        	userWord = setUpUserWord();    		
        	responseCounter = 0;    		
    		canvas.reset();
			while(!gameOver() && !wordFound()){
		    	String line = readUsersInput();			
				matchUsersInput(line);
			}
    	}
    	while(play());
	}
    
    public void init(){
    	canvas = new HangmanCanvas();
    	add(canvas);
    }
    
    private boolean play(){
    	String line = readLine("Play again? ");
    	if(line.equals("Y") || line.equals("y") || 
    			line.equals("Yes") || line.equals("yes") 
    			|| line.equals("")){
    		return true;
    	}
    	else
    		return false;
    }
    
    private String chooseRandomWord(){
    	return wordChooser.getWord(rGen.nextInt(0, 9));
    }
    
    private String setUpUserWord(){
    	String userWord = "";
    	for(int i = 0; i < randomWord.length(); i++){
    		userWord += "-";
    	}
    	return userWord;
    }
    
    private String readUsersInput(){
    	boolean valid = false;
    	String line = null;
    	while(!valid){
    		line = readLine("Your guess: ");
    		if (line.length() > 0 && (line.charAt(0) <= 'Z' && line.charAt(0) >= 'A' || line.charAt(0) <= 'z' && line.charAt(0) >= 'a')){
    			valid = true;
    		}
    		else{
    			valid = false;
    			println("Please enter a valid charactor.");
    		}
    	}
    	return line;
    }
    
    private void matchUsersInput(String line){
    	if(validInput(line)){
    		char charInput = line.charAt(0);
    		boolean guess = false;
    		for(int i = 0; i < randomWord.length(); i++){
    			if(Character.toUpperCase(charInput) == randomWord.charAt(i) && correctCharsStr.indexOf(charInput) == -1){
    				guess = true;
    				userWord = userWord.substring(0, i) + Character.toUpperCase(charInput) + userWord.substring(i + 1);
    				correctCharsStr += charInput;
    			}
    		}
    		printResult(guess, charInput);
    	}
    }
    
    private boolean validInput(String line){
    		return (line.length() == 1) && ('A' <= Character.toUpperCase(line.charAt(0)) && Character.toUpperCase(line.charAt(0)) <= 'Z');
    }
    
    private void printResult(boolean guess, char charInput){
    	if(guess){
    		println("That guess is correct.");
    	}
    	else{
    		println("There is no " + charInput + " in the word.");
    		canvas.noteIncorrectGuess(charInput);
    		responseCounter++;
    	}
    	println("The word looks like: " + userWord + ".");
    	canvas.displayWord(userWord);
    }
    
    private boolean gameOver(){
    	if (responseCounter == 8){
    		println("Game over.");
    		return true;
    	}
    	else 
    		return false;
    }
    
    private boolean wordFound(){
    	if(userWord.equals(randomWord)){
    		println("You got it right.");
    		return true;
    	}
    	else return false;
    }
    
    private int responseCounter;
    private HangmanCanvas canvas;
    private String correctCharsStr = "";
    private String userWord;
    private String randomWord;
    private RandomGenerator rGen = RandomGenerator.getInstance();
    private HangmanLexicon wordChooser = new HangmanLexicon();
}

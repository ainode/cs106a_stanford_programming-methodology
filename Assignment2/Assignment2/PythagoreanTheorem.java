/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		getNumbersFromUser();
		calculatePythagorean();
		printResult ();
	}

	int sideA;
	int sideB;
	double result;


	public void getNumbersFromUser(){
	sideA = readInt("Please enter the length of the first side:");
	sideB = readInt("Please enter the length of the second side:");
	}
	
	public void calculatePythagorean(){
		result = Math.sqrt(sideA * sideA + sideB * sideB);
	}
	
	public void printResult(){
		println(result);
	}
}

/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

/** Use hailstone calculations until you get to number one */
public class Hailstone extends ConsoleProgram {
	int resultNumber;
	int counter = 0;
	public void run() {
		int usersNumber = readInt("Please enter a number:");
		resultNumber = usersNumber;
		while (resultNumber != 1){
			if (resultNumberIsEven()){
				devideResultNumberByTwo();
				counter ++;
			}
				else if (!resultNumberIsEven()){
					multuplyBy3AndAddOne();
					counter ++;
				}
			}
		println("The number of operations: " + counter);
		}		
	
/** Verify if the number is even*/
	public boolean resultNumberIsEven(){
		if(resultNumber % 2 ==0 ){
			return true;
		}
		else{
			return false;
		}
	}
	
/** Precondition: number is even.
 *  postcondition: number is devided by two.*/	
	public void devideResultNumberByTwo(){
		println(resultNumber+" is even so I devide by 2 : " + (resultNumber /= 2));		
	}

/** Precondition: number is odd.
 *  Postcondition: do 3n + 1. */	
	public void multuplyBy3AndAddOne(){
		println(resultNumber + " is odd so I calculate 3n + 1 : " + (resultNumber = (resultNumber * 3) + 1));				
	}	
}


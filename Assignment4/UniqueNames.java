import java.util.ArrayList;

import acm.program.*;
import java.util.*;


public class UniqueNames extends ConsoleProgram{
	public void run(){
		while(true){
			String intake = takeInput();
			intake = validateInput(intake);
			if (intake.compareTo("") == 0)
				break;
			compareAndInsert(intake);			
		}
		printNames();				
	}

		
	private String takeInput(){
		return readLine("Enter name:");
	}
	private String validateInput(String line){
		while(!lineIsValid(line)){
			line = readLine("Invalid name, please enter again: ");
		}
		return line;
	}
	
	private boolean lineIsValid(String line){
		for(int i = 0; i < line.length(); i++){
			if(!Character.isLetter(line.charAt(i)))
				return false;
		}
		return true;
	}	
	
	private void compareAndInsert(String line){
		if(!names.contains(line))
			names.add(line);
	}
	
	private void printNames(){
		for(int i = 0; i < names.size(); i++)
			println(names.get(i));
	}
	private ArrayList<String> names = new ArrayList();
}

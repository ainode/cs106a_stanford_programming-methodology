//import java.util.HashMap;
import java.util.*;
//import java.util.Map;
import acm.program.*;

import acm.program.ConsoleProgram;


public class NameCounts extends ConsoleProgram{
	public void run(){
		readNames();
		displayNames();
	}
	
	public void readNames(){
		while(true){
			String name = readLine("Enter your name:");
			if(name.equals(""))
				break;
			Integer count = names.get(name);
			if (count == null)
				count = 1;
			else
				count = count + 1;
			names.put(name, count);
		}
	}
	public void displayNames(){
		for(String name : names.keySet()){	
			println(name + " times: " + names.get(name));
		}
	}
	
	private Map<String, Integer> names = new HashMap<>();
}

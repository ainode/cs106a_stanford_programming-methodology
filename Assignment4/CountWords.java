import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import acm.program.ConsoleProgram;


public class CountWords extends ConsoleProgram{
	public void run(){
		getFileName();
		countElements();
		printResult();		
	}
	
	public void getFileName(){
		reader = null; 
		while(reader == null){
			String fileName = readLine("Enter file name: ");
			try{
				reader = new BufferedReader(new FileReader(fileName));
			}
			catch(IOException ex){
				println("Bad file. ");
			}
		}
	}
	
	private void countElements(){
		while(true){
			try{
				String line = reader.readLine();
				if (line == null){
					break;
				}
				lineCount++;
				StringTokenizer st = new StringTokenizer(line);
				scanTokens(st);
			}
			catch(IOException ex){
				println(ex.getMessage());
			}
		}
	}
	
	
	private void scanTokens(StringTokenizer tokens){
		while (tokens.hasMoreTokens()){
			String nextToken = tokens.nextToken();
			boolean validWord = true;
			for(int i = 0; i < nextToken.length(); i++){
				if(Character.isLetterOrDigit(nextToken.charAt(i))){
					letterCount++;
				}
				else{
					validWord = false;
				}
			}
			if (validWord){
				wordCount++;
			}
		}		
		
	}
	
	private void printResult(){
		println(lineCount +"and"+wordCount+"and"+letterCount+". ");
	}

	private BufferedReader reader;
	private int lineCount;
	private int wordCount;
	private int letterCount;
}

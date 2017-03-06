import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import acm.*;
public class WordLadder {
	public static void main(String[] args){
		System.out.println("Please enter the first word?");
		while (true){
			previousWord = reader.nextLine();			
			if(arrList.contains(previousWord))
				break;
			System.out.println("First word should be an english word.");			
		}
		while (true){
			 currentWord = reader.nextLine();
			 if(currentWord.equals(""))
				break;			 
			 validateWord(currentWord);
		}
	}
	
	public static boolean validateWord(String word){
		if (!arrList.contains(word)){
			System.out.println("English please.");
			return false;
		}
		else if(!rightWord())
			return false;
		else return true;
	}
	
	public static boolean rightWord(){
		int difCharCount = 0;
		for(int i = 0; i < previousWord.length(); i++){
			if (previousWord.charAt(i) != currentWord.charAt(i))
				difCharCount ++;
		}
		if(difCharCount > 1){
			System.out.println("the two should differ in 1 char.");
			return false;
		}
		if(currentWord.length() != previousWord.length()){
			System.out.println("the length of the new word cant be more than the previous one.");
			return false;
		}
		return true;
	}
private static Scanner reader = new Scanner(System.in);	
private static String previousWord = "";
private static String currentWord = "";
private static String[] list = {"ali", "hasan", "moth", "pick", "alid" , "all"};
private static ArrayList<String> arrList = new ArrayList<String>(Arrays.asList(list));
}

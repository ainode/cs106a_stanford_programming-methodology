/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilterReader;
import java.io.IOException;
import java.util.ArrayList;

import acm.util.*;

public class HangmanLexicon {
	
	private final String FILE_NAME = "HangmanLexicon.txt";
	private ArrayList<String> words = new ArrayList<String>();
	
	public HangmanLexicon(){
		BufferedReader rd;
		rd = readFile(FILE_NAME);
		fillArrayList(rd);
	}
	
	private BufferedReader readFile(String file){
		BufferedReader rd = null;
		try{
			rd = new BufferedReader(new FileReader("HangmanLexicon.txt")); 
		}
		catch(IOException ex){
			System.out.println("Can't open the file. ");
		}
		return rd;
	}
	
	private void fillArrayList(BufferedReader rd){
		try{
			while(true){
				String word = rd.readLine();
				if(word == null){
					break;
				}
				words.add(word);
			}
		}
		catch(IOException ex){
			System.out.println(ex.getMessage());
		}
	}
	
/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return words.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		return words.get(index);
	}
}

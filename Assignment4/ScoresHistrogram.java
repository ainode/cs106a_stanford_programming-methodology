import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import acm.program.ConsoleProgram;


public class ScoresHistrogram extends ConsoleProgram{
	public void run(){
		categorizeScores();
		printResults();
	}
	
	private void categorizeScores(){
		readFile();
	}
	
	private void readFile(){
		int nextScore;
		try{
			BufferedReader rd = new BufferedReader(new FileReader("MidtrmScores.txt"));
			while(true){
				String line = rd.readLine();
				if (line == null)
					break;
				devideInTens(line);
			}
		}
		catch(IOException ex){
			println(ex.getMessage());
		}
	}
	
	private void devideInTens(String line){
		int index = 0;
		index = Integer.parseInt(line)/10;
		if ((index + 1) > scores.size()){
			for(int i = 0; scores.size() < index + 1; i++)
				scores.add(0);
		}
		scores.set(index, scores.get(index) + 1);
	}
	
	private void printResults(){
		String starsStr;
		for(int i = 0; i < scores.size(); i ++){
			starsStr = lineUpStars(scores.get(i));
			println((i * 10) + " - " + (i * 10 + 9) + " : " + starsStr );
		}
	}
	
	private String lineUpStars(int i){
		String stars = "";
		for (int j = 0; j < i; j++){
			stars += "*";
		}
		return stars;	
	}
	private ArrayList<Integer> scores = new ArrayList<>();
}

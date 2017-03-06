/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		makeArrays();
		for(int i = 0; i < N_SCORING_CATEGORIES; i++){	
			for(int j = 1; j <= nPlayers; j++){
				rollDice(j);
				displayAndWait();
				rollAgain();
				displayAndWait();
				rollAgain(); 
				display.displayDice(dices);
				chooseCategory(j);
			}
		}
		sumUpScores();
		announceWinner();
	}
	
/** Initiates a 2 dimentional array for 
 * scores of all players for each category.
 */
	private void makeArrays(){
		scores = new int[nPlayers][N_CATEGORIES];
		for(int i = 0; i < nPlayers; i++){
			for(int j = 0; j < N_CATEGORIES; j++){
				scores[i][j] = -1;
			}
		}
	}
		
/** Gives message to the user to roll dice and waits for 
 * specific user to roll and assigns random numbers to dices.  	
 * @param player
 */
	private void rollDice(int player){
		display.printMessage(playerNames[player - 1] + "'s turn! click the roll dice button to roll the dice.");
		display.waitForPlayerToClickRoll(player);
		getDiceNumbers();
	}
	
	private void getDiceNumbers(){
		for(int i = 0; i < N_DICE; i++){
			dices[i] = rgen.nextInt(1, 6);			
		}
	}
	
/** Assigns random numbers to selected dices.
 */
	private void rollAgain(){
		for(int i = 0; i < N_DICE; i++){
			if(display.isDieSelected(i)){
				dices[i] = rgen.nextInt(1, 6);
			}
		}
	}
	
	private void displayAndWait(){
		display.displayDice(dices);
		display.printMessage("You can roll again if you wish.");																
		display.waitForPlayerToSelectDice();		
	}
	
/** Prompts user to choose a category and checks
 * if the right category is chosen. If not it will 
 * assign 0 as score for that player for that category.	
 * @param player
 */
	private void chooseCategory(int player){ 
		display.printMessage("Select a catagory.");																
		while(true){
			int category = display.waitForPlayerToSelectCategory();
			if(checkCategory(category)){
				if(scores[player-1][category] == -1){
					int score = getScoreOfCategory(category);
					display.updateScorecard(category, player, score);
					scores[player-1][category] = score;
					break;
				}
			}
			else if (scores[player-1][category] == -1){
				display.updateScorecard(category, player, 0);
				scores[player-1][category] = 0;
				break;				
			}
		}
	}
	
/** Takes category number as parameter and returns the
 * score for that category	
 * @param category
 * @return score
 */
	private int getScoreOfCategory(int category){
		int score = 0;
		switch(category){
			case ONES : score = calculateDigitScores(ONES);
			break;
			case TWOS : score = calculateDigitScores(TWOS);
			break;
			case THREES : score = calculateDigitScores(THREES);
			break;
			case FOURS : score = calculateDigitScores(FOURS);
			break;
			case FIVES : score = calculateDigitScores(FIVES);
			break;
			case SIXES : score = calculateDigitScores(SIXES);
			break;
			case THREE_OF_A_KIND : score = sumAll();
			break;
			case FOUR_OF_A_KIND : score = sumAll();
			break;
			case FULL_HOUSE : score = 25;
			break;
			case SMALL_STRAIGHT : score = 30;
			break;
			case LARGE_STRAIGHT : score = 40;
			break;
			case YAHTZEE : score = 50;
			break;
			case CHANCE : score = sumAll();
			break;
		}
		return score;
	}
	
	
	private int calculateDigitScores(int digit){
		int total = 0;
		for(int i = 0; i < N_DICE; i++){
			if (dices[i] == digit)
				total += digit;
		}
		return total;
	}
	
	private int sumAll(){
		int total = 0;
		for(int i = 0; i < N_DICE; i++){
				total += dices[i];
		}
		return total;		
	}
	
	private void sumUpScores(){
		for(int i = 1; i <= nPlayers; i++){
			int upperScore = displayUpperScore(i);  
			int upperBonus = displayUpperBonus(i, upperScore);
			int lowerScore = displayLowerScore(i); 
			int total = upperScore + upperBonus + lowerScore;
			display.updateScorecard(TOTAL, i, total);
			scores[i - 1][TOTAL - 1] = total;
		}
	}
	
	private int displayUpperScore(int player){
		int upperScore = 0;
		for(int i = 0; i < UPPER_SCORE; i++){
			if(scores[player - 1][i] > 0)
				upperScore += scores[player - 1][i];
		}
		display.updateScorecard(UPPER_SCORE, player, upperScore);
		return upperScore;
	}
	
	private int displayLowerScore(int player){
		int lowerSum = 0;
		for(int i = THREE_OF_A_KIND - 1; i < CHANCE; i++){
			if(scores[player - 1][i] > 0)
				lowerSum += scores[player - 1][i];
		}
		display.updateScorecard(LOWER_SCORE, player, lowerSum);		
		return lowerSum;
	}
	
	private int displayUpperBonus(int player, int upperScore){
		int bonus = 0;
		if(upperScore >= 63){
			bonus = 35;
			scores[player - 1][UPPER_BONUS-1] = 35;
			display.updateScorecard(UPPER_BONUS, player, 35);
		}		
		return bonus;
	}
	
	private void announceWinner(){
		int winner = 0;
		int comparator = 0;
		for(int i = 0; i < nPlayers; i++){
			if (scores[i][TOTAL - 1] > comparator){
				comparator = scores[i][TOTAL - 1];
				winner = i;
			}
		}
		display.printMessage("The winner is: " + playerNames[winner]);
	}
	
	private boolean checkCategory(int category){
		switch(category){
			case ONES : return countDigits(category);
			case TWOS : return countDigits(category);
			case THREES : return countDigits(category);
			case FOURS : return countDigits(category);
			case FIVES : return countDigits(category);
			case SIXES : return countDigits(category);
			case THREE_OF_A_KIND : return countSimilars(category);
			case FOUR_OF_A_KIND : return countSimilars(category);
			case FULL_HOUSE : return countSimilars(category);
			case YAHTZEE : return countSimilars(category);
			case SMALL_STRAIGHT : return countSequentials(category);
			case LARGE_STRAIGHT : return countSequentials(category);
			case CHANCE : return true;
			default : return false;
		}
	}
	
	public boolean countDigits(int category){
		for(int j = 0; j < N_DICE; j++){
			if(dices[j] == category){
				return true;
			}
		}
		return false;
	}
	
	private boolean countSimilars(int category){
		int diceIndex = 0;
		ArrayList<Integer> listOfSimilars = new ArrayList<>();
		for(int i = 0; i <N_DICE; i++){
			listOfSimilars.add(dices[i]);
			for(int j = i + 1; j < N_DICE; j++){
				if(dices[i] == dices[j])
					listOfSimilars.add(dices[j]);
				diceIndex = i;
			}
			if(listOfSimilars.size() == 3 && category == THREE_OF_A_KIND)
				return true;
			if(listOfSimilars.size() == 4 && category == FOUR_OF_A_KIND)
				return true;
			if(listOfSimilars.size() == 5 && category == YAHTZEE)
				return true;
			if((listOfSimilars.size() == 3 || listOfSimilars.size() == 2) && category == FULL_HOUSE)
				return isFullHouse(diceIndex, listOfSimilars.size());
			listOfSimilars.removeAll(listOfSimilars);
		}
		return false;
	}
	
	private boolean isFullHouse(int diceIndex, int listSize){
		int similars = 1;
		for(int i = 0; i < N_DICE; i++){
			for(int j = i + 1; j < N_DICE; j++){
				if(dices[i] == dices[j] && dices[i] != dices[diceIndex]){
					similars++;
				}
			}
			if(similars > 0 && (similars + listSize) == 5)
				return true;
		}
		return false;
	}
	
	private boolean countSequentials(int category){
		int similars = 0;
		Arrays.sort(dices);
		for(int i = 0; i < N_DICE; i++)
			for(int j = i + 1; j < N_DICE; j++){
				if(dices[i] == dices[j]){
					similars++;
				}
			}
		if(similars == 0 && category == LARGE_STRAIGHT)
			return true;
		if(similars == 1 && category == SMALL_STRAIGHT && !(dices[0] == 1 && dices[4] == 5))
			return true;
		return false;
	}
	
/* Private instance variables */
	private int[] dices = new int[N_DICE];
	private int[][] scores;
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();

}

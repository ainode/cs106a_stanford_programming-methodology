import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class VaccationPlan {
	
	private static final String FLIGHTS_FILE = "flights.txt";
	private static final String FIRST_FLIGHT_PROMPT = "Please enter your first flight";
	
	public VaccationPlan(){
	}
	public static void main(String args[]){
		map = new MappedData();		
		map.readData(FLIGHTS_FILE);	
		displayCities();
		while(!loopComplete()){
			//getInput();
			displayFlights();
			//displayDestinations();
		}
	
	//System.out.println(map.getDestinations("New York"));
	}
	
	private static void displayCities(){
		System.out.println("Here is the list of all cities in our database:");
		map.displayCities();
		System.out.println("Lets plan a vaccation.");
		reader = new Scanner(System.in);
		System.out.println("Enter the starting city?");	
		displayFlights();
	}
	
	private static void displayFlights() {
		while (true){
			city = reader.nextLine();
			if(validCity()){
				tripPlan.add(city);
				break;
			}
			System.out.println("Please choose a city from the list.");
		}
		if(!loopComplete()){
		System.out.println("From " + city + " You can go to: ");
		map.getDestinations(city);
		System.out.println("Where do you want to go from " + city + " ?");
		city = "";
		}
		else{
			displayRoute();
		}
	}
	
	private static boolean validCity(){
			return (city != null && map.getCity(city.trim()));
	}
	
	/*private static void getInput(){
		//ArrayList<String> destinations = map.getDestinations(city);
		//String destination = reader.nextLine();
		while(true){
			System.out.println("Please choose from the list");
			reader = new Scanner(System.in);			
			String destination = reader.nextLine();
			if(destinations.contains(destination)){
				city = destination;
				tripPlan.add(city);
				break;
			}
		}		
	}
	*/
	
	private static void displayRoute(){
		System.out.println("The route you have chosen is: ");
		String route = tripPlan.get(0);
		for(int i = 1; i < tripPlan.size(); i++){
			route += "->" + tripPlan.get(i);
		}
		System.out.println(route);
	}
	
	private static boolean loopComplete(){
		return (tripPlan.get(0).equals(tripPlan.get(tripPlan.size() - 1)) && tripPlan.size() > 1);
	}
	/* instance variables*/
	private static MappedData map;
	private static String city; 
	private static Scanner reader;
	private static ArrayList<String> tripPlan = new ArrayList<>();
}
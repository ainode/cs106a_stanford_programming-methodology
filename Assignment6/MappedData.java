import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class MappedData {
	public void readData(String file){
		BufferedReader rd = null;
		try{
			rd = new BufferedReader(new FileReader(file)); 
		}
		catch(IOException ex){
			System.out.println("Can't open the file. ");
		}
		buildMap(rd);
	}
	
	private void buildMap(BufferedReader rd){
		try{
			while(true){
				String entry = rd.readLine();
				if(entry == null){
					break;
				}
				String[] entryArray = entry.split(" -> ");
				if(entryArray.length > 1){
					if(flights.get(entryArray[0]) == null){
						addNewEntry(entryArray);
					}
					else
						addNewDestination(entryArray);
				}
			}
		}
		catch(IOException ex){
			System.out.println(ex.getMessage());
		}
	}
	
	private void addNewEntry(String[] entryArr){
		ArrayList<String> destinationArray = new ArrayList<>();
		destinationArray.add(entryArr[1].trim());
		flights.put(entryArr[0].trim(), destinationArray);
	}
	
	private void addNewDestination(String[] entryArr){
		flights.get(entryArr[0]).add(entryArr[1].trim());		
	}
	
	public void getDestinations(String startPoint){
		for(String cities : flights.get(startPoint)){
			System.out.println(cities);
		}
	}
		
	public void displayCities(){
		for(String cities : flights.keySet()){
			System.out.println(cities);
		}
	}
	
	public boolean getCity(Object city){
		return flights.containsKey(city);
	}
	/* Instance variables*/
	private HashMap<String, ArrayList<String>> flights = new HashMap<>();
	private ArrayList<String> plan;
}

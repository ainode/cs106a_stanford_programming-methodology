import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

public class NameSurferDataBase implements NameSurferConstants {
	
/* Constructor: NameSurferDataBase(filename) */
/**
 * Creates a new NameSurferDataBase and initializes it using the
 * data in the specified file.  The constructor throws an error
 * exception if the requested file does not exist or if an error
 * occurs as the file is being read.
 */
	public NameSurferDataBase(String filename) {
		BufferedReader rd;
		rd = readFile(filename);
		fillHashMap(rd);
	}	
	
	private BufferedReader readFile(String file){
			BufferedReader rd = null;
			try{
				rd = new BufferedReader(new FileReader(file)); 
			}
			catch(IOException ex){
				System.out.println("Can't open the file. ");
			}
			return rd;
	}
	
		private void fillHashMap(BufferedReader rd){
			try{
				while(true){
					String entry = rd.readLine();
					if(entry == null){
						break;
					}
					String[] entryArray = entry.split(" ", 2);
					map.put(entryArray[0], entry);
				}
			}
			catch(IOException ex){
				System.out.println(ex.getMessage());
			}
		}		
	
	
/* Method: findEntry(name) */
/**
 * Returns the NameSurferEntry associated with this name, if one
 * exists.  If the name does not appear in the database, this
 * method returns null.
 */
	public NameSurferEntry findEntry(String name) {
		if(map.get(name) != null){
			NameSurferEntry entry = new NameSurferEntry(map.get(name));
			return entry;
		}
		else
			return null;
	}
	
	/* Instance variables*/
	private HashMap<String, String>  map = new HashMap<String, String>();
}


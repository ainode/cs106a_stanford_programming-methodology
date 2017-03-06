/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.text.html.HTMLDocument.Iterator;

public class FacePamphlet extends Program 
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public static void main(String[] args){
		new FacePamphlet().start(args);
	}
	public void init() {
		canvas = new FacePamphletCanvas();
		addInteractors();
		addActionListeners();
		database = new FacePamphletDatabase();
		add(canvas);
    }
    
	public void addInteractors(){
		add(new JLabel("Name"), NORTH);
		nameField = new JTextField(TEXT_FIELD_SIZE);
		add(nameField, NORTH);
		add(new JButton("Add"), NORTH);
		add(new JButton("Delete"), NORTH);
		add(new JButton("Look Up"), NORTH);
		status = new JTextField(TEXT_FIELD_SIZE);
		add(status, WEST);
		status.addActionListener(this);
		add(new JButton("Change Status"), WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		picture = new JTextField(TEXT_FIELD_SIZE);
		add(picture, WEST);
		picture.addActionListener(this);
		add(new JButton("Change Picture"), WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		friend = new JTextField(TEXT_FIELD_SIZE);
		add(friend, WEST);
		friend.addActionListener(this);
		add(new JButton("Add Friend"), WEST);
	}
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		// You fill this in as well as add any additional methods
    	if(e.getActionCommand().equals("Add")){
    		if(database.containsProfile(nameField.getText())){
    			canvas.displayProfile(database.getProfile(nameField.getText()));
    			String msg = "Profile for " + nameField.getText() + " already exists.";
    			canvas.showMessage(msg);
    			currentProfile = nameField.getText();
    		}
    		else
    		{
    			FacePamphletProfile person = new FacePamphletProfile(nameField.getText());
    			database.addProfile(person);
    			currentProfile = nameField.getText();
    			canvas.displayProfile(database.getProfile(nameField.getText()));
    			canvas.showMessage("New profile created.");
    		}
    	}
    	if(e.getActionCommand().equals("Delete")){
    		if(database.containsProfile(nameField.getText())){
    			FacePamphletProfile person = database.getProfile(nameField.getText());
    			Iterator<String> it = person.getFriends();
    			while(it.hasNext()){
    				String friend = it.next();
    				database.getProfile(friend).friends.remove(nameField.getText());
    			}
    			database.deleteProfile(nameField.getText());
    			currentProfile = null;
    			canvas.removeAll();
    			canvas.showMessage(nameField.getText() + " profile Deleted");    			
    		}
    		else
    			canvas.showMessage("The name you are trying to delete does not exist.");
    	}
    	if(e.getActionCommand().equals("Look Up")){
    		if (database.containsProfile(nameField.getText())){
    			canvas.displayProfile(database.getProfile(nameField.getText()));
    			canvas.showMessage("Looking up " + nameField.getText() + "'s profile.");
    			currentProfile = nameField.getText();
    		}
    		else 
    			canvas.showMessage("The profile does not exist.");
    	}
    	if(e.getActionCommand().equals("Change Status") || e.getSource() == status){
    		if(status.getText().trim().length() == 0){
    			canvas.showMessage("Please enter a status");
    		}
    		else{
	    		if (currentProfile != null){
	    			database.getProfile(currentProfile).setStatus(status.getText());
	    			canvas.displayProfile(database.getProfile(currentProfile));
	    			canvas.showMessage("Status is updated.");
	    		}
	    		else{
	    			canvas.showMessage("Please choose a profile.");
	    		}
    		}
	    }
    	if(e.getActionCommand().equals("Change Picture") || e.getSource() == picture){
    		GImage image = null;   		
    		if(currentProfile != null){
	    		if(picture.getText().trim().length() == 0) {
	    			canvas.showMessage("Please enter a valid image file address.");
	    		}
	    		else{
		   		 	try { 
		   		 		image = new GImage(picture.getText()); 
		   		 		database.getProfile(currentProfile).setImage(image);
			   			canvas.displayProfile(database.getProfile(currentProfile));   
		   	    		canvas.showMessage("Image updated.");			   			
		    		 } catch (ErrorException ex) { 
		    			 canvas.showMessage(ex.getMessage());
		    			 }
	    		}
    		}
    		else{
    			canvas.showMessage("Please select a profile.");
    		}
    	}
    	if(e.getActionCommand().equals("Add Friend") || e.getSource() == friend){
    		if(currentProfile != null){
    			if(currentProfile.equals(friend.getText())){
    				canvas.showMessage("You cant choose yourself as a friend.");
    			}
    			else if(database.containsProfile(friend.getText())){
	    				if(database.getProfile(currentProfile).friends.contains(friend.getText()))
	    					canvas.showMessage("The friend already exist.");
	    				else{
	    					database.getProfile(currentProfile).friends.add(friend.getText());
	    					database.getProfile(friend.getText()).friends.add(currentProfile);
	    					canvas.displayProfile(database.getProfile(currentProfile));
	    					canvas.showMessage(friend.getText() + " added as new friend.");
	    				}
	    			}    					
	    			else{
	    				canvas.showMessage("This person does not have a profile in our database.");
	    			}
	    		}
    		else{
    			canvas.showMessage("Please choose a profile.");
    		}
    	}  	
    }

    /*IVars*/
    FacePamphletCanvas canvas;
    FacePamphletDatabase database;
    JTextField nameField;
    JTextField status;
    JTextField picture; 
    JTextField friend;
    String currentProfile = null;
}

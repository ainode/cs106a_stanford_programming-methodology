/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import acm.program.Program;

import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		message = new GLabel("");
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		message.setLabel(msg);
		message.setFont(MESSAGE_FONT);
		add(message, (APPLICATION_WIDTH - msg.length())/2 ,APPLICATION_HEIGHT - BOTTOM_MESSAGE_MARGIN);
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		//Clear the canvas of the previous profile
		this.removeAll();
		displayName(profile.personsName);
		displayImage(profile.personsImage);
		displayStatus(profile);
		displayFriends(profile.friends);
	}
	
	 private void displayName(String name){
		GLabel personsName = new GLabel(name);
		personsNameHeight = personsName.getHeight();
		personsName.setLocation(LEFT_MARGIN, TOP_MARGIN + personsNameHeight);
		personsName.setColor(Color.BLUE);
		personsName.setFont(PROFILE_NAME_FONT);
		add(personsName);
	}
	 
/** This function checks if there is any image chosen 
 * by the user and if not will draw the frame with the 
 * text "No image" in the center. Otherwise it will 
 * display the image.	 
 * @param image
 */
	public void displayImage(GImage image){
		topOfImage = IMAGE_MARGIN + TOP_MARGIN + personsNameHeight;
		if(image == null){
			GRect frame = new GRect(LEFT_MARGIN, topOfImage, IMAGE_WIDTH, IMAGE_HEIGHT);
			add(frame);
			GLabel noImageLabel = new GLabel("No image");
			noImageLabel.setFont(PROFILE_IMAGE_FONT);
			add(noImageLabel, LEFT_MARGIN + (IMAGE_WIDTH - noImageLabel.getWidth())/2, IMAGE_MARGIN + TOP_MARGIN + personsNameHeight + (IMAGE_HEIGHT - noImageLabel.getHeight())/2);
		}
		else{
			image.setBounds(LEFT_MARGIN, topOfImage, IMAGE_WIDTH, IMAGE_HEIGHT);
			add(image);
		}
	}
	
	private void displayStatus(FacePamphletProfile profile){
		GLabel statLabel;
		if(profile.getStatus() != null){
			statLabel = new GLabel(profile.personsName + " is " + profile.getStatus() + ".");
		}
		else{
			statLabel = new GLabel("No current status.");
		}
		statLabel.setFont(PROFILE_STATUS_FONT);
		statLabel.setLocation(LEFT_MARGIN, topOfImage + IMAGE_HEIGHT + statLabel.getHeight() + STATUS_MARGIN);
		add(statLabel);
	}
	
	private void displayFriends(ArrayList<String> friends){
		GLabel header = new GLabel("Friends:");
		header.setFont(PROFILE_FRIEND_LABEL_FONT);		
		add(header, getWidth()/2, topOfImage);
		if(friends.size() > 0){
			for(int i = 0; i < friends.size(); i++){
				GLabel friendName = new GLabel(friends.get(i));
				friendName.setFont(PROFILE_FRIEND_FONT);
				add(friendName, getWidth()/2, topOfImage + header.getHeight() + i * friendName.getHeight());
			}
		}
	}
	
	/** I Vars **/
	double topOfImage;
	GLabel message;
	double personsNameHeight;
}

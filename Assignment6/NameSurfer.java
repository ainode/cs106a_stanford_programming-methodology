/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public static void main(String[] args){
		new NameSurfer().start(args);
	}
	public void init() {
	    addControls();
	    dataBase = new NameSurferDataBase(NAMES_DATA_FILE);
	    graph = new NameSurferGraph();
	    add(graph);
	}

	public void addControls(){
		add(new JLabel("Name"), NORTH);
		text = new JTextField(10);
		add(text, NORTH);
		add(new JButton("Graph"), NORTH);
		add(new JButton("Clear"), NORTH);
		addActionListeners();
	}
/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Graph")){
			if(text.getText().length() != 0){
				NameSurferEntry entry = dataBase.findEntry(text.getText());
				if (entry != null)
					graph.addEntry(entry);
			}
		}
		if(e.getActionCommand().equals("Clear")){
					graph.clear();
		}
	}
	
/* Instance variables*/
	JTextField text;
	NameSurferDataBase dataBase;
	private NameSurferGraph graph;
}

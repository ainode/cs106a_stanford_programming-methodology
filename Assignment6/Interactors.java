import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GPoint;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;





public class Interactors extends GraphicsProgram{
	
	private static final double BOX_WIDTH = 120; 
	private static final double BOX_HEIGHT = 50; 
	
	public void run(){
		add(new JLabel("Name"), NORTH);
		text = new JTextField(10);
		add(text, NORTH);
		addButtons();
		addActionListeners();
		addMouseListeners();
	}
	
	public void addButtons(){
		add(new JButton("Add"), NORTH);
		add(new JButton("Remove"), NORTH);
		add(new JButton("Clear"), NORTH);		
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("Add")){
			addBox();
		}
		else if(e.getActionCommand().equals("Remove")){
			removeBox();
		}
		else if(e.getActionCommand().equals("Clear")){
			clearBoxes();
		}
	}
	
	public void mousePressed(MouseEvent e){
		last = new GPoint(e.getPoint());
		object = getElementAt(last);
	}
	
	public void mouseDragged(MouseEvent e){
		if(object != null){
			object.move(e.getX() - last.getX(), e.getY() - last.getY());
			last = new GPoint(e.getPoint());
		}
	}
	
	public void addBox(){
		GCompound compound = new GCompound();
		GRect box = new GRect(BOX_WIDTH, BOX_HEIGHT);
		String boxName = text.getText(); 
		GLabel label = new GLabel(boxName);
		compound.add(box, 0, 0);
		compound.add(label, (BOX_WIDTH - label.getWidth())/2, (BOX_HEIGHT + label.getHeight()/2)/2);
		add(compound, getWidth()/2, getHeight()/2 );
		map.put(boxName, compound);
	}
	
	public void removeBox(){
		if(map.get(text.getText()) != null){
			remove(map.get(text.getText()));
		}
	}
	
	public void clearBoxes(){
		for(String boxName : map.keySet()){
			remove(map.get(boxName));
		}
	}
		
	/*Instance variables*/
	JTextField text;
	GObject object;
    GPoint last;
	HashMap<String, GCompound> map = new HashMap<>(); 
}

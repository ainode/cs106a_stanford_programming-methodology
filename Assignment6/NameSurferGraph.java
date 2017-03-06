/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	private static final int BAR_SPACE = APPLICATION_WIDTH/NDECADES; 
	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		addGrid();
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		entries.removeAll(entries);
		update();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		entries.add(entry);
		update();
	}
	
	private void addGrid(){
		int barX = 0;
		int decade = START_DECADE;
		for(int i = 0; i < NDECADES; i++){
			add(new GLabel(Integer.toString(decade), barX, resizeY()));
			barX += resizeX();
			add(new GLine(barX, 0, barX, resizeY()));
			decade += 10;
		}
		add(new GLine(0, resizeY() - GRAPH_MARGIN_SIZE, APPLICATION_WIDTH, resizeY() - GRAPH_MARGIN_SIZE));
	}
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	private void update() {
		removeAll();
		addGrid();
		drawGraphs();
	}
	
	private void drawGraphs(){
		for(int i = 0; i < entries.size(); i++ ){
			double previousX = 0;
			double rankYCoordinate = getRankCoordinates(i);
			double previousY = getCurrentY(rankYCoordinate);
			addRankLable(previousX, previousY, entries.get(i).getRank(1), entries.get(i).getName());
			for(int j = 2; j <= NDECADES; j++){
				double rank = (((double)entries.get(i).getRank(j))/1000) * getHeight() / MAX_APPLICATION_HEIGHT * APPLICATION_HEIGHT;
				double currentY = getCurrentY(rank);
				double currentX = previousX + resizeX();
				GLine graphLine = new GLine(previousX, previousY, currentX,
						(currentY));
				Color color = getLineColor(i);
				graphLine.setColor(color);
				add(graphLine);
				addRankLable(currentX, currentY, (double)entries.get(i).getRank(j), entries.get(i).getName());
				previousX  = currentX;
				previousY = currentY;
			}
		}
	}
	
	private double getRankCoordinates(int i){
		return ((double)entries.get(i).getRank(1)/1000) * getHeight() / (double)MAX_APPLICATION_HEIGHT * (double)APPLICATION_HEIGHT;
	}
	
	private double getCurrentY(double d){
		if(d == 0)
			return (getHeight() / (double)MAX_APPLICATION_HEIGHT) * (double)APPLICATION_HEIGHT - GRAPH_MARGIN_SIZE;
		else
			return d;
	}
	
	private double resizeX(){
		return (getWidth()/(double)MAX_APPLICATION_WIDTH) * APPLICATION_WIDTH / NDECADES;
	}
	
	private double resizeY(){
		return (getHeight()/(double)MAX_APPLICATION_HEIGHT) * APPLICATION_HEIGHT;
	}
	
	private void addRankLable(double currentX, double currentY, double rank, String name) {
		add(new GLabel(name +" "+Double.toString(rank), currentX, currentY));
	}
	
	private Color getLineColor(int i){
		int mod = i%4;
		Color color = null;
		switch (mod){
			case 0 : color = Color.BLUE;
			break;
			case 1 : color = Color.BLACK;
			break;
			case 2 : color = Color.RED;
			break;
			case 3 : color = Color.MAGENTA;
			break;
		}
		return color;
	}
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	
	/* Instance variables*/
	ArrayList<NameSurferEntry> entries = new ArrayList<>();
}

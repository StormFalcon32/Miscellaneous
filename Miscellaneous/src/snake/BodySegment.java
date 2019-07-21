package snake;


import java.awt.Color;
import java.awt.Graphics;


public class BodySegment implements Comparable<BodySegment> {
	//this BodySegment will act like a NODE
	//  he will point to the next BodySegment in the Snake
	//  You will need a variable for this
	
	public static final int SIZE=15;
	public Color hue = Color.green;
	public static int nextID = 0;
	
	public int xPos, yPos;
//	Doubly linked needs previous and next
	public BodySegment next;
	public BodySegment prev;
	
	public BodySegment(int x, int y, BodySegment a, BodySegment b){
		xPos = x;
		yPos = y;
		next = a;
		prev = b;
	}
	
	public BodySegment(int x, int y){
		xPos = x;
		yPos = y;
	}
	
	//~~~~~~~~~~~~~~~~accessors and mutators~~~~~~~~~~~~~~~~~~~~~~
	
	
	
	public boolean isTouching(BodySegment sp) {
		if (Math.abs(sp.xPos - this.xPos) <= 15 && Math.abs(sp.yPos - this.yPos) <= 15) {
			return true;
		}
		return false;		
	}
	
	//each BodySegment knows how to draw himself :)
	public void draw(Graphics g){
		g.setColor(hue);
		g.fillOval(xPos, yPos, SIZE, SIZE);
		//just for testing purposes, you can take this out later
		g.setColor(Color.BLACK);
		g.drawString(" ", xPos+SIZE/4, yPos+SIZE);
	}
	
//	In order to use contains
	public boolean equals(Object other) {
		if (((BodySegment) other).xPos == this.xPos && ((BodySegment) other).yPos == this.yPos) {
			return true;
		}
		return false;
	}
//	In order to use TreeSet
	@Override
	public int compareTo(BodySegment other) {
		if (this.xPos == other.xPos) {
			return Integer.compare(this.yPos, other.yPos);
		}
		return Integer.compare(this.xPos, other.xPos);
	}
}

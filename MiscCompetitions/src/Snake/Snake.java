package Snake;


import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.*;

public class Snake implements KeyListener{
	
	//variables you will need
	// 1) pointer to the first and/or last piece of the snake
	// 2) a variable to keep track of what direction this snake is facing
	public BodySegment first;
	public BodySegment last;
	public int dir;
	public int player;
	public int size = 1;
	public boolean tie = false;
	
	public Snake(int a) {
//		Player 1 and Player 2 will spawn in different places and start going opposite directions
		if (a == 1) {
			first = new BodySegment(285, 285, null, null);
			dir = 1;
		} else {
			first = new BodySegment(150, 150, null, null);
			dir = 0;
		}
		last = first;
		player = a;
	}
	
	public void addFirst() {
//		Increment size
		BodySegment temp;
		size++;
//		Depending on direction, add a node in front of the current first node and set it as the new first node
//		Old first node's previous node is new first node
		switch (dir) {
		case 0:
//			Up
			temp = new BodySegment(first.xPos, first.yPos - 15, first, null);
			first.prev = temp;
			first = temp;
			break;
		case 1:
//			Down
			temp = new BodySegment(first.xPos, first.yPos + 15, first, null);
			first.prev = temp;
			first = temp;
			break;
		case 2:
//			Left
			temp = new BodySegment(first.xPos - 15, first.yPos, first, null);
			first.prev = temp;
			first = temp;
			break;
		case 3:
//			Right
			temp = new BodySegment(first.xPos + 15, first.yPos, first, null);
			first.prev = temp;
			first = temp;
			break;
		}
	}
	
	public void removeLast() {
//		New last node is the previous node of old last
//		New last node does not have a next pointer
		last = last.prev;
		last.next = null;
		size--;
	}
	
	public boolean isDead(Snake other) {
//		Loop through and check if the head is overlapping with anything		
		BodySegment curr = this.first.next;
		int firstX = this.first.xPos;
		int firstY = this.first.yPos;
		while (curr != null) {
			if (curr.xPos == firstX && curr.yPos == firstY) {
				return true;
			}
			curr = curr.next;
		}
		curr = this.first;
//		Check if the head of the snake is out of bounds
		if (curr != null && (curr.xPos < 0 || curr.xPos > 445 || curr.yPos < 0 || curr.yPos > 445)) {
			return true;
		}
//		Check if the head of this snake is touching the other snake
		BodySegment otherCurr = other.first;
		for (int i = 0; i < other.size; i++) {
			if (curr != null && otherCurr != null && curr.equals(otherCurr)) {
				if (curr == this.first && otherCurr == other.first) {
					this.tie = true;
				}
				return true;
			}
			if (otherCurr.next != null) {
				otherCurr = otherCurr.next;
			}
		}
		return false;
	}
	
	public void draw(Graphics g){
//		Draw every segment
		BodySegment curr = first;
		while (curr.next != null) {
			curr.draw(g);
			curr = curr.next;
		}
//		Last segment wasn't drawn
		curr.draw(g);
	}
	
	
	@Override
//	Player 1 uses arrows and player 2 uses WASD
	public void keyPressed(KeyEvent e) {
		if (player == 1) {
			if (e.getKeyCode() == KeyEvent.VK_UP && dir != 1) {
				dir = 0;
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN && dir != 0) {
				dir = 1;
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT && dir != 3) {
				dir = 2;
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT && dir != 2) {
				dir = 3;
			}
		} else {
			if (e.getKeyCode() == KeyEvent.VK_W && dir != 1) {
				dir = 0;
			} else if (e.getKeyCode() == KeyEvent.VK_S && dir != 0) {
				dir = 1;
			} else if (e.getKeyCode() == KeyEvent.VK_A && dir != 3) {
				dir = 2;
			} else if (e.getKeyCode() == KeyEvent.VK_D && dir != 2) {
				dir = 3;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
	
}

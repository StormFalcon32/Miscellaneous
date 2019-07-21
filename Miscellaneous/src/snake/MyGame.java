package snake;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JOptionPane;

public class MyGame extends SnakeGame{
	//things you inherited from SnakeGame
	//protected Snake player;
	//protected BodySegment food;
	//protected double waitSeconds;
	private boolean gameOver = false;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyGame() {
		super();
		setBG(new Color(100,100,0));
		setGridColor( new Color(0,255,255,100));
		
		playGame();
	}
	
	public void gameFrame() {
//		Check for death
		if (gameOver) {
			return;
		}
//		Calculate time and display it
		int time = (int) ((System.currentTimeMillis() - startTime) / 1000);
		timer.setFont(new Font("TimesRoman", Font.BOLD, 25));
		timer.setText("Time: " + time);
		score.setFont(new Font("TimesRoman", Font.BOLD, 20));
//		Display each player's score (their size)
		score.setText("P1 Score: " + player1.size + "\n" + "P2 Score: " + player2.size);
		drawGame();//do this at some point
//		Movement
		player1.addFirst();
		player1.removeLast();
		player2.addFirst();
		player2.removeLast();
		BodySegment curr = player1.first;
//		Check whether either player is touching the food
		while (curr.next != null) {
			if (curr.isTouching(food)) {
//				If so, add a node and add a new food
				player1.addFirst();
				food = null;
				placeFood();
			}
			curr = curr.next;
		}
//		Forgot to check last node
		if (curr.isTouching(food)) {
			player1.addFirst();
			food = null;
			placeFood();
		}
		curr = player2.first;
		while (curr.next != null) {
			if (curr.isTouching(food)) {
//				If so, add a node and add a new food
				player2.addFirst();
				food = null;
				placeFood();
			}
			curr = curr.next;
		}
//		Forgot to check last node
		if (curr.isTouching(food)) {
			player2.addFirst();
			food = null;
			placeFood();
		}
//		Check whether either player either killed themselves or killed each other
		boolean p1Dead = player1.isDead(player2);
		boolean p2Dead = player2.isDead(player1);
		if (p1Dead && p2Dead) {
			JOptionPane.showMessageDialog(null, "Tie!");
			gameOver = true;
			new ScoreFrame(new ScoreRecord(System.getProperty("user.name"), time, player1.size));
		}
		if (p1Dead) {
			if (!p2Dead) {
				JOptionPane.showMessageDialog(null, "P2 Won!");
				gameOver = true;
				new ScoreFrame(new ScoreRecord(System.getProperty("user.name"), time, player1.size));
			}
		}
		if (p2Dead) {
			if (!p1Dead) {
				JOptionPane.showMessageDialog(null, "P1 Won!");
				gameOver = true;
				new ScoreFrame(new ScoreRecord(System.getProperty("user.name"), time, player1.size));
			}
		}
	}
	
	public static void main(String[] args) {
		new MyGame();
	}	
}

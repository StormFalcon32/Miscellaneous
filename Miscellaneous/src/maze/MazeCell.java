package maze;

import java.awt.*;
import javax.swing.*;

public class MazeCell extends JPanel {

	private static final long serialVersionUID = 1L;
	private boolean[] borders;
	public static final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;
	public static final int BLANK = 0, VISITED = 1, DEAD = 2;

	private int wallThickness = 5;
	private Stroke str;
	private Color defaultBG = Color.white;
	private Color[] colors = {Color.white, Color.cyan, Color.black, Color.darkGray, Color.gray, Color.green, Color.lightGray, Color.magenta, Color.orange, Color.pink, Color.red, Color.yellow};
	public MazeCell next;

	private int status;
	private int row, col;

	public MazeCell(int r, int c) {
		super();
		str = new BasicStroke(wallThickness);
		row = r;
		col = c;
		borders = new boolean[4];
		for (int i = 0; i < 4; i++)
			borders[i] = true;
		this.setBackground(defaultBG);
		status = BLANK;
	}

	public int row() {
		return row;
	}

	public int col() {
		return col;
	}

	public void setStatus(int x) {
		status = x;
		this.setBackground(colors[status]);
		this.paint(this.getGraphics());
	}

	public boolean isBlank() {
		return status == BLANK;
	}

	public boolean isVisited() {
		return status == VISITED;
	}

	public boolean isDead() {
		return status == DEAD;
	}

	public int getStatus() {
		return status;
	}

	public boolean dont = false;

	public void setDontComeBackJustYet(boolean b) {
		dont = b;
	}

	public boolean isBlockedUp() {
		return borders[UP];
	}

	public boolean isBlockedDown() {
		return borders[DOWN];
	}

	public boolean isBlockedLeft() {
		return borders[LEFT];
	}

	public boolean isBlockedRight() {
		return borders[RIGHT];
	}
	
	public boolean isBlockedDir(int dir) {
		return borders[dir];
	}

	public void clearWallUp() {
		borders[UP] = false;
		repaint();
	}

	public void clearWallDown() {
		borders[DOWN] = false;
		repaint();
	}

	public void clearWallLeft() {
		borders[LEFT] = false;
		repaint();
	}
	
	public void clearWallRight() {
		borders[RIGHT] = false;
		repaint();
	}

	public void clearWallDir(int dir) {
		borders[dir] = false;
		repaint();
	}
	
	public void blockWallUp() {
		borders[UP] = true;
		repaint();
	}
	
	public void blockWallDown() {
		borders[DOWN] = true;
		repaint();
	}
	
	public void blockWallLeft() {
		borders[LEFT] = true;
		repaint();
	}
	
	public void blockWallRight() {
		borders[RIGHT] = true;
		repaint();
	}
	
	public void blockWallDir(int dir) {
		borders[dir] = true;
		repaint();
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);
//		Random number 3 - 11
		int color = (int) (Math.random() * 9 + 3);
		g.setColor(colors[color]);
		((Graphics2D) g).setStroke(str);
		if (borders[UP])
			g.drawLine(0, 0, this.getWidth(), 0);
		if (borders[DOWN])
			g.drawLine(0, this.getHeight(), this.getWidth(), this.getHeight());
		if (borders[LEFT])
			g.drawLine(0, 0, 0, this.getHeight());
		if (borders[RIGHT])
			g.drawLine(this.getWidth(), 0, this.getWidth(), this.getHeight());

		// display your status
		Color c = new Color(colors[status].getRed(), colors[status].getGreen(), colors[status].getBlue(), 100);
		g.setColor(c);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.fillOval(this.getWidth() / 3, this.getHeight() / 3, this.getWidth() / 3, this.getHeight() / 3);
	}

	public String toString() {
		return "(" + row + ", " + col + ")";
	}

}

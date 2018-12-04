package Maze;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MazeFrame extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;
	public int ROWS, COLS;
	private int exitRow, entranceRow;

	private JPanel controls, maze;
	private JButton solve;
	private JButton newMaze;
	// *** you will need a 2DArray of MazeCells****

	public MazeCell[][] cells;
	private CellStack path = new CellStack();

	/** Constructor **/
	public MazeFrame() {
		super("MAZE");

		COLS = Integer.parseInt(JOptionPane.showInputDialog("Columns?"));
		ROWS = Integer.parseInt(JOptionPane.showInputDialog("Rows?"));
		if (COLS < 3 || ROWS < 3) {
			JOptionPane.showMessageDialog(null, "Minimum dimensions of 3");
			System.exit(0);
		}
		
		cells = new MazeCell[ROWS][COLS];

		setUpControlPanel();// make the buttons & put them in the north
		instantiateCells();// give birth to all the mazeCells & get them onto the screen
		carveARandomMaze();

		// finishing touches
		this.setSize(ROWS * 20, COLS * 20);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(ROWS * 40, COLS * 40);
		this.setVisible(true);
	} // end constructor

	/* 1111111111111111 PHASE 1 STUFF 1111111111111111111111 */
	private void instantiateCells() {
		maze = new JPanel();
		maze.setBackground(Color.WHITE);
		maze.setLayout(new GridLayout(ROWS, COLS));
		// construct your 2D Array & instantiate EACH MazeCell
		// be sure to add each MazeCell to the panel
		// * call maze.add( ?the cell ? );
		/** ~~~~ WRITE CODE HERE ~~~~ **/
//		Loop through maze
		for (int i = 0; i < ROWS; i++)
			for (int j = 0; j < COLS; j++) {
				cells[i][j] = new MazeCell(i, j);
				maze.add(cells[i][j]);
			}

		/** ~~~~ *************** ~~~~ **/
		// put the maze on the screen
		this.add(maze, BorderLayout.CENTER);
	}
	public void solveMaze() {
		path.push(cells[entranceRow][0]);
		cells[entranceRow][0].setStatus(1);
//		call solveStep until exit
		while (path.peek().col() != COLS - 1) {
			solveStep(path.peek());
		}
//		Reset everything
		for (MazeCell[] c : cells)
			for (MazeCell mc : c)
				if (mc.getStatus() == 2)
					mc.setStatus(0);

		for (int i = 0; i < ROWS; i++)
			cells[i][0].setStatus(2);

		for (int i = 0; i < ROWS; i++)
			cells[i][COLS - 1].setStatus(2);

		cells[entranceRow][0].setStatus(1);
		cells[exitRow][COLS - 1].setStatus(1);

	}

	/** 2222222222222222222 PHASE 2 STUFF 22222222222222222222222222 **/
	public boolean isInBounds(int r, int c) {
		return r >= 0 && r < ROWS && c >= 0 && c < COLS;
	}

	public void solveStep(MazeCell mc) { // takes the next step in solving the maze
//		Go to next blankNeighbor
		if (blankNeighbor(mc) != null) {
			MazeCell toAdd = blankNeighbor(mc);
			path.push(toAdd);
			toAdd.setStatus(1);
		} else {
			path.peek().setStatus(2);
			path.pop();
		}
	}

	/* 33333333333333333333 Phase 3 stuff 3333333333333333333333333 */

	public MazeCell getNeighbor(MazeCell mc, int dir) {
		if (dir == LEFT && mc.col() != 0)
			return cells[mc.row()][mc.col() - 1];

		if (dir == RIGHT && mc.col() != COLS - 1)
			return cells[mc.row()][mc.col() + 1];

		if (dir == UP && mc.row() != 0)
			return cells[mc.row() - 1][mc.col()];

		if (dir == DOWN && mc.row() != ROWS - 1)
			return cells[mc.row() + 1][mc.col()];

		return null;
	}

	public MazeCell blankNeighbor(MazeCell mc) {
		for (int i = 0; i < 4; i++)
			if (getNeighbor(mc, i) != null && !mc.isBlockedDir(i) && getNeighbor(mc, i).isBlank())
				return getNeighbor(mc, i);
		return null;
	}

	public ArrayList<MazeCell> blankNeighbors(MazeCell mc) {
		ArrayList<MazeCell> list = new ArrayList<MazeCell>();

		for (int i = 0; i < 4; i++)
			if (getNeighbor(mc, i) != null && getNeighbor(mc, i).isBlank())
				list.add(getNeighbor(mc, i));
		if (list.size() != 0)
			return list;
		return null;
	}

	public int getDirectionFrom(MazeCell orig, MazeCell dest) { // only works for touching squares
		if (orig.row() == dest.row())
			if (orig.col() == dest.col() + 1)
				return MazeCell.LEFT;
			else
				return MazeCell.RIGHT;
		else if (orig.row() == dest.row() + 1)
			return MazeCell.UP;
		else
			return MazeCell.DOWN;
	}

	public void carveStep() {
		while (path.size != 0) {
			ArrayList<MazeCell> curr = blankNeighbors(path.peek());
			if (curr == null) {
				path.peek().setStatus(2);
				path.pop();
				continue;
			}
			MazeCell nextMove = curr.get((int) (Math.random() * curr.size()));
			path.peek().clearWallDir(getDirectionFrom(path.peek(), nextMove));
			nextMove.clearWallDir(getDirectionFrom(nextMove, path.peek()));
			nextMove.setStatus(1);
			path.push(nextMove);

		}
		resetMaze();
	}

	public void carveARandomMaze() {
		for (int i = 0; i < ROWS; i++) {
			cells[i][0].setStatus(2);
		}
		for (int i = 0; i < ROWS; i++) {
			cells[i][COLS - 1].setStatus(2);
		}
//		Choose random entrance and exit
		entranceRow = (int) (Math.random() * ROWS);
		cells[entranceRow][0].setStatus(0);

		exitRow = (int) (Math.random() * ROWS);
		cells[exitRow][COLS - 1].setStatus(0);

//		Push entrance
		
		path.push(cells[entranceRow][0]);
		cells[entranceRow][0].setStatus(1);

		cells[entranceRow][1].clearWallLeft();
		cells[exitRow][COLS - 1].clearWallLeft();
//		Carve maze
		carveStep();
//		Block borders
		for (int i = 0; i < COLS; i++)
			cells[0][i].blockWallUp();
		for (int i = 0; i < COLS; i++)
			cells[ROWS - 1][i].blockWallDown();
	}

	// 4444444444444444444 PHASE 4 STUFF 4444444444444444444444444444
	private void resetMaze() {
//		Set all statuses to 0
		for (MazeCell[] c : cells)
			for (MazeCell mc : c)
				mc.setStatus(0);
//		Set status for edges
		for (int i = 0; i < ROWS; i++)
			cells[i][0].setStatus(2);

		for (int i = 0; i < ROWS; i++)
			cells[i][COLS - 1].setStatus(2);
//		Set status for entrance and exit
		cells[entranceRow][0].setStatus(0);
		cells[exitRow][COLS - 1].setStatus(0);
	}

	// This gets called any time that you press a button
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == solve) {
			this.solveMaze();
		}
		if (e.getSource() == newMaze) {
//			Block every cell off and reset it's status
			for (MazeCell[] c : cells)
				for (MazeCell mc : c)
					for (int i = 0; i < 4; i++) {
						if (!mc.isBlockedDir(i)) {
							mc.blockWallDir(i);
						}
						mc.setStatus(0);
					}
//			Carve a new random maze
			carveARandomMaze();
		}
	} // end action performed

	private void setUpControlPanel() {
		controls = new JPanel();

		solve = new JButton("Solve Maze");
		solve.addActionListener(this);
		controls.add(solve);
		newMaze = new JButton("New Maze");
		newMaze.addActionListener(this);
		controls.add(newMaze);
		this.add(controls, BorderLayout.NORTH);
	}

	public static void main(String[] args) {
		new MazeFrame();
		
	}

}

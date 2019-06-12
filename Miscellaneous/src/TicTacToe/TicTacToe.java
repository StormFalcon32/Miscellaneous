package TicTacToe;

import java.util.Scanner;

public class TicTacToe {
	
	public static void main(String[] args) throws InterruptedException {
		// 0 is nothing, 1 is X, -1 is O
		int[][] board = new int[3][3];
		Scanner in = new Scanner(System.in);
		System.out.println("Try to beat the AI!");
		System.out.println();
		System.out.println("Player is X");
		System.out.println();
		Thread.sleep(2000);
		System.out.println("Type 1 to go first, 2 to go second");
		int playerOrder = in.nextInt();
		while (playerOrder != 2 && playerOrder != 1) {
			System.out.println("Invalid num, type 1 to go first, 2 to go second");
			playerOrder = in.nextInt();
		}
		if (playerOrder == 2) {
			// Use minMax to calculate best AI move
			
			int bestScore = Integer.MAX_VALUE;
			Move aiBestMove = new Move();
			
			for (int r = 0; r < 3; r++) {
				for (int c = 0; c < 3; c++) {
					// If empty cell
					if (board[r][c] == 0) {
						// Try current cell
						board[r][c] = -1;
						int currScore = minMax(board, 0, true, new Move(r, c));
						// Undo try
						board[r][c] = 0;
						if (currScore < bestScore) {
							aiBestMove.row = r;
							aiBestMove.col = c;
							bestScore = currScore;
						}
					}
				}
			}
			board[aiBestMove.row][aiBestMove.col] = -1;
		}
		while (isWinner(board) == 0) {
			printBoard(board);
			System.out.println();
			System.out.println("Choose a numbered square");
			int choice = in.nextInt() - 1;
			int cChoice = choice % 3;
			int rChoice = (choice - cChoice) / 3;
			while (choice < 0 || choice > 8 || board[rChoice][cChoice] != 0) {
				System.out.println("Invalid num, choose another one");
				choice = in.nextInt() - 1;
				cChoice = choice % 3;
				rChoice = (choice - cChoice) / 3;
			}
			board[rChoice][cChoice] = 1;
			System.out.println();
			
			// Use minMax to calculate best AI move
			
			int bestScore = Integer.MAX_VALUE;
			Move aiBestMove = new Move();
			
			for (int r = 0; r < 3; r++) {
				for (int c = 0; c < 3; c++) {
					// If empty cell
					if (board[r][c] == 0) {
						// Try current cell
						board[r][c] = -1;
						int currScore = minMax(board, 0, true, new Move(r, c));
						// Undo try
						board[r][c] = 0;
						if (currScore < bestScore) {
							aiBestMove.row = r;
							aiBestMove.col = c;
							bestScore = currScore;
						}
					}
				}
			}
			if (aiBestMove.row != -1 && aiBestMove.col != -1) {
				board[aiBestMove.row][aiBestMove.col] = -1;
			}
		}
		printBoard(board);
		System.out.println((isWinner(board) == 1) ? "Player Wins!" : ((isWinner(board) == -1) ? "AI Wins!" : "Tie!"));
		in.close();
	}
	
	static int minMax(int[][] grid, int depth, boolean maximizer, Move root) {
		int result = isWinner(grid);
		if (result == 1) {
			return 50 - depth;
		} else if (result == -1) {
			return -50 + depth;
		} else if (result == -100) {
			return 0;
		}
		if (maximizer) {
			int max = Integer.MIN_VALUE;
			for (int r = 0; r < 3; r++) {
				for (int c = 0; c < 3; c++) {
					// If empty cell
					if (grid[r][c] == 0) {
						grid[r][c] = 1;
						max = Math.max(max, minMax(grid, depth + 1, false, new Move(r, c)));
						// Undo try
						grid[r][c] = 0;
					}
				}
			}
			return max;
		} else {
			int min = Integer.MAX_VALUE;
			for (int r = 0; r < 3; r++) {
				for (int c = 0; c < 3; c++) {
					// If empty cell
					if (grid[r][c] == 0) {
						grid[r][c] = -1;
						min = Math.min(min, minMax(grid, depth + 1, true, new Move(r, c)));
						// Undo try
						grid[r][c] = 0;
					}
				}
			}
			return min;
		}
	}
	
	static int isWinner(int[][] grid) {
		int count = 0;
		int val = 0;
		// Check rows
		for (int r = 0; r < 3; r++) {
			count = 0;
			val = 0;
			for (int c = 0; c < 2; c++) {
				if (grid[r][c] == grid[r][c + 1] && grid[r][c] != 0) {
					val = grid[r][c];
					count++;
				}
			}
			if (count == 2) {
				return val;
			}
		}
		// Check cols
		for (int c = 0; c < 3; c++) {
			count = 0;
			val = 0;
			for (int r = 0; r < 2; r++) {
				if (grid[r][c] == grid[r + 1][c] && grid[r][c] != 0) {
					val = grid[r][c];
					count++;
				}
			}
			if (count == 2) {
				return val;
			}
		}
		// Check diagonals
		count = 0;
		val = 0;
		for (int i = 0; i < 2; i++) {
			if (grid[i][i] == grid[i + 1][i + 1] && grid[i][i] != 0) {
				val = grid[i][i];
				count++;
			}
		}
		if (count == 2) {
			return val;
		}
		count = 0;
		val = 0;
		for (int i = 1; i < 3; i++) {
			if (grid[i][2 - i] == grid[i - 1][3 - i] && grid[i][2 - i] != 0) {
				val = grid[i][2 - i];
				count++;
			}
		}
		if (count == 2) {
			return val;
		}
		// Check for tie
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				if (grid[r][c] == 0) {
					return 0;
				}
			}
		}
		return -100;
	}
	
	static void printBoard(int[][] grid) {
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				int x = grid[r][c];
				System.out.print(((x == 1) ? "X" : (x == -1) ? "O" : ((r * grid.length) + c) + 1));
				if (c != grid[r].length - 1) {
					System.out.print("|");
				}
			}
			System.out.println();
			if (r != grid.length - 1) {
				System.out.println("-----");
			}
		}
		System.out.println();
	}
	
	static class Move {
		int row = -1;
		int col = -1;
		
		public Move() {
			
		}
		
		public Move(int a, int b) {
			row = a;
			col = b;
		}
	}
}

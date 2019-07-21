package dailyproblem;

import java.util.LinkedList;

public class Prob6 {
	
	static int[] dirR = { 0, 0, 1, -1 };
	static int[] dirC = { 1, -1, 0, 0 };
	static int R;
	static int C;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean[][] grid = { { false, false, false, false }, { true, true, false, true }, { false, false, false, false }, { false, false, false, false } };
		R = grid.length;
		C = grid[0].length;
		int startR = 3;
		int startC = 0;
		int endR = 0;
		int endC = 0;
		LinkedList<Node> q = new LinkedList<Node>();
		q.add(new Node(startR, startC, 0));
		boolean[][] visited = new boolean[R][C];
		while (!q.isEmpty()) {
			Node curr = q.poll();
			if (curr.r == endR && curr.c == endC) {
				System.out.println(curr.dist);
				break;
			}
			visited[curr.r][curr.c] = true;
			for (int i = 0; i < 4; i++) {
				int newR = dirR[i] + curr.r;
				int newC = dirC[i] + curr.c;
				if (inRange(newR, newC) && !visited[newR][newC] && !grid[newR][newC]) {
					q.add(new Node(newR, newC, curr.dist + 1));
				}
			}
		}
	}
	
	static boolean inRange(int r, int c) {
		return r < R && r >= 0 && c < C && c >= 0;
	}
	
	static class Node {
		int r;
		int c;
		int dist;
		
		public Node(int a, int b, int d) {
			r = a;
			c = b;
			dist = d;
		}
		
	}
}

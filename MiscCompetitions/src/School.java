import java.util.ArrayList;
import java.util.LinkedList;

public class School {

	static int[] dirX = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int[] dirY = { -1, -1, 0, 1, 1, 1, 0, -1 };

	public static void main(String[] args) {
		boolean[][] fullMap = new boolean[10][10];
		int[][] robotMap = new int[10][10];
		
		boolean[][] fuelMap = new boolean[10][10];
		boolean[][] karbMap = new boolean[10][10];
		fuelMap[9][9] = true;

		fullMap[4][0] = true;
		fullMap[4][1] = true;
		fullMap[4][2] = true;
		fullMap[4][3] = true;
		fullMap[4][4] = true;
		fullMap[0][4] = true;
		fullMap[1][4] = true;
		fullMap[2][4] = true;

		robotMap[8][7] = 1;
		robotMap[7][8] = 1;
		robotMap[8][8] = 1;
		int steps = 4;
		int x = 0;
		int y = 0;
		while (x != 9 || y != 9) {
			ArrayList<Integer> path = bfs(new Pos(x, y), 0, fullMap, robotMap, fuelMap, karbMap);
			int changeX = 0;
			int changeY = 0;
			for (int i = path.size() - 1; i >= 0; i--) {
				changeX += dirX[path.get(i)];
				changeY += dirY[path.get(i)];
				if (Math.pow(changeX, 2) + Math.pow(changeY, 2) > steps) {
					changeX -= dirX[path.get(i)];
					changeY -= dirY[path.get(i)];
					break;
				}
			}
			x += changeX;
			y += changeY;
			System.out.println(x + " " + y);
		}
	}

	static ArrayList<Integer> bfs(Pos loc, int destType, boolean[][] fullMap, int[][] robotMap, boolean[][] fuelMap, boolean[][] karbMap) {
		int mapLen = fullMap.length;
		boolean[][] visited = new boolean[mapLen][mapLen];
		int[][] path = new int[mapLen][mapLen];

		Pos dest = null;
		LinkedList<Pos> q = new LinkedList<Pos>();

		q.push(loc);
		visited[loc.x][loc.y] = true;

		while (q.size() > 0) {
			Pos curr = q.poll();
			visited[curr.x][curr.y] = true;
			if (destType == 0) {
				if (fuelMap[curr.x][curr.y]) {
					dest = new Pos(curr.x, curr.y);
					break;
				}
			}
			if (destType == 1) {
				if (karbMap[curr.x][curr.y]) {
					dest = new Pos(curr.x, curr.y);
					break;
				}
			}
			for (int i = 0; i < 8; i++) {
				int newX = dirX[i] + curr.x;
				int newY = dirY[i] + curr.y;
				if (isPassable(new Pos(newX, newY), fullMap, robotMap)) {
					if (!visited[newX][newY]) {
						path[newX][newY] = (i + 4) % 8;
						Pos toAdd = new Pos(newX, newY);
						visited[toAdd.x][toAdd.y] = true;
						q.add(toAdd);
					}
				}
			}
		}
		ArrayList<Integer> finalPath = new ArrayList<Integer>();
		int currX = dest.x;
		int currY = dest.y;

		while (true) {
			if (currX == loc.x && currY == loc.y) {
				return finalPath;
			}
			int dir = path[currX][currY];
			currX = currX + dirX[dir];
			currY = currY + dirY[dir];
			finalPath.add((dir + 4) % 8);
		}
	}

	static boolean isPassable(Pos loc, boolean[][] fullMap, int[][] robotMap) {
		int x = loc.x;
		int y = loc.y;
		int mapLen = fullMap.length;
		if (x >= mapLen || x < 0) {
			return false;
		} else if (y >= mapLen || y < 0) {
			return false;
		} else if (robotMap[x][y] > 0 || fullMap[x][y]) {
			return false;
		} else {
			return true;
		}
	}

	static class Pos {
		int x;
		int y;

		public Pos(int a, int b) {
			x = a;
			y = b;
		}
	}
}
import java.util.*;

public class School {

	static int[] dirX = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int[] dirY = { -1, -1, 0, 1, 1, 1, 0, -1 };

	public static void main(String[] args) {
		boolean[][] fullMap = new boolean[10][10];
		int[][] robotMap = new int[10][10];

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
		int steps = 4;
		int x = 0;
		int y = 0;
		while (x != 9 && y != 9) {
			ArrayList<Integer> path = bfs(new Pos(x, y), new Pos(9, 9), fullMap, robotMap);
			int changeX = 0;
			int changeY = 0;
			for (int i = 0; i < path.size(); i++) {
				changeX += dirX[path.get(i)];
				changeY += dirY[path.get(i)];
				if (Math.pow(changeX, 2) + Math.pow(changeY, 2) > steps) {
					changeX -= dirX[path.get(i)];
					changeY -= dirY[path.get(i)];
					break;
				}
			}
			System.out.println(changeX + " " + changeY);
			x += changeX;
			y += changeY;
		}
	}

	static ArrayList<Integer> bfs(Pos loc, Pos dest, boolean[][] fullMap, int[][] robotMap) {
		int mapLen = fullMap.length;
		boolean[][] visited = new boolean[mapLen][mapLen];
		int[][] path = new int[mapLen][mapLen];
		int[][] dist = new int[mapLen][mapLen];

		LinkedList<Pos> q = new LinkedList<Pos>();

		q.push(loc);
		visited[loc.x][loc.y] = true;
		dist[loc.x][loc.y] = 0; 

		while (q.size() > 0) {
			Pos curr = q.poll();
			visited[curr.x][curr.y] = true;
			for (int i = 0; i < 8; i++) {
				int newX = dirX[i] + curr.x;
				int newY = dirY[i] + curr.y;
				if (isPassable(new Pos(newX, newY), fullMap, robotMap)) {
					if (!visited[newX][newY]) {
						dist[newX][newY] = dist[curr.x][curr.y] + 1;
						path[newX][newY] = (i + 4) % 8;
						Pos toAdd = new Pos(newX, newY);
						visited[toAdd.x][toAdd.y] = true;
						q.push(toAdd);
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
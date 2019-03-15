package DailyProblem;

public class Prob5 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	static class BTree {
		Node root;
		
		public BTree(int N) {
			// Construct perfect unlocked binary tree of height N
			root = construct(0, N);
		}
		
		public Node construct(int currH, int H) {
			if (currH == H) {
				return null;
			}
			Node toAdd = new Node(construct(currH + 1, H), construct(currH + 1, H));
			return toAdd;
		}
	}
	
	static class Node {
		private boolean locked = false;
		Node left;
		Node right;
		
		public Node(Node l, Node r) {
			left = l;
			right = r;
		}
		
		public boolean isLocked() {
			return locked;
		}
		
		public boolean traverseCheck() {
			boolean lUnlocked = true;
			boolean rUnlocked = true;
			if (left != null) {
				if (left.isLocked()) {
					return false;
				}
				lUnlocked = left.traverseCheck();
			}
			if (right != null) {
				if (right.isLocked()) {
					return false;
				}
				rUnlocked = left.traverseCheck();
			}
			return lUnlocked && rUnlocked;
		}
		
		public boolean lock() {
			boolean ancestorsUnlocked = traverseCheck();
			if (ancestorsUnlocked) {
				locked = true;
			}
			return ancestorsUnlocked;
		}
		
		public boolean unlock() {
			boolean ancestorsUnlocked = traverseCheck();
			if (ancestorsUnlocked) {
				locked = false;
			}
			return ancestorsUnlocked;
		}
	}
	
}

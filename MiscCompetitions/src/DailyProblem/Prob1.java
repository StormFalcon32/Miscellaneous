package DailyProblem;

public class Prob1 {
	// All psuedo-code because Java has no pointers
	// A null object will have an address of 0 so null ^ 5 = 5
	/*
	public Node first;
	public Node last;
	int size = 0;
	
	public void add(int a) {
		if (size == 0) {
			addFirst(a);
			return;
		}
		Node old = last;
		last = new Node(a, null, old.pointer());
		old.both ^= last.pointer();
		size++;
	}
	
	public Node remove() {
		size--;
		Node old = first;
		first = getAtPointer(first.both ^ 0);
		first.both ^= old.pointer();
		return old;
	}
	
	public Node get(int index) {
		int i = 0;
		Node curr = first;
		Node prev = new Node(null, null, null);
		if (index == size - 1) {
			return last;
		}
		while ((curr.both ^ prev.pointer()) != null) {
			if (i == index) {
				return curr;
			}
			Node temp = curr;
			curr = curr.both ^ prev.pointer();
			prev = temp;
		}
		return null;
	}
	
	public void addFirst(int a) {
		first = new Node(a, null, null);
		last = first;
	}
	
	public Node getAtPointer(int pointer) {
		return Object @ pointer
	}
	
	static class Node {
		Object value;
		Address both;
		
		public Node(Object v, Node n, Node p) {
			value = v;
			both = n.pointer() ^ p.pointer();
		}
	}
	*/
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}

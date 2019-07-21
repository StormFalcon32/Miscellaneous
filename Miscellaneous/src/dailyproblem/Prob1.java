package dailyproblem;

public class Prob1 {
	// All psuedo-code because Java has no pointers
	// A null object will have an address of 0 so null ^ A = A
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
		last = new Node(a, null, old);
		old.both ^= last;
		size++;
	}
	
	public Node remove() {
		size--;
		Node old = first;
		first = first.both ^ null;
		first.both ^= old;
		return old;
	}
	
	public Node get(int index) {
		int i = 0;
		Node curr = first;
		Node prev = new Node(null, null, null);
		if (index == size - 1) {
			return last;
		}
		while ((curr.both ^ prev) != null) {
			if (i == index) {
				return curr;
			}
			Node temp = curr;
			curr = curr.both ^ prev;
			prev = temp;
		}
		return null;
	}
	
	public void addFirst(int a) {
		first = new Node(a, null, null);
		last = first;
	}
	
	static class Node {
		Object value;
		Address both;
		
		public Node(Object v, Node n, Node p) {
			value = v;
			both = n ^ p;
		}
	}
	*/
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}

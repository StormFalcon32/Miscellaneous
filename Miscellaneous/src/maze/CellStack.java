package maze;

public class CellStack {

	private MazeCell first;
	public int size = 0;

	public CellStack() {
		this.first = null;
	}

	public void push(MazeCell n) {
//		Make a previous first
		MazeCell prevFirst = first;
//		New first is new value
		this.first = n;
//		New first's next is previous first
		n.next = prevFirst;
//		Update size
		size++;
	}

	public MazeCell peek() {
		return first;
	}

	public MazeCell pop() {
//		Make previous first
		MazeCell prevFirst = first;
//		Prev first is new first
		first = first.next;
//		Update size
		size--;
//		Return old first
		return prevFirst;
	}
}

package Snake;

public class OrderedList {
	
	public OrderedListNode first;
	
	public OrderedList(){
		first = null;
	}
	
	public boolean isEmpty(){return first==null;}
	
	public void addFirst(ScoreRecord newValue){
		first = new OrderedListNode(newValue, first);
	}
	
	/*
	 * Preconditions:  The elements that are currently in the list
	 *  (if there are any) are arranged in order
	 *  Postconditions:  A new OrderedListNode is created containing newValue
	 *  	This new node is inserted into the list into the appropriate place
	 *  	so that the list is STILL in order
	 *  	(you can decide if you want ascending or descending order) 
	 */
	public void add(ScoreRecord newValue) {
		OrderedListNode newVal = new OrderedListNode(newValue);
//		Special case if empty
		if (this.isEmpty()) {
			this.addFirst(newValue);
			return;
		}
		OrderedListNode curr = first;
		OrderedListNode next = first.getNext();
//		Loop through and look for insertion point
		while (curr != null) {
			if (newValue.compareTo(curr.getValue()) == 0) {
//				If new value is equal to current you've found an insertion point
				curr.setNext(newVal);
				newVal.setNext(next);
				break;
			}
			if (newValue.compareTo(curr.getValue()) < 0) {
//				If new value is smaller than current you've found an insertion point
				newVal.setNext(curr);
//				Need to find node right behind first
				if (curr != first) {
					OrderedListNode temp = first;
					while (temp.getNext() != null && temp.getNext() != curr) {
						temp = temp.getNext();
					}
					temp.setNext(newVal);
					break;
				} else {
//					If curr is the first
					first = newVal;
				}
				break;
			}
			if (newValue.compareTo(curr.getValue()) > 0 && (next == null || newValue.compareTo(next.getValue()) <= 0)) {
//				If new value is bigger than current and smaller than next you've found and insertion point
				curr.setNext(newVal);
				newVal.setNext(next);
				break;
			}
//			Update curr and next
			curr = curr.getNext();
			if (next != null) {
				next = next.getNext();
			}
		}
	}
	
	public String toString() {
		String ans = "";
		OrderedListNode curr = first;
		while(curr != null){
			ans += curr.getValue() + "\n";
			curr = curr.getNext();
		}
		return ans;
	}
	
	//this function will be used to format the List for file output
	//Visit each node.  THe Value of the node is a ScoreRecord Object
	//You will need to call the formatForFile function on each ScoreRecord
	//combine them into one big String separated by "\r\n" (carriage return)
	public String toFileString(){
		String ans = "";
		OrderedListNode curr = first;
		while(curr != null) {
//			Need to cast to ScoreRecord to call formatForFile
			ans += ((ScoreRecord) (curr.getValue())).formatForFile() + "\r\n";
			curr = curr.getNext();
		}
		return ans;
	}
	
}

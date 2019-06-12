package Snake;

public class OrderedListNode {
	private ScoreRecord value; //will probably be a ScoreRecord
	private OrderedListNode next;
	
	//CONSTRUCTORS
	public OrderedListNode(ScoreRecord v, OrderedListNode n){
		value = v;
		next = n;
	}
	
	public OrderedListNode(ScoreRecord v){
		value = v;
		next = null;
	}
	
	//accessors
	public ScoreRecord getValue(){return value;}
	public OrderedListNode getNext(){return next;}
	
	//mutators
	public void setValue(ScoreRecord v){value = v;}
	public void setNext(OrderedListNode n){ next = n;}
	
	public String toString(){
		return value.toString();
	}
	
}

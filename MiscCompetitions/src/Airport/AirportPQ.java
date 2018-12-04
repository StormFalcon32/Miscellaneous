package Airport;

import java.util.*;

public class AirportPQ /* implements Queue */ {
//	Arraylist to store planetickets
	private ArrayList<PlaneTicket> data;

	// CONSTRUCTOR
	public AirportPQ() {
		data = new ArrayList<PlaneTicket>();
	}

	public boolean isEmpty() {
		return data.isEmpty();
	}

	public int size() {
		return data.size();
	}

	public boolean add(PlaneTicket p) {
//		Stamp the ticket
		p.stampTicket();
//		Add the ticket
		boolean success = data.add(p);
//		Sort the arraylist
		Collections.sort(data);
		return success;
	}

	public PlaneTicket remove() {
//		Remove first
		return data.remove(0);
	}

	public PlaneTicket peek() {
//		Show first
		return data.get(0);
	}

	public String toString() {
		// return a string containing all of the people that are waiting in line
		// does NOT need to be in priority order (but it could)
		StringBuilder s = new StringBuilder("");
		for (int i = 0; i < data.size(); i++) {
			s.append(data.get(i) + "\n");
		}
		return s.toString();
	}

}

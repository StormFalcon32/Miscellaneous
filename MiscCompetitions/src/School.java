import java.util.*;
public class School {

	public static void main(String[] args) {
		HashMap<String, String> phonebook = new HashMap<String, String>();
		phonebook.put("Jack Sparrow",  "555-5555");
		phonebook.put("Jack",  "555-5555");
		phonebook.put("Jason",  "555-5555");
		phonebook.put("Jackson",  "555-5555");
		phonebook.put("Jack",  "455-5555");
		Set<String> set = phonebook.keySet();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String s = it.next();
			System.out.println(s + " " + phonebook.get(s));
		}
	}

	static class Fraction implements Comparable<Fraction> {
		int num;
		int den;

		public Fraction(int n, int d) {
			num = n;
			den = d;
		}

		public Fraction(int x) {
			num = x;
			den = 1;
		}

		public double dec() {
			return (double) num / den;
		}

		@Override
		public int compareTo(Fraction other) {
			// TODO Auto-generated method stub
			return Double.compare(this.dec(), other.dec());
		}
	}
}
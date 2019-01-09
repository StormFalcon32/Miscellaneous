import java.util.Iterator;
import java.util.TreeSet;

public class School {

	public static void main(String[] args) {
		TreeSet<String> set = new TreeSet<String>();
		set.add("How");
		set.add("Now");
		set.add("Brown");
		set.add("Cow");
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			if (it.next().length() == 3) {
				it.remove();
			}
		}
		
		it = set.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
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
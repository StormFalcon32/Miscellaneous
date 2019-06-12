package Wookieepedia;

public class Person implements Comparable<Person> {
	private static final int NAME = 0;
	// private static final int HOMEWORLD = 1;
	// private static final int SPECICES = 2;
	// private static final int GENDER = 3;
	// private static final int OCCUPATION = 4;
	String[] attributes = new String[5];

	public Person(String[] ln) {
		for (int i = 0; i < 5; i++) {
			attributes[i] = ln[i];
		}
	}

	@Override
	public int compareTo(Person other) {
		return this.attributes[NAME].compareTo(other.attributes[NAME]);
	}

	public boolean equals(Object o) {
		Person other = (Person) o;
		return this.attributes[NAME].equals(other.attributes[NAME]);
	}

	@Override
	public int hashCode() {
		return attributes[NAME].hashCode();
	}

}

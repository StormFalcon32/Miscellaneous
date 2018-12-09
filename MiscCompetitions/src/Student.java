public class Student implements Comparable<Student> {
	private String fname, lname;
	private int grade;

	public Student(String fn, String ln, int g) {
		fname = fn;
		lname = ln;
		grade = g;
	}

	@Override
	public String toString() {
		return fname + " " + lname + " " + grade + "th";
	}

	@Override
	public int compareTo(Student that) {
		if (this.grade < that.grade) {
			return -5;
		} else if (this.grade > that.grade) {
			return 10;
		} else {
			return this.lname.compareTo(that.lname);
		}
	}
}
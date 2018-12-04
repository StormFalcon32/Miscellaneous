package Snake;

public class ScoreRecord implements Comparable<ScoreRecord> {
	private String userName;
	private double time;
	private int score;

	public ScoreRecord(String u, double t, int s) {
		userName = u;
		time = t;
		score = s;
	}

	// line is a colon-delmited string like this...
	// username:time:score
	public ScoreRecord(String line) {
		String[] stuff = line.split(":");
		if (stuff.length == 3) {
			userName = stuff[0];
			time = Double.parseDouble(stuff[1]);
			score = Integer.parseInt(stuff[2]);
		} else {
			userName = "";
			time = 0;
			score = 0;
		}
	}

	public String getUser() {
		return userName;
	}

	public double getTime() {
		return time;
	}

	public double getScore() {
		return score;
	}

	/********** Write a pretty toString for output *************/
	public String toString() {
		return userName + " " + "scored " + score + " and lasted " + time + " seconds.";
	}

	// Important: YOu want the file to be in colon-delmitted format!
	// BECAUSE when you are creating a ScoreRecord, the constructor
	// assumes that the input you give it is in this format.
	public String formatForFile() {
		return userName + ":" + time + ":" + score;
	}

	@Override
	public int compareTo(ScoreRecord that) {
		// you MAY change this depending on how you want to keep score
		if (this.score > that.score)
			return -100;
		if (this.score < that.score)
			return 100;
		return 0;
	}
}

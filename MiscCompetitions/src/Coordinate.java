public class Coordinate implements Comparable<Coordinate> {
		int x;
		int y;

		public Coordinate(int a, int b) {
			x = a;
			y = b;
		}

		public int quadrant() {
			if (x >= 0 && y >= 0) {
				return 1;
			} else if (x <= 0 && y >= 0) {
				return 2;
			} else if (x <= 0 && y <= 0) {
				return 3;
			} else {
				return 4;
			}
		}

		public double distance() {
			return Math.sqrt(x * x + y * y);
		}

		@Override
		public int compareTo(Coordinate that) {
			if (this.quadrant() < that.quadrant())
				return -5;
			else if (this.quadrant() > that.quadrant())
				return 100;
			else {
				if (this.distance() < that.distance())
					return -100;
				else if (this.distance() > that.distance())
					return 32;
				else
					return this.x - that.x;
			}
		}

		@Override
		public String toString() {
			return "(" + x + ", " + y + ")";
		}
	}
package dailyproblem;

public class Prob3 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] words = { "the", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog" };
		int N = words.length;
		int lineLength = 17;
		int totalLength = 0;
		for (int i = 0; i < N; i++) {
			totalLength += words[i].length();
		}
		int numLines = (totalLength / lineLength) + 1;
		String[] lines = new String[numLines];
		int currWord = 0;
		for (int i = 0; i < numLines; i++) {
			int l = 0;
			StringBuilder line = new StringBuilder("");
			int numSpaces = -1;
			while (l < lineLength && currWord < N) {
				line.append(words[currWord] + " ");
				l += words[currWord].length() + 1;
				currWord++;
				numSpaces++;
			}
			line.delete(line.length() - 1, line.length());
			if (line.length() > lineLength) {
				currWord--;
				numSpaces--;
				line.delete(line.length() - words[currWord].length() - 1, line.length());
			}
			int extraSpaces = lineLength - line.length();
			int spacesPerSpot = (numSpaces == 0) ? 1 : (extraSpaces % numSpaces == 0) ? extraSpaces / numSpaces : (extraSpaces / numSpaces) + 1;
			int spacesAdded = 0;
			for (int k = 0; k < line.length() && spacesAdded < extraSpaces; k++) {
				if (line.substring(k, k + 1).equals(" ")) {
					for (int j = 0; j < spacesPerSpot && spacesAdded < extraSpaces; j++) {
						line.insert(k, " ");
						spacesAdded++;
						k++;
					}
				}
			}
			lines[i] = line.toString();
		}
		for (int i = 0; i < numLines; i++) {
			System.out.println(lines[i]);
		}
	}
	
}

package Novel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class NovelAnalyzer {
	static String[] toRemove = { ".", ",", "!", "?", "\"", "'s", ";", ":", "(", ")", "--", "'" };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("dracula.txt"));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		String str = in.readLine();
		HashMap<String, Integer> analyze = new HashMap<String, Integer>();
		TreeSet<Output> outputs = new TreeSet<Output>();
		while (str != null) {
			for (int i = 0; i < toRemove.length; i++) {
				str.replace(toRemove[i], "");
			}
			while (ln.hasMoreTokens()) {
				String curr = ln.nextToken();
				if (analyze.containsKey(curr)) {
					analyze.put(curr, analyze.get(curr) + 1);
				} else {
					analyze.put(curr, 1);
				}
			}
			str = in.readLine();
		}
		Set<String> s = analyze.keySet();
		Iterator<String> it = s.iterator();
		while (it.hasNext()) {
			String key = it.next();
			outputs.add(new Output(analyze.get(key), key));
		}
		while (!outputs.isEmpty()) {
			Output o = outputs.pollFirst();
			System.out.println(o.freq + " " + o.word);
		}
		in.close();
	}
	
	static class Output implements Comparable<Output> {
		int freq;
		String word;
		
		public Output(int a, String b) {
			freq = a;
			word = b;
		}

		@Override
		public int compareTo(Output other) {
			if (this.freq == other.freq) {
				return this.word.compareTo(other.word);
			}
			return Integer.compare(this.freq, other.freq);
		}
	}
}

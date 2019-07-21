package dailyproblem;

import java.util.ArrayList;

public class Prob7 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] dict = { "theq", "the", "quick", "brown", "fox" };
		String word = "thequickbrownfox";
		solve(word, dict, 0, new ArrayList<String>());
	}
	
	static void solve(String word, String[] dict, int ind, ArrayList<String> ans) {
		if (ind == word.length()) {
			for (int i = 0; i < ans.size(); i++) {
				System.out.println(ans.get(i));
			}
		}
		for (int i = 0; i < dict.length; i++) {
			String curr = dict[i];
			int length = curr.length();
			if (ind + length <= word.length()) {
				String sub = word.substring(ind, ind + length);
				if (sub.equals(curr)) {
					ans.add(curr);
					solve(word, dict, ind + length, ans);
					ans.remove(curr);
				}
			}
		}
	}
}

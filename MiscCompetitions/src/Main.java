import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(ln.nextToken());
		int q = Integer.parseInt(ln.nextToken());
		char[] s = new char[n];
		for (int i = 0; i < n; i++) {
			s[i] = (char) ('A' + i);
		}
		int a = 0;
		for (int i = 0; i < n - 1 && a <= q; i++) {
			for (int j = i + 1; j < n && a <= q; j++) {
				System.out.println("? " + s[i] + " " + s[j]);
				String ans = in.readLine();
				if (ans.charAt(0) == '>') {
					char temp = s[i];
					s[i] = s[i + 1];
					s[i + 1] = temp;
				}
				a++;
			}
		}
		System.out.print("! ");
		for (int i = 0; i < s.length; i++) {
			System.out.print(s[i]);
		}
		System.out.println();
	}

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestClass {
	
	static long[] dp;
	
	public static void main(String args[]) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(in.readLine());
        int[] inputs = new int[t];
        int max = 0;
        for (int i = 0; i < t; i++) {
           inputs[i] = Integer.parseInt(in.readLine());   
           max = Math.max(max, inputs[i]);
        }
        dp = new long[max + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= max; i++) {
            dp[i] = (dp[i - 1] * i) % (1000000000 + 7);
        }
        for (int i = 0; i < t; i++) {
           System.out.println(dp[inputs[i]] % (1000000000 + 7));
        }
	}
}

package Snake;
public class SumOfPrimes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(sieve(2000000));
	}
    public static long sieve(int n)
    {
        boolean prime[] = new boolean[n + 1];
        for(int i = 0; i < n; i++) {
            prime[i] = true;
        }
        for(int p = 2; p * p <= n; p++)
        {
            if(prime[p] == true)
            {
                for(int i = p * 2; i <= n; i += p) {
                    prime[i] = false;
                }
            }
        }
        long sum = 0;
        for(int i = 2; i <= n; i++)
        {
            if(prime[i] == true) {
            sum += i;
            }
        }
        return sum;
    }
}

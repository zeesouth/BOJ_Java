package dp.n1793;

import java.io.*;
import java.math.*;

public class Main {
    static String line = null;
    static int n;
    static BigInteger[] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        dp = new BigInteger[251];
        dp[0] = new BigInteger("1");
        dp[1] = new BigInteger("1");
        dp[2] = new BigInteger("3");
        for(int i=3;i<=250;i++) dp[i] = dp[i-1].add(new BigInteger("2").multiply(dp[i-2]));

        while((line = br.readLine()) != null) {
            n = Integer.parseInt(line);
            System.out.println(dp[n]);
        }
        br.close();
    }
}

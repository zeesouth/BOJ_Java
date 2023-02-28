package dp.n11726;
// https://www.acmicpc.net/problem/11726

import java.util.*;

public class Main {
    static int n;
    static int[] dp;
    public static void main (String[] args) throws Exception {
        n = new Scanner(System.in).nextInt();
        dp = new int[n+1];
        dp[1] = 1;
        if(n > 1) dp[2] = 2;
        for(int i=3;i<=n;i++) dp[i] = (dp[i-1]+dp[i-2])%10007;
        System.out.println(dp[n]);
    }
}

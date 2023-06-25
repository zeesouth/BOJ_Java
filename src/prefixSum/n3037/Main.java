package prefixSum.n3037;

import java.util.*;

public class Main {
    static final int MOD = 1_000_000_007;
    static final int MAX_N = 1000, MAX_C = 10000;
    static int dp[][] = new int[MAX_N + 1][MAX_C + 1];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int C = sc.nextInt();
        /* TOP-DOWN : 시간초과
        https://kth990303.tistory.com/60
        dp = new int[N + 1][C + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= C; j++) Arrays.fill(dp[i], -1);
        }
        System.out.println(dp1(N, C)); -> 시간 초과
        */

        // BOTTOM-UP
        // https://blog.naver.com/pasdfq/221368925855
        dp[0][0] = 1;
        for (int i = 1; i <= N; i++) {
            int sum[] = new int[MAX_C + 2];
            sum[0] = dp[i - 1][0];
            for (int j = 1; j <= C; j++) sum[j] = (sum[j - 1] + dp[i - 1][j]) % MOD;

            for (int j = 0; j <= C; j++) {
                dp[i][j] = sum[j];
                if (j - N + i - 1 >= 0) dp[i][j] = (dp[i][j] - sum[j - N + i - 1] + MOD) % MOD;
            }
        }
        System.out.println(dp[N][C]);
    }

    /*
    static int dp1(int curr, int c) {
        if (curr * (curr - 1) / 2 < c) return 0;
        if (c == 0) return 1;
        if (curr == 1) return c == 0 ? 1 : 0;
        if (dp[curr][c] != -1) return dp[curr][c];
        dp[curr][c] = 0;
        dp[curr][c] += dp1(curr, c - 1) + dp1(curr - 1, c);
        dp[curr][c] %= MOD;
        if (c >= curr) dp[curr][c] -= dp1(curr - 1, c - curr);
        dp[curr][c] = (dp[curr][c] + MOD) % MOD;
        return dp[curr][c] % MOD;
    }
    */
}
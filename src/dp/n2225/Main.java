package dp.n2225;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws Exception {
        final int MOD = 1_000_000_000;
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        // dp[i][j] -> i개 선택한 수의 합이 j인 경우
        int dp[][] = new int[K + 1][N + 1];

        for (int i = 0; i <= N; i++) dp[1][i] = 1;

        for (int i = 1; i <= K; i++) {
            for (int j = 0; j <= N; j++) {
                for (int l = 0; l <= j; l++) {
                    dp[i][j] += dp[i - 1][j - l];
                    dp[i][j] %= MOD;
                }
            }
        }

        System.out.println(dp[K][N]);
    }
}

package dp.n2482;

import java.io.*;

public class Main {
    static final int MOD = 1_000_000_003;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        br.close();

        int dp[][] = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            dp[i][0] = 1;
            dp[i][1] = i;
        }

        // i가 3미만인 경우는 계산할 필요가 없음
        // 우선 일렬로 나열된 색상환이라 생각하고 계산
        for (int i = 3; i <= N; i++) {
            for (int j = 2; j <= (i + 1) / 2; j++) {
                dp[i][j] = (dp[i - 1][j] + dp[i - 2][j - 1]) % MOD;
            }
        }

        // 원형 판이므로
        // 마지막 N을 고르는 경우는 1, N-1을 고르면 안되므로 N-3개에서 K-1개를 고르면 됨
        // 마지막 N을 고르지 않는 경우 나머지에서 K개를 고르면 됨
        System.out.println((dp[N - 3][K - 1] + dp[N - 1][K]) % MOD);
    }
}

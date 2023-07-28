package dp.n2228;
// https://www.acmicpc.net/problem/2228

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 구간 별 누적합 구하기 (sum)
        int sum[] = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            sum[i] = sum[i - 1] + Integer.parseInt(br.readLine());
        }

        // i개의 배열로 j개의 구간을 선택했을 때 최댓값 (dp)
        int dp[][] = new int[N + 1][M + 1];
        for (int j = 1; j <= M; j++) dp[0][j] = -3276800;

        // 점화식은
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                dp[i][j] = dp[i - 1][j];
                for (int k = 1; k <= i; k++) {
                    if (k >= 2)
                        dp[i][j] = Math.max(dp[i][j], dp[k - 2][j - 1] + sum[i] - sum[k - 1]);
                    else if (k == 1 && j == 1)
                        dp[i][j] = Math.max(dp[i][j], sum[i]);
                }
            }
        }

        System.out.println(dp[N][M]);
        br.close();
    }
}
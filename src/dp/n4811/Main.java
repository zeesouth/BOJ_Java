package dp.n4811;
// https://www.acmicpc.net/problem/4811

import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            sb.append(getAns(N)).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    private static long getAns(int N) {
        // dp[t][h][w] -> t일이 지났을 때 반알짜리 알약의 개수가 h, 한알짜리 알약의 개수가 w일 때 가능한 모든 문자열의 개수

        long dp[][][] = new long[2 * N + 1][2 * N + 1][N + 1];

        dp[1][1][N - 1] = 1;
        for (int t = 2; t <= 2 * N; t++) {
            for (int h = 0; h <= 2 * N; h++) {
                for (int w = 0; w <= N; w++) {
                    if (h + 1 <= 2 * N) dp[t][h][w] += dp[t - 1][h + 1][w];
                    if (h - 1 >= 0 && w + 1 <= N) dp[t][h][w] += dp[t - 1][h - 1][w + 1];
                }
            }
        }

        long sum = 0;
        for (int h = 0; h <= 2 * N; h++) {
            for (int w = 0; w <= N; w++) {
                sum += dp[2 * N][h][w];
            }
        }
        return sum;
    }
}

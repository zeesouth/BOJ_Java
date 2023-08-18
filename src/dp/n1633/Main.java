package dp.n1633;

import java.io.*;

public class Main {
    static final int MAX_N = 1000;
    static int N, white[] = new int[MAX_N + 1], black[] = new int[MAX_N + 1];
    static int dp[][][] = new int[MAX_N + 1][15 + 1][15 + 1];

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(dfs(0, 0, 0));
    }

    private static int dfs(int idx, int w, int b) {
        if (idx == N || (w == 15 && b == 15)) return 0;

        if (dp[idx][w][b] != 0) return dp[idx][w][b];

        // 미선택
        dp[idx][w][b] = dfs(idx + 1, w, b);
        // white 선택
        if (w < 15) dp[idx][w][b] = Math.max(dfs(idx + 1, w + 1, b) + white[idx], dp[idx][w][b]);
        // black 선택
        if (b < 15) dp[idx][w][b] = Math.max(dfs(idx + 1, w, b + 1) + black[idx], dp[idx][w][b]);

        return dp[idx][w][b];
    }

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        while ((line = br.readLine()) != null) {
            String[] wb = line.split(" ");
            white[N] = Integer.parseInt(wb[0]);
            black[N] = Integer.parseInt(wb[1]);
            N++;
        }
    }
}

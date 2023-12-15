package dp.n7579;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int memory[] = new int[N];
        int cost[] = new int[N];
        int sum = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) memory[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) sum += cost[i] = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N][sum + 1];

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= sum; j++) {
                if(i == 0) {
                    if(j >= cost[i]) dp[i][j] = memory[i];
                } else {
                    if (j >= cost[i]) dp[i][j] = Math.max(dp[i - 1][j - cost[i]] + memory[i], dp[i - 1][j]);
                    else dp[i][j] = dp[i - 1][j];
                }

                if (dp[i][j] >= M) ans = Math.min(ans, j);
            }
        }
        System.out.println(ans);
    }
}

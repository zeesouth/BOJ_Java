package prefixSum.n2208;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int sum[] = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            int value = Integer.parseInt(br.readLine());
            sum[i] = sum[i - 1] + value;
        }

        int dp[] = new int[N + 1];
        dp[M] = sum[M];
        for (int i = M + 1; i <= N; i++) {
            dp[i] = Math.max(dp[i - 1] + sum[i] - sum[i - 1], sum[i] - sum[i - M]);
        }

        int ans = 0;
        for(int i=1;i<=N;i++) ans = Math.max(ans,dp[i]);

        System.out.println(ans);
        br.close();
    }
}

/*
0~

 */

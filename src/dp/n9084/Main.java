package dp.n9084;

import java.io.*;
import java.util.*;
public class Main {
    static int T, N, M, coin[];
    static long dp[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++) {
            N = Integer.parseInt(br.readLine());
            coin = new int[N+1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=1;i<=N;i++) coin[i] = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(br.readLine());

            dp = new long[M+1];
            dp[0] = 1;
            for(int i=1;i<=N;i++) {
                for(int j=coin[i];j<=M;j++) dp[j] += dp[j-coin[i]];
            }
            sb.append(dp[M]).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}

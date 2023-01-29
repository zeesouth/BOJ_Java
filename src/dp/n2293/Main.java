package dp.n2293;
// https://www.acmicpc.net/problem/2293

import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] coin;
    static int[] dp;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        coin = new int[N];
        for(int i=0;i<N;i++) coin[i] = Integer.parseInt(br.readLine());

        dp = new int[K+1];
        // 행 -> 동전의 가지수
        // 렬 -> 1원 ~ K원

        dp[0] = 1;
        for(int i=0;i<N;i++) {
            for(int j=1;j<=K;j++) if(j >= coin[i]) dp[j] += dp[j-coin[i]];
        }

        bw.write(dp[K]+"");
        bw.flush();
        br.close();
    }
}

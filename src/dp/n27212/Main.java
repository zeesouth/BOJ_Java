package dp.n27212;
// https://www.acmicpc.net/problem/27212

import java.io.*;
import java.util.*;
public class Main {
    static int N, M, C;
    static int[] a, b;
    static int[][] W;
    static long ans;
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        W = new int[C+1][C+1];
        for(int i=1;i<=C;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=C;j++)  W[j][i] = Integer.parseInt(st.nextToken());
        }
        a = new int[N+1];
        b = new int[M+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) a[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=M;i++) b[i] = Integer.parseInt(st.nextToken());
        br.close();

        dp = new long[N+1][M+1];

        for(int i=1;i<=N;i++) {
            for (int j = 1; j <= M; j++) {
                int value = W[a[i]][b[j]];
                dp[i][j] = Math.max(Math.max(dp[i - 1][j], dp[i - 1][j - 1] + value), dp[i][j - 1]);
            }
        }

        bw.write(dp[N][M]+"\n");
        bw.flush();
    }
}

/*
* 팔이 교차하지 않게 악수를 해야함
* 악수를 하지 못하는 사람이 있을 수도 있음
* */
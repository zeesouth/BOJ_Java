package dp.n12865;
// https://www.acmicpc.net/problem/12865

import java.io.*;
import java.util.*;

public class Main {

    static int N, K;        // 물품의 수, 버틸 수 있는 무게
    static int[] W, V;      // 각 물건의 무게, 각 물건의 가치 배열
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        W = new int[N+1];
        V = new int[N+1];
        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }
        dp = new int[N+1][K+1]; // dp[i][j] = i물건 인덱스, K무게만큼 허용
        for(int k=1;k<=K;k++) {         // 무게
            for(int i=1;i<=N;i++) {     // item
                dp[i][k] = dp[i - 1][k];
                if(W[i] <= k) dp[i][k] = Math.max(dp[i-1][k],dp[i-1][k-W[i]]+V[i]);
            }
        }
        System.out.println(dp[N][K]);
    }
}

/*
* 짐을 쪼갤 수 있는 배낭 문제 : Fraction Kanpsack    -> Greedy
* 짐을 쪼갤 수 없는 배낭 문제 : 0/1 Knapsack Problem -> DP
* */
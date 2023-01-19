package dp.n11066;
// https://www.acmicpc.net/problem/11066

import java.io.*;
import java.util.*;

public class Main {
    static int T, K;
    static int[] novel, sum;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;
        T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++) {
            K = Integer.parseInt(br.readLine());
            novel = new int[K+1];   // novel[i] = i번째 파일의 용량
            dp = new int[K+1][K+1]; // dp[i][j] = i~j까지의 최소가 되는 파일 용량 합
            sum = new int[K+1];     // sum[i] = 1~i까지의 파일 용량 합
            st = new StringTokenizer(br.readLine());
            for(int i=1;i<=K;i++) {
                novel[i] = Integer.parseInt(st.nextToken());
                sum[i] = sum[i-1] + novel[i];
            }

            for(int n=1;n<=K;n++) {                         // 몇장을 묶기 시작할 것인가?
                for(int from = 1; from+n <= K; from++) {    // 어디부터 묶기 시작할 것인가?
                    int to = from+n;
                    dp[from][to] = Integer.MAX_VALUE;
                    for(int divide = from; divide < to; divide++) {
                        dp[from][to] = Math.min(dp[from][to],
                                dp[from][divide] + dp[divide+1][to] + sum[to]-sum[from-1]);
                    }
                }
            }
            bw.write(dp[1][K]+"\n");
        }
        bw.flush();
        br.close();
    }
}

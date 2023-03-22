package dp.n5557;

import java.io.*;
import java.util.*;

public class Main {
    static int N, ans;
    static int[] arr;
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N-1];
        for(int i=0;i<N;i++) {
            if(i == N-1) ans = Integer.parseInt(st.nextToken());
            else arr[i] = Integer.parseInt(st.nextToken());
        }

        dp = new long[N-1][20+1];
        dp[0][arr[0]] = 1;

        for(int i=1;i<N-1;i++) {
            int digit = arr[i];
            for(int j=0;j<=20;j++) {
                if(j+digit >= 0 && j+digit <= 20) dp[i][j+digit] += dp[i-1][j];
                if(j-digit >= 0 && j-digit <= 20) dp[i][j-digit] += dp[i-1][j];
            }
        }

        System.out.println(dp[N-2][ans]);
    }
}

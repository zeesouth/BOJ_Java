package dp.n16194;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N+1];
        for(int i=1;i<=N;i++) arr[i] = Integer.parseInt(st.nextToken());

        dp = new int[N+1][N+1];

        for(int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                if(i==1) dp[i][j] = arr[1]*j;
                else {
                    if(j<=i) {
                        if (i == j) dp[i][j] = Math.min(arr[i], dp[i - 1][j]);
                        else dp[i][j] = dp[i-1][j];
                    }
                    else dp[i][j] = Math.min(arr[i]+dp[i][j-i], dp[i-1][j]);
                }
            }
        }
        System.out.println(dp[N][N]);
    }
}

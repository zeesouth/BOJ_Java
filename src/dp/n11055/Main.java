package dp.n11055;
// https://www.acmicpc.net/problem/11055

import java.io.*;
import java.util.*;

public class Main {
    static int N, ans = Integer.MIN_VALUE;
    static int[] arr, dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) arr[i] = Integer.parseInt(st.nextToken());

        dp = new int[N];
        for(int i=0;i<N;i++) {
            dp[i] = arr[i];
            for(int j=0;j<i;j++) {
                if (arr[j] < arr[i] && dp[i] < dp[j] + arr[i]) {
                    dp[i] = dp[j] + arr[i];
                }
            }
        }

        for(int i=0;i<N;i++) ans = Math.max(ans, dp[i]);

        bw.write(ans+"");
        bw.flush();
        br.close();
    }
}

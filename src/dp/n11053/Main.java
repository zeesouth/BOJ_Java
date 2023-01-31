package dp.n11053;
// https://www.acmicpc.net/problem/11053
// 최장 증가 부분 수열 문제 (LIS)

import java.io.*;
import java.util.*;

public class Main {
    static int N;
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
            dp[i] = 1;
            for(int j=0;j<i;j++)
                if(arr[j] < arr[i] && dp[i] < dp[j] + 1) dp[i] = dp[j]+1;
        }

        int max = -1;
        for(int i=0;i<N;i++) max = Math.max(dp[i], max);
        bw.write(max+"");
        bw.flush();
        br.close();
    }
}

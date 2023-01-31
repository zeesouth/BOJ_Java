package dp.n11722;
// https://www.acmicpc.net/problem/11722

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
                if(arr[i] < arr[j] && dp[i] < dp[j]+1) dp[i] = dp[j]+1;
        }
        int ans = -1;
        for(int i=0;i<N;i++) ans = Math.max(ans, dp[i]);
        bw.write(ans+"");
        bw.flush();
        br.close();
    }
}

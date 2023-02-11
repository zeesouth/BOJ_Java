package dp.n15486;
// https://www.acmicpc.net/problem/15486

import java.io.*;
import java.util.*;

public class Main {
    static int N, max = Integer.MIN_VALUE;
    static int[] time;
    static int[] cost;
    static int[] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        time = new int[N+2];
        cost = new int[N+2];
        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            cost[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N+2];

        for(int i=1;i<N+2;i++) {
            if(max<dp[i]) max = dp[i];
            int day = i+time[i];
            if(day < N+2) dp[day] = Math.max(dp[day], max+cost[i]);
        }

        bw.write(max+"");
        bw.flush();
        br.close();
    }
}

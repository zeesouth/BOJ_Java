package dp.n2629;
// https://www.acmicpc.net/problem/2629

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, question, max=15000, arr[];
    static boolean dp[][];

    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N= Integer.parseInt(br.readLine());
        arr= new int[N+1];
        dp= new boolean[31][max+1];

        StringTokenizer st= new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) arr[i]= Integer.parseInt(st.nextToken());

        // 추로 만들 수 있는 무게 계산
        dp(0,0);

        StringBuilder sb= new StringBuilder();
        M= Integer.parseInt(br.readLine());
        st= new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            question= Integer.parseInt(st.nextToken());
            if(question>15000)  sb.append("N ");
            else sb.append(dp[N][question]?"Y ":"N ");
        }
        System.out.println(sb);
        br.close();
    }

    static void dp(int idx, int weight) {
        if(dp[idx][weight]) return;
        dp[idx][weight] = true;
        if(idx == N) return;

        dp(idx+1, weight+arr[idx+1]);     // 현재 추를 더하는 경우
        dp(idx+1, weight);                       // 현재 추를 더하지 않는 경우
        dp(idx+1, Math.abs(weight-arr[idx+1]));  // 현재 추를 빼는 경우
    }
}

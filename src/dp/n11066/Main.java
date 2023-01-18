package dp.n11066;
// https://www.acmicpc.net/problem/11066

import java.io.*;
import java.util.*;

public class Main {
    static int T, K, ans;
    static int[] arr;
    static int[] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++) {
            K = Integer.parseInt(br.readLine());
            arr = new int[K];
            dp = new int[1<<K];
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<K;i++) arr[i] = Integer.parseInt(st.nextToken());
            ans = dfs(0, 0, 0);
            sb.append(ans).append("\n");
        }
        System.out.print(sb);
        br.close();
    }

    static int dfs(int count, int status, int sum) {
        if(count == K) return sum;
        int s = Integer.MAX_VALUE;
        for(int i=0;i<K;i++) {
            if(((1<<i) & status) != (1<<i))
                s = Math.min(s, arr[i]+dfs(count+1, status | (1<<i), sum+arr[i]));
                s = Math.min(s, sum + dfs(count+1, status | (1<<i), arr[i]));
        }
        return s;
    }
}

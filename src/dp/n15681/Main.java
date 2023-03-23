package dp.n15681;
// https://www.acmicpc.net/problem/15681

import java.io.*;
import java.util.*;

public class Main {
    static int N, R, Q;
    static int[] dp;
    static boolean[] visited;
    static ArrayList<Integer>[] nodes;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        nodes = new ArrayList[N+1];
        dp = new int[N+1];
        visited = new boolean[N+1];

        for(int i=1;i<=N;i++) {
            nodes[i] = new ArrayList<>();
            dp[i] = 1;
        }

        for(int i=0;i<N-1;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            nodes[u].add(v);
            nodes[v].add(u);
        }

        dfs(R);

        for(int i=0;i<Q;i++)
            sb.append(dp[Integer.parseInt(br.readLine())]).append("\n");

        System.out.println(sb);
    }


    static int dfs(int cur) {
        if(visited[cur]) return dp[cur];
        visited[cur] = true;

        for(int next : nodes[cur]){
            if(visited[next]) continue;
            dp[cur] += dfs(next);
        }
        return dp[cur];
    }
}

package tree.n1967;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, ans;
    static ArrayList<int[]> graph[];
    static boolean visited[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 1; i <= N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[a].add(new int[]{b, cost});
            graph[b].add(new int[]{a, cost});
        }

        if(N > 1) {
            dfs(1, 0);

            visited = new boolean[N + 1];
            dfs(M, 0);
        }

        System.out.println(ans);
    }

    private static void dfs(int node, int cost) {
        visited[node] = true;

        if (ans < cost) {
            M = node;
            ans = Math.max(cost, ans);
        }

        for (int[] next : graph[node]) {
            if (visited[next[0]]) continue;
            dfs(next[0], cost + next[1]);
        }
    }
}

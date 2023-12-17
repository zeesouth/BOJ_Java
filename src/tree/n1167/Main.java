package tree.n1167;
// https://www.acmicpc.net/problem/1167

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int V, node;
    static ArrayList<int[]> graph[];
    static boolean visited[];
    static int ans;

    public static void main(String[] args) throws Exception {
        init();


        visited = new boolean[V + 1];
        dfs(1, 0);

        visited = new boolean[V + 1];
        dfs(node, 0);

        System.out.println(ans);
    }

    private static void dfs(int curr, int cost) {
        if (cost > ans) {
            ans = cost;
            node = curr;
        }

        visited[curr] = true;

        for (int[] next : graph[curr]) {
            if (visited[next[0]]) continue;
            dfs(next[0], cost + next[1]);
        }
    }

    private static void init() throws Exception {
        V = Integer.parseInt(br.readLine());
        graph = new ArrayList[V + 1];

        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b;
            while ((b = Integer.parseInt(st.nextToken())) != -1) {
                int cost = Integer.parseInt(st.nextToken());
                graph[a].add(new int[]{b, cost});
                graph[b].add(new int[]{a, cost});
            }
        }
    }
}

/*
코드 설명 : https://moonsbeen.tistory.com/101
 */

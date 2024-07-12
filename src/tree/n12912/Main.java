package tree.n12912;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, last, end;
    static long max, ans, dp[];
    static ArrayList<int[]> graph[];
    static ArrayList<int[]> edges = new ArrayList<>();
    static boolean visited[];

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());

        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) graph[i] = new ArrayList<>();

        int a, b, cost;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());

            graph[a].add(new int[]{b, cost});
            graph[b].add(new int[]{a, cost});
            edges.add(new int[]{a, b, cost});
        }

        visited = new boolean[N];
        dp = new long[N];
        for (int[] e : edges) {
            long firstD = treeDiameter(e[0], e[1]);
            long secondD = treeDiameter(e[1], e[0]);

            ans = Math.max(firstD + secondD + e[2], ans);
        }

        System.out.println(ans);
    }

    private static long treeDiameter(int from, int to) {
        last = -1;
        end = -1;

        // 시작점이 leaf
        if (graph[from].size() == 2) end = from;
        max = 0;
        Arrays.fill(dp, 0);
        Arrays.fill(visited, false);
        visited[to] = true;
        dfs(from);

        // 본인이 leaf노드인 경우: LAST를 갱신할 수 없음으로 본인으로 설정하기
        if (last == -1) last = from;
        max = 0;
        Arrays.fill(dp, 0);
        Arrays.fill(visited, false);
        visited[to] = true;
        dfs(last);

        return max;
    }

    private static void dfs(int node) {
        visited[node] = true;

        // 원래 leaf노드이거나, 간선의 제거로 leaf 노드인 경우 갱신
        if ((graph[node].size() == 1 || node == end) && dp[node] > max) {
            max = Math.max(max, dp[node]);
            last = node;
            return;
        }

        for (int[] next : graph[node]) {
            if (visited[next[0]]) continue;
            dp[next[0]] += dp[node] + next[1];
            dfs(next[0]);
        }
    }

}

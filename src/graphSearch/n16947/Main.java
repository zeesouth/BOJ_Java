package graphSearch.n16947;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static ArrayList<Integer> graph[];
    static boolean isCycle[];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        init();

        // 사이클 발생 구간 체크
        for (int i = 1; i <= N; i++) {
            if (checkCycle(i, i, i)) break;
            isCycle = new boolean[N + 1];
        }

        // 모든 노드에서 사이클이 발생하는 노드까지의 거리 계산
        for (int i = 1; i <= N; i++) {
            if (isCycle[i]) sb.append(0);
            else sb.append(bfs(i));
            sb.append(" ");
        }

        System.out.println(sb);

    }

    private static int bfs(int node) {
        Queue<int[]> q = new LinkedList<>();
        boolean visited[] = new boolean[N + 1];
        q.add(new int[]{node, 0});
        visited[node] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            if (isCycle[curr[0]]) return curr[1];

            for (int next : graph[curr[0]]) {
                if (visited[next]) continue;
                visited[next] = true;
                q.add(new int[]{next, curr[1] + 1});
            }
        }

        return 0;
    }

    private static boolean checkCycle(int prev, int node, int start) {
        isCycle[node] = true;

        for (int next : graph[node]) {
            if (!isCycle[next]) {
                if (checkCycle(node, next, start)) return true;
            } else if (next != prev && next == start) return true;
        }

        isCycle[node] = false;
        return false;
    }

    private static void init() throws Exception {
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        isCycle = new boolean[N + 1];
    }
}

/*
- 무방향 그래프에서 사이클 판별 : union-find (서로소 집합, disjoint set)
- 방향 그래프에서 사이클 판별 : dfs


 */
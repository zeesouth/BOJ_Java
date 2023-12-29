package test.n30985;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final long INF = Long.MAX_VALUE;
    static int N, M, K;
    static long dp[][];
    static ArrayList<Pair> graph[];

    public static void main(String[] args) throws Exception {
        init();

        dijkstra(0);
        dijkstra(1);

        long min = INF;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            long x = Long.parseLong(st.nextToken());
            if (x == -1 || dp[i][0] == INF || dp[i][1] == INF) continue;
            min = Math.min(min, x * (K - 1) + dp[i][0] + dp[i][1]);
        }

        System.out.println(min == INF ? -1 : min);
    }

    private static void dijkstra(int x) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        int node;
        if (x == 0) node = 1; // x == 0 : 1에서부터 i까지의 최단경로
        else node = N; // x == 1 i에서부터 N까지의 최단경로

        dp[node][x] = 0;
        pq.offer(new Pair(node, 0));

        while (!pq.isEmpty()) {
            Pair curr = pq.poll();

            for (Pair next : graph[curr.node]) {
                if (dp[next.node][x] < curr.dist + next.dist) continue;
                dp[next.node][x] = curr.dist + next.dist;
                pq.offer(new Pair(next.node, dp[next.node][x]));
            }
        }
    }

    private static void init() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Pair(b, c));
            graph[b].add(new Pair(a, c));
        }

        dp = new long[N + 1][2];
        for (int i = 1; i <= N; i++) {
            dp[i][0] = INF;
            dp[i][1] = INF;
        }
    }

    static class Pair implements Comparable<Pair> {
        int node;
        long dist;

        Pair(int node, long dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pair o) {
            return Long.compare(this.dist, o.dist);
        }
    }
}

/*
접근 방법
1-> A-> 엘리베이터를 타고 꼭대기로 -> N
1. 1에서 모든 곳까지의 최단경로 구하기
2. 모든곳에서 N까지의 최단경로 구하기
 */

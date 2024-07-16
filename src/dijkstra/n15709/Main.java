package dijkstra.n15709;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final long INF = Long.MAX_VALUE;
    static int N, M, B, K, Q;
    static long dist[][];
    static StringBuilder sb = new StringBuilder();
    static ArrayList<int[]> road[];

    public static void main(String[] args) throws Exception {
        init();

        for (int i = 1; i <= B; i++) {
            dijkstra(i);
        }

        int s, e;
        long ans;
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            ans = INF;
            for (int i = 1; i <= B; i++) {
                if (dist[i][s] == INF || dist[i][e] == INF) continue;
                ans = Math.min(dist[i][s] + dist[i][e], ans);
            }

            if (ans == INF) sb.append(-1);
            else sb.append(ans);
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void dijkstra(int r) {
        Arrays.fill(dist[r], INF);
        dist[r][r + N + M] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(r + N + M, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (dist[r][curr.num] < curr.cost) continue;

            for (int[] next : road[curr.num]) {
                if (dist[r][next[0]] <= curr.cost + next[1]) continue;
                dist[r][next[0]] = curr.cost + next[1];
                pq.offer(new Node(next[0], dist[r][next[0]]));
            }
        }
    }

    private static void init() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 집
        M = Integer.parseInt(st.nextToken());   // 병원
        B = Integer.parseInt(st.nextToken());   // 다리
        K = Integer.parseInt(st.nextToken());   // 도로
        Q = Integer.parseInt(st.nextToken());

        // 도로
        road = new ArrayList[N + M + B + 1];
        dist = new long[B + 1][N + M + B + 1];
        for (int i = 1; i <= N + M + B; i++) {
            road[i] = new ArrayList<>();
        }

        int a, b, k;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            road[a].add(new int[]{b, k});
            road[b].add(new int[]{a, k});
        }
    }

    static class Node implements Comparable<Node> {
        int num;
        long cost;

        Node(int num, long cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node node) {
            return Long.compare(this.cost, node.cost);
        }
    }
}

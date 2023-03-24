package dijkstra.n2176;
// https://www.acmicpc.net/problem/2176

import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int to, weight;
        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    static final int S = 1, T = 2;
    static int N, M;
    static int[] dist, dp;
    static boolean[] visit;
    static ArrayList<Node>[] graph;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visit = new boolean[N+1];
        graph = new ArrayList[N+1];
        dist = new int[N+1];
        dp = new int[N+1];

        for(int i=1;i<=N;i++) {
            dist[i] = Integer.MAX_VALUE;
            graph[i] = new ArrayList<>();
            dp[i] = -1;
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        dijkstra();
        System.out.println(play(S));
    }

    static int play(int curr) {
        if(dp[curr] != -1) return dp[curr];
        if(curr == T) return dp[curr] = 1;
        dp[curr] = 0;
        for(Node next : graph[curr]) {
            if(dist[curr] > dist[next.to]) dp[curr] += play(next.to);
        }
        return dp[curr];
    }

    static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2)->o1.weight-o2.weight);
        dist[T] = 0;
        pq.add(new Node(T, 0));

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            if(node.weight > dist[node.to]) continue;

            for(Node next : graph[node.to]) {
                if(dist[next.to] > dist[node.to] + next.weight) {
                    dist[next.to] = dist[node.to] + next.weight;
                    pq.add(new Node(next.to, dist[next.to]));
                }
            }
        }
    }
}

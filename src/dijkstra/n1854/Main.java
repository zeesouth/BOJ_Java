package dijkstra.n1854;
// https://www.acmicpc.net/problem/1854

import java.io.*;
import java.util.*;

class Node {
    int v, cost;

    Node(int v, int cost) {
        this.v = v;
        this.cost = cost;
    }
}

public class Main {
    static int N, M, K;
    static ArrayList<Node>[] graph;
    static boolean[] visited;
    static int[] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();


        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        dist = new int[N+1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, c));
        }

        for(int i=1;i<=N;i++) sb.append(dijkstra(i, K) + "\n");


        bw.write(sb.toString());
        bw.flush();
        br.close();
    }

    static int dijkstra(int end, int k) {

        PriorityQueue<Integer> len = new PriorityQueue<>();

        visited = new boolean[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Node> q = new PriorityQueue<>(
                (o1, o2) -> o1.cost - o2.cost
        );
        q.add(new Node(1, 0));
        dist[1] = 0;

        while (!q.isEmpty()) {
            Node curr = q.poll();

            if (!visited[curr.v] && curr.v != end) visited[curr.v] = true;
            for (Node next : graph[curr.v]) {

                if (next.v == end) {
                    int cost = curr.cost + next.cost;
                    if(!len.contains(cost)) len.add(cost);
                } else if (!visited[next.v] && dist[next.v] > curr.cost + next.cost) {
                    dist[next.v] = curr.cost + next.cost;
                    q.add(new Node(next.v, dist[next.v]));
                }
            }
        }

        ArrayList<Integer> res = new ArrayList<>();
        res.addAll(len);

        return res.size() < K ? -1 : res.get(k-1);
    }
}
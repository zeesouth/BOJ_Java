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
    static PriorityQueue<Integer>[] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();


        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        dist = new PriorityQueue[N+1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = new PriorityQueue<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, c));
        }

        dijkstra(1);

        for(int i=1;i<=N;i++) {
            if(dist[i].size() == K) sb.append(-dist[i].peek()).append("\n");
            else sb.append(-1).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
    }

    static void dijkstra(int start) {

        dist[start].add(0);

        PriorityQueue<Node> q = new PriorityQueue<>(
                (o1, o2) -> o1.cost - o2.cost
        );
        q.add(new Node(start, 0));

        while (!q.isEmpty()) {
            Node curr = q.poll();

            for (Node next : graph[curr.v]) {

                if(dist[next.v].size() < K) {
                    dist[next.v].add(-(curr.cost + next.cost));
                    q.add(new Node(next.v, curr.cost + next.cost));
                } else if (-dist[next.v].peek() > curr.cost + next.cost) {
                    dist[next.v].poll();
                    dist[next.v].add(-(curr.cost + next.cost));
                    q.add(new Node(next.v, curr.cost + next.cost));
                }
            }
        }
    }
}
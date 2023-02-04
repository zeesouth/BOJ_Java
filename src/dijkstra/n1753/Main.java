package dijkstra.n1753;

import java.io.*;
import java.util.*;

class Node {
    int v;
    int cost;
    Node(int v, int cost) {this.v = v; this.cost = cost;}
}

public class Main {
    static int V, E, start;
    static boolean[] visited;
    static ArrayList<Node>[] graph;
    static int[] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());

        graph = new ArrayList[V+1];
        dist = new int[V+1];
        visited = new boolean[V+1];

        for(int i=1;i<=V;i++) {
            graph[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        for(int i=0;i<E;i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v, w));
        }

        dijkstra(start);

        for (int i=1;i<=V;i++) sb.append(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]).append("\n");

        bw.write(sb.toString());
        bw.flush();
        br.close();

    }

    static void dijkstra(int node) {
        // 우선순위 큐 사용, 가중치를 기준으로 오름차순
        PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        q.add(new Node(start, 0));
        dist[start] = 0;

        while(!q.isEmpty()) {
            // 현재 최단 거리가 가장 짧은 노드를 꺼내서 방문 처리
            Node now = q.poll();

            if(!visited[now.v]) visited[now.v] = true;

            for(Node next : graph[now.v]) {
                // 방문하지 않았으면서 현재 노드를 거쳐 다른 노드로 이동하는 거리가 더 짧은 경우
                if(!visited[next.v] && dist[next.v] > now.cost + next.cost) {
                    dist[next.v] = now.cost + next.cost;
                    q.add(new Node(next.v, dist[next.v]));
                }
            }
        }
    }
}

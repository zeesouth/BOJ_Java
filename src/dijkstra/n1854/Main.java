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
    static HashSet<Integer>[] beforeNodes;
    static boolean[] check, visited;
    static int[] dist;
    static int[][] original, graph;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st  = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new int[N+1][N+1];
        original = new int[N+1][N+1];
        dist = new int[N+1];
        beforeNodes = new HashSet[N+1];

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a][b] = c;
            original[a][b] = c;
        }

        for(int i=1;i<=N;i++) {
            check = new boolean[N+1];
            for(int j=0;j<K;j++) {
                dijkstra(1, i);
                deletePath(i);
            }
            sb.append(dijkstra(1, i)+"\n");

            for(int j=1;j<=N;j++) graph[j] = Arrays.copyOf(original[j], N+1);
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
    }

    static void deletePath(int node) {
        if (check[node]) return;
        check[node] = true;
        for (int key : beforeNodes[node]) {
            graph[key][node] = 0;
            deletePath(key);
        }
    }

    static int dijkstra(int start, int end) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        visited = new boolean[N+1];

        PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        q.add(new Node(start, 0));
        dist[start] = 0;

        beforeNodes = new HashSet[N+1];
        for (int i = 1; i <= N; i++) beforeNodes[i] = new HashSet<>();

        while (!q.isEmpty()) {
            Node curr = q.poll();
            if (visited[curr.v]) continue;
            visited[curr.v] = true;

            for (int i = 1; i <= N; i++) {
                int cost = graph[curr.v][i];

                if(visited[i] || cost == 0) continue;
                if(dist[i] < cost + curr.cost) continue;

                if (dist[i] > cost + curr.cost) beforeNodes[i].clear();

                beforeNodes[i].add(curr.v);
                dist[i] = cost + curr.cost;
                q.add(new Node(i, dist[i]));
            }
        }
        return dist[end] == Integer.MAX_VALUE || dist[end] == 0 ? -1 : dist[end];
    }

}

package dijkstra.n5719;
// https://www.acmicpc.net/problem/5719

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
    static int N, M, S, D;
    static int[][] graph;
    static HashSet<Integer>[] beforeNodes; // nodes[i] = i까지 최소 가중치로 있을 때 직전 노드들
    static boolean[] check, visited; // 노드 방문 여부
    static int[] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0) break;

            graph = new int[N][N];
            dist = new int[N];
            check = new boolean[N];

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                graph[u][v] = p;
            }

            dijkstra(S, D);
            deletePath(D);
            sb.append(dijkstra(S, D) + "\n");
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
        visited = new boolean[N];

        PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        q.add(new Node(start, 0));
        dist[start] = 0;

        beforeNodes = new HashSet[N];
        for (int i = 0; i < N; i++) beforeNodes[i] = new HashSet<>();

        while (!q.isEmpty()) {
            Node curr = q.poll();
            if (visited[curr.v]) continue;
            visited[curr.v] = true;

            for (int i = 0; i < N; i++) {
                int cost = graph[curr.v][i];

                if(visited[i] || cost == 0) continue;
                if(dist[i] < cost + curr.cost) continue;

                if (dist[i] > cost + curr.cost) beforeNodes[i].clear();

                beforeNodes[i].add(curr.v);
                dist[i] = cost + curr.cost;
                q.add(new Node(i, dist[i]));
            }
        }
        return dist[end] == Integer.MAX_VALUE ? -1 : dist[end];
    }
}

/*
 * 1. 최단 경로에 해당하는 경로들을 모두 찾는다.
 * 2. 1번의 최단 경로들을 지워준다. -> graph 변수에서 삭제
 * 3. 다익스트라를 통해 갱신된 경로에서 최단 경로를 다시 찾는다 -> 거의 최단 경로
 *
 */

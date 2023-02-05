package dijkstra.n1504;
// https://www.acmicpc.net/problem/1504

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
    static final int INF = 200000000;
    static int N, E, v1, v2, res1, res2, ans;
    static ArrayList<Node>[] graph;
    static int[] dist;
    static boolean[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        dist = new int[N+1];
        visit = new boolean[N+1];

        for(int i=1;i<=N;i++) graph[i] = new ArrayList<>();

        for(int i=0;i<E;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        // 1-> v1 -> v2 -> N
        res1 = dijkstra(1, v1);
        res1 += dijkstra(v1, v2);
        res1 += dijkstra(v2, N);

        // 1-> v2 -> v1 -> N
        res2 = dijkstra(1, v2);
        res2 += dijkstra(v2, v1);
        res2 += dijkstra(v1, N);

        ans = (res1 >= INF && res2 >= INF) ? -1 : Math.min(res1, res2);
        bw.write(ans+"");
        bw.flush();
        br.close();
    }

    static int dijkstra(int start, int end) {
        // 초기화
        Arrays.fill(dist, INF);
        visit = new boolean[N+1];

        // cost 오름차순
        PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        q.add(new Node(start, 0));
        dist[start] = 0;

        while(!q.isEmpty()) {
            Node curr = q.poll();

            if(!visit[curr.v]) visit[curr.v] = true;

            for(Node next : graph[curr.v]) {
                if(!visit[next.v] && dist[next.v] > curr.cost + next.cost) {
                    dist[next.v] = curr.cost + next.cost;
                    q.add(new Node(next.v, dist[next.v]));
                }
            }
        }

        return dist[end];
    }
}

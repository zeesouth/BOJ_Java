package dijkstra.n1238;

import java.io.*;
import java.util.*;

class Node {
    int end, time;
    Node(int end, int time) {
        this.end = end;
        this.time = time;
    }
}

public class Main {
    static int N, M, X, res, ans = Integer.MIN_VALUE;
    static ArrayList<Node>[] graph;
    static int[] dist;
    static boolean visit[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        visit = new boolean[N+1];
        graph = new ArrayList[N+1];
        dist = new int[N+1];
        for(int i=1;i<=N;i++) graph[i] = new ArrayList<>();

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            graph[s].add(new Node(e, t));
        }

        for(int i=1;i<=N;i++) {
            res = dijkstra(i, X);
            res += dijkstra(X, i);
            ans = Math.max(res, ans);
        }
        bw.write(ans+"");
        bw.flush();
        br.close();
    }

    static int dijkstra(int start, int end) {
        visit = new boolean[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> o1.time-o2.time);
        q.add(new Node(start, 0));
        dist[start] = 0;

        while(!q.isEmpty()) {
            Node curr = q.poll();

            if(!visit[curr.end]) visit[curr.end] = true;

            for(Node next : graph[curr.end]) {
                if(!visit[next.end] && dist[next.end] > curr.time + next.time) {
                    dist[next.end] = curr.time + next.time;
                    q.add(new Node(next.end, dist[next.end]));
                }
            }
        }

        return dist[end];
    }
}

package mst.n1647.prim;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int to, weight;
        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    static int N, M;
    static ArrayList<Node>[] graph;
    static boolean[] visited;
    static PriorityQueue<Node> pq;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for(int i=1;i<=N;i++) graph[i] = new ArrayList<>();

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph[A].add(new Node(B, C));
            graph[B].add(new Node(A, C));
        }

        visited = new boolean[N+1];
        pq = new PriorityQueue<>((o1, o2)->o1.weight-o2.weight);
        pq.add(new Node(1, 0));
        System.out.println(prim());
    }

    static long prim() {
        long res = 0L;
        int mc = 0;
        while(!pq.isEmpty()) {
            Node curr = pq.poll();

            if(visited[curr.to]) continue;
            visited[curr.to] = true;
            res += curr.weight;
            if(mc < curr.weight) mc = curr.weight;

            for(Node next : graph[curr.to]) {
                if(!visited[next.to]) pq.add(next);
            }
        }
        return res-mc;
    }
}

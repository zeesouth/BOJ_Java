package mst.n1647.kruskal;

import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int from, to, weight;
        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
    static int N, M;
    static int[] parents;
    static PriorityQueue<Edge> edges;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new PriorityQueue<>((o1, o2)->o1.weight-o2.weight);
        parents = new int[N+1];
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            edges.add(new Edge(A, B, C));
        }
        System.out.println(kruskal());
    }

    private static int kruskal() {
        int res = 0, cnt = 0;
        initParent();
        while(!edges.isEmpty()) {
            Edge e = edges.poll();
            if(union(e.from, e.to)) {
                res += e.weight;
                if(++cnt == N-2) return res;
            }
        }
        return res;
    }

    private static boolean union(int a, int b) {
        int aRoot = findParent(a);
        int bRoot = findParent(b);

        if(aRoot == bRoot) return false;
        parents[aRoot] = bRoot;
        return true;
    }

    private static int findParent(int n) {
        if(n == parents[n]) return n;
        else return parents[n] = findParent(parents[n]);
    }

    private static void initParent() {
        for(int i=1;i<=N;i++) parents[i] = i;
    }
}

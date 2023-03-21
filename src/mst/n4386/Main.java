package mst.n4386;
// https://www.acmicpc.net/problem/4386

import java.io.*;
import java.util.*;

public class Main {
    static class Vertax {
        float x, y;
        Vertax(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Edge {
        int from, to;
        double dist;
        Edge(int from, int to, double dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }
    }
    static int N;
    static int[] parents;
    static Vertax[] vertaxes;
    static PriorityQueue<Edge> edges;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        vertaxes = new Vertax[N+1];
        parents = new int[N+1];
        edges = new PriorityQueue<>((o1, o2)-> Double.compare(o1.dist,o2.dist));
        StringTokenizer st;
        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            float a = Float.parseFloat(st.nextToken());
            float b = Float.parseFloat(st.nextToken());
            vertaxes[i] = new Vertax(a, b);
            for(int j=1;j<i;j++) edges.add(new Edge(i, j, dist(vertaxes[i], vertaxes[j])));
        }
        System.out.println(kruskal());
    }

    static double kruskal() {
        int cnt = 0;
        double res = 0.0;

        init();

        while(!edges.isEmpty()) {
            Edge e = edges.poll();
            if(union(e.from, e.to)) {
                res += e.dist;
                if(++cnt == N-1) return res;
            }
        }

        return res;
    }

    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return false;
        parents[aRoot] = bRoot;
        return true;
    }

    static int find(int n) {
        if(n == parents[n]) return n;
        else return parents[n] = find(parents[n]);
    }

    static double dist(Vertax a, Vertax b) {
        return Math.sqrt(Math.abs((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y)));
    }

    static void init() {
        for(int i=1;i<=N;i++) parents[i] = i;
    }
}

package mst.n1922.kruskal;
// https://www.acmicpc.net/problem/1922 - kruskal - 간선의 개수가 적을 때 사용

import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int from, to, weight;
        Edge (int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
    static int N, M, parents[];
    static Edge[] edges;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        edges = new Edge[M];
        parents = new int[N+1];
        StringTokenizer st;
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(a, b, c);
        }

        System.out.println(kruskal());
    }

    private static int kruskal() {
        int res = 0, cnt = 0;
        Arrays.sort(edges, (o1, o2)->o1.weight-o2.weight);

        // 정점 초기화
        for(int i=1;i<=N;i++) parents[i] = i;

        for(Edge e : edges) {
            if(!isCycle(e.from, e.to)) {
                System.out.println(e.from+", "+e.to);
                res += e.weight;
                if(++cnt == N-1) return res;
            }
        }
        return res;
    }

    private static boolean isCycle(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return true;
        parents[aRoot] = bRoot;
        return false;
    }

    private static int find(int n) {
        if(n == parents[n]) return n;
        else return parents[n] = find(parents[n]);
    }
}

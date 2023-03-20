package mst.n1197.kruskal;
// https://www.acmicpc.net/problem/1197
// MST 최소 스패닝 트리 - Kruskal (간선의 수가 작을 때 사용)

import java.io.*;
import java.util.*;

class Edge {
    int from, to, weight;
    Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}

public class Main {
    static int V, E, parents[];
    static Edge[] edgeList;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edgeList = new Edge[E];
        parents = new int[V+1];

        for(int i=0;i<E;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edgeList[i] = new Edge(a, b, c);
        }

        System.out.println(kruskal());
    }

    private static int kruskal() {
        int res = 0, cnt = 0;
        Arrays.sort(edgeList, (o1, o2)->o1.weight-o2.weight);

        for(int i=1;i<=V;i++) parents[i] = i;

        for(Edge edge : edgeList) {
            // 싸이클 형성 여부 판단 : 싸이클이 형성이 안되면 true
            if(union(edge.from, edge.to)) {
                // 간선 사용
                res += edge.weight;
                // 정점-1개의 간선이 이어지면 MST 완성
                if(++cnt == V-1) return res;
            }
        }

        return res;
    }

    private static boolean union(int a,int b) {
        int aRoot = findParent(a);
        int bRoot = findParent(b);

        if(aRoot == bRoot) return false;
        parents[aRoot] = bRoot;
        return true;
    }

    private static int findParent(int n) {
        if(n == parents[n]) return n;
        return parents[n] = findParent(parents[n]);
    }
}

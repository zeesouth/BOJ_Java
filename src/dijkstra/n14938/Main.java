package dijkstra.n14938;
// https://www.acmicpc.net/problem/14938

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, R, max = 0;

    static class Node {
        int idx, weight;
        Node(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }
    }
    static int[] items, dist;
    static boolean[] check;
    static ArrayList<Node>[] graph;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        items = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) items[i] = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for(int i=1;i<=N;i++) graph[i] = new ArrayList<Node>();

        for(int i=0;i<R;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, l));
            graph[b].add(new Node(a, l));
        }

        dist = new int[N+1];
        check = new boolean[N+1];

        for(int i=1;i<=N;i++) max = Math.max(max, dijkstra(i));

        System.out.println(max);
    }

    static int dijkstra(int start) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        check = new boolean[N+1];

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (o1, o2) -> o1.weight-o2.weight
        );
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()) {
            Node currNode = pq.poll();

            if(!check[currNode.idx]) check[currNode.idx]= true;

            for(Node nextNode : graph[currNode.idx]) {
                if(!check[nextNode.idx] &&
                        dist[nextNode.idx] > dist[currNode.idx] + nextNode.weight) {
                    dist[nextNode.idx] = dist[currNode.idx] + nextNode.weight;
                    pq.add(new Node(nextNode.idx, dist[nextNode.idx]));
                }
            }
        }

        int res = 0;
        for(int i=1;i<=N;i++) {
            if(dist[i] <= M) res+= items[i];
        }

        return res;
    }
}

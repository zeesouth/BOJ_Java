package mst.n1197;
// https://www.acmicpc.net/problem/1197
// MST 최소 스패닝 트리

import java.io.*;
import java.util.*;

class Node {
    int to, weight;

    public Node(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}

public class Main {
    static StringTokenizer st;
    static int v, e;
    static boolean[] visit;
    static PriorityQueue<Node> queue;
    static ArrayList<ArrayList<Node>> nodeList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        visit = new boolean[v + 1];
        queue = new PriorityQueue<>((o1, o2) -> o1.weight-o2.weight);

        nodeList = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            nodeList.add(new ArrayList<>());
        }

        for(int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            nodeList.get(v1).add(new Node(v2,weight));
            nodeList.get(v2).add(new Node(v1,weight));
        }

        queue.add(new Node(1,0));

        int sum = 0;
        while(!queue.isEmpty()){
            Node n = queue.poll();
            int to = n.to;
            int weight = n.weight;

            if(visit[to]) continue;

            visit[to] = true;
            sum += weight;

            for(Node next : nodeList.get(to)) {
                if(!visit[next.to]) queue.offer(next);
            }
        }
        System.out.println(sum);
    }
}

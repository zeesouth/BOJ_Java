package mst.n1922.prim;

import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int to, weight;
        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    static int N, M;
    static boolean[] visit;
    static PriorityQueue<Node> pq;
    static ArrayList<Node>[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        visit = new boolean[N+1];
        arr = new ArrayList[N+1];
        for(int i=1;i<=N;i++) arr[i] = new ArrayList<>();

        StringTokenizer st;
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr[a].add(new Node(b, c));
            arr[b].add(new Node(a, c));
        }

        pq = new PriorityQueue<>((o1, o2)->o1.weight-o2.weight);
        pq.add(new Node(1, 0));

        int sum = 0;
        while(!pq.isEmpty()) {
            Node curr = pq.poll();

            if(visit[curr.to]) continue;
            visit[curr.to] = true;
            sum += curr.weight;

            for(Node next : arr[curr.to]) {
                if(!visit[next.to]) pq.add(next);
            }
        }
        System.out.println(sum);
    }
}

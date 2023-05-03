package dijkstra.n2211;

import java.io.*;
import java.util.*;
public class Main {
    static int N, M;
    static ArrayList<Node> graph[];
    static int dist[];
    static boolean visited[];
    static ArrayList<Node> res;
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
            graph[A].add(new Node(A, B, C));
            graph[B].add(new Node(B, A, C));
        }

        dijkstra();

        StringBuilder sb = new StringBuilder();
        sb.append(res.size()).append("\n");
        for(Node node : res)
            sb.append(node.from).append(" ").append(node.to).append("\n");
        System.out.println(sb);
        br.close();
    }

    static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.value-o2.value);
        pq.add(new Node(1, 1, 0));
        visited = new boolean[N+1];
        visited[1] = true;
        dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        res = new ArrayList<>();

        while(!pq.isEmpty()) {
            Node curr = pq.poll();

            if(!visited[curr.to]) {
                visited[curr.to] = true;
                res.add(curr);
            }

            for(Node next : graph[curr.to]) {
                if(dist[next.to] > curr.value+next.value) {
                    dist[next.to] = curr.value+next.value;
                    pq.add(new Node(next.from, next.to, dist[next.to]));
                }
            }
        }
    }

    static class Node {
        int from, to, value;
        Node(int from, int to, int value) {
            this.from = from;
            this.to = to;
            this.value = value;
        }
    }
}
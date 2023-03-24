package dp_tree.n1135;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] dp;
    static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N];
        dp = new int[N];
        for(int i=0;i<N;i++) graph[i] = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            int p = Integer.parseInt(st.nextToken());
            if(p != -1) graph[p].add(i);
        }
        System.out.println(dfs(0));
    }

    static int dfs(int curr) {
        int cnt = 0, max = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2)->o2.weight-o1.weight);

        for(int next : graph[curr]) {
            dp[next] = dfs(next);
            pq.add(new Node(next, dp[next]));
        }

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            cnt++;
            max = Math.max(max, node.weight+cnt);
        }
        return max;
    }

    static class Node {
        int idx, weight;
        Node(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }
    }
}

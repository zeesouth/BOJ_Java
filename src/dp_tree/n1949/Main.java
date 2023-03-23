package dp_tree.n1949;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] w;
    static int[][] dp;
    static boolean[] visit;
    static ArrayList<Integer>[] graph, tree;
    static PriorityQueue<Integer> pq;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        w = new int[N+1];
        dp = new int[N+1][2];
        visit = new boolean[N+1];
        graph = new ArrayList[N+1];
        tree = new ArrayList[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) {
            w[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<>();
            tree[i] = new ArrayList<>();
        }

        for(int i=0;i<N-1;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        pq = new PriorityQueue<>();

        buildTree(1, 0);

        int res = 0;
        int rootInclude = search(1, 1);
        int rootNonInclude = search(1, 0);
        res = Math.max(rootInclude, rootNonInclude);
        System.out.println(res);
    }

    static int search(int curr, int check) {
        int res = 0;
        // 이미 탐색한 정점이라면
        if(dp[curr][check] != 0) return dp[curr][check];

        // 현재 정점을 포함하면
        if(check == 1) {
            res += w[curr];
            for(int next : tree[curr]) res += search(next, 0);
        }
        // 현재 정점을 미포함한다면
        else {
            for(int next : tree[curr]) {
                int include = search(next, 1);  // 인접한 정점 포함
                int nonInclude = search(next, 0);   // 인접한 정점 미포함
                res += Math.max(include, nonInclude);
            }
        }
        return dp[curr][check] = res;
    }

    static void buildTree(int node, int root) {
        for(int next : graph[node]) {
            if(next != root) {
                tree[node].add(next);
                buildTree(next, node);
            }
        }
    }
}

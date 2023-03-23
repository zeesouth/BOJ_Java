package dp_tree.n2213;
// https://www.acmicpc.net/problem/2213

import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static boolean[] visit;
    static int[] w;
    static int[][] dp;
    static ArrayList<Integer>[] graph; // graph 저장
    static ArrayList<Integer>[] tree; // tree 저장
    static PriorityQueue<Integer> pq;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1][2];
        visit = new boolean[N+1];
        w = new int[N+1];
        graph = new ArrayList[N+1];
        tree = new ArrayList[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) {
            graph[i] = new ArrayList<>();
            tree[i] = new ArrayList<>();
            w[i] = Integer.parseInt(st.nextToken());
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

        if(rootInclude > rootNonInclude) {
            res += rootInclude;
            trace(1, 1);
        } else {
            res += rootNonInclude;
            trace(1, 0);
        }
        sb.append(res).append("\n");
        while(!pq.isEmpty()) sb.append(pq.poll()).append(" ");
        System.out.println(sb);
        br.close();
    }

    static void trace(int curr, int check) {
        if(check == 1) {
            pq.add(curr);
            for(int next : tree[curr]) trace(next, 0);  // 인접 정점 미포함
        } else {    // 현재 정점 미포함
            for(int next : tree[curr]) {
                // 인접한 정점에서 가중치가 더 큰 (포함 싱태, 미포함 싱태)
                if(dp[next][1] > dp[next][0]) trace(next, 1);
                else trace(next, 0);
            }
        }
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

    // 그래프에 대한 내용으로 트리 만드는 함수
    static void buildTree(int n, int root) {
        for(int next : graph[n]) {
            if(next != root) {
                tree[n].add(next);
                buildTree(next, n);
            }
        }
    }
}

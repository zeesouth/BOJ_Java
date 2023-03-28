package dp_tree.n14267;
// https://www.acmicpc.net/problem/14267

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] res;
    static ArrayList<Integer>[] tree;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        res = new int[N+1];
        tree = new ArrayList[N+1];

        for(int i=1;i<=N;i++) tree[i] = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) {
            int node = Integer.parseInt(st.nextToken());
            if(node != -1) tree[node].add(i);
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            res[node] += val;
        }

        dfs(1);

        for(int i=1;i<=N;i++) sb.append(res[i]).append(" ");
        System.out.println(sb);
    }

    static void dfs(int node) {
        for(int next : tree[node]) {
            res[next] += res[node];
            dfs(next);
        }
    }
}

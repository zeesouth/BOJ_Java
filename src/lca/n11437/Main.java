package lca.n11437;
// https://www.acmicpc.net/problem/11437


import java.io.*;
import java.util.*;

public class Main {
    static LinkedList<Integer>[] tree;
    static int[] parent;
    static int[] depth;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/n11437.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        tree = new LinkedList[N+1];
        for(int i=1;i<=N;i++) tree[i] = new LinkedList<>();

        StringTokenizer st = null;
        for(int i=0;i<N-1;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }

        parent = new int[N+1];
        depth = new int[N+1];

        // DFS를 통해 깊이와 부모 노드 배열에 저장
        // 최상위 노드가 1이므로 cur(nodeNum):1, d(depth):0, p(parent):-1 부터 시작
        dfs(1, 0, -1);

        int M = Integer.parseInt(br.readLine());
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // LCA 시작,
            // 1) 최소 횟수로 a,b 깊이를 동일하게 만들어주기
            int depthA = depth[a];
            int depthB = depth[b];

            while(depthA > depthB) {
                a = parent[a];
                depthA--;
            }

            while(depthB > depthA) {
                b = parent[b];
                depthB--;
            }

            // 2) 같은 부모가 나올 때까지 반복
            while(a != b) {
                a = parent[a];
                b = parent[b];
            }

            System.out.println(a);
        }

    }

    static void dfs(int cur, int d, int p) {
        depth[cur] = d;
        parent[cur] = p;

        for(int next : tree[cur]) {
            // 부모는 무조건 1개여야 하고, 1로부터 좀 더 가까운 노드가 부모노드가 됨
            if(next != p) {
                dfs(next, d+1, cur);
            }
        }
    }
}

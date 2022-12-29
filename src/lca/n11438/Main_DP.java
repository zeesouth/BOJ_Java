package lca.n11438;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
// https://www.acmicpc.net/problem/11438
// DP를 활용한 풀이

public class Main_DP {

    static int N, h;
    static ArrayList<Integer>[] edge;
    static int[] depth;     // 노드별 깊이
    static int[][] parent;  // 높이별 부모

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/n11438.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        edge = new ArrayList[N+1];
        for(int i=1;i<=N;i++) edge[i] = new ArrayList<>();

        for(int i=0;i<N-1;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edge[a].add(b); edge[b].add(a);
        }

        h = (int) Math.ceil(Math.log(N)/Math.log(2))+1; // 최대 트리 높이
        depth = new int[N+1];
        parent = new int[N+1][h];

        init(1, 0, 0);
        fillParents();
        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            sb.append(LCA(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))+"\n");
        }
        System.out.println(sb.toString());
    }

    // 어떤 노드의 직속 부모 구하기
    static void init(int node, int h, int p) {
        depth[node] = h;
        for(int next : edge[node]) {
            if(next != p){
                init(next, h+1, node);
                parent[next][0] = node; // next의 직속 부모는 node
            }
        }
    }

    // 나머지 2^0, 2^1, ..., 2^h-1번째 부모 노드 채워주기
    static void fillParents() {
        for(int i=1;i<h;i++) {
            for(int j=1;j<N+1;j++) parent[j][i] = parent[parent[j][i-1]][i-1];
        }
    }

    static int LCA(int a, int b) {
        int ah = depth[a];  // a의 깊이
        int bh = depth[b];  // b의 깊이

        // ah > bh로 setting
        if(ah < bh) {
            int temp = a;
            a = b;
            b = temp;
        }

        // 1. 높이 맞추기
        for(int i = h-1;i>=0;i--) {
            if(Math.pow(2, i) <= depth[a]-depth[b]) a = parent[a][i];
        }
        if(a==b) return a;  // a==b라는 뜻은 a, b가 동일한 노드임을 의미

        // 2. LCA 찾기
        for(int i=h-1; i>=0;i--){
            if(parent[a][i] != parent[b][i]){
                a = parent[a][i];
                b = parent[b][i];
            }
        }
        return parent[a][0];
    }
}

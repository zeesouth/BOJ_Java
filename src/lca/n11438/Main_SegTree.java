package lca.n11438;

import java.io.*;
import java.util.*;

public class Main_SegTree {

    static int N;
    static ArrayList<Integer>[] edge;
    static int[] firstVisitCnt, segMinTree;
    static ArrayList<Integer> visitRoute;

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

        firstVisitCnt = new int[N+1];
        visitRoute = new ArrayList<>();
        dfs(1, 0, 0);

        int h = (int)Math.ceil(Math.log(visitRoute.size())/Math.log(2));
        int size = 1 << (h+1);
        segMinTree = new int[size];
        init(1, 0, visitRoute.size()-1);

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = firstVisitCnt[Integer.parseInt(st.nextToken())];
            int b = firstVisitCnt[Integer.parseInt(st.nextToken())];

            if(a>b){
                int tmp = a;
                a = b;
                b = tmp;
            }

            sb.append(getMin(1, 0, visitRoute.size()-1, a, b)+"\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

    static void dfs(int node, int p, int d) {
        if(firstVisitCnt[node] == 0)
            firstVisitCnt[node] = visitRoute.size();
        visitRoute.add(node);
        for(int child : edge[node]) {
            if(child != p) dfs(child, node, d+1);
        }
        if(p != 0) visitRoute.add(p);
    }

    static int init(int node, int start, int end) {
        if(start == end)
            return segMinTree[node] = visitRoute.get(start);
        int mid = (start+end)/2;
        return segMinTree[node] = Math.min(init(node*2, start, mid), init(node*2+1, mid+1, end));
    }

    static int getMin(int node, int start, int end, int left, int right) {
        if(end < left || start > right)
            return Integer.MAX_VALUE;
        if(left <= start && end <= right)
            return segMinTree[node];
        int mid = (start+end)/2;
        return Math.min(getMin(node*2, start, mid, left, right), getMin(node*2+1, mid+1, end, left, right));
    }
}

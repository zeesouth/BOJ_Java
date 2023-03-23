package dp_tree.n2533;
// https://www.acmicpc.net/problem/2533

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] dp;
    static boolean[] visit;
    static ArrayList<Integer>[] node;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        node = new ArrayList[N+1];
        dp = new int[N+1][2];
        visit = new boolean[N+1];

        for(int i=1;i<=N;i++) node[i] = new ArrayList<>();


        for(int i=0;i<N-1;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            node[u].add(v);
            node[v].add(u);
        }

        dfs(1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));

    }

    static void dfs(int n) {
        if(visit[n]) return;
        visit[n] = true;
        dp[n][0] = 0;   // n번 노드가 얼리어답터가 아닌 경우
        dp[n][1] = 1;   // n번 노드가 얼리어답터인 경우

        for(int next : node[n]) {
            if(!visit[next]) {
                dfs(next); // 자식 노드의 dp값을 체크
                dp[n][0] += dp[next][1];    // 자식 노드가 무조건 얼리어답터여야 함
                dp[n][1] += Math.min(dp[next][1], dp[next][0]); // 최소 얼리어답터 수를 뽑는 경우이므로 자식 노드가 얼리어답터일 수도, 아닐 수도 있다.
            }
        }
    }
}

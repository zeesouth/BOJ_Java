package binarySearch.n1939;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static ArrayList<int[]> graph[];
    static boolean visited[];
    static int N, s, e, max, ans;

    public static void main(String[] args) throws Exception {
        init();
        binarySearch();
        System.out.println(ans);
    }

    private static void binarySearch() {
        int left = 1, right = max, mid;

        while (left <= right) {
            mid = (left + right) / 2;

            if (isPossible(mid)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }

    private static boolean isPossible(int mid) {
        visited = new boolean[N + 1];
        return dfs(s, mid);
    }

    private static boolean dfs(int node, int max) {
        if (node == e) {
            return true;
        }

        visited[node] = true;

        for (int next[] : graph[node]) {
            if (visited[next[0]]) continue;
            if (next[1] < max) continue;
            if (dfs(next[0], max)) return true;
        }

        return false;
    }


    private static void init() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph[A].add(new int[]{B, C});
            graph[B].add(new int[]{A, C});
            max = Math.max(C, max);
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
    }
}

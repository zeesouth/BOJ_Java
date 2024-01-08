package permutationCycleDecomposition.n10451;

import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 1000;
    static int arr[] = new int[MAX + 1];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N, ans;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            init();
            simulate();
        }
        System.out.println(sb);
    }

    private static void simulate() {
        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue;
            findCycle(i);
            ans++;
        }

        sb.append(ans).append("\n");
    }

    private static void init() throws Exception {
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) arr[i] = Integer.parseInt(st.nextToken());

        ans = 0;
    }

    private static void findCycle(int node) {
        int curr = node;
        do {
            visited[curr] = true;
            curr = arr[curr];
        } while (curr != node);
    }
}

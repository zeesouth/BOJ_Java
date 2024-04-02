package floydWarshall.n1613;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final int INF = 400;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int graph[][];

    public static void main(String[] args) throws Exception {
        init();

        int S = Integer.parseInt(br.readLine()), A, B;
        while (S-- > 0) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            sb.append(solve(A, B)).append("\n");
        }
        System.out.println(sb);
    }

    private static int solve(int a, int b) {
        if (graph[a][b] == INF && graph[b][a] == INF) return 0;
        else if (graph[a][b] != INF && graph[a][b] > 0) return -1;
        else if (graph[b][a] != INF && graph[b][a] > 0) return 1;
        return 0;
    }

    private static void init() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        graph = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }

        int K = Integer.parseInt(st.nextToken()), A, B;
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            graph[A][B] = 1;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= N; k++) {
                    graph[j][k] = Math.min(graph[j][k], graph[j][i] + graph[i][k]);
                }
            }
        }
    }
}

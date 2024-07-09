package dp_tree.n23887;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, K, S, C;
    static int map[][], stu[][];
    static int dp[];
    static int dy[] = {1, 0, -1, 0, 1, 1, -1, -1};
    static int dx[] = {0, 1, 0, -1, 1, -1, 1, -1};
    static ArrayList<Integer>[] arr;
    static boolean visited[];

    public static void main(String[] args) throws Exception {
        init();
        makeTree(S);

        if (C != K) System.out.println(-1);
        else {
            dfs(S);
            print();
        }
    }

    private static void dfs(int node) {
        dp[node] = 1;

        for (int child : arr[node]) {
            dfs(child);
            dp[node] += dp[child];
        }
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= K; i++) {
            sb.append(dp[i]).append(" ");
        }
        System.out.println(sb);
    }


    private static void makeTree(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);

        visited = new boolean[K + 1];
        visited[start] = true;

        C = 1;

        while (!q.isEmpty()) {
            int size = q.size();
            PriorityQueue<Integer> pq = new PriorityQueue<>();

            for (int i = 0; i < size; i++) {
                int curr = q.poll();

                for (int d = 0; d < 8; d++) {
                    int ny = stu[curr][0] + dy[d];
                    int nx = stu[curr][1] + dx[d];

                    if (!isRange(ny, nx)) continue;
                    if (map[ny][nx] == 0) continue;
                    if (visited[map[ny][nx]]) continue;

                    visited[map[ny][nx]] = true;
                    C++;
                    arr[curr].add(map[ny][nx]);
                    pq.offer(map[ny][nx]);
                }
            }

            while (!pq.isEmpty()) q.offer(pq.poll());
        }
    }

    private static boolean isRange(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }

    private static void init() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        stu = new int[K + 1][2];

        int y, x;
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            y = Integer.parseInt(st.nextToken()) - 1;
            x = Integer.parseInt(st.nextToken()) - 1;

            map[y][x] = i;
            stu[i][0] = y;
            stu[i][1] = x;
        }

        S = Integer.parseInt(br.readLine());
        dp = new int[K + 1];

        arr = new ArrayList[K + 1];
        for (int i = 0; i <= K; i++) arr[i] = new ArrayList<>();
    }
}

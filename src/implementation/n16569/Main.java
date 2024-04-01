package implementation.n16569;

import java.io.*;
import java.util.*;

public class Main {
    // https://www.acmicpc.net/problem/16569
    static final int[] dy = {1, 0, -1, 0};
    static final int[] dx = {0, 1, 0, -1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static PriorityQueue<int[]> pq1 = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
    static Queue<int[]> q1 = new LinkedList<>();
    static Queue<int[]> q2 = new LinkedList<>();
    static int M, N, V, Y, X;
    static int map[][];
    static boolean visited1[][];
    static boolean visited2[][];
    static int T, ans1, ans2;

    public static void main(String[] args) throws Exception {
        init();
        while (solve()) {
        }
        System.out.println(ans1 + " " + (ans2));
    }

    private static boolean solve() {
        // 시각이 되면 화산 쇄설류를 덮음
        while (!pq1.isEmpty() && pq1.peek()[2] == T) {
            int[] curr = pq1.poll();
            q1.add(new int[]{curr[0], curr[1]});
            visited1[curr[0]][curr[1]] = true;
        }

        T++;

        // 화산 쇄설류 덮기
        Queue<int[]> n_q1 = new LinkedList<>();
        while (!q1.isEmpty()) {
            int[] curr = q1.poll();

            for (int i = 0; i < 4; i++) {
                int nextY = curr[0] + dy[i];
                int nextX = curr[1] + dx[i];

                if (!isRange(nextY, nextX)) continue;
                if (visited1[nextY][nextX]) continue;

                n_q1.add(new int[]{nextY, nextX});
                visited1[nextY][nextX] = true;
            }
        }

        q1 = n_q1;

        // 움직이기
        Queue<int[]> n_q2 = new LinkedList<>();
        while (!q2.isEmpty()) {
            int[] curr = q2.poll();
            // if (visited1[curr[0]][curr[1]]) continue;

            for (int i = 0; i < 4; i++) {
                int nextY = curr[0] + dy[i];
                int nextX = curr[1] + dx[i];

                if (!isRange(nextY, nextX)) continue;
                if (visited1[nextY][nextX]) continue;
                if (visited2[nextY][nextX]) continue;
                if (map[nextY][nextX] == -1) continue;

                if (ans1 < map[nextY][nextX]) {
                    ans1 = map[nextY][nextX];
                    ans2 = T;
                } else if (ans1 == map[nextY][nextX]) {
                    ans2 = Math.min(T, ans2);
                }
                visited2[nextY][nextX] = true;
                n_q2.add(new int[]{nextY, nextX});
            }
        }

        if (n_q2.isEmpty()) return false;
        q2 = n_q2;


        return true;
    }

    private static boolean isRange(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }

    private static void init() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Y = Integer.parseInt(st.nextToken()) - 1;
        X = Integer.parseInt(st.nextToken()) - 1;


        map = new int[N][M];
        visited1 = new boolean[N][M];
        visited2 = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans1 = map[Y][X];
        q2.add(new int[]{Y, X});
        visited2[Y][X] = true;

        int y, x, t;
        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());
            y = Integer.parseInt(st.nextToken()) - 1;
            x = Integer.parseInt(st.nextToken()) - 1;
            t = Integer.parseInt(st.nextToken());

            pq1.add(new int[]{y, x, t});
            map[y][x] = -1;
        }
    }
}

package simulation.n1113;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final int SIZE = 50;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int map[][] = new int[SIZE][SIZE];
    static boolean visited[][][] = new boolean[SIZE][SIZE][10];
    static Queue<int[]> q = new LinkedList<>();
    static int N, M;
    static int ans, max;

    public static void main(String[] args) throws Exception {
        init();
        simulate();
        System.out.println(ans);
    }

    private static int bfs(int y, int x, int h) {
        int cnt = 1;
        q.add(new int[]{y, x});
        visited[y][x][h] = true;

        boolean flag = true;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[] curr = q.poll();
                for (int d = 0; d < 4; d++) {
                    int nextY = curr[0] + dy[d];
                    int nextX = curr[1] + dx[d];

                    if (!isRange(nextY, nextX)) {
                        flag = false;
                        continue;
                    }
                    if (visited[nextY][nextX][h]) continue;
                    if (map[nextY][nextX] >= h) continue;

                    q.add(new int[]{nextY, nextX});
                    visited[nextY][nextX][h] = true;
                    cnt++;
                }
            }
        }

        if(flag) return cnt;
        else return 0;
    }

    private static void simulate() {
        for (int h = 1; h <= max; h++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (visited[i][j][h]) continue;
                    if (map[i][j] >= h) continue;
                    ans += bfs(i, j, h);
                }
            }
        }
    }

    private static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                max = Math.max(map[i][j] = s.charAt(j) - '0', max);
            }
        }
    }

    static boolean isRange(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }
}

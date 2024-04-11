package graphSearch.n19952;

import java.io.*;
import java.util.*;

public class Main {
    static final int dy[] = {1, 0, -1, 0};
    static final int dx[] = {0, 1, 0, -1};
    static final int SIZE = 100, INF = Integer.MAX_VALUE;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int H, W, O, F, sy, sx, ey, ex;
    static int map[][] = new int[SIZE][SIZE];
    static int dist[][] = new int[SIZE][SIZE];

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            init();
            sb.append(bfs() ? "잘했어!!\n" : "인성 문제있어??\n");
        }
        System.out.println(sb);
    }

    private static boolean bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sy, sx, 0});
        dist[sy][sx] = 0;

        boolean flag = false;
        loop:
        while (!q.isEmpty()) {
            int[] curr = q.poll();

            int nextY, nextX, nextF = curr[2] + 1;
            for (int i = 0; i < 4; i++) {
                nextY = curr[0] + dy[i];
                nextX = curr[1] + dx[i];

                if (!isRange(nextY, nextX)) continue;
                int diff = map[nextY][nextX] - map[curr[0]][curr[1]];

                if (diff > 0 && F - curr[2] < diff) continue;

                if (dist[nextY][nextX] <= nextF) continue;
                if (nextY == ey && nextX == ex) {
                    flag = true;
                    break loop;
                }
                dist[nextY][nextX] = nextF;

                if (nextF < F) q.add(new int[]{nextY, nextX, nextF});

            }
        }

        return flag;
    }

    private static boolean isRange(int y, int x) {
        return y >= 0 && y < H && x >= 0 & x < W;
    }

    private static void init() throws Exception {
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        O = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());
        sy = Integer.parseInt(st.nextToken()) - 1;
        sx = Integer.parseInt(st.nextToken()) - 1;
        ey = Integer.parseInt(st.nextToken()) - 1;
        ex = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                map[i][j] = 0;
                dist[i][j] = INF;
            }
        }

        int oy, ox, ol;
        for (int i = 0; i < O; i++) {
            st = new StringTokenizer(br.readLine());
            oy = Integer.parseInt(st.nextToken()) - 1;
            ox = Integer.parseInt(st.nextToken()) - 1;
            ol = Integer.parseInt(st.nextToken());
            map[oy][ox] = ol;
        }
    }
}

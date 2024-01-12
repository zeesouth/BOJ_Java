package simulation.n15644;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final int dy[] = {1, 0, -1, 0};
    static final int dx[] = {0, 1, 0, -1};
    static final char d[] = {'D', 'R', 'U', 'L'};
    static final int MAX = 10;
    static char map[][] = new char[MAX][MAX];
    static boolean visited[][][][] = new boolean[MAX][MAX][MAX][MAX];
    static int N, M, ry, rx, by, bx, hy, hx;
    static int ans1 = 11;
    static char[] ans2;

    public static void main(String[] args) throws Exception {
        init();
        dfs(ry, rx, by, bx, 0, new char[10]);

        if (ans1 > 10) System.out.println(-1);
        else {
            System.out.println(ans1);
            System.out.println(new String(Arrays.copyOfRange(ans2, 0, ans1)));
        }
    }

    private static void dfs(int ry, int rx, int by, int bx, int cnt, char[] road) {

        if (map[ry][rx] == 'O') {
            if (ans1 > cnt) {
                ans1 = cnt;
                ans2 = Arrays.copyOfRange(road, 0, cnt);
            }
            return;
        }

        if (cnt >= 10) return;

        visited[ry][rx][by][bx] = true;

        for (int i = 0; i < 4; i++) {
            int nry = ry;
            int nrx = rx;
            int nby = by;
            int nbx = bx;
            boolean isHole = false;
            while (!isHole && isRange(nby + dy[i], nbx + dx[i])) {
                nby += dy[i];
                nbx += dx[i];
                if (nby == hy && nbx == hx) isHole = true;
            }

            if (isHole) continue;

            while (isRange(nry + dy[i], nrx + dx[i])) {
                nry += dy[i];
                nrx += dx[i];
                if (nry == hy && nrx == hx) break;
            }

            if (nry == nby && nrx == nbx) {
                if (i == 0) {
                    if (ry < by) {
                        nry -= 1;
                    } else {
                        nby -= 1;
                    }
                } else if (i == 1) {
                    if (rx < bx) {
                        nrx -= 1;
                    } else {
                        nbx -= 1;
                    }
                } else if (i == 2) {
                    if (ry > by) {
                        nry += 1;
                    } else {
                        nby += 1;
                    }
                } else {
                    if (rx > bx) {
                        nrx += 1;
                    } else {
                        nbx += 1;
                    }
                }
            }

            road[cnt] = d[i];

            if (visited[nry][nrx][nby][nbx]) continue;
            dfs(nry, nrx, nby, nbx, cnt + 1, road);
            visited[nry][nrx][nby][nbx] = false;
        }
    }

    private static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'R') {
                    ry = i;
                    rx = j;
                    map[i][j] = '.';
                } else if (map[i][j] == 'B') {
                    by = i;
                    bx = j;
                    map[i][j] = '.';
                } else if (map[i][j] == 'O') {
                    hy = i;
                    hx = j;
                }
            }
        }
    }

    private static boolean isRange(int y, int x) {
        return y > 0 && y < N - 1 && x > 0 && x < M - 1 && map[y][x] != '#';
    }
}

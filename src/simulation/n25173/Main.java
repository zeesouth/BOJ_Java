package simulation.n25173;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final int[] dy = {-1, 0, 1, 0};
    static final int[] dx = {0, 1, 0, -1};
    static final int SIZE = 50;
    static int map[][] = new int[SIZE][SIZE];
    static int N, M, by, bx;
    // y, x, d, 공격력, 체력
    static int[] ari = new int[5];
    static int[] boss = new int[5];

    public static void main(String[] args) throws Exception {
        init();
        while (simulate()) ;

        System.out.println(ari[3] > 0 ? "VICTORY!" : "CAVELIFE...");
    }

    private static boolean simulate() {
        if (ariFight()) return false;
        if (ariMove()) return false;
        if (bossFight()) return false;
        bossMove();
        return true;
    }


    static boolean ariFight() {
        if ((boss[3] -= ari[4]) <= 0) return true;
        return false;
    }

    static boolean ariMove() {
        by = ari[0];
        bx = ari[1];
        for (int i = 0; i < 4; i++) {
            int d = (ari[2] + i) % 4;
            int nextY = ari[0] + dy[d];
            int nextX = ari[1] + dx[d];

            if (isRange(nextY, nextX) && map[nextY][nextX] == 0 && !(nextY == boss[0] && nextX == boss[1])) {
                ari[0] = nextY;
                ari[1] = nextX;
                ari[2] = d;
                return false;
            }

            if (--ari[3] <= 0) return true;
        }
        return false;
    }

    static boolean bossFight() {
        if (boss[4] == 1) return false;

        int L = 2;
        int cnt = N * M - 1; //N*M;
        int y = boss[0];
        int x = boss[1];
        int d = boss[2];

        loop:
        while (cnt > 0) {
            int l = L / 2;

            for (int i = 0; i < l; i++) {
                y += dy[d];
                x += dx[d];

                if (!isRange(y, x)) continue;
                if (map[y][x] == 1) {
                    break loop;
                }
                cnt--;
            }



            d = (d + 1) % 4;
            L++;
        }

        if (cnt <= 0) return false;
        else {
            if (bfs(y, x)) return true;
            else return false;
        }
    }

    private static boolean bfs(int y, int x) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{y, x, boss[4]});

        boolean visited[][][] = new boolean[N][M][boss[4] + 1];
        visited[y][x][boss[4]] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            for (int d = 0; d < 4; d++) {
                int nextY = curr[0] + dy[d];
                int nextX = curr[1] + dx[d];
                if (!isRange(nextY, nextX)) continue;
                if (visited[nextY][nextX][curr[2] - 1]) continue;
                if (map[nextY][nextX] == 1) continue;
                if (nextY == boss[0] && nextX == boss[1]) continue;
                if (nextY == ari[0] && nextX == ari[1]) {
                    if ((ari[3] -= curr[2] - 1) <= 0) return true;
                    return false;
                }

                if (curr[2] - 1 == 1) continue;

                visited[nextY][nextX][curr[2] - 1] = true;
                q.add(new int[]{nextY, nextX, curr[2] - 1});
            }
        }
        return false;
    }

    static void bossMove() {
        if (by == ari[0] && bx == ari[1]) return;
        boss[0] = by;
        boss[1] = bx;
        boss[2] = ari[2];
    }

    private static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    ari[0] = i;
                    ari[1] = j;
                    map[i][j] = 0;
                } else if (map[i][j] == 3) {
                    boss[0] = i;
                    boss[1] = j;
                    map[i][j] = 0;
                }
            }
        }

        if (boss[0] < ari[0]) ari[2] = boss[2] = 2;
        else if (boss[1] < ari[1]) ari[2] = boss[2] = 1;
        else if (boss[0] > ari[0]) ari[2] = boss[2] = 0;
        else ari[2] = boss[2] = 3;

        st = new StringTokenizer(br.readLine());

        ari[3] = Integer.parseInt(st.nextToken());
        ari[4] = Integer.parseInt(st.nextToken());
        boss[3] = Integer.parseInt(st.nextToken());
        boss[4] = Integer.parseInt(st.nextToken());
    }

    static boolean isRange(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }
}

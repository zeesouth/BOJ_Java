package simulation.n2424;

import java.io.*;
import java.util.*;

public class Main {
    static final int[] dy = {1, 0, -1, 0};
    static final int[] dx = {0, 1, 0, -1};
    static int N, M;
    static char map[][];
    static int p_map[][];
    static boolean visited[][];

    static int[] sua = new int[2], pirate = new int[2], treasure = new int[2];


    public static void main(String[] args) throws Exception {
        input();
        solve();
    }


    private static boolean moveSua() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sua[0], sua[1], 0});
        visited[sua[0]][sua[1]] = true;

        while (!q.isEmpty()) {
            int currS[] = q.poll();

            if (currS[0] == treasure[0] && currS[1] == treasure[1]) return true;

            for (int i = 0; i < 4; i++) {
                int nextY = currS[0] + dy[i];
                int nextX = currS[1] + dx[i];
                if (!isRange(nextY, nextX)) continue;
                if (visited[nextY][nextX]) continue;
                if (map[nextY][nextX] == 'I') continue;
                if (p_map[nextY][nextX] <= currS[2] + 1) continue;

                // 해적이 이동할 수 있는 최단거리보다 짧은 거리로 이동했을 경우만 이동
                visited[nextY][nextX] = true;
                q.add(new int[]{nextY, nextX, currS[2] + 1});
            }
        }

        return false;
    }

    private static void make_pMap() {
        Queue<int[]> q = new LinkedList<>();
        p_map[pirate[0]][pirate[1]] = 0;
        q.add(pirate);

        while (!q.isEmpty()) {
            int[] currP = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextY = currP[0] + dy[i];
                int nextX = currP[1] + dx[i];

                if (!isRange(nextY, nextX)) continue;
                if (map[nextY][nextX] == 'I') continue;
                if (p_map[nextY][nextX] <= p_map[currP[0]][currP[1]] + 1) continue;

                p_map[nextY][nextX] = p_map[currP[0]][currP[1]] + 1;
                q.add(new int[]{nextY, nextX});
            }
        }
    }

    private static void arrange_pMap() {
        int[][] new_pMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'I') {
                    new_pMap[i][j] = min = Integer.MAX_VALUE;
                    continue;
                }
                new_pMap[i][j] = min = Math.min(min, p_map[i][j]);
            }

            min = Integer.MAX_VALUE;
            for (int j = M - 1; j >= 0; j--) {
                if (map[i][j] == 'I') {
                    new_pMap[i][j] = min = Integer.MAX_VALUE;
                    continue;
                }
                min = Math.min(min, p_map[i][j]);
                new_pMap[i][j] = Math.min(min, new_pMap[i][j]);
            }
        }

        for (int j = 0; j < M; j++) {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                if (map[i][j] == 'I') {
                    new_pMap[i][j] = min = Integer.MAX_VALUE;
                    continue;
                }
                min = Math.min(min, p_map[i][j]);
                new_pMap[i][j] = Math.min(min, new_pMap[i][j]);
            }

            min = Integer.MAX_VALUE;
            for (int i = N - 1; i >= 0; i--) {
                if (map[i][j] == 'I') {
                    new_pMap[i][j] = min = Integer.MAX_VALUE;
                    continue;
                }
                min = Math.min(min, p_map[i][j]);
                new_pMap[i][j] = Math.min(min, new_pMap[i][j]);
            }
        }

        p_map = new_pMap;
    }

    private static boolean isRange(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }

    private static void solve() {
        make_pMap();
        arrange_pMap();
        System.out.println(moveSua() ? "YES" : "NO");
    }


    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        p_map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);

                if (map[i][j] == 'Y') {
                    sua[0] = i;
                    sua[1] = j;
                    map[i][j] = '.';
                } else if (map[i][j] == 'V') {
                    pirate[0] = i;
                    pirate[1] = j;
                    map[i][j] = '.';
                } else if (map[i][j] == 'T') {
                    treasure[0] = i;
                    treasure[1] = j;
                    map[i][j] = '.';
                }
                p_map[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

/*
.: 바다
I: 섬
Y: 수아 위치
T: 보물 위치
V: 해적 위치

- 수아는 매 턴마다 이동해야 함
- 해적은 매 턴마다 이동하거나, 그렇지 않을 수 있음

 */

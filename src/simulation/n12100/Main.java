package simulation.n12100;
// https://www.acmicpc.net/problem/12100

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final int MAX_N = 20;
    static int[][] map = new int[MAX_N][MAX_N];
    static int N, max;

    public static void main(String[] args) throws Exception {
        init();
        dfs(map, 0);
        System.out.println(max);
    }

    private static void print(int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void dfs(int[][] map, int cnt) {
        if (cnt == 5) {
            findMax(map);
            return;
        }

        for (int d = 0; d < 4; d++) {
            int[][] newMap = push(map, d);
            dfs(newMap, cnt + 1);
        }
    }

    private static int[][] push(int[][] map, int d) {
        int[][] newMap = new int[N][N];
        boolean[][] visited = new boolean[N][N];

        // 아래
        if (d == 0) {
            for (int j = 0; j < N; j++) {
                int r = N - 1;
                for (int i = N - 1; i >= 0; i--) {
                    if (map[i][j] == 0) continue;
                    if (r == N - 1) newMap[r][j] = map[i][j];
                    else {
                        if (visited[r + 1][j]) newMap[r][j] = map[i][j];
                        else {
                            if (newMap[r + 1][j] == map[i][j]) {
                                newMap[r + 1][j] *= 2;
                                visited[r + 1][j] = true;
                                r++;
                            } else newMap[r][j] = map[i][j];
                        }
                    }
                    r--;
                }
            }
        }
        // 오른쪽
        else if (d == 1) {
            for (int i = 0; i < N; i++) {
                int c = N - 1;
                for (int j = N - 1; j >= 0; j--) {
                    if (map[i][j] == 0) continue;
                    if (c == N - 1) newMap[i][c] = map[i][j];
                    else {
                        if (visited[i][c + 1]) newMap[i][c] = map[i][j];
                        else {
                            if (newMap[i][c + 1] == map[i][j]) {
                                newMap[i][c + 1] *= 2;
                                visited[i][c + 1] = true;
                                c++;
                            } else newMap[i][c] = map[i][j];
                        }
                    }
                    c--;
                }
            }
        }
        // 위
        else if (d == 2) {
            for (int j = 0; j < N; j++) {
                int r = 0;
                for (int i = 0; i < N; i++) {
                    if (map[i][j] == 0) continue;
                    if (r == 0) newMap[r][j] = map[i][j];
                    else {
                        if (visited[r - 1][j]) newMap[r][j] = map[i][j];
                        else {
                            if (newMap[r - 1][j] == map[i][j]) {
                                newMap[r - 1][j] *= 2;
                                visited[r - 1][j] = true;
                                r--;
                            } else newMap[r][j] = map[i][j];
                        }
                    }
                    r++;
                }
            }
        }
        // 왼쪽
        else {
            for (int i = 0; i < N; i++) {
                int c = 0;
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 0) continue;
                    if (c == 0) newMap[i][c] = map[i][j];
                    else {
                        if (visited[i][c - 1]) newMap[i][c] = map[i][j];
                        else {
                            if (newMap[i][c - 1] == map[i][j]) {
                                newMap[i][c - 1] *= 2;
                                visited[i][c - 1] = true;
                                c--;
                            } else newMap[i][c] = map[i][j];
                        }
                    }
                    c++;
                }
            }
        }

        return newMap;
    }

    private static void findMax(int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(max, map[i][j]);
            }
        }
    }

    private static void init() throws Exception {
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

}

package simulation.n18809;

import java.io.*;
import java.util.*;

public class Main {
    static final int[] dy = {1, 0, -1, 0};
    static final int[] dx = {0, 1, 0, -1};
    static int N, M, G, R, K, tempAns, ans;
    static int[][] map, tempMap;
    static int[] canGrow, red, green;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        init();
        dfs(0, 0, 0);
        System.out.println(ans);
    }

    private static void dfs(int rCnt, int gCnt, int start) {
        if (R + G - rCnt - gCnt > K - start) return;

        if (rCnt + gCnt == R + G) {
            tempAns = 0;
            tempMap = copy();
            visited = new boolean[N][M];
            bfs();
        }

        if (rCnt < R) {
            for (int i = start; i < K; i++) {
                red[rCnt] = canGrow[i];
                dfs(rCnt + 1, gCnt, i + 1);
            }
        }

        if (gCnt < G) {
            for (int i = start; i < K; i++) {
                green[gCnt] = canGrow[i];
                dfs(rCnt, gCnt + 1, i + 1);
            }
        }

    }

    private static void bfs() {
        Queue<int[]> q = new LinkedList<>();

        // 초록 배양액의 시작 위치 지정 & 큐에 넣기
        for (int i = 0; i < G; i++) {
            int y = green[i] / M;
            int x = green[i] % M;
            tempMap[y][x] = 3;
            visited[y][x] = true;
            q.offer(new int[]{y, x, tempMap[y][x]});
        }

        // 빨간 배양액의 시작 위치 지정 & 큐에 넣기
        for (int i = 0; i < R; i++) {
            int y = red[i] / M;
            int x = red[i] % M;
            tempMap[y][x] = 4;
            q.offer(new int[]{y, x, tempMap[y][x]});
        }

        // 배양액 확산 시뮬레이션
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[] temp = q.poll();
                int y = temp[0], x = temp[1], color = temp[2];
                if (tempMap[y][x] == 5) continue;   // 이미 꽃이 핀 장소는 SKIP
                tempMap[y][x] = color;
                visited[y][x] = true;
                q.offer(new int[]{y, x, color});
            }

            size = q.size();
            while (size-- > 0) {
                int[] temp = q.poll();
                int y = temp[0], x = temp[1], color = temp[2];
                for (int d = 0; d < 4; d++) {
                    int nextY = y + dy[d], nextX = x + dx[d];
                    if (!isRange(nextY, nextX)) continue;
                    if (visited[nextY][nextX]) continue;
                    // 호수거나 이미 배양액을 뿌리거나 이미 꽃이 핀 경우
                    if (tempMap[nextY][nextX] == 0 || tempMap[nextY][nextX] >= 3) continue;

                    // 배양액이 확산될 수 있는 칸인 경우
                    if (tempMap[nextY][nextX] == 1 || tempMap[nextY][nextX] == 2) {
                        tempMap[nextY][nextX] = -color;
                        q.offer(new int[]{nextY, nextX, color});
                    }
                    // 꽃이 피게 될 경우
                    else if (tempMap[nextY][nextX] - color == -7) {
                        tempMap[nextY][nextX] = 5;
                        tempAns++;
                    }
                }
            }
        }

        ans = Math.max(ans, tempAns);
    }

    static int[][] copy() {
        int[][] tempMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tempMap[i][j] = map[i][j];
            }
        }
        return tempMap;
    }


    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        green = new int[G];
        red = new int[R];
        canGrow = new int[N * M];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    canGrow[K++] = M * i + j;
                }
            }
        }
    }

    static boolean isRange(int y, int x) {
        return y >= 0 && y < N && x >= 0 & x < M;
    }
}

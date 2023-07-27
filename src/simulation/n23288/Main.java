package simulation.n23288;

import java.io.*;
import java.util.*;

public class Main {
    // 동 남 서 북
    private static final int[] dy = {0, 1, 0, -1};
    private static final int[] dx = {1, 0, -1, 0};
    private static final int MAX_N = 20, MAX_M = 20;
    private static int map[][] = new int[MAX_N][MAX_M];
    private static boolean visited[][];
    private static int dice[] = {0, 1, 5, 3, 4, 2, 6};  // 상 (남 동 서 북) 하
    private static int N, M, K, Y, X, D, ans;

    private static void moveDice(int type) {
        int temp = dice[1];
        switch (type) {
            case 0:     // 오른쪽
                dice[1] = dice[4];
                dice[4] = dice[6];
                dice[6] = dice[3];
                dice[3] = temp;
                break;
            case 1:     // 아래쪽
                dice[1] = dice[5];
                dice[5] = dice[6];
                dice[6] = dice[2];
                dice[2] = temp;
                break;
            case 2:     // 왼쪽
                dice[1] = dice[3];
                dice[3] = dice[6];
                dice[6] = dice[4];
                dice[4] = temp;
                break;
            case 3:     // 위쪽
                dice[1] = dice[2];
                dice[2] = dice[6];
                dice[6] = dice[5];
                dice[5] = temp;
                break;
        }
    }


    public static void main(String[] args) throws Exception {
        init();
        while (K-- > 0) {
            move();
            getScore();
            compareNumber();
        }
        System.out.println(ans);
    }

    private static void compareNumber() {
        if(dice[6] == map[Y][X]) return;

        if (dice[6] > map[Y][X]) {
            D = (D + 1) % 4;
        } else {
            D = (D - 1 + 4) % 4;
        }
    }

    private static void getScore() {
        visited = new boolean[N][M];
        dfs(Y, X, map[Y][X]);
    }

    private static void dfs(int y, int x, int target) {
        visited[y][x] = true;
        ans += target;

        for (int i = 0; i < 4; i++) {
            int nextY = y + dy[i];
            int nextX = x + dx[i];

            if (!isRange(nextY, nextX)) continue;
            if (visited[nextY][nextX]) continue;
            if (map[nextY][nextX] != target) continue;

            dfs(nextY, nextX, target);
        }
    }

    private static void move() {
        if (!isRange(Y + dy[D], X + dx[D])) D = (D + 2) % 4;

        moveDice(D);

        Y += dy[D];
        X += dx[D];
    }

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }
    }

    private static boolean isRange(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }
}

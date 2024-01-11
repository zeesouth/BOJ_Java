package dp.n17181;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final int dy[] = {1, 0, -1, 0};
    static final int dx[] = {0, 1, 0, -1};
    static final int MAX = 50, INF = Integer.MAX_VALUE;
    static int map[][] = new int[MAX][MAX];
    static int dp[][][] = new int[MAX][MAX][3];
    static int N, M;

    public static void main(String[] args) throws Exception {
        init();
        if (map[0][0] <= 13)
            dfs(0, 0, 0, 0);
        int ans = Math.min(dp[N - 1][M - 1][1], dp[N - 1][M - 1][2]);
        System.out.println(ans == INF ? "BAD" : ans);
    }

    private static void dfs(int y, int x, int type, int cnt) {
        if (dp[y][x][type] <= cnt) return;
        dp[y][x][type] = cnt;

        for (int i = 0; i < 4; i++) {
            int nextY = y + dy[i];
            int nextX = x + dx[i];
            if (!isRange(nextY, nextX)) continue;

            int currT = checkC(map[nextY][nextX]);

            if (type == 0 && currT == 1) dfs(nextY, nextX, 1, cnt + 1);
            if (type == 1 && currT == 0) {
                dfs(nextY, nextX, 2, cnt);
                dfs(nextY, nextX, 0, cnt);
            }
            if (type == 2 && currT == 0) dfs(nextY, nextX, 0, cnt);
        }
    }

    static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                for (int k = 0; k < 3; k++) dp[i][j][k] = INF;
            }
        }
    }

    static int checkC(int num) {
        if (num >= 0 && num <= 13) return 0;
        else return 1;
    }

    static boolean isRange(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }
}

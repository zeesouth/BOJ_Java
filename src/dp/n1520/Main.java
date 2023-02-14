package dp.n1520;
// https://www.acmicpc.net/problem/1520

import java.io.*;
import java.util.*;

public class Main {
    static int M, N, H;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int[][] map;
    static int[][] dp;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }
        dp = new int[M][N];
        visited = new boolean[M][N];
        dfs(0, 0);

        bw.write(dp[0][0] + "");
        bw.flush();
        br.close();
    }

    static int dfs(int y, int x) {

        visited[y][x] = true;

        if(y == M-1 && x == N-1) return dp[M-1][N-1] = 1;

        for (int i = 0; i < 4; i++) {
            int currY = y + dy[i];
            int currX = x + dx[i];

            if (isValid(currY, currX)) {
                if (map[y][x] > map[currY][currX]) {

                    if(dp[currY][currX] == 0 && !visited[currY][currX]) dp[y][x] += dfs(currY, currX);
                    else dp[y][x] += dp[currY][currX];

                }
            }
        }

        return dp[y][x];
    }

    static boolean isValid(int y, int x) {
        return y >= 0 && y < M && x >= 0 && x < N;
    }
}

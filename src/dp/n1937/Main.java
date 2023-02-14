package dp.n1937;

import java.io.*;
import java.util.*;

public class Main {
    static int N, ans = Integer.MIN_VALUE;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int[][] map;

    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<N;i++) {
            for (int j=0;j<N;j++) ans = Math.max(ans, dfs(i, j));
        }

        bw.write(ans+"");
        bw.flush();
        br.close();
    }

    static int dfs(int y, int x) {
        if(dp[x][y] == 0) {
            dp[x][y] = 1;
            for(int i=0;i<4;i++) {
                int currY = y+dy[i];
                int currX = x+dx[i];
                if(isValid(currY, currX) && map[y][x] < map[currY][currX]) {
                    dp[x][y] = Math.max(dp[x][y], dfs(currY, currX)+1);
                }
            }
        }
        return dp[x][y];
    }
    static boolean isValid(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < N;
    }
}

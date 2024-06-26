package dp.n28218;
// https://koo209.tistory.com/27 참고..

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static char[][] map;
    static int N, M, K, y, x;
    static int dp[][];

    public static void main(String[] args) throws Exception {
        init();

        int T = Integer.parseInt(br.readLine()), res;
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            y = Integer.parseInt(st.nextToken()) - 1;
            x = Integer.parseInt(st.nextToken()) - 1;

            res = move(y, x);
            if (res == 1) sb.append("First\n");
            else sb.append("Second\n");
        }

        System.out.println(sb);
    }

    private static int move(int y, int x) {
        if (dp[y][x] != -1) return dp[y][x];

        if (isRange(y + 1, x) && map[y + 1][x] == '.')
            if (move(y + 1, x) == 0) return dp[y][x] = 1;
        if (isRange(y, x + 1) && map[y][x + 1] == '.')
            if (move(y, x + 1) == 0) return dp[y][x] = 1;

        for (int i = 1; i <= K; i++) {
            if (isRange(y + i, x + i) && map[y + i][x + i] == '.')
                if (move(y + i, x + i) == 0) return dp[y][x] = 1;
        }

        return 0;   // 아무리 해도 첫 번째가 이길 수 없다면 두 번째가 이김
    }

    private static boolean isRange(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }

    private static void init() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }
        dp[N - 1][M - 1] = 0;
    }
}

/*
#: 막힌 칸
.: 열린 칸
한국, 정올 중 한국 먼저 시작
 */
package greedy.n1576;

import java.io.*;
import java.util.*;

public class Main {
    static int map[][], dp[][][];
    static final int INF = 987654321;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new int[4][4];
        String str[] = new String[N];

        for (int i = 0; i < N; i++) str[i] = br.readLine();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                for (int k = 0; k < str[i].length(); k++) {
                    int x = charToInt(str[i].charAt(k));
                    int y = charToInt(str[j].charAt(k));
                    if (x > y) {
                        int tmp = x;
                        x = y;
                        y = tmp;
                    }
                    map[x][y]++;
                }
            }
        }

        dp = new int[4][4][10 * 16 * 2 + 10];
        for(int i=0;i<4;i++) {
            for(int j=0;j<4;j++) Arrays.fill(dp[i][j], -1);
        }
        int res = play(3, 3, 160);
        double ans = (double) res / (N*(N-1)/2);
        System.out.printf("%.2f", ans);
        br.close();
    }

    static int play(int x, int y, int s) {
        if (x == 3 && y == -1) {
            if (s == 160) return 0;
            else return -INF;
        }

        if (x < y || x < 0 || y < 0) return -INF;
        if (s > 10 * 16 * 2 + 5 || s < 0) return -INF;

        if (dp[x][y][s] != -1) return dp[x][y][s];
        dp[x][y][s] = -INF;

        int tx = 0, ty = 0;
        if (x == y) {
            tx = 3;
            ty = y - 1;
        } else {
            tx = x - 1;
            ty = y;
        }

        if (x == y) {
            for (int i = 1; i <= 10; i++) dp[x][y][s] = Math.max(dp[x][y][s], play(tx, ty, s - i) + map[y][x] * i);
        } else {
            for (int i = -10; i <= 10; i++) dp[x][y][s] = Math.max(dp[x][y][s], play(tx, ty, s - 2 * i) + map[y][x] * i);
        }

        return dp[x][y][s];
    }

    static int charToInt(char c) {
        switch (c) {
            case 'A':
                return 0;
            case 'C':
                return 1;
            case 'G':
                return 2;
        }
        return 3;
    }
}
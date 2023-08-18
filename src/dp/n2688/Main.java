package dp.n2688;

import java.io.*;

public class Main {
    static int T;
    static long dp[][] = new long[10 + 64][64 + 1];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        init();
        test();
        System.out.println(sb);
    }

    static void test() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[10 + n - 1][n]).append("\n");
        }
    }

    static void init() {
        int a = 64 + 10 - 1;
        int b = 64;

        for (int i = 1; i <= a; i++) dp[i][0] = 1;
        for (int i = 1; i <= a; i++) dp[i][1] = i;

        for (int i = 2; i <= a; i++) {
            for (int j = 2; j <= b; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
    }
}

/*
10C3
10 3 4

12 11
2 1


 */
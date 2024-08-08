package string.n27980;

import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int N, M, dp[][], ans;
    static String s1, s2;

    public static void main(String[] args) throws Exception {
        N = sc.nextInt();
        M = sc.nextInt();

        s1 = sc.next();
        s2 = sc.next();

        dp = new int[N][M];
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1);

        // start
        for (int i = 0; i < N; i++) {
            ans = Math.max(dfs(i, 0), ans);
        }


        System.out.println(ans);
    }

    private static int dfs(int idx1, int idx2) {
        if (idx2 == M) return 0;

        if (dp[idx1][idx2] != -1) return dp[idx1][idx2];

        int point = 0;

        if (idx1 > 0) {
            point = dfs(idx1 - 1, idx2 + 1);
        }

        if (idx1 < N - 1) {
            point = Math.max(point, dfs(idx1 + 1, idx2 + 1));
        }

        if (s1.charAt(idx1) == s2.charAt(idx2)) ++point;

        return dp[idx1][idx2] = point;
    }
}

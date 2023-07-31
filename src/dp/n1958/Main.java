package dp.n1958;
// https://www.acmicpc.net/problem/1958

import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        String str[] = { sc.nextLine(), sc.nextLine(), sc.nextLine() };

        int A = str[0].length();
        int B = str[1].length();
        int C = str[2].length();

        int[][][] dp = new int[A + 1][B + 1][C + 1];
        for (int i = 1; i <= A; i++) {
            for (int j = 1; j <= B; j++) {
                for (int k = 1; k <= C; k++) {
                    if (str[0].charAt(i - 1) == str[1].charAt(j - 1) && str[1].charAt(j - 1) == str[2].charAt(k - 1))
                        dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;
                    else {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], Math.max(dp[i][j - 1][k], dp[i][j][k - 1]));
                    }
                }
            }
        }
        System.out.println(dp[A][B][C]);
    }
}

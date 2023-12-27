package dp.n2091;
// https://www.acmicpc.net/problem/2091

import java.util.*;

public class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);

        int X = sc.nextInt();
        int[] sum = new int[X + 1];
        int[] cnt = new int[X + 1];
        int[][] coin = new int[4][2];
        for (int i = 0; i < 4; i++) coin[i][1] = sc.nextInt();

        coin[0][0] = 1;
        coin[1][0] = 5;
        coin[2][0] = 10;
        coin[3][0] = 25;

        int[][] dp = new int[4][X + 1];
        for (int i = 1; i <= X; i++) {
            for (int j = 0; j < 4; j++) {
                if (i < coin[j][0]) break;
                int id = i - coin[j][0];

                int check = dp[j][id] + 1;
                if (check > coin[j][1]) continue;

                int total = sum[id] + coin[j][0];
                if (total != i) continue;

                int count = cnt[id] + 1;

                if (cnt[i] >= count) continue;

                cnt[i] = count;
                sum[i] = total;

                for (int k = 0; k < 4; k++) {
                    if (k == j) dp[k][i] = check;
                    else dp[k][i] = dp[k][id];
                }
            }
        }

        sb.append(dp[0][X]).append(' ').append(dp[1][X]).append(' ').append(dp[2][X]).append(' ').append(dp[3][X]);
        System.out.println(sb);
    }
}

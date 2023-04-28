package dp.n2240;
// https://www.acmicpc.net/problem/2240

import java.io.*;
import java.util.*;

public class Main {
    static int T, W, dp[][][], ans = 0;
    static int tree[];  // 1번, 2번
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        tree = new int[T + 1];
        for (int i = 1; i <= T; i++) tree[i] = Integer.parseInt(br.readLine());
        dp = new int[W + 1][T + 1][3]; // 이동횟수, time, 위치(A, B)

        for (int i = 0; i <= W; i++) {
            for (int j = 1; j <= T; j++) {
                int pos = tree[j];
                // 이동 X
                if(i >= 0) {
                    if(pos == 1)
                        dp[i][j][1] = Math.max(dp[i][j - 1][1] + 1, dp[i][j][1]);
                    else if(pos == 2 && i != 0)
                        dp[i][j][2] = Math.max(dp[i][j - 1][2] + 1, dp[i][j][2]);
                }

                // 이동 O
                if(i >= 1) {
                    if(pos == 1 && j != 1)
                        dp[i][j][1] = Math.max(dp[i - 1][j - 1][2] + 1, dp[i][j][1]);
                    else if(pos == 2)
                        dp[i][j][2] = Math.max(dp[i - 1][j - 1][1] + 1, dp[i][j][2]);
                }

                if(pos == 1) dp[i][j][2] = Math.max(dp[i][j-1][2], dp[i][j][2]);
                else dp[i][j][1] = Math.max(dp[i][j-1][1], dp[i][j][1]);
            }
        }

        for (int i = 0; i <= W; i++) ans = Math.max(Math.max(ans, dp[i][T][1]), dp[i][T][2]);

        System.out.println(ans);
        br.close();
    }
}

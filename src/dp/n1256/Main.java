package dp.n1256;

import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();

        int[][] dp = new int[N+1][M+1];

        for(int i=0;i<=N;i++) dp[i][0] = 1;
        for(int j=0;j<=M;j++) dp[0][j] = 1;

        for(int i=1;i<=N;i++) {
            for(int j=1;j<=M;j++) {
                dp[i][j] = dp[i-1][j]+dp[i][j-1];
                if(dp[i][j] > 1_000_000_000) dp[i][j] = 1_000_000_000;
            }
        }

        StringBuilder sb = new StringBuilder();
        if(dp[N][M] < K) sb.append("-1");
        else {
            int a_cnt = N;
            int z_cnt = M;
            for(int i=0;i<N+M;i++) {

                if (a_cnt == 0) {
                    sb.append("z");
                    z_cnt--;
                    continue;
                }
                else if (z_cnt == 0) {
                    sb.append("a");
                    a_cnt--;
                    continue;
                }

                int as = dp[a_cnt - 1][z_cnt];

                if (K <= as) {
                    sb.append("a");
                    a_cnt--;
                }
                else {
                    K -= as;
                    sb.append("z");
                    z_cnt--;
                }
            }
        }
        System.out.println(sb);
    }
}

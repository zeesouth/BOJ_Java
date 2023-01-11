package dp.n1562;

import java.io.*;

public class Main {
    static long ans;
    static int N;
    static long[][][] dp;
    static final int MOD = 1000000000;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        ans = 0;
        N = Integer.parseInt(br.readLine());
        dp = new long[N+1][10][1<<10];

        for(int k=1;k<=9;k++) dp[1][k][1<<k] = 1;

        for(int n=2;n<=N;n++) {
            for(int k=0;k<=9;k++) {
                for(int v = 0; v < (1<<10); v++) {
                    int currV = v | (1<<k);
                    if(k == 0) dp[n][k][currV] += dp[n-1][k+1][v]%MOD;
                    else if(k==9) dp[n][k][currV] += dp[n-1][k-1][v]%MOD;
                    else dp[n][k][currV] += (dp[n-1][k-1][v]%MOD+dp[n-1][k+1][v]%MOD);

                    dp[n][k][currV] %= MOD;
                }
            }
        }

        for(int k=0;k<=9;k++) {
            ans += dp[N][k][(1<<10)-1]%MOD;
            ans %= MOD;
        }
        bw.write(ans+"\n");
        bw.flush();
        br.close();
    }
}

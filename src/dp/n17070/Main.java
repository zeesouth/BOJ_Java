package dp.n17070;
// https://www.acmicpc.net/problem/17070

import java.io.*;
import java.util.*;

public class Main {
    static int N, map[][], dp[][][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N][3];
        StringTokenizer st;
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        dp[0][1][0] = 1;
        System.out.println(dp());
        br.close();
    }

    static int dp() {
        for(int i=0;i<N;i++) {
            for(int j=1;j<N;j++) {
                if(map[i][j] == 1) continue;
                dp[i][j][0] += dp[i][j-1][0] + dp[i][j-1][1];
                if(i==0) continue;
                dp[i][j][2] += dp[i-1][j][2] + dp[i-1][j][1];
                if(map[i-1][j] == 1 || map[i][j-1] == 1) continue;
                dp[i][j][1] += dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
            }
        }
        return dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2];
    }
}
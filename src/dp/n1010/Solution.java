package dp.n1010;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static int N, M;
    static int[][] dp;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("res/n1010.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            dp = new int[M+1][N+1];

            for (int i = 0; i < M + 1; i++) {
                for(int j = 0; j <= i && j != N + 1; j++) {
                    if(i == j || j == 0) dp[i][j] = 1 ;
                    else dp[i][j] = dp[i-1][j-1] + dp[i-1][j] ;
                }
            }
            System.out.println(dp[M][N]);

        }
    }

    static boolean isValid(int y, int x) {
        return y >=0 && y < M && x >= 0 && x < N;
    }
}
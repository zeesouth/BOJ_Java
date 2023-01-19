package dp.n7579;

import java.io.*;
import java.util.*;

public class Main {
    static int N, necessaryM;
    static int[] M, C;  // 활성화 되어있는 앱의 바이트 수, 재실행 하려면 필요한 바이트 수

    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        necessaryM = Integer.parseInt(st.nextToken());
        M = new int[N+1];
        C = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) M[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) C[i] = Integer.parseInt(st.nextToken());
        br.close();

        dp = new int[N+1][necessaryM+1];
        for(int i=1;i<=N;i++) {
            for(int j=1;j<=necessaryM;j++) {
                if(M[i] <= j) dp[i][j] += C[i];
            }
        }
        bw.write(dp[N][N]+"");
        bw.flush();
    }
}
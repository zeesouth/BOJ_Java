package dp.n7579;

import java.io.*;
import java.util.*;

public class Main {
    static boolean flag;
    static int N, necessaryM, mSum, cSum, ans;
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
        mSum = cSum = 0;
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) {
            M[i] = Integer.parseInt(st.nextToken());
            mSum += M[i];
        }
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) {
            C[i] = Integer.parseInt(st.nextToken());
            cSum += C[i];
        }

        ans = Integer.MAX_VALUE;
        dp = new int[N+1][cSum+1];

        for(int c=1;c<=cSum;c++) {
            for(int n=1;n<=N;n++) {
                dp[n][c] = dp[n-1][c];
                if(C[n]<=c) dp[n][c] = Math.max(dp[n-1][c], dp[n-1][c-C[n]]+M[n]);
                if(dp[n][c] >= necessaryM) {
                    ans = c; break;
                }
            }
            if(ans != Integer.MAX_VALUE) break;
        }
        br.close();
        bw.write(ans+"");
        bw.flush();
    }
}
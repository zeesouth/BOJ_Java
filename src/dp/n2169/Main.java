package dp.n2169;
// https://www.acmicpc.net/problem/2169

import java.io.*;
import java.util.*;

public class Main {
    static int i, j, N, M, map[][], tmp[][], dp[][];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        tmp = new int[2][M+2];
        dp = new int[N+1][M+1];  // 왼, 오, 아래
        for(i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for(j=1;j<=M;j++) map[i][j] = Integer.parseInt(st.nextToken());
        }
        dp[1][1] = map[1][1];

        // 1,1부터 시작한다면 오른쪽 방향으로만 이동 가능
        for(i=2;i<=M;i++) dp[1][i] = dp[1][i-1]+map[1][i];

        for(i=2;i<=N;i++) {
            // (1) 아래이동 후 -> 오른쪽 쭉 이동
            tmp[0][0] = dp[i-1][1];
            for (j = 1; j <= M; j++)
                tmp[0][j] = Math.max(tmp[0][j - 1], dp[i - 1][j]) + map[i][j];

            // (2) 아래이동 후 -> 왼쪽 쭉 이동
            tmp[1][M + 1] = dp[i - 1][M];
            for (j = M; j >= 1; j--)
                tmp[1][j] = Math.max(tmp[1][j + 1], dp[i - 1][j]) + map[i][j];

            // (1), (2) 비교 후 저장
            for(j=1;j<=M;j++)
                dp[i][j] = Math.max(tmp[0][j], tmp[1][j]);
        }
        System.out.println(dp[N][M]);
    }
}

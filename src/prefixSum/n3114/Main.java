package prefixSum.n3114;

import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int map[][][] = new int[R+1][C+1][2];
        for (int i = 1; i <= R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= C; j++) {
                String data = st.nextToken();
                int f = data.charAt(0)-'A';
                int n = Integer.parseInt(data.substring(1, data.length()));
                map[i][j][f] = n;
            }
        }

        // 2차원 누적합 계산 (세로 기준)
        int sum[][][] = new int[R+1][C+1][2];
        for(int j=1;j<=C;j++) {
            for(int i=1;i<=R;i++) {
                for(int k=0;k<2;k++) sum[i][j][k] = map[i][j][k] + sum[i-1][j][k];
            }
        }


        // i,j까지 위치의 최대 과일 개수
        int dp[][] = new int[R+1][C+1];

        // 1행, 1열 전처리
        // 이유 : 1행 C열, R행 1열로 시작하는 경우 오답

        // 전처리 (1행 초기화)
        for (int j = 1; j <= C; j++) dp[1][j] = dp[1][j-1] + (sum[R][j][0]-sum[1][j][0]);

        // 전처리 (1열 초기화)
        for (int i = 1; i <= R; i++) dp[i][1] = sum[R][1][0]-sum[i][1][0];

        for(int i=2;i<=R;i++) {
            for(int j=2;j<=C;j++) {
                int a = dp[i-1][j-1] + sum[i-1][j][1] + (sum[R][j][0]-sum[i][j][0]);
                int b = dp[i][j-1] + sum[i-1][j][1] + (sum[R][j][0]-sum[i][j][0]);
                int c = dp[i-1][j] - (sum[i][j][0]-sum[i-1][j][0]);
                dp[i][j] = Math.max(Math.max(a, b), c);
            }
        }

        System.out.println(dp[R][C]);
        br.close();
    }
}
/*
불도저가 지나간 길의
아래쪽에 있는 사과나무의 개수와
위쪽에 있는 바나나 나무 개수의 합을 크게 만드려고 한다.

dp[i][j - 1] + apple[R][j] - apple[R][j - 1] - apple[i][j] + apple[i][j - 1] + banana[i - 1][j] - banana[i - 1][j - 1]);

dp[i - 1][j] - (apple[i][j] - apple[i][j - 1] - apple[i - 1][j] + apple[i - 1][j - 1]));

dp[i - 1][j - 1] + apple[R][j] - apple[R][j - 1] - apple[i][j] + apple[i][j - 1] + banana[i - 1][j] - banana[i - 1][j - 1]);

 */

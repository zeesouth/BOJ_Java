package prefixSum.n2616;

import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int sum[] = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int data = Integer.parseInt(st.nextToken());
            sum[i] = data + sum[i - 1];
        }

        int MAX = Integer.parseInt(br.readLine());

        // i번째 소형 기관차가 객차 j번까지 보았을 때, 최대로 운송할 수 있는 승객 수
        int dp[][] = new int[3+1][N+1];

        for (int i = 1; i <= 3; i++) {
            // 기관차가 i대일 때는 최소 MAX*i대의 객차가 필요
            for (int j = i * MAX; j <= N; j++) {
                // 1.dp[i][j-1] : j번째 객차를 선택하지 않았을 경우
                // 2. dp[i-1][j-MAX] : j번째 객차를 선택했을 경우
                //    -> i-1번째 소형기관차가 객차 j-MAX번째 까지 보았을 때 최대 승객 수 + 객차 j-MAX ~ j까지의 승객 합
                dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-MAX] + (sum[j]-sum[j-MAX]));
            }
        }
        System.out.println(dp[3][N]);
        br.close();
    }
}

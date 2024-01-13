package dp.n13398;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        int arr[] = new int[N + 1];
        int dp[][] = new int[N + 1][2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[1][0] = dp[1][1] = arr[1];
        for (int i = 2; i <= N; i++) {
            // 숫자 제거 없이 i번째 수까지 더했을 때 가장 큰 합
            dp[i][0] = Math.max(dp[i - 1][0] + arr[i], arr[i]);

            // 하나의 숫자를 제거한 후, i번째 수까지 더했을 때 가장 큰 합
            // arr[i-1] 제거, 혹은 1~i-2중 제거
            dp[i][1] = Math.max(dp[i - 2][0] + arr[i], dp[i - 1][1] + arr[i]);
        }

        int ans = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            ans = Math.max(ans, Math.max(dp[i][0], dp[i][1]));
        }

        System.out.println(ans);
    }
}

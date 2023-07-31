package dp.n2631;
// https://www.acmicpc.net/problem/2631

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int arr[] = new int[N];

        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(br.readLine());

        int max = 0;
        int dp[] = new int[N + 1];
        // LIS (최장 부분 증가 수열) 구하기
        for (int i = 1; i <= N; i++) {
            dp[i] = 1;
            for (int j = 1; j < i; j++) {
                if (arr[j - 1] < arr[i - 1] && dp[i] < dp[j] + 1) dp[i]++;
            }
            if (max < dp[i]) max = Math.max(dp[i], max);
        }
        System.out.println(N - max);
        br.close();
    }
}
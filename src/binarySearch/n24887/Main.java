package binarySearch.n24887;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final int MAX = 200000;
    static int arr[] = new int[MAX + 1];
    static int N, M;

    public static void main(String[] args) throws Exception {
        if (init()) binarySearch();
    }

    private static void binarySearch() {
        // 이분 탐색의 기준: 연속해서 쉴 수 있는 최솟값의 최댓값,
        int left = 1, right = N - 1;
        int ans = 0;

        long[] dp = new long[N + 1];

        while (left <= right) {
            int mid = (left + right) / 2;

            // dp[i]: 간격의 최솟값이 mid일 때, i일까지 얻을 수 있는 최대 할당량

            // dp[i-1]: 당일에 일을 하지 않는 경우, 지금까지의 최댓값을 이어받음
            // dp[i-mid] + arr[i]: 당일에 일을 하는 경우, mid일 전까지의 최댓값을 이어받음

            for (int i = 1; i <= mid; i++) {
                dp[i] = Math.max(dp[i - 1], arr[i]);
            }

            for (int i = mid + 1; i <= N; i++) {
                dp[i] = Math.max(dp[i - 1], arr[i] + dp[i - mid]);
            }

            if (dp[N] >= M) {
                left = mid + 1;
                ans = Math.max(ans, mid);
            } else {
                right = mid - 1;
            }
        }

        System.out.println(ans - 1);
    }

    static boolean init() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        long sum = 0;
        for (int i = 1; i <= N; i++) {
            sum += (arr[i] = Integer.parseInt(st.nextToken()));
            if (arr[i] >= M) {
                System.out.println("Free!");
                return false;
            }
        }

        if (sum < M) {
            System.out.println(-1);
            return false;
        }

        return true;
    }
}

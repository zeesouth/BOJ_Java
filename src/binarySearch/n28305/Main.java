package binarySearch.n28305;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final int MAX = 200000;
    static int N, T;
    static int arr[] = new int[MAX + 1], dp[] = new int[MAX + 1];

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(bst());
    }

    private static int bst() {
        // 기준: 동시에 진행되는 최대 세미나의 개수
        int left = 1;
        int right = N;

        int mid = (left + right) / 2;

        while (left < right) {
            if (cal(mid)) right = mid - 1;
            else left = mid + 1;

            mid = (left + right) / 2;
        }

        if (!cal(mid)) mid++;

        return mid;
    }

    // num값으로 세미나를 배치할 수 있는지 확인
    private static boolean cal(int num) {
        if (num == 0) return false;

        for (int i = 0; i < N; i++) dp[i] = arr[i];

        for (int i = 0; i < N; i++) {
            if (i < num) {
                if (dp[i] >= T) dp[i] = arr[i] + 1;
                else dp[i] = T + 1;
            } else {
                if (dp[i - num] > arr[i]) return false;

                if (dp[i - num] + T <= arr[i] + 1) dp[i] = arr[i] + 1;
                else dp[i] = dp[i - num] + T;
            }
        }

        return true;
    }

    private static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
    }
}

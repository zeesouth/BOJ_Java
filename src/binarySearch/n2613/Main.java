package binarySearch.n2613;
// https://www.acmicpc.net/problem/2613

import java.io.*;
import java.util.*;

public class Main {
    private static int N, M, arr[];
    private static int left, right;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        init();
        binarySearch();
        countNumberOfGroup();
        System.out.println(sb.toString());
    }

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        left = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            left = Math.max(left, arr[i]);
            right += arr[i];
        }
        br.close();
    }

    private static void countNumberOfGroup() {
        int cnt = 0;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += arr[i];
            if (sum > left) {
                M--;
                sum = arr[i];
                sb.append(cnt).append(" ");
                cnt = 1;
            } else cnt++;

            if (M == N - i) break;
        }

        while(M-- > 0) {
            sb.append(cnt).append(" ");
            cnt = 1;
        }
    }


    private static void binarySearch() {
        while (left <= right) {
            int mid = (left + right) / 2;
            int cnt = possible(mid);
            if (cnt > M) left = mid + 1;
            else right = mid - 1;
        }
        sb.append(left).append("\n");
    }

    private static int possible(int mid) {
        int sum = 0;
        int cnt = 1;
        for (int i = 0; i < N; i++) {
            sum += arr[i];
            if (sum > mid) {
                cnt++;
                sum = arr[i];
            }
        }
        return cnt;
    }
}

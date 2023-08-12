package binarySearch.n14002;

import java.io.*;
import java.util.*;

public class Main {
    static int N, arr[], LIS[], trace[], ans[], cnt = 1, a;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        init();
        getLIS();
        printLIS();
    }

    private static void printLIS() {
        sb.append(cnt).append("\n");
        for (int i = 0; i < cnt; i++) {
            sb.append(ans[i]).append(" ");
        }
        System.out.println(sb);
    }

    private static void getLIS() {
        LIS = new int[N];
        trace = new int[N];

        LIS[0] = arr[0];

        int t = 0;
        trace[t++] = 0;
        for (int i = 1; i < N; i++) {
            if (LIS[cnt - 1] < arr[i]) {
                LIS[cnt] = arr[i];
                trace[t++] = cnt++;
            } else {
                int idx = binarySearch(arr[i], 0, cnt);
                LIS[idx] = arr[i];
                trace[t++] = idx;
            }
        }

        a = cnt - 1;
        ans = new int[cnt];
        int tmp = cnt - 1;
        for (int i = t - 1; i >= 0; i--) {
            if (trace[i] == tmp) {
                tmp--;
                ans[a--] = arr[i];
            }
        }
    }

    private static int binarySearch(int val, int s, int e) {
        while (s < e) {
            int mid = s + (e - s) / 2;
            if (val <= LIS[mid]) e = mid;
            else s = mid + 1;
        }
        return s;
    }


    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        br.close();
    }
}
/*
5
2 3 2 4 1
 */
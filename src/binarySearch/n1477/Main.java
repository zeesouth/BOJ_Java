package binarySearch.n1477;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M, L, arr[];

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(binarySearch(1, L - 1));
    }

    private static int binarySearch(int left, int right) {
        while (left <= right) {
            int mid = (left + right) / 2;
            int cnt = 0;
            for (int i = 1; i < arr.length; i++) {
                int dist = arr[i] - arr[i - 1];
                cnt += dist / mid;
                if (dist % mid == 0) cnt--;
            }

            if (cnt > M) left = mid + 1;
            else right = mid - 1;           // 거리의 최솟값이므로 cnt = M인 경우도 범위를 계속해서 줄여줌
        }
        return left;
    }

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        arr = new int[N + 2];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) arr[i] = Integer.parseInt(st.nextToken());
        arr[N + 1] = L;

        Arrays.sort(arr);
    }
}
/*
탐색의 기준 : 휴게소 M개 사이의 거리를 distance로 했을 때, 몇 개의 휴게소를 세울 수 있는지


 */

package binarySearch.n3079;

import java.io.*;
import java.util.*;

public class Main {
    private static int N, M, min = 1_000_000_000, time[];

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(binarySearch(min, (long) min * M));
    }


    private static long binarySearch(long left, long right) {
        while (left <= right) {
            long mid = (left + right) / 2;
            long cnt = 0;
            for (int i = 0; i < time.length; i++) cnt += (mid / time[i]);
            if (cnt >= M) right = mid - 1;
            else left = mid + 1;
        }
        return left;
    }

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        time = new int[N];
        for (int i = 0; i < N; i++) min = Math.min(time[i] = Integer.parseInt(br.readLine()), min);

        br.close();
    }
}

/*
시간동안 심사를 받을 수 있는 인원

 */

/*
2 * 3
6 * 1

2 * 3
6 * 1

 */

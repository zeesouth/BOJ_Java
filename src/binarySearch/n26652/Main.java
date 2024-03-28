package binarySearch.n26652;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static long[] L, A, maxL;
    static int N;
    static long M, maxi;

    public static void main(String[] args) throws Exception {
        init();
        solve();
    }

    private static void solve() {
        long left, right, mid, tmp, ans;

        // 각 성향마다 몇 레벨까지 올릴 수 있는지 계산하기
        for (int i = 0; i < N; i++) {
            left = L[i];
            right = 3000000000L;
            ans = 0;

            while (left <= right) {
                mid = (left + right) / 2;
                tmp = (mid - 1) * mid / 2 - (L[i] - 1) * L[i] / 2;

                if (tmp <= A[i]) {
                    ans = Math.max(mid, ans);
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            maxL[i] = ans;
        }


        // 정 N각형을 만들 수 있는 최대 레벨이 무엇인지
        left = maxi;
        right = (long) 10e+12;
        ans = 0;

        long sum;
        while (left <= right) {
            mid = (left + right) / 2;
            sum = 0;

            for (int i = 0; i < N; i++) {
                sum += Math.max(0, mid - maxL[i]);
            }

            if (sum <= M) {
                ans = Math.max(mid, ans);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(ans == 0 ? -1 : ans);
    }


    private static void init() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());

        L = new long[N];
        A = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            maxi = Math.max(L[i] = Long.parseLong(st.nextToken()), maxi);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Long.parseLong(st.nextToken());
        }

        maxL = new long[N];
    }
}

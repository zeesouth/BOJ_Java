package kmp.n7575;
// https://blog.naver.com/adamdoha/222087359842
// https://blog.naver.com/dldndud61/222245538047

import java.io.*;
import java.util.*;
public class Main {
    static int N, K, arr[][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N][];

        for (int i = 0; i < N; i++) {
            int M = Integer.parseInt(br.readLine());
            arr[i] = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i <= arr[0].length - K; i++) {
            if (kmp(i)) {
                System.out.println("YES");
                return;
            }
        }
        System.out.println("NO");
        br.close();
    }

    static boolean kmp(int st) {
        int[] pattern = new int[K];
        int[] reversePattern = new int[K];

        for (int i = st; i < st + K; i++) {
            pattern[i - st] = arr[0][i];
            reversePattern[K - 1 - (i - st)] = pattern[i - st];
        }

        int[] pi = makePi(pattern);
        int[] rpi = makePi(reversePattern);

        for (int i = 1; i < N; i++) {
            boolean res = kmpTest(i, pattern, pi);  // 정방향
            if (!res) {
                res = kmpTest(i, reversePattern, rpi);  // 역방향
                if (!res) return false;  // 둘다 없다면 false
            }
        }
        return true;
    }

    static int[] makePi(int[] pattern) {
        int[] pi = new int[K];
        for (int i = 1, j = 0; i < K; i++) {
            while (j > 0 && pattern[i] != pattern[j]) j = pi[j - 1];
            if (pattern[i] == pattern[j]) pi[i] = ++j;
        }
        return pi;
    }

    static boolean kmpTest(int i, int[] pattern, int[] pi) {
        int[] src = arr[i];
        for (int idx = 0, j = 0; idx < src.length; idx++) {
            while (j > 0 && src[idx] != pattern[j]) j = pi[j - 1];
            if (src[idx] == pattern[j]) {
                if (j == K - 1) return true;
                else ++j;
            }
        }
        return false;
    }
}

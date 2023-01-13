package binarySearch.n1450;
// https://www.acmicpc.net/problem/1450

import java.util.*;
import java.io.*;

public class Main {
    static int N, C, ans, idx, aSize, bSize;
    static int[] wt, aSum, bSum;

    static void calcLeft(int i, int sum) {
        if (sum > C) return;
        if (i == N / 2) {
            aSum[aSize++] = sum;
            return;
        }
        calcLeft(i + 1, sum + wt[i]);
        calcLeft(i + 1, sum);
    }

    static void calcRight(int i, int sum) {
        if (sum > C) return;
        if (i == N) {
            bSum[bSize++] = sum;
            return;
        }
        calcRight(i + 1, sum + wt[i]);
        calcRight(i + 1, sum);
    }

    static void binarySearch(int st, int ed, long val) {
        if (st > ed) return;
        int mid = (st + ed) / 2;

        if (bSum[mid] + val <= C) {
            idx = mid;
            binarySearch(mid + 1, ed, val);
        }
        else {
            binarySearch(st, mid - 1, val);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        wt = new int[N];
        aSum = new int[33000];
        bSum = new int[33000];
        for(int i=0;i<N;i++) wt[i] = Integer.parseInt(st.nextToken());

        aSize = bSize = 0;
        calcLeft(0, 0);
        calcRight(N/2, 0);
        Arrays.sort(bSum, 0, bSize);

        ans = 0;
        for (int i = 0; i < aSize; i++) {
            idx = -1;
            binarySearch(0, bSize - 1, aSum[i]);
            ans += (idx + 1);
        }

        bw.write(ans+"\n");
        bw.flush();
        br.close();
    }
}
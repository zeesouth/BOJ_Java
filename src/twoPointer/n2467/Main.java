package twoPointer.n2467;
// https://www.acmicpc.net/problem/2467

import java.io.*;
import java.util.*;

public class Main {
    static int N, left, right;
    static long[] pv;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        pv = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            pv[i] = Integer.parseInt(st.nextToken());
        }

        left = 0;
        right = N-1;
        int minL = 0, minR = 0;
        long min = Long.MAX_VALUE;
        while(left < right) {
            long sum = pv[left]+pv[right];
            if(min > Math.abs(sum)) {
                min = Math.abs(sum);
                minL = left; minR = right;
            }
            if(sum >= 0) right--;
            else left++;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(pv[minL]).append(" ").append(pv[minR]);
        System.out.println(sb);
    }
}
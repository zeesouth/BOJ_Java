package binarySearch.n10816;
// https://www.acmicpc.net/problem/10816
// 숫자 카드 2 - 이분 탐색
// counting 방식

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] have;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        have = new int[N];
        for(int i=0;i<N;i++) have[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(have);

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++) {
            int val = Integer.parseInt(st.nextToken());
            sb.append(upperBound(val)-lowerBound(val)).append(" ");
        }
        System.out.println(sb);
    }

    static int lowerBound(int val) {
        int start = 0;
        int end = N;

        while(start < end) {
            int mid = (start+end)/2;
            if(val <= have[mid]) end = mid;
            else start = mid+1;
        }
        return start;
    }
    static int upperBound(int val){
        int start = 0;
        int end = N;

        while(start < end) {
            int mid = (start+end)/2;
            if(val < have[mid]) end = mid;
            else start = mid+1;
        }
        return start;
    }
}

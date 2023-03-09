package binarySearch.n10815;
// https://www.acmicpc.net/problem/10815
// 숫자 카드 - 이분 탐색

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
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        Arrays.sort(have);
        for(int i=0;i<M;i++)
            sb.append(binarySearch(Integer.parseInt(st.nextToken()))).append(" ");
        System.out.println(sb);
    }

    static int binarySearch(int val) {
        int start = 0;
        int end = N-1;

        while(start <= end) {
            int mid = (start+end)/2;
            if(have[mid] == val) return 1;

            if(have[mid] < val) start = mid+1;
            else end = mid-1;
        }
        return 0;
    }
}

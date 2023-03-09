package binarySearch.n2805;
// https://www.acmicpc.net/problem/2805

import java.io.*;
import java.util.*;

public class Main {
    static int N;

    static long M, max = 0;
    static long[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());
        arr = new long[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) max = Math.max(arr[i] = Long.parseLong(st.nextToken()), max);
        System.out.println(binarySearch());
    }
    static long binarySearch() {
        long min = 0;
        long mid = -1;

        while(min < max) {
            mid = (min+max)/2;
            long tree = 0;
            for(int i=0;i<N;i++) if(arr[i] > mid) tree+=(arr[i]-mid);

            if(tree < M) max = mid;
            else min = mid+1;
        }
        return min-1;
    }
}

/*
* 2700000000
* 2000000000
* */
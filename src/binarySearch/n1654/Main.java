package binarySearch.n1654;
// https://www.acmicpc.net/problem/1654

import java.io.*;
import java.util.*;

public class Main {

    static int K, N;
    static long max = 0;
    static int[] arr;

    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr= new int[K];
        for(int i=0;i<K;i++) max = Math.max(arr[i] = Integer.parseInt(br.readLine()), max);
        max++;
        System.out.println(binarySearch());
    }

    static long binarySearch() {
        long min = 0;

        while(min < max) {
            long mid = (max+min)/2;
            long cnt = 0;

            for(int i=0;i<arr.length;i++)
                cnt += (arr[i]/mid);

            if(cnt < N) max = mid;
            else min = mid+1;
        }

        return min-1;
    }
}
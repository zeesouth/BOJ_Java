package twoPointer.n1806;
// https://www.acmicpc.net/problem/1806

import java.io.*;
import java.util.*;

public class Main {
    static int N, S, start, ans, end, sum;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        S = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) arr[i] = Integer.parseInt(st.nextToken());
        br.close();

        ans = Integer.MAX_VALUE;
        sum = 0;
        start = end = 0;

        while(start < N || end < N) {
            int beforeStart = start;
            int beforeEnd = end;
            while(sum >= S && start < N) sum-=arr[start++];
            if(end != 0 && sum < S) ans = Math.min(end-(start-1), ans);

            while(sum < S && end < N) sum+=arr[end++];
            if(sum >= S) ans = Math.min(end-start, ans);

            if(beforeStart == start && beforeEnd == end) break;
            if(start == 0 && end == N && sum < S) {
                ans = 0; break;
            }
        }
        if(ans == Integer.MAX_VALUE) ans = 0;
        if(S == 0) ans = 1;
        bw.write(ans+"");
        bw.flush();
    }
}
package binarySearch.n2110;
// https://www.acmicpc.net/problem/2110
// https://gom20.tistory.com/179

import java.io.*;
import java.util.*;

public class Main {
    static int N, C, res = 0;
    static int[] X;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        X = new int[N];
        for(int i=0;i<N;i++) X[i] = Integer.parseInt(br.readLine());
        Arrays.sort(X);

        binarySearch();
        System.out.println(res);
    }

   static void binarySearch(){
        int left = 1;               // 최소 인접 거리
        int right = X[N-1] - X[0];  // 최대 인접 거리
        while(left <= right) {
            int mid = (left+right)/2;
            if(getPossibleCnt(mid) >= C) {
                res = Math.max(res, mid);
                left = mid+1;
            } else right = mid-1;
        }
   }

   // 현재 인접 거리로 공유기 설치 시 가능한 설치 개수
   static int getPossibleCnt(int dist) {
        int cnt = 1;
        int prev = X[0];
        for(int i=0;i<X.length;i++) {
            if(X[i]-prev>=dist) {
                prev = X[i];
                cnt++;
            }
        }

        return cnt;
   }
}

/*
* 언제 어디서나 와이파이를 즐기기 위해서 집에 공유기 C개를 설치하려고 한다.
* 가장 인접한 두 공유기 사이의 거리를 가능한 크게 하여 설치하려고 한다.
*
* 1 2 4 8 9
*
* */

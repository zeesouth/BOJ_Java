package binarySearch.n3020;
// https://www.acmicpc.net/problem/3020
// https://loosie.tistory.com/557
// 이분 탐색 방법

import java.io.*;
import java.util.*;

public class Main {
    static int N, H, min, cnt;
    static int[] down, up;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        down = new int[N/2];
        up = new int[N/2];
        for(int i=0;i<N/2;i++) {
            int num1 = Integer.parseInt(br.readLine());
            int num2 = Integer.parseInt(br.readLine());
            down[i] = num1;
            up[i] = num2;
        }


        Arrays.sort(up);
        Arrays.sort(down);


        min = N;
        cnt = 0;

        for(int i=1;i<=H;i++) {
            int cntWall = binarySearch(0, N/2, i, down) +
                    binarySearch(0, N/2, H-i+1, up);

            if(min == cntWall) {
                cnt++;
                continue;
            }
            if(min > cntWall) {
                min = cntWall;
                cnt = 1;
            }
        }

        // 짝수 : 아래, 홀수 : 위
        sb.append(min).append(" ").append(cnt);
        System.out.println(sb);
    }

    static int binarySearch(int left, int right, int h, int[] arr) {
        while(left<right) {
            int mid = (left+right)/2;
            if(arr[mid] >= h) right = mid;
            else left = mid+1;
        }
        return arr.length-right;
    }
}


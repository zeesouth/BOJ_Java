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
        // 입력과 동시에 max를 갱신
        for(int i=0;i<K;i++) max = Math.max(arr[i] = Integer.parseInt(br.readLine()), max);

        // 반드시 max의 초기값은 최대길이+1 이어야 한다.
        max++;
        System.out.println(binarySearch());
    }

    static long binarySearch() {
        long min = 0;   // 탐색 길이 최소값

        while(min < max) {
            long mid = (max+min)/2;
            long cnt = 0;   // 범위 내의 랜선이 몇개가 만들어지는지

            for(int i=0;i<arr.length;i++)
                cnt += (arr[i]/mid);

            // 만약 잘린 랜선 개수가 필요한 랜선 개수보다 작다면
            // 자르고자 하는 길이를 줄여야 함, 즉 최대 길이를 줄여야 함
            // 그렇지 않으면 자르고자 하는 길이를 늘려야 하므로 최소 길이를 늘림
            if(cnt < N) max = mid;
            else min = mid+1;
        }

        // UpperBound로 얻어진 값 (min)에 -1이 최대 길이가 됨
        return min-1;
    }
}
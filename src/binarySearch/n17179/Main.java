package binarySearch.n17179;
// https://www.acmicpc.net/problem/17179
// 롤케이크를 잘랐을 때 가장 작은 길이의 최댓값

import java.io.*;
import java.util.*;
public class Main {
    static int N, M, L, area[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        area = new int[M+1];
        for (int i = 0; i < M; i++) area[i] = Integer.parseInt(br.readLine());
        area[M] = L;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int cut = Integer.parseInt(br.readLine());
            sb.append(binarySearch(cut)).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    static int binarySearch(int cut) {
        int answer = 0;
        int start = 0;
        int end = L;
        while (start <= end) {
            int mid = (start + end) / 2;
            int left = 0;
            int cnt = 0;
            for(int i=0;i<=M;i++) {
                int right = area[i];
                if(Math.abs(right-left) >= mid) {
                    cnt++;
                    left = right;
                }
            }
            if(cnt > cut) {
                start = mid+1;
                answer = Math.max(answer, mid);
            }
            else end = mid-1;
        }
        return answer;
    }
}

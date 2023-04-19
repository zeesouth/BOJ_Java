package binarySearch.n1508;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K, judge[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        judge = new int[K];
        for (int i = 0; i < K; i++) judge[i] = Integer.parseInt(st.nextToken());
        System.out.println(binarySearch());
        br.close();
    }

    static String binarySearch() {
        String answer = null;
        int left = 1;
        int right = N;
        while (left <= right) {
            int mid = (left + right) / 2;
            String referees = setReferee(mid);
            if (referees.charAt(0) == '-') right = mid - 1;
            else {
                left = mid + 1;
                answer = referees;
            }
        }
        return answer;
    }

    static String setReferee(int dist) {
        StringBuilder sb = new StringBuilder();
        int cnt = 1;
        sb.append("1");
        int lastIdx = judge[0];
        for (int i = 1; i < K; i++) {
            int currIdx = judge[i];
            if (currIdx - lastIdx < dist) sb.append("0");
            else {
                sb.append("1");
                lastIdx = currIdx;
                cnt++;
            }

            if (cnt == M) {
                sb.append("0".repeat(K - i - 1));
                break;
            }
        }
        return cnt == M ? sb.toString() : "-";
    }
}

// 레이스 트랙 : N
// 심판 수 : M
// 심판 가 위치할 수 있는 곳 K개, judge에 적힌 위치
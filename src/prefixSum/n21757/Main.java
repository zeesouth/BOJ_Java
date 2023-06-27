package prefixSum.n21757;
// https://www.acmicpc.net/problem/21757

import java.io.*;
import java.util.*;

public class Main {
    static final int MAX_N = 100000;
    static long psum[] = new long[MAX_N + 1];
    static long dp[][] = new long[MAX_N + 1][3 + 1];
    static long val;
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) psum[i] = psum[i - 1] + Long.parseLong(st.nextToken());

        long ans = 0;
        // 총합이 4로 나누어 떨어지는 수일 경우 진행
        if (psum[N] % 4 == 0) {
            // 총합이 0인 경우
            if (psum[N] == 0) {
                long zero = 0;
                for (int i = 1; i <= N; i++) if (psum[i] == 0) zero++;
                ans = (zero - 1) * (zero - 2) * (zero - 3) / 6;
            } else {
                val = psum[N] / 4;
                for (int i = 0; i <= N; i++) Arrays.fill(dp[i], -1);
                for (int i = 1; i <= N; i++) {
                    if (psum[i] == val) ans += DP(i + 1, 1);
                }
            }
        }

        System.out.println(ans);
        br.close();
    }

    static long DP(int idx, int cnt) {
        if (dp[idx][cnt] >= 0) return dp[idx][cnt];

        if (idx > N) return dp[idx][cnt] = 0;

        if (cnt == 3) {
            if (psum[N] - psum[idx - 1] == val) return dp[idx][cnt] = 1;
            else return dp[idx][cnt] = 0;
        }

        long ret = 0;
        for (int i = idx; i <= N; i++) {
            long tmp = psum[i] - psum[idx - 1];

            // 첫 번쨰 수열 값과 같은 경우
            if(tmp == val) ret += DP(i+1, cnt+1);
        }

        return dp[idx][cnt] = ret;
    }
}

/*
수열을 무조건 연속된 네 부분으로 나누기
dp[i][j] : i번째까지 j번으로 나누는 경우의 수
 */

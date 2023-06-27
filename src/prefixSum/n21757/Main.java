package prefixSum.n21757;
// https://www.acmicpc.net/problem/21757

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long psum[] = new long[N + 1];
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
                long dp[] = new long[5];
                dp[0] = 1;
                long val = psum[N] / 4;

                for (int i = 1; i <= N; i++) {
                    int t = (int) (psum[i] / val);
                    if(psum[i] % val != 0 || t < 1 || t > 4) continue;
                    dp[t] += dp[t-1];
                }
                ans = dp[4];
            }
        }

        System.out.println(ans);
        br.close();
    }
}

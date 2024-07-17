package dp.n20667;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, K;
    static final int M_MAX = 1001, P_MAX = 500;
    static int cpu[], memory[], priority[];

    public static void main(String[] args) throws Exception {
        init();
        simulate();
    }

    private static void simulate() {
        int dp[][] = new int[P_MAX + 1][M_MAX + 1];

        for (int i = 0; i <= P_MAX; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }

        dp[0][0] = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = P_MAX; j >= 0; j--) {
                for (int k = M_MAX; k >= 0; k--) {
                    if (j + priority[i] > P_MAX) continue;
                    if (k + cpu[i] <= M_MAX) {
                        dp[j + priority[i]][k + cpu[i]] =
                                Math.max(dp[j + priority[i]][k + cpu[i]], dp[j][k] + memory[i]);
                    } else {
                        dp[j + priority[i]][M_MAX] =
                                Math.max(dp[j + priority[i]][M_MAX], dp[j][k] + memory[i]);
                    }

                }
            }
        }

        int ans = -1;
        for (int i = 0; i <= P_MAX; i++) {
            for (int j = M; j <= M_MAX; j++) {
                if (dp[i][j] < K) continue;
                if (ans == -1 || ans > i) ans = i;
            }
        }

        System.out.println(ans);
    }

    private static void init() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 크롬 탭 수
        M = Integer.parseInt(st.nextToken());   // 목표 CPU 사용량
        K = Integer.parseInt(st.nextToken());   // 목표 메모리 할당량

        cpu = new int[N + 1];
        memory = new int[N + 1];
        priority = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            cpu[i] = Integer.parseInt(st.nextToken());
            memory[i] = Integer.parseInt(st.nextToken());
            priority[i] = Integer.parseInt(st.nextToken());
        }
    }
}

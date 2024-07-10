package dp.n16169;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static ArrayList<int[]> arr[];     // number:{number, level, time}

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        arr = new ArrayList[N + 1];

        for (int i = 0; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }

        int l, t;

        int L = 0;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            l = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            arr[l].add(new int[]{i, t});
            L = Math.max(l, L);
        }

        int[][] dp = new int[L + 1][N + 1];

        for (int i = 1; i <= L; i++) {
            for (int k = 0; k < arr[i].size(); k++) {
                int b_i = arr[i].get(k)[0];
                int b_t = arr[i].get(k)[1];

                for (int j = 0; j < arr[i - 1].size(); j++) {
                    int a_i = arr[i - 1].get(j)[0];

                    dp[i][k] = Math.max(dp[i - 1][j] + (a_i - b_i) * (a_i - b_i), dp[i][k]);
                }

                dp[i][k] += b_t;
            }
        }

        int ans = 0;
        for (int i = 0; i < arr[L].size(); i++) {
            ans = Math.max(dp[L][i], ans);
        }

        System.out.println(ans);
    }
}

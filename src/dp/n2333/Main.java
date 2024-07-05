package dp.n2333;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int INF = Integer.MAX_VALUE;
    static int D, G, HP = 4000;
    static int arr[][], dp[][][];

    public static void main(String[] args) throws Exception {
        init();

        Queue<int[]> q = new LinkedList<>();
        // time, hp, height
        q.offer(new int[]{0, 10, 0, -1});

        int h, hp;
        while (!q.isEmpty()) {
            int[] curr = q.poll();

            for (int i = curr[3] + 1; i < G; i++) {
                if (arr[i][0] - curr[0] > curr[1]) break;

                // 내용물을 먹거나, 발판을 만들거나
                hp = curr[1] - (arr[i][0] - curr[0]) + arr[i][1];
                h = Math.min(D, curr[2] + arr[i][2]);

                // 내용물을 먹는 경우
                if (dp[curr[2]][hp][i] > arr[i][0]) {
                    dp[curr[2]][hp][i] = arr[i][0];
                    q.offer(new int[]{arr[i][0], hp, curr[2], i});
                }

                // 발판을 만드는 경우
                if (dp[h][curr[1] - (arr[i][0] - curr[0])][i] > arr[i][0]) {
                    dp[h][curr[1] - (arr[i][0] - curr[0])][i] = arr[i][0];

                    if (h == D) continue;
                    q.offer(new int[]{arr[i][0], curr[1] - (arr[i][0] - curr[0]), h, i});
                }
            }
        }

        int ans = INF;
        for (int i = 0; i <= HP; i++) {
            for (int j = 0; j < G; j++) {
                ans = Math.min(dp[D][i][j], ans);
            }
        }
        if (ans == INF) {
            // 무조건 많이 먹는 게 오래 삼
            ans = 0;
            hp = 10;
            for (int i = 0; i < G; i++) {
                if (arr[i][0] - ans > hp) break;
                hp += arr[i][1] - (arr[i][0] - ans);
                ans = arr[i][0];
            }
            ans += hp;
        }

        System.out.println(ans);
    }

    private static void init() throws Exception {
        st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());

        arr = new int[G][3];

        for (int i = 0; i < G; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, ((o1, o2) -> o1[0] - o2[0]));

        // height, hp
        dp = new int[D + 1][HP + 1][G];

        for (int i = 0; i <= D; i++) {
            for (int j = 0; j <= HP; j++) {
                Arrays.fill(dp[i][j], INF);
            }
        }
    }
}

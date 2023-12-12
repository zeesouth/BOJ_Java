package backTracking.n1079;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final int MAX_N = 16;
    static int guilty[] = new int[MAX_N];
    static int R[][] = new int[MAX_N][MAX_N];
    static boolean die[] = new boolean[MAX_N];
    static int N, E, ans;

    public static void main(String[] args) throws Exception {
        init();
        dfs(N, 0);
        System.out.println(ans);
    }

    private static void dfs(int size, int cnt) {
        if (size % 2 == 1) {
            int max = Integer.MIN_VALUE;
            int min_id = N;

            for (int i = 0; i < N; i++) {
                if (die[i]) continue;
                if (max < guilty[i]) {
                    max = guilty[i];
                    min_id = i;
                }
            }

            if (min_id == E) {
                ans = Math.max(ans, cnt);
                return;
            }

            die[min_id] = true;
            dfs(size - 1, cnt);
            die[min_id] = false;

        } else {
            if (size == 2) {                      //마피아 1명, 시민 1명이 남았으면 마피아 승으로 끝
                ans = Math.max(ans, cnt + 1);
                return;
            }

            for (int i = 0; i < N; i++) {
                if (i == E) continue;
                if (die[i]) continue;

                die[i] = true;

                for (int j = 0; j < N; j++) {
                    guilty[j] += R[i][j];
                }

                dfs(size - 1, cnt + 1);

                for (int j = 0; j < N; j++) {
                    guilty[j] -= R[i][j];
                }

                die[i] = false;
            }
        }
    }

    private static void init() throws Exception {
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            guilty[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                R[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        E = Integer.parseInt(br.readLine());
    }

}

/*
참가자가 짝수 명 남았을 때는 밤, 참가자가 홀수 명 남았을 때는 낮
낮에는 참가자들이 가장 죄가 있을 것 같은 사람들 한 명 죽인다.
밤에는 마피아가 죽일 사람 한 명을 고른다. 죽은 사람은 게임에 더 이상 참여할 수 없다.
마피아가 0명이라면 시민 이김 / 시민이 0명이라면 마피아가 이김 -> 즉시 종료

은진이는 가장 마지막에 남은 마피아
 */
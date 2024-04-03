package prefixSum.n2173;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static String answer;
    static int N, M;
    static int map[][], sum[][];
    static boolean visited[][];

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(solve(0, "") ? answer : 0);
    }

    private static boolean solve(int cnt, String s) {
        if (cnt == M) {
            answer = s;
            return true;
        }

        int max = Integer.MIN_VALUE;
        ArrayList<int[]> arr = new ArrayList<>();

        // start
        for (int i = 1; i <= N - 2; i++) {
            for (int j = 1; j <= N - 2; j++) {
                // end
                for (int k = i + 2; k <= N; k++) {
                    for (int l = j + 2; l <= N; l++) {
                        if (!isValid(i, j, k, l)) continue;
                        int score = getScore(i, j, k, l);
                        if (score > max) {
                            max = score;
                            arr.clear();
                            arr.add(new int[]{i, j, k, l});
                        } else if (score == max) {
                            arr.add(new int[]{i, j, k, l});
                        }
                    }
                }
            }
        }

        if (max == Integer.MIN_VALUE) return false;

        int y1, x1, y2, x2;
        StringBuilder tmp;
        boolean ans = false;
        for (int[] pairs : arr) {
            y1 = pairs[0];
            x1 = pairs[1];
            y2 = pairs[2];
            x2 = pairs[3];

            // 방문 처리
            changeFlag(y1, x1, y2, x2, true);

            tmp = new StringBuilder();
            tmp.append(max).append(" ").append(y1).append(" ").append(x1).append(" ").append(y2).append(" ").append(x2).append(" ").append("\n");

            if (ans = solve(cnt + 1, s + tmp.toString())) break;

            // 방문 해제
            changeFlag(y1, x1, y2, x2, false);

        }

        return ans;
    }

    private static boolean isValid(int y1, int x1, int y2, int x2) {
        // 방문 여부 검사
        for (int i = x1; i <= x2; i++) {
            if (visited[y1][i] || visited[y2][i]) return false;
        }

        for (int i = y1 + 1; i < y2; i++) {
            if (visited[i][x1] || visited[i][x2]) return false;
        }

        return true;
    }

    private static void changeFlag(int y1, int x1, int y2, int x2, boolean flag) {
        // 방문 처리
        for (int i = x1; i <= x2; i++) {
            visited[y1][i] = flag;
            visited[y2][i] = flag;
        }

        for (int i = y1 + 1; i < y2; i++) {
            visited[i][x1] = flag;
            visited[i][x2] = flag;
        }
    }

    private static void init() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sum = new int[N + 1][N + 1];
        // 가로 합
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sum[i][j] = map[i][j] + sum[i][j - 1];
            }
        }

        // 세로 합
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sum[i][j] += sum[i - 1][j];
            }
        }

        visited = new boolean[N + 1][N + 1];
    }


    // 선택한 꼭짓점의 양파링 점수 구하기
    private static int getScore(int y1, int x1, int y2, int x2) {
        // y1 < y2 && x1 < x2
        int big = sum[y2][x2] - sum[y2][x1 - 1] - sum[y1 - 1][x2] + sum[y1 - 1][x1 - 1];
        int small = sum[y2 - 1][x2 - 1] - sum[y2 - 1][x1] - sum[y1][x2 - 1] + sum[y1][x1];
        return big - small;
    }
}

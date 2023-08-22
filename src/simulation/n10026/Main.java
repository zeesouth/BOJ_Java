package simulation.n10026;
// 현대오토에버 3분기 3번

import java.io.*;
import java.util.*;

public class Main {
    static final int MAX_N = 100;
    static char map[][] = new char[MAX_N][MAX_N];
    static boolean visited[][];

    static int dy[] = {1, 0, -1, 0};
    static int dx[] = {0, 1, 0, -1};
    static int N, ans;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        init();
        simulate(1);
        simulate(2);
        System.out.println(sb);
    }

    private static void simulate(int type) {
        // 정상인의 경우 type 1
        // 적록색약의 경우 type 2
        ans = 0;
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    bfs(type, i, j);
                    ans++;
                }
            }
        }
        sb.append(ans).append(" ");
    }

    private static void bfs(int type, int y, int x) {
        Queue<int[]> q = new LinkedList<>();
        visited[y][x] = true;
        q.add(new int[]{y, x});
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextY = curr[0] + dy[i];
                int nextX = curr[1] + dx[i];
                if (!isValid(nextY, nextX)) continue;
                if (visited[nextY][nextX]) continue;

                if (!visited[nextY][nextX]) {

                    boolean flag = false;
                    if (type == 1) {
                        if (map[curr[0]][curr[1]] == map[nextY][nextX]) flag = true;
                    } else {
                        if ((map[curr[0]][curr[1]] != 'B' && map[nextY][nextX] != 'B') ||
                                (map[curr[0]][curr[1]] == 'B' && map[nextY][nextX] == 'B')) flag = true;
                    }

                    if (flag) {
                        q.add(new int[]{nextY, nextX});
                        visited[nextY][nextX] = true;
                    }
                }
            }
        }

    }

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) map[i][j] = s.charAt(j);
        }
        br.close();
    }

    private static boolean isValid(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < N;
    }
}

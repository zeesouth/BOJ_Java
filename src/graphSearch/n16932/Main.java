package graphSearch.n16932;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int dy[] = {1, 0, -1, 0};
    static final int dx[] = {0, 1, 0, -1};
    static ArrayList<int[]> arr = new ArrayList<>();
    static ArrayList<Integer> cntList = new ArrayList<>();
    static boolean visited[][];
    static int N, M, map[][], ans, idx = 1;
    static HashSet<Integer> check = new HashSet<>();

    public static void main(String[] args) throws Exception {
        init();
        simulate();
        System.out.println(ans);
    }

    private static void simulate() {
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) continue;
                if (visited[i][j]) continue;

                bfs(i, j);
                idx++;
            }
        }


        for (int[] pair : arr) {
            int cnt = 1;

            check.clear();

            for (int i = 0; i < 4; i++) {
                int nextY = dy[i] + pair[0];
                int nextX = dx[i] + pair[1];

                if (!isRange(nextY, nextX)) continue;
                if (map[nextY][nextX] == 0) continue;
                if (check.contains(map[nextY][nextX] - 1)) continue;

                check.add(map[nextY][nextX] - 1);
                cnt += cntList.get(map[nextY][nextX] - 1);
            }

            ans = Math.max(ans, cnt);
        }

    }

    private static void bfs(int y, int x) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{y, x});
        visited[y][x] = true;
        map[y][x] = idx;

        int cnt = 1;
        while (!q.isEmpty()) {
            int[] curr = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextY = curr[0] + dy[i];
                int nextX = curr[1] + dx[i];

                if (!isRange(nextY, nextX)) continue;
                if (visited[nextY][nextX]) continue;
                if (map[nextY][nextX] == 0) continue;

                visited[nextY][nextX] = true;

                cnt++;
                map[nextY][nextX] = idx;
                q.add(new int[]{nextY, nextX});
            }
        }

        cntList.add(cnt);
    }

    private static boolean isRange(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }

    private static void init() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                if ((map[i][j] = Integer.parseInt(st.nextToken())) == 0) {
                    arr.add(new int[]{i, j});
                }
            }
        }
    }
}

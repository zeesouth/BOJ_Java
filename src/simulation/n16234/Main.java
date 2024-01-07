package simulation.n16234;

import java.io.*;
import java.util.*;

public class Main {
    static final int dy[] = {1, 0, -1, 0};
    static final int dx[] = {0, 1, 0, -1};
    static final int MAX = 50;
    static int[][] map = new int[MAX][MAX], newMap;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, L, R;
    static int T;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        init();
        while(simulate()) T++;
        System.out.println(T);
    }

    private static boolean simulate() {
        visited = new boolean[N][N];
        newMap = new int[N][N];

        boolean flag = false;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) continue;
                if (bfs(i, j)) flag = true;
            }
        }

        map = newMap;

        return flag;
    }

    private static boolean bfs(int y, int x) {

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{y, x});
        visited[y][x] = true;

        ArrayList<int[]> area = new ArrayList<>();
        area.add(new int[]{y, x});

        int sum = map[y][x];

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int currY = curr[0];
            int currX = curr[1];

            for (int i = 0; i < 4; i++) {
                int nextY = currY + dy[i];
                int nextX = currX + dx[i];

                if (!isRange(nextY, nextX)) continue;
                if (visited[nextY][nextX]) continue;
                int diff = Math.abs(map[currY][currX] - map[nextY][nextX]);
                if (diff < L || diff > R) continue;
                visited[nextY][nextX] = true;
                area.add(new int[]{nextY, nextX});
                q.add(new int[]{nextY, nextX});
                sum += map[nextY][nextX];
            }
        }

        if (area.size() == 1) {
            newMap[y][x] = map[y][x];
            return false;
        }

        for (int[] node : area) {
            newMap[node[0]][node[1]] = sum / area.size();
        }

        return true;
    }

    private static boolean isRange(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < N;
    }

    private static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}

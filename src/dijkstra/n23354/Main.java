package dijkstra.n23354;

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, map[][], dist[][], C, ans = Integer.MAX_VALUE;
    static ArrayList<int[]> arr = new ArrayList<>();
    static final int dy[] = {1, 0, -1, 0};
    static final int dx[] = {0, 1, 0, -1};
    static int visited[][];
    static boolean visit[];

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    arr.add(new int[]{i, j});
                } else if (map[i][j] == -1) {
                    map[i][j] = 0;
                    arr.add(0, new int[]{i, j});
                }
            }
        }

        if (arr.size() == 1) {
            System.out.println(0);
            return;
        }

        dist = new int[arr.size()][arr.size()];
        visited = new int[N][N];

        for (int i = 0; i < arr.size() - 1; i++) {
            for (int j = i + 1; j < arr.size(); j++) {
                dist[i][j] = dist[j][i] = dijkstra(i, j);
            }
        }

        visit = new boolean[arr.size() + 1];
        dfs(0, 1, 0);
        System.out.println(ans);
    }

    private static int dijkstra(int s, int e) {
        C++;
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (o1, o2) -> o1[2] - o2[2]
        );
        pq.offer(new int[]{arr.get(s)[0], arr.get(s)[1], 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            if (curr[0] == arr.get(e)[0] && curr[1] == arr.get(e)[1]) {
                return curr[2];
            }

            for (int i = 0; i < 4; i++) {
                int nextY = curr[0] + dy[i];
                int nextX = curr[1] + dx[i];

                if (!isRange(nextY, nextX)) continue;
                if (visited[nextY][nextX] == C) continue;
                visited[nextY][nextX]++;
                pq.offer(new int[]{nextY, nextX, curr[2] + map[nextY][nextX]});
            }
        }

        return 0;
    }

    private static void dfs(int idx, int cnt, int sum) {
        visit[idx] = true;

        if (cnt == arr.size()) {
            ans = Math.min(sum + dist[idx][0], ans);
            return;
        }

        for (int i = 0; i < arr.size(); i++) {
            if (visit[i]) continue;
            dfs(i, cnt + 1, sum + dist[idx][i]);
            visit[i] = false;
        }
    }

    private static boolean isRange(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < N;
    }
}

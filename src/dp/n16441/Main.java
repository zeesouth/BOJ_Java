package dp.n16441;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

//https://www.acmicpc.net/problem/16441
public class Main {
    static int N, M;
    static char[][] map;
    static boolean[][] visited;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Initialize
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M];

        int[][] wolf = new int[N * M][2];
        int wCnt = 0;

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                if (line.charAt(j) == '.') map[i][j] = 'P';  // 우선 초원의 위치를 P으로 표시
                else {
                    if (line.charAt(j) == 'W') wolf[wCnt++] = new int[]{i, j};
                    map[i][j] = line.charAt(j);
                }
            }
        }

        for (int i = 0; i < wCnt; i++) {
            // wolf
            Queue<int[]> q = new LinkedList<>();
            q.add(wolf[i]);

            while (!q.isEmpty()) {
                int[] curr = q.poll();
                for (int j = 0; j < 4; j++) {
                    int currY = curr[0] + dy[j];
                    int currX = curr[1] + dx[j];
                    if (isValid(currY, currX) && !visited[currY][currX]) {
                        char c = map[currY][currX];
                        if (c != '#') {
                            // 아직 지나지 않은 초원이라면
                            if (c == 'P') map[currY][currX] = '.';
                                // 빙판길을 건넌 것이라면
                            else if (c == '+') { //
                                // 현재 길이 빙판길이면서 다음 위치가 산이 아니거나 이동할 수 있는 거리면 이동
                                while (isValid(currY + dy[j], currX + dx[j])
                                        && map[currY][currX] == '+'
                                        && map[currY + dy[j]][currX + dx[j]] != '#') {
                                    currY += dy[j];
                                    currX += dx[j];
                                }
                                // 현재 위치가 안전한 초원이라면
                                if (map[currY][currX] == 'P') map[currY][currX] = '.';
                            }

                            // 늑대가 방문하지 않았던 곳이라면 방문체크/큐에 추가
                            if (!visited[currY][currX]) {
                                visited[currY][currX] = true;
                                q.add(new int[]{currY, currX});
                            }
                        }
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) sb.append(map[i][j]);
            sb.append("\n");
        }
        System.out.println(sb);

        br.close();
    }

    static boolean isValid(int y, int x) {
        return y >= 1 && y < N - 1 && x >= 1 && x < M - 1;
    }
}
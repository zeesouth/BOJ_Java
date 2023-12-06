package graphSearch.n1175;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final int dy[] = {1, 0, -1, 0};
    static final int dx[] = {0, 1, 0, -1};
    static final int SIZE = 50;
    static char[][] map = new char[SIZE][SIZE];
    static boolean[][][][] visited = new boolean[SIZE][SIZE][4][4];
    static int N, M, sy, sx, by1, bx1, by2, bx2;
    static int answer = -1;

    public static void main(String[] args) throws Exception {
        init();
        simulate();
        System.out.println(answer);
    }

    private static void simulate() {
        int cnt = 0;
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < 4; i++) {
            q.add(new int[]{sy, sx, i, 0});
            visited[sy][sx][i][0] = true;
        }

        while (!q.isEmpty()) {
            int size = q.size();
            while (size > 0) {
                int[] curr = q.poll();
                int y = curr[0];
                int x = curr[1];
                int d = curr[2];
                int g = curr[3];

                if (y == by1 && x == bx1) {
                    if (g != 1) g += 1;
                } else if (y == by2 && x == bx2) {
                    if (g != 2) g += 2;
                }

                if (g == 3) {
                    answer = cnt;
                    return;
                }

                for (int i = 0; i < 4; i++) {
                    if (i == d) continue;

                    int ny = y + dy[d];
                    int nx = x + dx[d];

                    if (!isRange(ny, nx)) continue;
                    if (visited[ny][nx][i][g]) continue;
                    q.add(new int[]{ny, nx, i, g});
                    visited[ny][nx][i][g] = true;
                }
                size--;
            }
            cnt++;
        }

    }

    private static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        boolean flag = true;
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                int c = map[i][j] = s.charAt(j);
                if (c == 'S') {
                    sy = i;
                    sx = j;
                } else if (c == 'C') {
                    if (flag) {
                        by1 = i;
                        bx1 = j;
                        flag = false;
                    } else {
                        by2 = i;
                        bx2 = j;
                    }
                }
            }
        }
    }

    private static boolean isRange(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M && map[y][x] != '#';
    }
}

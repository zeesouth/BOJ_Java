package simulation.n2933;

import java.util.*;
import java.io.*;

public class Main {
    static final int[] dy = {1, 0, 0, -1};
    static final int[] dx = {0, 1, -1, 0};
    static int N, R, C;
    static char map[][];
    static boolean visited[][], totalVisited[][];

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        init();
        simulate();
        print();
    }

    private static void simulate() {
        for (int i = 1; i <= N; i++) {
            boolean isLeft = i % 2 == 1 ? true : false;
            int r = R - Integer.parseInt(st.nextToken());
            int c;
            if ((c = brokeMineral(r, isLeft)) == -1) continue;
            totalVisited = new boolean[R][C];
            for (int d = 0; d < 4; d++) {
                int nextR = r + dy[d];
                int nextC = c + dx[d];
                if (!isRange(nextR, nextC)) continue;
                if (totalVisited[nextR][nextC]) continue;
                if (map[nextR][nextC] == '.') continue;
                ArrayList<int[]> cluster = getCluster(nextR, nextC);
                moveCluster(cluster);
            }
        }
    }

    private static void moveCluster(ArrayList<int[]> cluster) {
        /*
        구해야 하는 것
        1. 각 열마다 최대로 내려갈 수 있는 행 수
        2. 1에서 구한 값의 최솟값 찾기
        3. 최솟값만큼 옮겨주기!
        */

        int size = R - 1;

        for (int[] pair : cluster) {
            int rr = pair[0];
            int cc = pair[1];

            int tmp = 0;
            for (int i = rr + 1; i < R; i++) {
                if (map[i][cc] == 'x') break;
                tmp++;
            }

            size = Math.min(tmp, size);
        }

        char[][] newMap = new char[R][C];

        for (int i = R - 1; i >= 0; i--) {
            for (int j = 0; j < C; j++) {
                if (!visited[i][j]) newMap[i][j] = map[i][j];
                else {
                    newMap[i][j] = '.';
                    newMap[i + size][j] = map[i][j];
                }
            }
        }

        map = newMap;
    }

    private static ArrayList<int[]> getCluster(int r, int c) {
        /*
        구해야 하는 것
        1. 클러스터 열 범위
        2. 1번의 범위 내 각 열 별로 가장 높은 행 idx 구하기
        3. 부서진 미네랄은 꼭 제외해야함
        */
        visited = new boolean[R][C];
        totalVisited = new boolean[R][C];

        ArrayList<int[]> res = new ArrayList<>();
        int[] rList = new int[C];

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});
        visited[r][c] = true;
        totalVisited[r][c] = true;
        rList[c] = r;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextY = curr[0] + dy[i];
                int nextX = curr[1] + dx[i];
                if (!isRange(nextY, nextX)) continue;
                if (visited[nextY][nextX]) continue;
                if (map[nextY][nextX] == '.') continue;
                visited[nextY][nextX] = true;
                rList[nextX] = Math.max(rList[nextX], nextY);
                q.add(new int[]{nextY, nextX});
            }
        }

        for (int i = 0; i < C; i++) {
            if (!visited[rList[i]][i]) continue;
            res.add(new int[]{rList[i], i});
        }

        return res;
    }

    private static int brokeMineral(int r, boolean isLeft) {
        if (isLeft) {
            for (int j = 0; j < C; j++) {
                if (map[r][j] == 'x') {
                    map[r][j] = '.';
                    return j;
                }
            }
        } else {
            for (int j = C - 1; j >= 0; j--) {
                if (map[r][j] == 'x') {
                    map[r][j] = '.';
                    return j;
                }
            }
        }

        return -1;
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void init() throws Exception {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
    }

    private static boolean isRange(int y, int x) {
        return y >= 0 && y < R && x >= 0 && x < C;
    }
}

/*
창영 : 왼쪽
상근 : 오른쪽
 */
package simulation.n13460;
// https://www.acmicpc.net/problem/13460

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] map;
    static boolean[][][][] visited;
    static int holeX, holeY;
    static Marble blue, red;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1}; //0, 1, 2, 3 (상, 우, 하, 좌) - 시계 방향

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[N][M][N][M];

        // 구슬 map 구성
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'O') {
                    holeY = i;
                    holeX = j;
                } else if (map[i][j] == 'B') {
                    blue = new Marble(0, 0, i, j, 0);
                } else if (map[i][j] == 'R') {
                    red = new Marble(i, j, 0, 0, 0);
                }
            }
        }

        System.out.println(bfs());

        br.close();
    }

    public static int bfs() {
        Queue<Marble> queue = new LinkedList<>();
        queue.add(new Marble(red.ry, red.rx, blue.by, blue.bx, 1));
        visited[red.ry][red.rx][blue.ry][blue.rx] = true;

        while (!queue.isEmpty()) {
            Marble marble = queue.poll();

            int currRy = marble.ry;
            int currRx = marble.rx;
            int currBy = marble.by;
            int currBx = marble.bx;
            int currCnt = marble.cnt;

            if (currCnt > 10) { // 이동 횟수가 10 초과시 실패
                return -1;
            }

            for (int i = 0; i < 4; i++) {
                int newRy = currRy;
                int newRx = currRx;
                int newBy = currBy;
                int newBx = currBx;

                boolean isRedHole = false;
                boolean isBlueHole = false;

                // 빨간 구슬 이동 -> # 벽을 만날 때까지 이동
                while (map[newRy + dy[i]][newRx + dx[i]] != '#') {
                    newRy += dy[i];
                    newRx += dx[i];

                    // 이동 중 구멍을 만날 경우
                    if (newRy == holeY && newRx == holeX) {
                        isRedHole = true;
                        break;
                    }
                }

                // 파란 구슬 이동 -> # 벽을 만날 때까지 이동
                while (map[newBy + dy[i]][newBx + dx[i]] != '#') {
                    newBy += dy[i];
                    newBx += dx[i];

                    // 이동 중 구멍을 만날 경우
                    if (newBy == holeY && newBx == holeX) {
                        isBlueHole = true;
                        break;
                    }
                }

                if (isBlueHole) { // 파란 구슬이 구멍에 빠지면 무조건 실패
                    continue; // 하지만 큐에 남은 다른 좌표도 봐야하니 다음으로
                }

                if (isRedHole && !isBlueHole) { // 빨간 구슬만 구멍에 빠지면 성공
                    return currCnt;
                }

                // 둘 다 구멍에 빠지지 않았는데 이동할 위치가 같은 경우 -> 위치 조정
                if (newRy == newBy && newRx == newBx) {
                    if (i == 0) { // 위쪽으로 기울이기
                        // 더 큰 y값을 가지는 구슬이 뒤로 감
                        if (currRy > currBy) newRy -= dy[i];
                        else newBy -= dy[i];
                    } else if (i == 1) { // 오른쪽으로 기울이기
                        // 더 작은 x값을 가지는 구슬이 뒤로 감
                        if (currRx < currBx) newRx -= dx[i];
                        else newBx -= dx[i];
                    } else if (i == 2) { // 아래쪽으로 기울이기
                        // 더 작은 y값을 가지는 구슬이 뒤로 감
                        if (currRy < currBy) newRy -= dy[i];
                        else newBy -= dy[i];
                    } else { // 왼쪽으로 기울이기
                        // 더 큰 x값을 가지는 구슬이 뒤로 감
                        if (currRx > currBx) newRx -= dx[i];
                        else newBx -= dx[i];
                    }
                }

                // 두 구슬이 이동할 위치가 처음 방문하는 곳인 경우만 이동 -> 큐에 추가
                if (!visited[newRy][newRx][newBy][newBx]) {
                    visited[newRy][newRx][newBy][newBx] = true;
                    queue.add(new Marble(newRy, newRx, newBy, newBx, currCnt + 1));
                }
            }
        }

        return -1;
    }

}

class Marble {
    int ry, rx, by, bx, cnt;
    Marble(int ry, int rx, int by, int bx, int cnt) {
        this.ry = ry;
        this.rx = rx;
        this.by = by;
        this.bx = bx;
        this.cnt = cnt;
    }
}

/*
 * . : 빈칸
 * # : 공이 이동할 수 없는 벽
 * O : 구멍 위치
 * R : 빨간 구슬 위치
 * B : 파란 구슬 위치
 * 빨간 구슬과 파란 구슬은 동시에 같은 위치에 있을 수 없다.
 * 파란 구슬이 빠지면 실패 (빨간 구슬이 동시에 빠져도 실패)
 * */
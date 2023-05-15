package simulation.n15683;
// https://www.acmicpc.net/problem/15683
import java.io.*;
import java.util.*;
public class Main {
    static int[][][] dy =  {
            {},
            {{0}, {0}, {1}, {-1}},                              // 1
            {{0, 0}, {1, -1}},                                  // 2
            {{-1, 0}, {0, 1}, {1, 0}, {0, -1}},                 // 3
            {{-1, 0, 1}, {0, 1, 0}, {1, 0, -1}, {0, -1, 0}},    // 4
            {{-1, 0, 0, 1}}                                     // 5
    };
    static int[][][] dx = {
            {},
            {{1}, {-1}, {0}, {0}},
            {{1, -1}, {0, 0}},
            {{0, 1}, {1, 0}, {0, -1}, {-1, 0}},
            {{0, 1, 0}, {1, 0, -1}, {0, -1, 0}, {-1, 0, 1}},
            {{0, 1, -1, 0}}
    };

    static int N, M, map[][], ans, temp;
    static ArrayList<CCTV> cctv = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) temp++;
                else if(map[i][j] <= 5) cctv.add(new CCTV(i, j));
            }
        }
        ans = temp;
        simulate(0);
        System.out.println(ans);
        br.close();
    }

    static void simulate(int idx) {
        if(idx == cctv.size()) {
            ans = Math.min(ans, temp);
            return;
        }

        CCTV curCCTV = cctv.get(idx);
        int curID = map[curCCTV.y][curCCTV.x];

        int[][] curDy = dy[curID];
        int[][] curDx = dx[curID];
        int dl = curDy.length;

        for(int i=0;i<dl;i++) {
            for (int j = 0; j < curDy[i].length; j++) {
                int currY = curCCTV.y + curDy[i][j];
                int currX = curCCTV.x + curDx[i][j];
                while (isValid(currY, currX)) {
                    if (map[currY][currX] == 0) temp--;
                    if (map[currY][currX] <= 0) map[currY][currX] -= 1;
                    currY += curDy[i][j];
                    currX += curDx[i][j];
                }
            }

            simulate(idx + 1);

            for (int j = 0; j < curDy[i].length; j++) {
                int currY = curCCTV.y + curDy[i][j];
                int currX = curCCTV.x + curDx[i][j];
                while (isValid(currY, currX)) {
                    if (map[currY][currX] == -1) temp++;
                    if (map[currY][currX] < 0) map[currY][currX] += 1;
                    currY += curDy[i][j];
                    currX += curDx[i][j];
                }
            }
        }
    }

    static boolean isValid(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M && map[y][x] != 6;
    }

    static class CCTV {
        int y, x;
        CCTV(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}

/*
* CCTV는 벽을 통과할 수 없고, CCTV가 감시할 수 없는 영역은 사각지대라고 한다.
* CCTV는 회전시킬 수 있는데, 회전은 항상 90도 방향으로 해야 하며 하는 방향이 가로 또는 세로 방향이어야 한다.
* 사각 지대의 최소 크기를 구하는 프로그램 작성하기
* */

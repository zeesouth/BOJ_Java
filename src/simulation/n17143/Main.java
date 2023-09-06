package simulation.n17143;

import java.io.*;
import java.util.*;

public class Main {
    static final int dy[] = {-1, 1, 0, 0};
    static final int dx[] = {0, 0, 1, -1};
    static final int MAX_M = 10000;
    static int R, C, M;
    static int map[][];
    static int shark[][] = new int[MAX_M + 1][5]; // y, x, s(속력), d(이동방향), z(크기)
    static int score;

    public static void main(String[] args) throws Exception {
        init();
        for (int c = 0; c < C; c++) {
            simulate(c);
        }
        System.out.println(score);
    }

    private static void simulate(int col) {
        catchShark(col);
        moveShark();
    }

    private static void moveShark() {
        int newMap[][] = new int[R][C];

        for (int i = 1; i <= M; i++) {
            if (shark[i] == null) continue;

            int currShark[] = shark[i];
            int newY = currShark[0];
            int newX = currShark[1];
            int newD = currShark[3];


            int moveSize = currShark[2];
            if (newD <= 1) moveSize %= (2 * R - 2);
            else moveSize %= (2 * C - 2);

            while (moveSize > 0) {
                if (!isRange(newY + moveSize * dy[newD], newX + moveSize * dx[newD])) {
                    if (newD == 0) {
                        moveSize -= newY;
                        newY = 0;
                        newD = 1;
                    } else if (newD == 1) {
                        moveSize -= R - 1 - newY;
                        newY = R - 1;
                        newD = 0;
                    } else if (newD == 2) {
                        moveSize -= C - 1 - newX;
                        newX = C - 1;
                        newD = 3;
                    } else {
                        moveSize -= newX;
                        newX = 0;
                        newD = 2;
                    }
                } else {
                    newY += moveSize * dy[newD];
                    newX += moveSize * dx[newD];
                    moveSize = 0;
                }

            }

            shark[i][0] = newY;
            shark[i][1] = newX;
            shark[i][3] = newD;

            if (newMap[newY][newX] != 0) {
                if (shark[newMap[newY][newX]][4] < currShark[4]) {
                    shark[newMap[newY][newX]] = null;
                    newMap[newY][newX] = i;
                } else {
                    shark[i] = null;
                }
            } else {
                newMap[newY][newX] = i;
            }
        }

        map = newMap;
    }

    private static void catchShark(int col) {
        for (int i = 0; i < R; i++) {
            if (map[i][col] != 0) {
                score += shark[map[i][col]][4];
                shark[map[i][col]] = null;
                map[i][col] = 0;
                break;
            }
        }
    }

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            shark[i][0] = Integer.parseInt(st.nextToken()) - 1;
            shark[i][1] = Integer.parseInt(st.nextToken()) - 1;
            shark[i][2] = Integer.parseInt(st.nextToken());
            shark[i][3] = Integer.parseInt(st.nextToken()) - 1;
            shark[i][4] = Integer.parseInt(st.nextToken());

            map[shark[i][0]][shark[i][1]] = i;
        }

        br.close();

    }

    private static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean isRange(int y, int x) {
        return y >= 0 && y < R && x >= 0 && x < C;
    }
}

/*
C-1
R-1

00
 */

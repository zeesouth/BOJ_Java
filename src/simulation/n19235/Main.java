package simulation.n19235;
// https://www.acmicpc.net/problem/19235

import java.io.*;
import java.util.*;

public class Main {
    static final int ROW = 10, COL = 4, GREEN = 0, BLUE = 1;
    static final int my[] = {0, 0, 1};
    static final int mx[] = {0, 1, 0};
    static int T, score;
    static int t, y1, x1, y2, x2;
    static int board[][][] = new int[2][ROW][COL];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            simulate(br.readLine());
        }

        bw.write(score + "\n" + (getCellCount(GREEN) + getCellCount(BLUE)));
        bw.flush();
        br.close();
    }

    // 셀 개수 세기
    private static int getCellCount(int type) {
        int ret = 0;

        for (int i = 6; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (board[type][i][j] > 0) ret++;
            }
        }

        return ret;
    }

    private static void simulate(String line) {
        StringTokenizer st = new StringTokenizer(line);
        t = Integer.parseInt(st.nextToken());
        y1 = Integer.parseInt(st.nextToken());
        x1 = Integer.parseInt(st.nextToken());

        y2 = y1 + my[t - 1];
        x2 = x1 + mx[t - 1];

        // green
        dropBlock(GREEN, y1, x1, y2, x2, t == 3 ? 1 : t);
        deleteRowUntilAble(GREEN);
        handleSoftCell(GREEN);

        // blue
        dropBlock(BLUE, x1, y1, x2, y2, t == 3 ? 2 : 1);
        deleteRowUntilAble(BLUE);
        handleSoftCell(BLUE);
    }

    // 연한 칸 처리 함수 (0~1행)
    private static void handleSoftCell(int type) {
        int count = 0;

        for (int r = 5; r >= 4; r--) {
            for (int c = 0; c < COL; c++) {
                if (board[type][r][c] > 0) {
                    count++;
                    break;
                }
            }
            if (count == 0) return;
        }

        for (int r = ROW - 1; r > ROW - 1 - count; r--) {
            deleteRow(type, r);
        }

        downBlock(type, ROW - 1 - count);
    }

    // 더 이상 삭제할 행이 없을 때까지 행 삭제하는 함수
    private static void deleteRowUntilAble(int type) {
        while (true) {
            int maxDeleteRow = -1;

            // 행 삭제
            for (int r = 4; r < ROW; r++) {
                boolean isDelete = true;

                for (int c = 0; c < COL; c++) {
                    if (board[type][r][c] == 0) {
                        isDelete = false;
                        break;
                    }
                }

                if (!isDelete) continue;

                deleteRow(type, r);
                score++;
                maxDeleteRow = r;
            }

            if (maxDeleteRow == -1) break;

            downBlock(type, maxDeleteRow-1);
        }
    }

    // 블럭 내리는 함수
    private static void downBlock(int type, int row) {
        for (int r = row; r >= 4; r--) {
            for (int c = 0; c < 4; c++) {
                if (board[type][r][c] == 1) {
                    board[type][r][c] = 0;
                    dropBlock(type, r, c, r, c, 1);
                } else if (board[type][r][c] == 2) {
                    board[type][r][c] = board[type][r][c + 1] = 0;
                    dropBlock(type, r, c, r, c + 1, 2);
                }
            }
        }
    }

    // 행 삭제
    private static void deleteRow(int type, int r) {
        for (int c = 0; c < COL; c++) {
            board[type][r][c] = 0;
        }
    }

    // 블럭 떨구는 함수
    private static void dropBlock(int type, int y1, int x1, int y2, int x2, int block) {
        while (y1 < ROW && y2 < ROW && board[type][y1][x1] == 0 && board[type][y2][x2] == 0) {
            y1++;
            y2++;
        }

        board[type][y1 - 1][x1] = board[type][y2 - 1][x2] = block;
    }
}

/*
8
2 0 1
2 0 1
3 0 3
2 0 0
3 0 2
1 0 0
1 0 1
3 0 3

ans)

2

12
 */

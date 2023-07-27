package simulation.n14499;

import java.io.*;
import java.util.*;

public class Main {
    // 동 서 북 남
    static int dy[] = {0, 0, -1, 1};
    static int dx[] = {1, -1, 0, 0};
    static int dice[] = new int[6];

    static void move(int type) {
        /*
        초기 주사위 값
          2
        4 1 3
          5
          6
         */
        int temp = -1;
        switch (type) {
            case 1:         // 오른쪽
                temp = dice[1];
                dice[1] = dice[0];
                dice[0] = dice[4];
                dice[4] = dice[5];
                dice[5] = temp;
                break;
            case 2:         // 왼쪽
                temp = dice[4];
                dice[4] = dice[0];
                dice[0] = dice[1];
                dice[1] = dice[5];
                dice[5] = temp;
                break;
            case 3:         // 앞쪽
                temp = dice[3];
                dice[3] = dice[0];
                dice[0] = dice[2];
                dice[2] = dice[5];
                dice[5] = temp;
                break;
            case 4:         // 뒷쪽
                temp = dice[5];
                dice[5] = dice[2];
                dice[2] = dice[0];
                dice[0] = dice[3];
                dice[3] = temp;
                break;
        }
    }

    static boolean isRange(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }

    static int N, M, X, Y, K;
    static int map[][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        while (K-- > 0) {
            int d = Integer.parseInt(st.nextToken());
            int nextY = Y + dy[d - 1];
            int nextX = X + dx[d - 1];
            if (!isRange(nextY, nextX)) continue;

            Y = nextY;
            X = nextX;

            move(d);

            if (map[Y][X] == 0) map[Y][X] = dice[0];
            else {
                dice[0] = map[Y][X];
                map[Y][X] = 0;
            }
            sb.append(dice[5]).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}

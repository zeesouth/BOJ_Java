package adHoc.n17302;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static final int MAX = 2000;
    static final int dy[] = {0, 1, 0, -1, 0};
    static final int dx[] = {0, 0, 1, 0, -1};
    static char[][] map = new char[MAX][MAX];
    static int[][] res = new int[MAX][MAX];
    static int N, M;

    public static void main(String[] args) throws Exception {
        init();
        simulate();
        print();
    }

    private static void simulate() {
        // reverse
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int d = 0; d < 5; d++) {
                    if (!isRange(i + dy[d], j + dx[d])) continue;
                    int data = map[i + dy[d]][j + dx[d]];
                    map[i + dy[d]][j + dx[d]] = (data == 'W') ? 'B' : 'W';
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'B') {
                    map[i][j] = 'W';
                    res[i][j] = 2;
                }
            }
        }
    }

    private static void print() {
        sb.append(1).append("\n");

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(res[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                res[i][j] = 3;
            }
        }
    }

    private static boolean isRange(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }
}

/*
333
BWB

1. WBB
2. BWW
3. BBB



 */
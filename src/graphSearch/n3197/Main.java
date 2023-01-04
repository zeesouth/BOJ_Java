package graphSearch.n3197;

import java.io.*;
import java.util.*;

public class Main {
    static int T, R, C, answer;
    static int[][] map;
    static int[][] swan;
    static Queue<int[]> q;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int[] a, b;  // 백조 a, b;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/n3197.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            map = new int[R][C];
            swan = new int[R][C];
            a = b = null;
            q = new LinkedList<>();
            answer = Integer.MAX_VALUE;

            for (int i = 0; i < R; i++) {
                String s = br.readLine();
                for (int j = 0; j < C; j++) {
                    switch (s.charAt(j)) {
                        case 'X':
                            map[i][j] = -1;
                            break;   // 얼음
                        case '.':
                            map[i][j] = 0;
                            q.add(new int[]{i, j});
                            break;   // 물
                        case 'L':
                            map[i][j] = -2;           // 백조
                            if(a==null) a = new int[]{i, j};
                            else b = new int[]{i, j};
                    }
                }
            }
            setting();
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++)
                    System.out.print(swan[i][j] + " ");
                System.out.println();
            }
            // System.out.println(bfs());

        }
    }

    static int bfs() {

        // 각 얼음이 녹는 최소 시간 계산
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (int i = 0; i < 4; i++) {
                int currY = dy[i] + curr[0];
                int currX = dx[i] + curr[1];
                if (isValid(currY, currX)) {
                    if(map[currY][currX] == -1) {
                        map[currY][currX] = map[curr[0]][curr[1]] + 1;
                        q.add(new int[]{currY, currX});
                    } else if (map[currY][currX] > map[curr[0]][curr[1]]+1) {
                        map[currY][currX] = map[curr[0]][curr[1]]+1;
                    }
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++)
                System.out.print(map[i][j] + " ");
            System.out.println();
        }

        return 0;
    }

    static boolean isValid(int y, int x) {
        return y >= 0 && y < R && x >= 0 && x < C;
    }

    static void setting() {
        int[] ddy = {0, 0}, ddx = {0, 0};

        // a기준
        if(a[0] < b[0]) ddy[0] = 1;
        else if(a[0] > b[0]) ddy[0] = -1;

        if(a[1] < b[1]) ddx[1] = 1;
        else if(a[1] > b[1]) ddx[1] = -1;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {a[0], a[1]});

        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            for(int i=0;i<2;i++) {
                int currY = ddy[i] + curr[0];
                int currX = ddx[i] + curr[1];
                if(isValid(currY, currX)) {
                    swan[currY][currX] = swan[curr[0]][curr[1]]+1;
                    if(currY != b[0] && currX != b[1])
                        queue.add(new int[]{currY, currX});
                }
            }
        }
    }
}

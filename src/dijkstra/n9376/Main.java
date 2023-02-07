package dijkstra.n9376;

import java.io.*;
import java.util.*;

public class Main {
    static int T, h, w;
    static int[] p1, p2;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static char[][] map;
    static int[][] cal;
    static boolean[][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++) {
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            map = new char[h][w];
            p1 = p2 = null;
            for(int i=0;i<h;i++) {
                map[i] = br.readLine().toCharArray();
                for(int j=0;j<w;j++) {
                    if (map[i][j] == '$') {
                        if (p1 == null) p1 = new int[]{i, j};
                        else p2 = new int[]{i, j};
                    }
                }
            }
            cal = new int[h][w];
            bfs_0_1_1();
            bfs_0_1_2();

        }
    }

    static void bfs_0_1_2() {
        System.out.println();
        for(int i=0;i<h;i++) {
            for(int j=0;j<w;j++){
                System.out.print(cal[i][j]+"\t");
            }
            System.out.println();
        }
    }

    static void bfs_0_1_1() {
        visited = new boolean[h][w];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {p1[0], p1[1], 0});
        visited[p1[0]][p1[1]] = true;

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            for(int i=0;i<4;i++) {
                int currY = curr[0] + dy[i];
                int currX = curr[1] + dx[i];
                int currCount = curr[2];
                if(isValid(currY, currX) && !visited[currY][currX]) {
                    if(map[currY][currX] == '#') currCount+=1;
                    q.add(new int[] {currY, currX, currCount});
                    cal[currY][currX] = currCount;
                    visited[currY][currX] = true;
                }
            }
        }

    }

    static boolean isValid(int y, int x) {
        return y >= 0 && y < h && x >= 0 && x < w && map[y][x] != '*';
    }
}

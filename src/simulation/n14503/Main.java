package simulation.n14503;
// https://www.acmicpc.net/problem/14503

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, r, c, d, map[][], ans = 0;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static boolean[][] visit;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visit = new boolean[N][M];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        while(true) {
            if(!visit[r][c]) {
                visit[r][c] = true;
                ans++;
            }

            boolean flag = false;

            for(int i=0;i<4;i++) {
                int currY = r+dy[(i+d)%4];
                int currX = c+dx[(i+d)%4];

                if(isValid(currY, currX) && map[currY][currX] == 0 && !visit[currY][currX]) flag = true;
                if (flag) break;
            }

            if(flag) {
                d = d-1 < 0 ? 3 : (d-1)%4;
                if(isValid(r+dy[d], c+dx[d]) && map[r+dy[d]][c+dx[d]] == 0 && !visit[r+dy[d]][c+dx[d]]) {
                    r += dy[d];
                    c += dx[d];
                }
            } else {
                if(isValid(r-dy[d], c-dx[d]) && map[r-dy[d]][c-dx[d]] == 0) {
                    r -= dy[d];
                    c -= dx[d];
                } else break;
            }
        }

        System.out.println(ans);
    }

    static boolean isValid(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }
}

package dp.n1103;

import java.io.*;
import java.util.*;

public class Main {
    static boolean flag = false;
    static int N, M, ans = -1;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int[][] map, dp;
    static boolean[][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        map = new int[N][M];
        for(int i=0;i<N;i++) {
            String s = br.readLine();
            for(int j=0;j<M;j++) {
                int curr = s.charAt(j);
                if(curr == 'H') map[i][j] = -1;
                else map[i][j] = curr-'0';
            }
        }

        dp = new int[N][M];
        visited = new boolean[N][M];

        game(0, 0, 1);
        bw.write((flag ? -1 : ans)+"");
        bw.flush();
        br.close();
    }

    static void game(int y, int x, int cnt) {
        dp[y][x] = cnt;
        int X = map[y][x];

        if(cnt > ans) ans = cnt;

        for(int i=0;i<4;i++) {
            int currY = y+dy[i]*X;
            int currX = x+dx[i]*X;
            if(isValid(currY, currX)) {
                if(!isValid(currY, currX)) continue;
                if(cnt < dp[currY][currX]) continue;
                if(visited[currY][currX]) {
                    flag = true; return;
                }

                visited[currY][currX] = true;
                game(currY, currX, cnt+1);
                visited[currY][currX] = false;
            }
        }

    }

    static int stoi(String s){
        return Integer.parseInt(s);
    }

    static boolean isValid(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M && map[y][x] != -1;
    }

}

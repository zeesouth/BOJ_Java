package dp.n17090;
// https://www.acmicpc.net/problem/17090

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, ans = 0;
    static char[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) map[i][j] = s.charAt(j);
        }
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (dfs(i, j)) ans++;
            }
        }
        bw.write(ans + "");
        bw.flush();
        br.close();
    }


    static boolean dfs(int y, int x) {
        boolean res = false;
        if (!isValid(y, x)) return true;

        if (map[y][x] == 'T') return true;
        else if(map[y][x] == 'F') return false;

        if(visited[y][x]) return false;

        visited[y][x] = true;
        switch (map[y][x]) {
            case 'U':
                res = dfs(y - 1, x);
                break;
            case 'D':
                res = dfs(y + 1, x);
                break;
            case 'L':
                res = dfs(y, x - 1);
                break;
            case 'R':
                res = dfs(y, x + 1);
                break;
        }
        map[y][x] = res ? 'T':'F';
        return res;
    }


    static boolean isValid(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }
}

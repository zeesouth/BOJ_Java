package backTracking.n1987;

import java.io.*;
import java.util.*;

public class Main {
    static int R, C, ans;
    static char[][] map;
    static boolean[] alphabet;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for(int i=0;i<R;i++) {
            String s = br.readLine();
            for(int j=0;j<C;j++) map[i][j] = s.charAt(j);
        }

        ans = Integer.MIN_VALUE;
        alphabet = new boolean['Z'-'A'+1];

        dfs(1, 0, 0);

        bw.write(ans+"");
        bw.flush();
        br.close();
    }

    static void dfs(int cnt, int y, int x) {
        alphabet[map[y][x]-'A'] = true;
        for(int i=0;i<4;i++) {
            int currY = y+dy[i];
            int currX = x+dx[i];

            if(isValid(currY, currX)) {
                if(!alphabet[map[currY][currX]-'A']) dfs(cnt+1, currY, currX);
                else ans = Math.max(ans, cnt);
            }
        }
        alphabet[map[y][x]-'A'] = false;
    }

    static boolean isValid(int y, int x) {
        return y >= 0 && y < R && x >= 0 && x < C;
    }
}

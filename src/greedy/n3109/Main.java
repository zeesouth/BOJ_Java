package greedy.n3109;

import java.io.*;
import java.util.*;

public class Main {
    static int[] dy = {-1, 0, 1};
    static int[] dx = {1, 1, 1};
    static int R, C, ans = 0;
    static char map[][];
    static boolean visited[][];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];
        for(int i=0;i<R;i++) {
            String line = br.readLine();
            for(int j=0;j<C;j++) map[i][j] = line.charAt(j);
        }

        for(int i=0;i<R;i++) {
            visited[i][0] = true;
            boolean res = dfs(i, 0);
            if(res) ans += 1;
        }

        System.out.println(ans);
        br.close();
    }

    static boolean dfs(int y, int x) {
        if(x == C-1) return true;

        boolean res = false;

        for(int i=0;i<3;i++) {
            int currY = y+dy[i];
            int currX = x+dx[i];

            if(isValid(currY, currX) && !visited[currY][currX]) {
                visited[currY][currX] = true;
                res = dfs(currY, currX);
                if(res) return true;
            }
        }
        return res;
    }

    static boolean isValid(int y, int x) {
        return y >= 0 && y < R && x >= 0 && x < C && map[y][x] == '.';
    }
}

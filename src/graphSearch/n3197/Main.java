package graphSearch.n3197;

import java.io.*;
import java.util.*;

public class Main {
    static int R, C, answer;
    static int[][] map;
    static boolean[][] visited;
    static Queue<int[]> q1, q2, q3;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] a, b;  // 백조 a, b;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        q1 = new LinkedList<int[]>();

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                switch (s.charAt(j)) {
                    case 'X':
                        map[i][j] = -1;
                        break;   // 얼음
                    case '.':
                        map[i][j] = 0;
                        q1.add(new int[]{i, j});
                        break;   // 물
                    case 'L':
                        map[i][j] = -2;           // 백조
                        if (a == null) a = new int[]{i, j};
                        else b = new int[]{i, j};
                        q1.add(new int[]{i, j});
                }
            }
        }
        // 두 백조 사이의 최소 거리 계산
        System.out.println(bfs());
        br.close();
    }

    static int bfs() {
        visited = new boolean[R][C];
        q2 = new LinkedList<>();
        q2.add(a);
        visited[a[0]][a[1]] = true;

        while(true) {
            q3 = new LinkedList<>();
            while(!q2.isEmpty()) {
                int[] curr = q2.poll();

                if(curr[0] == b[0] && curr[1] == b[1]) {
                    return answer;
                }

                for(int i=0;i<4;i++) {
                    int currY = curr[0]+dy[i];
                    int currX = curr[1]+dx[i];

                    // 이동한 위치가 얼음이 아닌 경우
                    if(isValid(currY, currX) && !visited[currY][currX]) {
                        visited[currY][currX] = true;
                        // 백조의 위치인 경우
                        if (map[currY][currX] == -1) q3.add(new int[] {currY, currX});
                        else q2.add(new int[] {currY, currX});
                    }
                }
            }
            q2 = q3;
            int size = q1.size();
            for(int j=0;j<size;j++) {
                int[] curr = q1.poll();
                for(int i=0;i<4;i++) {
                    int currY = curr[0]+dy[i];
                    int currX = curr[1]+dx[i];
                    if(isValid(currY, currX)) {
                        if (map[currY][currX] == -1) { // 다음 턴에 물이 되는 곳
                            map[currY][currX] = 0;
                            q1.add(new int[]{currY, currX});
                        }
                    }
                }
            }
            answer++;
        }
    }

    static boolean isValid(int y, int x) {
        return y >= 0 && y < R && x >= 0 && x < C;
    }
}
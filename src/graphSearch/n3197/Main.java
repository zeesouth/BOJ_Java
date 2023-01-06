package graphSearch.n3197;

import java.io.*;
import java.util.*;

public class Main {
    static boolean flag;
    static int R, C, answer; // answer: 두 백조가 만나는 최소시간
    static int[][] map, currMap;
    static boolean[][] visited;
    static Queue<int[]> q, q2, q3;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] a, b;  // 백조 a, b;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        q = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                switch (s.charAt(j)) {
                    case 'X':
                        map[i][j] = -1;
                        break;   // 얼음
                    case '.':
                        map[i][j] = 0;
                        q.add(new int[]{i, j}); // 물 추가
                        break;   // 물
                    case 'L':
                        map[i][j] = -2;           // 백조
                        q.add(new int[]{i, j}); // 물 추가
                        if (a == null) a = new int[]{i, j};
                        else b = new int[]{i, j};
                }
            }
        }
        // 두 백조 사이의 최소 거리 계산
        System.out.println(bfs());
        br.close();
    }

    static int bfs() {
        while(true) {
            currMap = new int[R][C];
            visited = new boolean[R][C];
            for(int i=0;i<map.length;i++) currMap[i] = Arrays.copyOf(map[i], map[i].length);

            q2 = new LinkedList<>();
            while (!q.isEmpty()) {
                int[] curr = q.poll();
                for (int i = 0; i < 4; i++) {
                    int currY = dy[i] + curr[0];
                    int currX = dx[i] + curr[1];
                    if (isValid(currY, currX) && !visited[currY][currX]) {
                        if (map[currY][currX] == -1) {  // 인근 위치가 얼음인 경우
                            currMap[currY][currX] = 0;  // 물로 바꾸어줌
                            q2.add(new int[] {currY, currX});
                        } else if (map[currY][currX] == 0){
                            q2.add(new int[] {currY, currX});
                            visited[currY][currX] = true;
                        }
                    }
                }
            }
            answer++;
            visited = new boolean[R][C];
            q3 = new LinkedList<>();
            q3.add(new int[] {a[0], a[1]});
            flag = false;
            while(!q3.isEmpty()) {
                int[] curr = q3.poll();
                for(int i=0;i<4;i++) {
                    int currY = curr[0]+dy[i];
                    int currX = curr[1]+dx[i];
                    // 이동한 위치가 얼음이 아닌 경우
                    if(isValid(currY, currX) && !visited[currY][currX] && currMap[currY][currX] != -1) {
                        // 백조의 위치인 경우
                        if(currY == b[0] && currX == b[1]) {
                            flag = true; break;
                        }
                        // 백조의 위치가 아니고 물인 경우
                        else if (currMap[currY][currX] == 0){
                            q3.add(new int[] {currY, currX});
                            visited[currY][currX] = true;
                        }
                    }
                }
                if(flag) break;
            }
            if(flag) break;
            q = q2;
            map = currMap;
        }
        return answer;
    }

    static boolean isValid(int y, int x) {
        return y >= 0 && y < R && x >= 0 && x < C;
    }
}
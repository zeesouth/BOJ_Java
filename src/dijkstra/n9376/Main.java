package dijkstra.n9376;
// https://www.acmicpc.net/problem/9376

import java.io.*;
import java.util.*;

class Prisoner {
    int y, x, open;

    Prisoner(int y, int x) {
        this.y = y;
        this.x = x;
        this.open = 0;
    }

    Prisoner(int y, int x, int open) {
        this.y = y;
        this.x = x;
        this.open = open;
    }
}

public class Main {
    static int T, h, w, p;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static char[][] map;
    static boolean[][] visited;
    static int[][] p1, p2, sg;
    static Prisoner[] prisoner;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            map = new char[h + 2][w + 2];
            // map 가장자리는 빈공간으로 채운다
            Arrays.fill(map[0], '.');
            for (int i = 1; i <= h; i++) {
                map[i][0] = '.';
                map[i][w + 1] = '.';
            }
            Arrays.fill(map[h + 1], '.');
            p = 0;
            prisoner = new Prisoner[2];
            for (int i = 1; i <= h; i++) {
                String line = br.readLine();
                for (int j = 1; j <= w; j++) {
                    map[i][j] = line.charAt(j - 1);
                    if (map[i][j] == '$') prisoner[p++] = new Prisoner(i, j);
                }
            }
            p1 = bfs(prisoner[0]);
            p2 = bfs(prisoner[1]);
            sg = bfs(new Prisoner(0, 0));

//            printMap(p1);
//            printMap(p2);
//            printMap(sg);

            sb.append(getMinDoor()).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        br.close();
    }

    static void printMap(int[][] arr) {
        for (int[] a : arr) {
            System.out.println(Arrays.toString(a));
        }
        System.out.println();
    }

    static int getMinDoor() {
        int res = Integer.MAX_VALUE;
        int doorCnt = 0;
        for (int i = 0; i < h + 2; i++) {
            for (int j = 0; j < w + 2; j++) {
                if (map[i][j] == '*') continue;
                if (!visited[i][j]) continue;
                int sum = p1[i][j] + p2[i][j] + sg[i][j];
                if (map[i][j] == '#') {
                    doorCnt ++;
                    sum -= 2;
                }
                res = Math.min(res, sum);
            }
        }
        return res;
    }

    static int[][] bfs(Prisoner p) {
        PriorityQueue<Prisoner> pq = new PriorityQueue<>((o1, o2) -> o1.open - o2.open);
        visited = new boolean[h + 2][w + 2];
        int[][] doorCnt = new int[h + 2][w + 2];

        pq.add(p);
        visited[p.y][p.x] = true;
        doorCnt[p.y][p.x] = p.open;
        while (!pq.isEmpty()) {
            Prisoner curr = pq.poll();
            for (int i = 0; i < 4; i++) {
                int currY = curr.y + dy[i];
                int currX = curr.x + dx[i];
                if (isValid(currY, currX) && !visited[currY][currX] && map[currY][currX] != '*') {
                    visited[currY][currX] = true;
                    if (map[currY][currX] == '#')
                        doorCnt[currY][currX] = curr.open + 1;
                    else
                        doorCnt[currY][currX] = curr.open;
                    pq.add(new Prisoner(currY, currX, doorCnt[currY][currX]));
                }
            }
        }

        return doorCnt;
    }


    static boolean isValid(int y, int x) {
        return y >= 0 && y < h + 2 && x >= 0 && x < w + 2;
    }
}

/*
 * 1. 죄수 1이 움직인다.
 * 2. 죄소 2가 움직인다.
 * 3. 외부인이 움직인다.
 *
 * 주의사항
 * 1 : 합산하는 위치에 문이 있다면, 3명중에 1명만 열기 되면 되기 때문에 -2
 * 2 : BF로 각 인원들을 움직여줄 때, 운선순위 큐로 문을 가장 적게 연 사람들을 우선적으로 탐색
 * */
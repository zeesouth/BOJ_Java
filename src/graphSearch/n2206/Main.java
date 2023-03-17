package graphSearch.n2206;
// https://www.acmicpc.net/problem/2206

import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int y, x, wall;
        Node(int y, int x, int wall) {
            this.y = y;
            this.x = x;
            this.wall = wall;
        }
    }
    static int N, M;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static char[][] map;
    static int[][] dist;
    static boolean[][][] visit;
    static Queue<Node> q;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        dist = new int[N][M];
        visit = new boolean[N][M][2];

        for(int i=0;i<N;i++) map[i] = br.readLine().toCharArray();



        q = new LinkedList<>();
        q.add(new Node(0, 0, 0));
        visit[0][0][0] = visit[0][0][1] = true;
        dist[0][0] = 1;

        while(!q.isEmpty()) {
            Node curr = q.poll();

            for(int i=0;i<4;i++) {
                int cy = curr.y+dy[i];
                int cx = curr.x+dx[i];

                if(isValid(cy, cx)) {
                    // 벽을 만났으며, 현재 벽을 부순 적이 없음, 지금까지 벽을 부순 적이 없음
                    if(map[cy][cx] == '1'&& curr.wall == 0 && !visit[cy][cx][curr.wall+1]) {
                        visit[cy][cx][curr.wall+1] = true;
                        dist[cy][cx] = dist[curr.y][curr.x] + 1;
                        q.add(new Node(cy, cx, curr.wall+1));
                    }
                    // 벽이 아니면서, 방문 안한 곳
                    if(map[cy][cx] == '0' && !visit[cy][cx][curr.wall]) {
                        visit[cy][cx][curr.wall] = true;
                        dist[cy][cx] = dist[curr.y][curr.x]+1;
                        q.add(new Node(cy, cx, curr.wall));
                    }

                    if(cy == N-1 && cx == M-1) {
                        System.out.println(dist[N-1][M-1]);
                        return;
                    }
                }
            }
        }
        System.out.println(dist[N-1][M-1] == 0 ? -1 : dist[N-1][M-1]);

    }

    static boolean isValid(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }
}

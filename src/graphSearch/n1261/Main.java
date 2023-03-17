package graphSearch.n1261;
// https://www.acmicpc.net/problem/1261

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

    static int M, N;
    static int[] dy = {0, 0, -1, 1}, dx = {1, -1, 0, 0};
    static char[][] map;
    static int[][] visit;
    static Queue<Node> q;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visit = new int[N][M];

        for(int i=0;i<N;i++) {
            map[i] = br.readLine().toCharArray();
            Arrays.fill(visit[i], Integer.MAX_VALUE);
        }

        q = new LinkedList<>();
        q.add(new Node(0, 0, 0));
        visit[0][0] = 0;
        while(!q.isEmpty()) {
            Node curr = q.poll();

            for(int i=0;i<4;i++) {
                int currY = curr.y+dy[i];
                int currX = curr.x+dx[i];

                if(isValid(currY, currX)) {
                    if(map[currY][currX] == '1') {
                        if(visit[currY][currX] > curr.wall+1) {
                            visit[currY][currX] = curr.wall+1;
                            q.add(new Node(currY, currX, visit[currY][currX]));
                        }
                    } else {
                        if(visit[currY][currX] > curr.wall) {
                            visit[currY][currX] = curr.wall;
                            q.add(new Node(currY, currX, visit[currY][currX]));
                        }
                    }
                }
            }
        }
        System.out.println(visit[N-1][M-1]);
    }

    static boolean isValid(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }
}

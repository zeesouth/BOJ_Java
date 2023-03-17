package graphSearch.n1600;

import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int y, x, horse;
        Node(int y, int x, int horse) {
            this.y = y;
            this.x = x;
            this.horse = horse;
        }
    }
    static int K, W, H;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, -1, 0, 1};
    static int[] hy = {1, 2, 1, 2, -1, -2, -1, -2};
    static int[] hx = {2, 1, -2, -1, 2, 1, -2, -1};
    static int[][] map, dist;
    static boolean[][][] visit;
    static Queue<Node> q;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        dist = new int[H][W];
        visit = new boolean[H][W][K+1];

        for(int i=0;i<H;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<W;j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        q = new LinkedList<>();
        q.add(new Node(0, 0, 0));
        visit[0][0][0] = true;


        while(!q.isEmpty()) {
            Node curr = q.poll();

            // 그냥 이동
            for(int i=0;i<4;i++) {
                int currY = curr.y+dy[i];
                int currX = curr.x+dx[i];
                if(isValid(currY, currX) && map[currY][currX] != 1 && !visit[currY][currX][curr.horse]) {
                    dist[currY][currX] = dist[curr.y][curr.x]+1;
                    visit[currY][currX][curr.horse] = true;
                    if(currY == H-1 && currX == W-1) {
                        System.out.println(dist[currY][currX]);
                        return;
                    }
                    q.add(new Node(currY, currX, curr.horse));
                }
            }

            // 말로 이동
            if(curr.horse < K) {
                for (int i = 0; i < 8; i++) {
                    int currY = curr.y + hy[i];
                    int currX = curr.x + hx[i];
                    if(isValid(currY, currX) && map[currY][currX] != 1 && !visit[currY][currX][curr.horse+1]) {
                        dist[currY][currX] = dist[curr.y][curr.x]+1;
                        visit[currY][currX][curr.horse+1] = true;
                        if(currY == H-1 && currX == W-1) {
                            System.out.println(dist[currY][currX]);
                            return;
                        }
                        q.add(new Node(currY, currX, curr.horse+1));
                    }
                }
            }

        }
        System.out.println(W==1 && H==1 ? 0 : -1);
    }


    static boolean isValid(int y, int x) {
        return y >= 0 && y < H && x >= 0 && x < W;
    }
}

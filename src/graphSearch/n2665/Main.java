package graphSearch.n2665;
// https://www.acmicpc.net/problem/2665

import java.io.*;
import java.util.*;

class Node {
    int y, x;
    Node(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class Main {
    static int N, ans;
    static int[] dy = {0, 0, 1, -1}, dx = {1, -1, 0, 0};
    static char[][] map;
    static int[][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new int[N][N];

        for(int i=0;i<N;i++) {
            map[i] = br.readLine().toCharArray();
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }

        ans = Integer.MAX_VALUE;
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0));
        visited[0][0] = 0;

        while(!q.isEmpty()) {
            Node curr = q.poll();

            for(int i=0;i<4;i++) {
                int currY = curr.y + dy[i];
                int currX = curr.x + dx[i];

                if(!isValid(currY, currX)) continue;

                // 흰방
                if(map[currY][currX] == '1') {
                    if(visited[currY][currX] > visited[curr.y][curr.x]) {
                        visited[currY][currX] = visited[curr.y][curr.x];
                        q.add(new Node(currY, currX));
                    }
                }
                // 검은방
                else {
                    if(visited[currY][currX] > visited[curr.y][curr.x]+1) {
                        visited[currY][currX] = visited[curr.y][curr.x]+1;
                        q.add(new Node(currY, currX));
                    }
                }
            }
        }

        System.out.println(visited[N-1][N-1]);
    }

    static boolean isValid(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < N;
    }
}

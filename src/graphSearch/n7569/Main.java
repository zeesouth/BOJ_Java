package graphSearch.n7569;

import java.io.*;
import java.util.*;

public class Main {
    static int T,N,M,H;
    static int total, current, time, answer;  // 모든 토마토 개수, 현재 익은 토마토 개수
    static int[][][] tomato;
    static boolean[][][] visited;

    static Queue<int[]> q;

    static int[] dx = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1};


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/n7569.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            total = current = answer = time = 0;
            tomato = new int[H][N][M];
            visited = new boolean[H][N][M];
            q = new LinkedList<>();
            for(int i=0;i<H;i++) {
                for(int j=0;j<N;j++) {
                    st = new StringTokenizer(br.readLine());
                    for(int k=0;k<M;k++) {
                        switch (tomato[i][j][k] = Integer.parseInt(st.nextToken())) {
                            case 1: current++; q.add(new int[]{i, j, k});
                            case 0: total++;
                        }
                    }
                }
            }
            System.out.println(total+", "+current);

            if(total == current) answer = 0;
            else answer = total == bfs() ? time : -1;
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
        br.close();
    }

    static int bfs() {
        while(!q.isEmpty() && current != total) {
            int[] curr = q.poll();
            for(int i=0;i<6;i++) {
                int currX = dx[i] + curr[2];
                int currY = dy[i] + curr[1];
                int currZ = dz[i] + curr[0];
                if(isValid(currZ, currY, currX)) {
                    if(tomato[currZ][currY][currX] == 0) {

                    }
                }
            }
        }

        return current;
    }

    static boolean isValid(int z, int y, int x) {
        return z >= 0 && z < H && y >= 0 && y < N && x >= 0 && x < M;
    }
}

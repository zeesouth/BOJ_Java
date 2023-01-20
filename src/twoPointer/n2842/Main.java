package twoPointer.n2842;
// https://www.acmicpc.net/problem/2842

import java.io.*;
import java.util.*;

public class Main {
    static int N, ans, ansMin, ansMax;
    static char[][] village;
    static int[][] altitude;
    static boolean[][] visited;

    static int[] dy = {1, 1, 0, 1, -1, -1, -1, 0};
    static int[] dx = {1, -1, 1, 0, -1, 1, 0, -1};
    static int[] p;
    static Queue<int[]> k, q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        village = new char[N][N];
        altitude = new int[N][N];
        k = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                village[i][j] = s.charAt(j);
                if (village[i][j] == 'P') p = new int[]{i, j};
                else if (village[i][j] == 'K') k.add(new int[]{i, j});
            }
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) altitude[i][j] = Integer.parseInt(st.nextToken());
        }
        br.close();

        ansMax = Integer.MIN_VALUE;
        ansMin = Integer.MAX_VALUE;
        while (!k.isEmpty()) {
            int[] currK = k.poll();
            q = new LinkedList<>();
            q.add(new int[]{currK[0], currK[1], altitude[currK[0]][currK[1]], altitude[currK[0]][currK[1]]});
            visited = new boolean[N][N];
            visited[currK[0]][currK[1]] = true;
            while (!q.isEmpty()) {
                int[] curr = q.poll();
                for (int i = 0; i < 8; i++) {
                    int currY = curr[0] + dy[i];
                    int currX = curr[1] + dx[i];
                    if (isValid(currY, currX) && !visited[currY][currX]) {

                    }
                }
            }
        }

        bw.write(ans + "");
        bw.flush();
    }

    static boolean isValid(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < N;
    }
}

/*
 * 피로도 : 상덕이가 배달하면서 방문한 칸 중 가장 높은 곳과 낮은 곳의 고도 차이
 * 상덕이가 가장 낮은 피로도로 배달을 하려면 어떻게 해야 하는지 구하세요.
 * 피로도가 낮을 수록 좋은 것 : 즉, 고도의 차이가 많이 안날 수록
 * */
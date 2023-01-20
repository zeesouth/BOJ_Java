package twoPointer.n2842;
// https://www.acmicpc.net/problem/2842

import java.io.*;
import java.util.*;

public class Main {
    static int N, staticMin, staticMax, ansMin, ansMax;
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
        ansMax = staticMax = Integer.MIN_VALUE;
        ansMin = staticMin = Integer.MAX_VALUE;
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
            for (int j = 0; j < N; j++) {
                altitude[i][j] = Integer.parseInt(st.nextToken());
                if(altitude[i][j] != '.') {
                    staticMax = Math.max(altitude[i][j], staticMax);
                    staticMin = Math.min(altitude[i][j], staticMin);
                }
            }
        }
        br.close();

        while (!k.isEmpty()) {
            int[] currK = k.poll();
            int currAnsMax = Math.max(altitude[p[0]][p[1]], altitude[currK[0]][currK[1]]);
            int currAnsMin = Math.min(altitude[p[0]][p[1]], altitude[currK[0]][currK[1]]);
            q = new LinkedList<>();
            q.add(p);
            visited = new boolean[N][N];
            visited[p[0]][p[1]] = true;
            while(!q.isEmpty()) {
                int[] curr = q.poll();

                int firstY = -1;
                int firstX = -1;

                int minSub = Integer.MAX_VALUE;
                int secondY = -1;
                int secondX = -1;

                boolean flag = false;
                for(int i=0;i<8;i++) {
                    int currY = curr[0] + dy[i];
                    int currX = curr[1] + dx[i];
                    if(isValid(currY, currX) && !visited[currY][currX]) {
                        if(currY == currK[0] && currX == currK[1]) {
                            flag = true;
                            break;
                        } else if (village[currY][currX] != 'K') {
                            int currAltitude = altitude[currY][currX];
                            if(currAnsMin <= currAltitude && currAltitude <= currAnsMax) {
                                firstY = currY;
                                firstX = currX;
                            } else {
                                if(currAltitude > currAnsMax) {
                                    if(minSub > Math.abs(currAnsMax-currAltitude)) {
                                        minSub = Math.abs(currAnsMax-currAltitude);
                                        secondY = currY;
                                        secondX = currX;
                                    } else if(minSub == Math.abs(currAnsMax-currAltitude)) {
                                        if(altitude[secondY][secondX] > currAnsMax) {
                                            if(Math.abs(staticMax-currAltitude) < Math.abs(staticMax-altitude[secondY][secondX])){
                                                secondY = currY;
                                                secondX = currX;
                                            }
                                        } else if (altitude[secondY][secondX] < currAnsMin) {
                                            if(Math.abs(staticMax-currAltitude) < Math.abs(staticMin-altitude[secondY][secondX])){
                                                secondY = currY;
                                                secondX = currX;
                                            }
                                        }
                                    }
                                } else if (currAltitude < currAnsMin) {
                                    if(minSub > Math.abs(currAnsMin-currAltitude)) {
                                        minSub = Math.abs(currAnsMin-currAltitude);
                                        secondY = currY;
                                        secondX = currX;
                                    } else if(minSub == Math.abs(currAnsMax-currAltitude)) {
                                        if(altitude[secondY][secondX] > currAnsMax) {
                                            if(Math.abs(staticMin-currAltitude) < Math.abs(staticMax-altitude[secondY][secondX])){
                                                secondY = currY;
                                                secondX = currX;
                                            }
                                        } else if (altitude[secondY][secondX] < currAnsMin) {
                                            if(Math.abs(staticMin-currAltitude) < Math.abs(staticMin-altitude[secondY][secondX])){
                                                secondY = currY;
                                                secondX = currX;
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }

                if(flag) {
                    ansMax = Math.max(currAnsMax, ansMax);
                    ansMin = Math.min(currAnsMin, ansMin);
                    break;
                } else {
                    if(firstY != -1) {
                        q.add(new int[]{firstY, firstX});
                        visited[firstY][firstX] = true;
                    } else if(secondY != -1) {
                        currAnsMax = Math.max(altitude[secondY][secondX], currAnsMax);
                        currAnsMin = Math.min(altitude[secondY][secondX], currAnsMin);
                        q.add(new int[]{secondY, secondX});
                        visited[secondY][secondX] = true;
                    }
                }
            }
        }

        bw.write((ansMax-ansMin) + "\n");
        bw.flush();
    }

    static boolean isValid(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < N;
    }
}
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
        ansMax = Integer.MIN_VALUE;
        ansMin = Integer.MAX_VALUE;
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
                if(village[i][j] != '.'){
                    ansMax = Math.max(altitude[i][j], ansMax);
                    ansMin = Math.min(altitude[i][j], ansMin);
                }
            }
        }
        br.close();

        while (!k.isEmpty()) {
            System.out.println(ansMax+", "+ansMin);
            System.out.println("----");
            int[] currK = k.poll();
            q = new LinkedList<>();
            q.add(new int[]{currK[0], currK[1]});
            visited = new boolean[N][N];
            visited[currK[0]][currK[1]] = true;
            while (!q.isEmpty()) {
                int[] curr = q.poll();

                boolean flag = false;

                // 최대 ~ 최소 사이에 있는 Y, X
                int firstSub = Integer.MAX_VALUE;
                int firstY = -1;
                int firstX = -1;

                // 현재 고도와 고도 차이 절댓값이 가장 작은 Y, X
                int secondSub = Integer.MAX_VALUE;
                int secondY = -1;
                int secondX = -1;

                for (int i = 0; i < 8; i++) {
                    int currY = curr[0] + dy[i];
                    int currX = curr[1] + dx[i];
                    if (isValid(currY, currX) && !visited[currY][currX]) {
                        int currAltitude = altitude[currY][currX];
                        if(village[currY][currX] == 'P') {
                            flag = true;
                            break;
                        }
                        else if(village[currY][currX] != 'K' && currAltitude <= ansMax && currAltitude >= ansMin) {
                            if(firstSub >= Math.abs(altitude[curr[0]][curr[1]]-currAltitude)) {
                                firstSub = Math.abs(altitude[curr[0]][curr[1]]-currAltitude);
                                firstY = currY;
                                firstX = currX;
                            }
                        } else if(village[currY][currX] != 'K'){
                            if(currAltitude > ansMax) {
                                if(secondSub >= Math.abs(ansMax-currAltitude)) {
                                    secondSub = Math.abs(ansMax-currAltitude);
                                    secondY = currY;
                                    secondX = currX;
                                }
                            } else if(currAltitude < ansMin) {
                                if(secondSub >= Math.abs(ansMin-currAltitude)) {
                                    secondSub = Math.abs(ansMin-currAltitude);
                                    secondY = currY;
                                    secondX = currX;
                                }
                            }
                        }
                    }
                }

                if(flag) break;

                if(firstY != -1 && firstX != -1) {
                    q.add(new int[]{firstY, firstX});
                    visited[firstY][firstX] = true;
                    System.out.println("first info");
                    System.out.println(altitude[firstY][firstX]);

                } else if(secondY != -1 && secondX != -1){
                    ansMax = Math.max(ansMax, altitude[secondY][secondX]);
                    ansMin = Math.min(ansMin, altitude[secondY][secondX]);
                    visited[secondY][secondX] = true;
                    q.add(new int[]{secondY, secondX});
                    System.out.println("second info");
                    System.out.println(altitude[secondY][secondX]);
                }
            }
        }

        bw.write((ansMax-ansMin) + "");
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
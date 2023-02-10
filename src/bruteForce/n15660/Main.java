package bruteForce.n15660;
// https://www.acmicpc.net/problem/15660
// 테트로미노 두개를 놓고 최대값을 구하는 문제

import java.io.*;
import java.util.*;

public class Main {

    static int n, m, ty, tx;
    static int[][] temp;
    static int[][] map;
    static int[][] visited;
    static int[] ans = new int[2];
    static int tet[][][][] = {

            // style 1
            {
                    {{1,1,1,1}}, {{1}, {1}, {1}, {1}},
            },

            // style 2
            {
                    {{1, 1}, {1,1}},
            },
            // style 3
            {
                    {{1,0},{1,0},{1,1}},
                    {{0,1},{0,1},{1,1}},
                    {{1,1},{0,1},{0,1}},
                    {{1,1},{1,0},{1,0}},

                    {{1,1,1},{1,0,0}},
                    {{1,1,1},{0,0,1}},
                    {{1,0,0},{1,1,1}},
                    {{0,0,1},{1,1,1}},

            },
            // style 4
            {
                    {{1,0},{1,1},{0,1}},
                    {{0,1},{1,1},{1,0}},
                    {{0,1,1},{1,1,0}},
                    {{1,1,0},{0,1,1}},
            },
            // style 5
            {
                    {{1,1,1},{0,1,0}},
                    {{0,1,0},{1,1,1}},
                    {{0,1},{1,1},{0,1}},
                    {{1,0},{1,1},{1,0}},
            }

    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new int[n][m];

        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++) map[i][j] = Integer.parseInt(st.nextToken());
        }
        Arrays.fill(ans, Integer.MIN_VALUE);
        game();
        game2();

        bw.write((ans[0] + ans[1])+"");
        bw.flush();
        br.close();
    }

    static void init1(int[][] currTet, int y, int x) {
        for(int ci = 0; ci < currTet.length;ci++) {
            for(int cj = 0; cj < currTet[ci].length;cj++) {
                if(currTet[ci][cj] == 1 && visited[y+ci][x+cj] >= 1) visited[y+ci][x+cj]--;
            }
        }
    }


    static void game() {
        int sum = 0;
        for(int i=0;i<tet.length;i++) {
            for(int j=0;j<tet[i].length;j++) {
                int[][] currTet = tet[i][j];
                for(int y=0;y<n-currTet.length+1;y++) {
                    for(int x=0;x<m-currTet[0].length+1;x++) {
                        sum = 0;
                        for(int ci = 0; ci < currTet.length;ci++) {
                            for(int cj = 0; cj < currTet[ci].length;cj++) {
                                if(currTet[ci][cj] == 1) {
                                    sum+=map[y+ci][x+cj];
                                    visited[y+ci][x+cj]++;
                                }
                            }
                        }
                        if(ans[0] < sum) {
                            ans[0] = sum;
                            if(temp != null)
                                init1(temp, ty, tx);
                            temp = currTet;
                            ty = y;
                            tx = x;
                        } else init1(currTet, y, x);
                    }
                }
            }
        }
    }

    static void game2() {
        int sum = 0;
        boolean flag = false;
        for(int i=0;i<tet.length;i++) {
            for(int j=0;j<tet[i].length;j++) {
                int[][] currTet = tet[i][j];
                for(int y=0;y<n-currTet.length+1;y++) {
                    for(int x=0;x<m-currTet[0].length+1;x++) {
                        sum = 0;
                        flag = false;
                        for(int ci = 0; ci < currTet.length && !flag ;ci++) {
                            for(int cj = 0; cj < currTet[ci].length;cj++) {
                                if(visited[y+ci][x+cj] == 1) {
                                    flag = true; break;
                                }
                                if(currTet[ci][cj] == 1) sum+=map[y+ci][x+cj];
                            }
                        }
                        if(!flag) ans[1] = ans[1]>=sum ? ans[1] : sum;
                    }
                }
            }
        }
    }
}
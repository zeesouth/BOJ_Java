package greedy.n2873;

import java.io.*;
import java.util.*;

public class Main {
    static int R, C, code;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }
        code = styleCode(R, C);
        if (code != 4) {
            if (code != 3) {
                for (int i = 1; i <= R * C - 1; i++) {
                    if (i % C == 0) sb.append('D');
                    else if ((i / C) % 2 == 0) sb.append('R');
                    else sb.append('L');
                }
            } else {
                for (int i = 1; i <= R * C - 1; i++) {
                    if (i % R == 0) sb.append('R');
                    else if ((i / R) % 2 == 0) sb.append('D');
                    else sb.append('U');
                }
            }
        } else {
            even(sb);
        }
        System.out.println(sb.toString());
        br.close();
    }

    private static void even(StringBuilder sb) {
        int minValue = Integer.MAX_VALUE, minY = 0, minX = 0;
        // 최솟값이 짝수행/홀수열 ex) 2,1
        // 최솟값이 홀수행/짝수열 ex) 3,0
        for(int i=0;i<R;i++) {
            for(int j=0;j<C;j++) {
                if((i%2==0 &&j%2==1) || (i%2==1&&j%2==0)){
                    if(map[i][j] == Math.min(minValue, map[i][j])){
                        minValue = map[i][j];
                        minY = i; minX = j;
                    }
                }
            }
        }

        for(int i=0;i<R;i++) {
            for(int j=0;j<C;j++) {
                if(minY%2 == 1) {
                    if(i == minY) {
                        if(j == minX) {
                            sb.append("RD");
                        } else {
                            if(j<minX && j%2==0) sb.append("DRUR");
                            else if(j>minX && j%2==0) sb.append("RURD");
                        }
                    } else if(i < minY-1) {
                        if(j==C-1) {
                            sb.append('D');
                        } else {
                            if(i%2==0) sb.append('R');
                            else sb.append('L');
                        }
                    } else if(i > minY){
                        if(j==0) sb.append('D');
                        else {
                            if(i%2==0) sb.append('L');
                            else sb.append('R');
                        }
                    }
                } else {
                    if(i == minY) {
                        if(j == minX) {
                            sb.append("DR");
                        } else {
                            if(j<minX && j%2==1) sb.append("DRUR");
                            else if(j > minX && j%2 == 1) sb.append("RURD");
                        }
                    } else if(i < minY) {
                        if(j==C-1) {
                            sb.append('D');
                        } else {
                            if(i%2==0) sb.append('R');
                            else sb.append('L');
                        }
                    } else if(i > minY+1) {
                        if(j==0) sb.append('D');
                        else {
                            if(i%2==0) sb.append('L');
                            else sb.append('R');
                        }
                    }
                }
            }
        }
    }

    static int styleCode(int r, int c) {
        // 홀수 * 홀수
        if (r % 2 == 1 && c % 2 == 1) return 1;
            // 홀수 * 짝수
        else if (r % 2 == 1 && c % 2 == 0) return 2;
            // 짝수 * 홀수
        else if (r % 2 == 0 && c % 2 == 1) return 3;
            // 짝수 * 짝수
        else return 4;
    }

}

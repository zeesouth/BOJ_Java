package dp.n11049;
// https://www.acmicpc.net/problem/11049

import java.io.*;
import java.util.*;

public class Main {
    static int N, dp[][];
    static class Matrix {
        int r, c;
        Matrix(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static Matrix[] m;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        m = new Matrix[N];
        dp = new int[N][N];
        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            m[i] = new Matrix(r, c);
        }

        for(int i=1;i<N;i++) {  // 구간 범위의 크기
            for(int j=0;i+j<N;j++) { // 범위의 시작 지점
                dp[j][i+j] = Integer.MAX_VALUE;
                for(int k=j;k<i+j;k++) {   // 구간 범위를 두 부분으로 나눌 떄의 기준점
                    dp[j][j+i] = Math.min(dp[j][j+i],
                                          dp[j][k]+dp[k+1][i+j]
                                                  +m[j].r*m[k].c*m[i+j].c);

                }
            }
        }
        System.out.println(dp[0][N-1]);
    }
}
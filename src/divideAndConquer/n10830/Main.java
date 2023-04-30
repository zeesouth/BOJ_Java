package divideAndConquer.n10830;

import java.io.*;
import java.util.*;
public class Main {
    static int N;
    static long B;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());
        int A[][] = new int[N][N];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) A[i][j] = Integer.parseInt(st.nextToken());
        }
        int res[][] = pow(A, B);

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) sb.append(res[i][j]).append(" ");
            sb.append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    static int[][] pow(int[][] A, long exponent) {
        if(exponent == 1) return format(A);

        int[][] temp = pow(A, exponent/2);

        if(exponent % 2 == 1) return multiply(multiply(temp, temp), A);
        return multiply(temp, temp);
    }

    static int[][] multiply(int[][] A, int[][] B) {
        int[][] res = new int[N][N];
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                for(int k=0;k<N;k++) res[i][j] += A[i][k]*B[k][j];
            }
        }
        res = format(res);
        return res;
    }

    static int[][] format(int[][] A) {
        int[][] res = new int[N][N];
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) res[i][j] = A[i][j] % 1000;
        }
        return res;
    }
}

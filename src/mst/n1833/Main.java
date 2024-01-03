package mst.n1833;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[][] matrix;
    static int sum, cnt;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        init();
        prim(0);

        System.out.println(sum + " " + cnt);
        System.out.println(sb);
    }


    private static void prim(int start) {
        int[] dist = new int[N];
        int[] prev = new int[N];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        boolean visited[] = new boolean[N];

        for (int i = 0; i < N; i++) {
            int minValue = Integer.MAX_VALUE;
            int minIdx = 0;

            for (int j = 0; j < N; j++) {
                if (visited[j]) continue;
                if (minValue <= dist[j]) continue;
                minValue = dist[j];
                minIdx = j;
            }

            visited[minIdx] = true;
            sum += Math.abs(minValue);

            if (i != start && minValue > 0) {
                cnt++;
                sb.append(prev[minIdx] + 1).append(" ").append(minIdx + 1).append("\n");
            }

            for (int j = 0; j < N; j++) {
                if (visited[j]) continue;
                if (dist[j] <= matrix[minIdx][j]) continue;

                dist[j] = matrix[minIdx][j];
                prev[j] = minIdx;
            }
        }
    }


    private static void init() throws Exception {
        N = Integer.parseInt(br.readLine());
        matrix = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());

                if (matrix[i][j] < 0) {
                    sum -= matrix[i][j];
                    matrix[i][j] = 0;
                }
            }
        }
        sum /= 2;
    }
}

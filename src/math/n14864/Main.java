package math.n14864;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        int[] arr = new int[N + 1];
        for (int i = 1; i <= N; i++) arr[i] = i;

        int x, y;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            arr[x]++;
            arr[y]--;
        }

        boolean[] visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            if (visited[arr[i]]) {
                System.out.println(-1);
                return;
            }
            visited[arr[i]] = true;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) sb.append(arr[i]).append(" ");

        System.out.println(sb);
    }
}

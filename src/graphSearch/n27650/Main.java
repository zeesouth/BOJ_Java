package graphSearch.n27650;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static boolean visited[];
    static String command[];
    static int start, end, cnt;
    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            init();
            bfs();
        }

        System.out.println(sb);
    }

    private static void bfs() {
        visited[start] = true;
        q.clear();
        q.add(start);


        while (!q.isEmpty() && !visited[end]) {
            int curr = q.poll();

            int D = (2 * curr) % 10000;
            int S = curr - 1 >= 0 ? curr - 1 : 9999;
            int L = (curr % 1000) * 10 + curr / 1000;
            int R = (curr % 10) * 1000 + curr / 10;

            if (!visited[D]) {
                q.add(D);
                visited[D] = true;
                command[D] = command[curr] + "D";
            }

            if (!visited[S]) {
                q.add(S);
                visited[S] = true;
                command[S] = command[curr] + "S";
            }

            if (!visited[L]) {
                q.add(L);
                visited[L] = true;
                command[L] = command[curr] + "L";
            }

            if (!visited[R]) {
                q.add(R);
                visited[R] = true;
                command[R] = command[curr] + "R";
            }
        }

        sb.append(command[end]).append("\n");
    }

    private static void init() throws Exception {
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        cnt = Integer.MAX_VALUE;
        visited = new boolean[10000];
        command = new String[10000];

        for (int i = 0; i < 10000; i++) {
            command[i] = "";
        }
    }

}

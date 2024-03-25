package permutationCycleDecomposition.n6505;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static final int MAX_N = 80;
    static ArrayList<Integer>[] circ = new ArrayList[MAX_N];
    static int[] id = new int[MAX_N], p = new int[MAX_N], visited = new int[MAX_N];
    static int N, M;
    static String str;

    public static void main(String[] args) throws Exception {

        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) break;

            simulate();
        }

        System.out.println(sb);
    }

    private static void simulate() throws Exception {
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            p[i] = Integer.parseInt(st.nextToken()) - 1;
            id[i] = 0;
            circ[i] = new ArrayList<>();
            visited[i] = -1;
        }

        str = br.readLine();

        for (int i = 0; i < N; i++) {
            if (visited[i] != -1) continue;

            int idx = i;
            while (true) {
                if (visited[idx] != -1) break;

                visited[idx] = i;
                circ[i].add(idx);
                id[idx] = circ[i].size() - 1;
                idx = p[idx];
            }

            visited[i] = i;
        }

        char[] res = new char[N];
        for (int i = 0; i < N; i++) {
            int k = visited[i];
            int size = circ[k].size();
            int curr = (id[i] + M % size);
            res[circ[k].get(curr % size)] = str.charAt(i);
        }

        sb.append(new String(res)).append("\n");
    }
}

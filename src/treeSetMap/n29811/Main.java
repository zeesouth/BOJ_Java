package treeSetMap.n29811;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int arr[];
    static TreeSet<int[]> NS = new TreeSet<>((o1, o2) -> sort(o1, o2)), MS = new TreeSet<>((o1, o2) -> sort(o1, o2));

    public static void main(String[] args) throws Exception {
        init();
        int K = Integer.parseInt(br.readLine());
        while (K-- > 0) {
            simulate();
        }

        System.out.println(sb);
    }

    private static void simulate() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());

        char order = st.nextToken().charAt(0);

        if (order == 'U') {
            // x번 경로의 인구를 y로 바꾼다.
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (x <= N) {
                NS.remove(new int[]{arr[x], x});
                NS.add(new int[]{arr[x] = y, x});
            } else {
                MS.remove(new int[]{arr[x], x});
                MS.add(new int[]{arr[x] = y, x});
            }

        } else {
            sb.append(NS.first()[1]).append(" ").append(MS.first()[1]).append("\n");
        }
    }

    private static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N + M + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            NS.add(new int[]{arr[i], i});
        }

        st = new StringTokenizer(br.readLine());
        for (int i = N + 1; i <= N + M; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            MS.add(new int[]{arr[i], i});
        }
    }

    static int sort(int[] o1, int[] o2) {
        if (o1[0] != o2[0]) return o1[0] - o2[0];
        return o1[1] - o2[1];
    }
}

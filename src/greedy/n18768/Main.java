package greedy.n18768;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static final int MAX_N = 100_000;
    static int arr[][] = new int[MAX_N][2];
    static int N, K;
    static long ans;

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            init();
            simulate();
            sb.append(ans).append("\n");
        }
        System.out.println(sb);

    }

    private static void simulate() {
        // getDist
        PriorityQueue<int[]> pq1 = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        PriorityQueue<int[]> pq2 = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        ans = 0;
        int diff, a = 0, b = 0;
        for (int i = 0; i < N; i++) {
            diff = Math.abs(arr[i][0] - arr[i][1]);
            if (arr[i][0] > arr[i][1]) {
                a++;
                ans += arr[i][0];
                pq1.add(new int[]{i, diff});
            } else {
                b++;
                ans += arr[i][1];
                pq2.add(new int[]{i, diff});
            }
        }

        int[] curr;
        while (Math.abs(a - b) > K) {
            if (a > b) {
                curr = pq1.poll();
                a--;
                b++;
            } else {
                curr = pq2.poll();
                a++;
                b--;
            }
            ans -= curr[1];
        }
    }

    private static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i][0] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i][1] = Integer.parseInt(st.nextToken());
    }
}

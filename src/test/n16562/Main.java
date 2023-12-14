package test.n16562;

import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 10000;
    static int N, K;
    static int price[] = new int[MAX + 1];
    static ArrayList<Integer> graph[] = new ArrayList[MAX + 1];

    public static void main(String[] args) throws Exception {
        init();
        int ans = play();
        System.out.println(ans == -1 ? "Oh no" : ans);
    }

    private static int play() {
        int ans = 0;
        boolean visited[] = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue;
            visited[i] = true;

            int min = price[i];
            Queue<Integer> q = new LinkedList<>();
            q.add(i);

            while (!q.isEmpty()) {
                int curr = q.poll();
                for (int next : graph[curr]) {
                    if (visited[next]) continue;
                    visited[next] = true;
                    min = Math.min(min, price[next]);
                    q.add(next);
                }
            }
            ans += min;
        }

        return ans > K ? -1 : ans;
    }

    static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            price[i] = Integer.parseInt(st.nextToken());
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }
    }
}

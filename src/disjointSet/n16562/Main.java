package disjointSet.n16562;

import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 10000;
    static int N, K;
    static int price[] = new int[MAX + 1];
    static int parent[] = new int[MAX + 1];

    public static void main(String[] args) throws Exception {
        init();
        int ans = play();
        System.out.println(ans == -1 ? "Oh no" : ans);
    }

    private static int play() {
        int ans = 0;
        boolean visited[] = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            int root = find(i);

            if (visited[root]) {
                visited[i] = true;
                continue;
            }

            visited[root] = true;
            visited[i] = true;
            ans += price[root];
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
            price[i] = Integer.parseInt(st.nextToken());
            parent[i] = i;
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b);
        }
    }

    private static void union(int a, int b) {
        int p1 = find(a);
        int p2 = find(b);

        if (price[p1] >= price[p2]) parent[p1] = p2;
        else parent[p2] = p1;
    }

    private static int find(int i) {
        if (parent[i] == i) return i;
        return parent[i] = find(parent[i]);
    }
}

/*
분리 집합 : https://4legs-study.tistory.com/94
 */
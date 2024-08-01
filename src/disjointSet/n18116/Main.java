package disjointSet.n18116;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int parent[], cnt[];
    static int N;
    static final int MAX = 1000000;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        simulate();
        System.out.println(sb);
    }

    private static void simulate() throws Exception {
        parent = new int[MAX + 1];
        cnt = new int[MAX + 1];

        for (int i = 1; i <= MAX; i++) {
            parent[i] = i;
            cnt[i] = 1;
        }

        N = Integer.parseInt(br.readLine());

        char c;
        int a, b;
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            c = st.nextToken().charAt(0);
            a = Integer.parseInt(st.nextToken());

            if (c == 'I') {
                b = Integer.parseInt(st.nextToken());
                union(a, b);
            } else {
                sb.append(cnt[find(a)]).append('\n');
            }
        }
    }

    private static void union(int a, int b) {
        int A = find(a);
        int B = find(b);

        if (A == B) return;

        if (A < B) {
            parent[B] = A;
            cnt[A] += cnt[B];
        } else {
            parent[A] = B;
            cnt[B] += cnt[A];
        }
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

}

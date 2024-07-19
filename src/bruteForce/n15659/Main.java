package bruteForce.n15659;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    static int[] num, select, operator = new int[4]; // +, -, *, /

    public static void main(String[] args) throws Exception {
        init();
        dfs(0);
        System.out.println(max);
        System.out.println(min);
    }

    private static void dfs(int idx) {
        if (idx == N - 1) {
            calculate();
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operator[i] == 0) continue;

            select[idx] = i;
            operator[i]--;
            dfs(idx + 1);
            operator[i]++;
        }
    }

    private static void calculate() {
        Deque<Integer> oq = new LinkedList<>();
        Deque<Integer> nq = new LinkedList<>();
        nq.offer(num[0]);

        for (int i = 0; i < N - 1; i++) {
            if (select[i] <= 1) {
                oq.offerLast(select[i]);
                nq.offerLast(num[i + 1]);
            } else if (select[i] == 2) {
                nq.offerLast(nq.pollLast() * num[i + 1]);
            } else {
                nq.offerLast(nq.pollLast() / num[i + 1]);
            }
        }

        int res = nq.poll();

        while (!oq.isEmpty()) {
            int o = oq.poll();
            int n = nq.poll();

            if (o == 0) res += n;
            else res -= n;
        }

        max = Math.max(res, max);
        min = Math.min(res, min);
    }

    private static void init() throws Exception {
        N = Integer.parseInt(br.readLine());
        num = new int[N];
        select = new int[N - 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) num[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) operator[i] = Integer.parseInt(st.nextToken());
    }
}

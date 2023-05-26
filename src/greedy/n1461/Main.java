package greedy.n1461;

import java.io.*;
import java.util.*;
public class Main {
    static int M, ans = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> nega = new PriorityQueue<>((o1, o2) -> o2 - o1);
        PriorityQueue<Integer> posi = new PriorityQueue<>((o1, o2) -> o2 - o1);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int pos = Integer.parseInt(st.nextToken());
            if (pos > 0) posi.add(pos);
            else nega.add(-pos);
        }

        int max = 0;
        if (posi.isEmpty()) max = nega.peek();
        else if (nega.isEmpty()) max = posi.peek();
        else max = Math.max(nega.peek(), posi.peek());

        putBook(posi);
        putBook(nega);

        ans -= max;
        System.out.println(ans);
        br.close();
    }

    static void putBook(PriorityQueue<Integer> pq) {
        while (!pq.isEmpty()) {
            int temp = pq.poll();
            for (int i = 0; i < M - 1; i++) {
                pq.poll();
                if (pq.isEmpty()) break;
            }
            ans += temp * 2;
        }
    }
}

package priorityQueue.n30054;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (o1, o2) -> {
                    if (o1[1] != o2[1]) return o1[1] - o2[1];
                    return o1[0] - o2[0];
                }
        );

        int arrive[] = new int[400_000 + 1];
        Arrays.fill(arrive, -1);

        int r, a;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            arrive[r] = a;
            pq.add(new int[]{r, a});
        }

        int time = 1, ans = 0;

        loop:
        while (!pq.isEmpty()) {
            a = arrive[time];

            if (a == -1 || a > time) {
                if (pq.peek()[1] <= time) {
                    int[] node = pq.poll();

                    while (arrive[node[0]] == -1) {
                        if (pq.isEmpty()) break loop;
                        node = pq.poll();
                    }

                    ans = Math.max(ans, time - node[1]);
                    arrive[node[0]] = -1;
                }
            } else {
                ans = Math.max(ans, time - arrive[time]);
                arrive[time] = -1;
            }

            time++;
        }

        System.out.println(ans);
    }
}

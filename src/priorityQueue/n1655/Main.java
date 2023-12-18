package priorityQueue.n1655;
// https://www.acmicpc.net/problem/1655

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> small = new PriorityQueue<>(
                (o1, o2) -> o2 - o1
        );
        PriorityQueue<Integer> big = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            int data = Integer.parseInt(br.readLine());
            if (i == 0) {
                big.add(data);
                sb.append(data).append("\n");
                continue;
            } else if (i == 1) {
                if (big.peek() < data) {
                    small.add(big.poll());
                    big.add(data);
                    sb.append(small.peek()).append("\n");
                } else {
                    sb.append(data).append("\n");
                    small.add(data);
                }
                continue;
            }

            int b = big.peek();
            int a = small.peek();

            if (i % 2 == 0) {
                if (data >= b) {
                    // 4 4
                    sb.append(b);
                    small.add(big.poll());
                    big.add(data);
                } else if (data <= a) {
                    sb.append(a);
                    small.add(data);
                } else {
                    sb.append(data);
                    small.add(data);
                }

            } else {
                if (data >= b) {
                    // 4 / 3
                    big.add(data);
                    sb.append(a);
                } else if (data <= a) {
                    // 4 / 3
                    big.add(small.poll());
                    // 3 / 4
                    small.add(data);
                    sb.append(small.peek());
                } else {
                    // 4 / 3
                    big.add(data);
                    sb.append(a);
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}

package priorityQueue.n1826;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, L, P;
    static PriorityQueue<Node> pq1 = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(simulate());
    }

    private static int simulate() {
        int ans = 0;
        PriorityQueue<Integer> pq2 = new PriorityQueue<>(Collections.reverseOrder());

        while (P < L) {
            while (!pq1.isEmpty() && pq1.peek().a <= P) pq2.offer(pq1.poll().b);

            if (pq2.isEmpty()) return -1;

            ans++;
            P += pq2.poll();
        }

        return ans;
    }

    private static void init() throws Exception {
        N = Integer.parseInt(br.readLine());

        int a, b;
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());   // 주유소까지의 거리 (위치)
            b = Integer.parseInt(st.nextToken());   // 연료 양
            pq1.offer(new Node(a, b));
        }

        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());   // 마을까지의 거리
        P = Integer.parseInt(st.nextToken());   // 시작 연료 양
    }

    static class Node implements Comparable<Node> {
        int a, b;

        Node(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int compareTo(Node node) {
            return this.a - node.a;
        }
    }
}

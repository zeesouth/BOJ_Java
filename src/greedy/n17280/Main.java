package greedy.n17280;
// https://www.acmicpc.net/problem/17280

import java.io.*;
import java.util.*;

public class Main {
    static int T, N, M;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            PriorityQueue<Integer> customer = new PriorityQueue<>((o1,o2)->o2-o1);
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) customer.add(Integer.parseInt(st.nextToken()));

            PriorityQueue<Driver> driver = new PriorityQueue<>(
                    (o1, o2) -> o1.end == o2.end ? o2.start - o1.start : o2.end - o1.end
            );
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                driver.add(new Driver(start, end));
            }

            int ans = 0;
            PriorityQueue<Integer> reverseCustomer = new PriorityQueue<>((o1, o2)->o2-o1);
            while (!driver.isEmpty()) {
                Driver curDir = driver.peek();

                if(reverseCustomer.isEmpty() && customer.isEmpty()) break;

                if(!reverseCustomer.isEmpty()) {
                    int curr = reverseCustomer.poll();
                    if(curr >= curDir.start && curr <= curDir.end) {
                        driver.poll();
                        ans++;
                    }
                    continue;
                }

                while(!customer.isEmpty()) {
                    int curr = customer.peek();
                    if(curr < curDir.start) {
                        if(reverseCustomer.isEmpty()) driver.poll();
                        break;
                    }
                    else if(curr >= curDir.start && curr <= curDir.end) reverseCustomer.add(customer.poll());
                    else customer.poll();
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    static class Driver {
        int start, end;

        Driver(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}

/*
 * i번째 승객은 출발지에서 서쪽으로 x미터 떨어진 곳에 위치한 목적지에 가고 싶어한다.
 * j번째 승객은 출발지에서 서쪽으로 y미터 이상 z미터 이하 떨어진 곳 사이에 가고 싶어한다. (최대 1명 까지만)
 *
 * */
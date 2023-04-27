package greedy.n17280;
// https://www.acmicpc.net/problem/17280
// 소가 길을 건너간 이유 4와 같은 문제
// https://github.com/jeeneee/Algorithms/blob/master/%EB%B0%B1%EC%A4%80/%EA%B7%B8%EB%A6%AC%EB%94%94%20%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98/17280_%EC%B9%B4%ED%92%80%20%EB%A7%A4%EC%B9%AD.cpp

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

            TreeMap<Integer, Integer> customer = new TreeMap<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int val = Integer.parseInt(st.nextToken());
                if(!customer.containsKey(val)) customer.put(val, 0);
                customer.replace(val, customer.get(val)+1);
            }

            PriorityQueue<Driver> driver = new PriorityQueue<>(
                    (o1, o2) -> o1.end == o2.end ? o1.start - o2.start : o1.end - o2.end
            );

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                driver.add(new Driver(start, end));
            }

            int ans = 0;
            while(!driver.isEmpty()) {
                Driver d = driver.poll();
                Integer key = customer.ceilingKey(d.start);
                if(key != null && key <= d.end) {
                    ans++;
                    int val = customer.get(key);
                    if(val-1 == 0) customer.remove(key);
                    else customer.replace(key, val-1);
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
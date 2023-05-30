package greedy.n2258;

import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 가격 오름차순 -> 무게 내림차순
        PriorityQueue<Meat> pq = new PriorityQueue<>(
                (o1, o2) -> o1.price == o2.price ? o2.weight - o1.weight : o1.price - o2.price
        );

        int total_weight = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            pq.add(new Meat(w, p));
            total_weight += w;
        }

        int ans = -1;
        if(total_weight >= M) {
            int beforePrice = 0;
            int weightSum = 0;
            int priceSum = 0;
            int priceMin = Integer.MAX_VALUE;
            while(!pq.isEmpty()) {
                Meat m = pq.poll();
                weightSum += m.weight;

                // 가격이 동일하지 않은 고기의 경우 지불할 고기의 가격이 바뀜 => 가격 초기화
                if(beforePrice != m.price) priceSum = beforePrice = m.price;
                // 가격이 동일한 경우 지불할 가격을 증가
                else priceSum += m.price;

                // 고기 덩어리의 합산이 M이상인 경우 지불할 최소 가격 계산
                if(weightSum >= M) priceMin = Math.min(priceMin, priceSum);
            }
            ans = priceMin;
        }

        System.out.println(ans);
        br.close();
    }

    static class Meat {
        int weight, price;
        Meat(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }
    }
}
package greedy.n8980;
// https://www.acmicpc.net/problem/8980

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(br.readLine());

        Delivery dList[] = new Delivery[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            dList[i] = new Delivery(f, t, v);
        }
        Arrays.sort(dList, (o1, o2) -> o1.to == o2.to ? o1.from - o2.from : o1.to - o2.to);

        int truck[] = new int[N + 1];
        int max, possible, total = 0;

        for (int i = 0; i < M; i++) {
            Delivery currD = dList[i];
            int from = currD.from;
            int to = currD.to;
            int value = currD.value;
            max = 0;

            // 지나가는 구간동안 트럭에 실린 박스의 최댓값 구하기
            for (int j = from; j < to; j++) max = Math.max(max, truck[j]);

            // 보내는 구간까지 트럭에 실을 수 있는 박스 수 : possible
            possible = Math.min(C - max, value);
            total += possible;
            for (int j = from; j < to; j++) truck[j] += possible;
        }

        System.out.println(total + truck[N]);
        br.close();
    }

    static class Delivery {
        int from, to, value;
        Delivery(int from, int to, int value) {
            this.from = from;
            this.to = to;
            this.value = value;
        }
    }
}

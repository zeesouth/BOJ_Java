package prefixSum.n10800;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // balls[i] = i크기인 컬러볼들 저장 (idx,
        ArrayList<Ball>[] balls = new ArrayList[2000 + 1];
        for (int i = 1; i <= 2000; i++) balls[i] = new ArrayList();

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int color = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            balls[size].add(new Ball(i, color));
        }

        int pSum = 0; // 지금까지 계산한 공들의 크기 합
        int[] color_sum = new int[N + 1]; // 색깔별 사이즈 합
        int[] ret = new int[N + 1]; // x번째 공이 잡을 수 있는 공의 사이즈 합
        // i: size
        for (int i = 1; i <= 2000; i++) {
            for (Ball b : balls[i]) ret[b.idx] = pSum - color_sum[b.color];
            pSum += balls[i].size() * i;
            for (Ball b : balls[i]) color_sum[b.color] += i;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(ret[i]).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    private static class Ball {
        int idx, color;

        Ball(int idx, int color) {
            this.idx = idx;
            this.color = color;
        }
    }
}

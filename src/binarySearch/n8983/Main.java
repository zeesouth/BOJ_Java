package binarySearch.n8983;
// https://www.acmicpc.net/problem/8983

import java.io.*;
import java.util.*;
public class Main {
    static int M, N, L, xPos[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        xPos = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) xPos[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(xPos);

        int ans = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int pos = binarySearch(x, y, 0, M-1);
            int dist = Math.abs(xPos[pos] - x) + y;
            if (dist <= L) ans++;
        }
        System.out.println(ans);
        br.close();
    }

    static int binarySearch(int x, int y, int left, int right) {
        int pl = left;
        int pr = right;

        do {
            int pm = (pl + pr) / 2;
            int dist = Math.abs(xPos[pm] - x) + y;
            // 동물의 x좌표와 일치하는 사대가 존재한다면 pm 반환
            if (dist <= L || xPos[pm] == x) return pm;

            // 동물의 x좌표보다 현재 x좌표가 작다면 오른쪽으로 이동할 수록 거리가 좁아지므로
            if (xPos[pm] < x) pl = pm + 1;
            // 동물의 x좌표보다 현재 x좌표가 크다면 왼쪽으로 이동할 수록 거리가 좁아지므로
            else pr = pm - 1;

        } while (pl <= pr);

        if (pl > right) return pr;
        if (pr < left) return pl;

        // 사대와 동물간 거리가 최소인 사대의 인덱스 출력
        return (Math.abs(xPos[pl] - x) < Math.abs(xPos[pr] - x) ? pl : pr);
    }
}

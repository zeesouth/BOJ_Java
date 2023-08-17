package greedy.n2513;

import java.io.*;
import java.util.*;

public class Main {
    static int N, K, S, apart[][];

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(play());
    }

    private static long play() {
        long answer = 0;
        long p = 0;

        int i = 0;
        while (i < apart.length && apart[i][0] < S) {
            p += apart[i][1];
            while (p > 0) {
                p -= K;
                answer += (S - apart[i][0]) * 2;
            }
            i++;
        }

        i = apart.length - 1;
        p = 0;

        while (i >= 0 && apart[i][0] > S) {
            p += apart[i][1];
            while (p > 0) {
                p -= K;
                answer += (apart[i][0] - S) * 2;
            }
            i--;
        }

        return answer;
    }

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        apart = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            apart[i][0] = Integer.parseInt(st.nextToken());
            apart[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(apart, (o1, o2) -> o1[0] - o2[0]);
    }
}

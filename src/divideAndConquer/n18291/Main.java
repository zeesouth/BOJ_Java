package divideAndConquer.n18291;

import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static final long MOD = (long) (1e9 + 7);

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        int N;
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            if (N == 1) sb.append(1);
            else sb.append(pow(2, N - 2));
            sb.append("\n");
        }
        System.out.println(sb);
    }

    // 1부터 N까지 가는 경우는 2^(N-2)
    // N이 최대 10억이므로, [분할정복을 이용한 거듭제곱]으로 시간복잡도를 줄여야 함
    static long pow(int a, int b) {
        if (b == 0) return 1;

        long mul = pow(2, b / 2);

        if (b % 2 == 0) {
            return mul * mul % MOD;
        } else {
            return a * mul * mul % MOD;
        }
    }
}

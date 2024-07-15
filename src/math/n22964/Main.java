package math.n22964;

import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        long N = sc.nextLong();
        long K = sc.nextLong();
        long X = sc.nextLong();

        long r = 1;
        long mod = 998244353;
        for (int i = 0; i < N + K - 2; i++) {
            r *= X;
            r %= mod;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N - K + 1; i++) {
            sb.append((X + 1) * X / 2 % mod * ((X + 1) * X / 2 % mod) % mod * r % mod * K % mod).append(" ");
        }

        System.out.println(sb);
    }
}

package lca.n26216;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    private static int log_base(long num, int base) {
        int ans = 0;

        while (num % base == 0) {
            num /= base;
            ans++;
        }

        return ans;
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        int factor = K + 1;
        long max = (long) Math.pow(K + 1, H);

        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            long A = Long.parseLong(st.nextToken());
            long B = Long.parseLong(st.nextToken());

            if (A >= max || B >= max) sb.append(-1);
            else if (A == B) sb.append(0);
            else {
                int height_A = log_base(A, factor);
                int height_B = log_base(B, factor);
                int LCA = Math.max(height_A, height_B) + 1;
                long p = (long) Math.pow(factor, LCA);

                while (A / p != B / p) {
                    LCA++;
                    p *= factor;
                }

                sb.append(LCA - height_A + LCA - height_B);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}

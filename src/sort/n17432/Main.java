package sort.n17432;

import java.io.*;
import java.util.*;


public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int T, N, cnt;
    static boolean chk[];
    static Stack<Integer> s = new Stack<>();

    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            cnt = Integer.parseInt(st.nextToken());

            chk = new boolean[N + 1];
            s.clear();

            for (int i = N - 1; i >= 0; i--) {
                if (cnt >= i) {
                    chk[N - i] = true;
                    cnt -= i;
                    s.push(N - i);
                }
            }

            for (int i = N; i >= 1; i--) {
                if (!chk[i]) s.push(i);
            }

            while (!s.empty()) {
                sb.append(s.pop()).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}

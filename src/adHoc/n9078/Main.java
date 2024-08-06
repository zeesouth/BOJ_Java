package adHoc.n9078;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int T, N, arr[], cnt;

    public static void main(String[] args) throws Exception {
        while (T-- > 0) {
            T = Integer.parseInt(br.readLine());
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            arr = new int[N];
            cnt = 0;

            for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < i; j++) if (arr[i] < arr[j]) cnt++;
            }

            if (cnt % 2 == 0) sb.append("YES");
            else sb.append("NO");
            sb.append("\n");

        }
    }
}

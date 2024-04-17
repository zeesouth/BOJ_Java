package prefixSum.n20159;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] odd = new int[N / 2 + 1];
        int[] even = new int[N / 2 + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N / 2; i++) {
            odd[i] = odd[i - 1] + Integer.parseInt(st.nextToken());
            even[i] = even[i - 1] + Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        for (int i = 0; i <= N / 2; i++) {
            int o = odd[i];
            int e = even[N / 2] - even[i];

            if (i != 0) {
                e = Math.max(e, even[N / 2 - 1] - even[i - 1]);
            }

            answer = Math.max(answer, o + e);
        }

        System.out.println(answer);
    }
}

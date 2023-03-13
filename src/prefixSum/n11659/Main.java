package prefixSum.n11659;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] sum;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        sum = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            int data = Integer.parseInt(st.nextToken());
            sum[i] = sum[i - 1] + data;
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            sb.append(sum[end]-sum[start-1]).append("\n");
        }

        System.out.println(sb);
    }
}

package dp.n11054;
// https://www.acmicpc.net/problem/11054

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr, dp1, dp2; // 1:증가, 2:감소

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) arr[i] = Integer.parseInt(st.nextToken());
        dp1 = new int[N];
        dp2 = new int[N];

        for(int i=0;i<N;i++) {
            dp1[i] = 1; dp2[N-1-i] = 1;
            for(int j=0;j<i;j++) {
                if (arr[j] < arr[i] && dp1[i] < dp1[j] + 1) dp1[i] = dp1[j] + 1;
                if (arr[N-1-j] < arr[N-1-i] && dp2[N-1-i] < dp2[N-1-j]+1) dp2[N-1-i] = dp2[N-1-j] + 1;
            }

        }

        int max = -1;
        for(int i=0;i<N;i++) max = Math.max(max, dp1[i]+dp2[i]-1);

        bw.write(max+"");
        bw.flush();
        br.close();
    }
}

package prefixSum.n10986;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long res = 0;
        long sum[] = new long[N + 1]; // 합배열
        long cnt[] = new long[M];   //

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            sum[i] = (sum[i - 1] + Integer.parseInt(st.nextToken())) % M;
            if (sum[i] == 0) res++;
            cnt[(int)sum[i]]++;
        }

        for (int i = 0; i < M; i++) {
            if(cnt[i] > 1) res += cnt[i] * (cnt[i]-1) / 2;
        }

        System.out.println(res);
        br.close();
    }
}

/*
(sum[j]-sum[i-1])%M = 0
s[j]%M - s[i-1]%M = 0
s[j]%M = s[i-1]%M;

 */

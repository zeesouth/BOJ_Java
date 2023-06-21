package twoPointer.n13422;

import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int money[] = new int[N];
            st = new StringTokenizer(br.readLine());

            int temp = 0;
            for (int i = 0; i < N; i++) {
                money[i] = Integer.parseInt(st.nextToken());
                if (i >= 0 && i < M) temp += money[i];
            }

            long ans = 0;
            if(N == M) ans = temp < K ? 1 : 0;
            else {
                for (int i = M; i < N + M; i++) {
                    if (temp < K) ans++;
                    temp = money[i % N] + temp - money[(i - M) % N];
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}

/*
도둑이 붙잡히지 않고,
마을을 무사히 빠져나가기 위해 돈을 훔칠 M개의 연속된 집을 고르는 방법의 수


 */

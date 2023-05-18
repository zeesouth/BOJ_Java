package combination.n15824;
// https://www.acmicpc.net/problem/15824

import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N+1], pows = new long[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        pows[0] = 1;
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            pows[i] = (pows[i - 1] * 2) % 1000000007;
        }
        Arrays.sort(arr);

        long res = 0;
        for (int i = 1; i <= N; i++) {
            res += (pows[i - 1] - pows[N - i]) * arr[i];
            res %= 1000000007;
        }
        System.out.println(res);
    }
}
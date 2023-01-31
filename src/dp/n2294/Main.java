package dp.n2294;
// https://www.acmicpc.net/problem/2294

import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 10001;
    static int N, K;
    static Integer[] coin;
    static HashSet<Integer> coinSet;
    static int[] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        coinSet = new HashSet<>();
        for(int i=0;i<N;i++) {
            int value = Integer.parseInt(br.readLine());
            if(value < MAX)
                coinSet.add(value);
        }
        coin = coinSet.toArray(new Integer[coinSet.size()]);
        Arrays.sort(coin);

        dp = new int[K+1];
        Arrays.fill(dp, MAX);
        dp[0] = 0;

        for(int i=0;i<coinSet.size();i++)
            for(int j=coin[i];j<=K;j++) dp[j] = Math.min(dp[j], dp[j-coin[i]]+1);

        if(dp[K] == MAX) bw.write(-1+"");
        else bw.write(dp[K]+"");

        bw.close();
        br.close();
    }
}
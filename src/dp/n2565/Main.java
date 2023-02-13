package dp.n2565;
// https://www.acmicpc.net/problem/2565

import java.io.*;
import java.util.*;

public class Main {
    static int N, ans = 1;
    static int[] dp;
    static int[][] wire;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        dp = new int[N+1];
        wire = new int[N+1][2];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            wire[i][0] = a;
            wire[i][1] = b;
        }

        Arrays.sort(wire, new Comparator<int[]>(){
            public int compare(int[] o1, int[] o2){
                return o1[0]-o2[0];
            }
        });

        for(int i=1;i<=N;i++) {
            dp[i] = 1;
            for(int j=1;j<i;j++) {
                if(wire[j][1] < wire[i][1]) dp[i] = Math.max(dp[i], dp[j]+1);
            }
            ans = Math.max(ans, dp[i]);
        }

        bw.write((N-ans)+"");
        bw.flush();
        br.close();
    }
}

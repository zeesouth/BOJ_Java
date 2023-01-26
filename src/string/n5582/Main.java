package string.n5582;
// https://www.acmicpc.net/problem/5582

import java.io.*;

public class Main {
    static int ans = 0;
    static String a, b;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        a = br.readLine();
        b = br.readLine();
        dp = new int[a.length()+1][b.length()+1];
        for(int i=1;i<=a.length();i++) {
            for(int j=1;j<=b.length();j++) {
                if(a.charAt(i-1) == b.charAt(j-1)) dp[i][j] = dp[i-1][j-1]+1;
                ans = Math.max(dp[i][j], ans);
            }
        }
        bw.write(ans+"");
        bw.flush();
        br.close();
    }
}

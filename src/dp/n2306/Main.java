package dp.n2306;
// https://ujajuck.tistory.com/7


import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String dna = br.readLine();
        int N = dna.length();
        int dp[][] = new int[N][N];
        // dp[i][j] -> i번 idx부터 j번 idx까지 KOI 유전자의 최대 길이

        for (int size = 1; size < N; size++) {
            for (int s = 0; s + size < N; s++) {
                int e = s + size;

                // 1. 어떤 X가 KOI 유전자라면, aXt, gXc도 KOI 유전자
                if ((dna.charAt(s) == 'a' && dna.charAt(e) == 't') || (dna.charAt(s) == 'g' && dna.charAt(e) == 'c')) {
                    dp[s][e] = dp[s + 1][e - 1] + 2;
                }

                // 2. 어떤 X, Y가 KOI 유전자라면, 이 둘을 연결한 XY도 KOI 유젼자
                for (int m = s; m < e; m++) {
                    dp[s][e] = Math.max(dp[s][e], dp[s][m] + dp[m + 1][e]);
                }
            }
        }

        System.out.println(dp[0][N - 1]);
    }
}

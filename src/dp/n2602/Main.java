package dp.n2602;
// https://nahwasa.com/entry/%EB%B0%B1%EC%A4%80-2602-%EC%9E%90%EB%B0%94-%EB%8F%8C%EB%8B%A4%EB%A6%AC-%EA%B1%B4%EB%84%88%EA%B8%B0-BOJ-2602-JAVA

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String[] arr = {br.readLine(), br.readLine()};

        int A = s.length();
        int B = arr[0].length();

        int dp[][][] = new int[2][A + 1][B + 1];

        for (int select = 0; select <= 1; select++) {
            Arrays.fill(dp[select][0], 1);
            for (int i = 1; i <= A; i++) {
                String cur = arr[(i - select) & 1];
                for (int j = 1; j <= B; j++)
                    dp[select][i][j] = dp[select][i][j - 1] + (cur.charAt(j - 1) == s.charAt(i - 1) ? dp[select][i - 1][j - 1] : 0);
            }
        }

        System.out.println(dp[0][A][B] + dp[1][A][B]);
        br.close();
    }
}

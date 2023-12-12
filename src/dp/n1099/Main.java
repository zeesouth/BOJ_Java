package dp.n1099;
// https://www.acmicpc.net/problem/1099

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int N = Integer.parseInt(br.readLine());
        String word[] = new String[N];
        for (int i = 0; i < N; i++) word[i] = br.readLine();

        int dp[] = new int[str.length() + 1];
        Arrays.fill(dp, Integer.MAX_VALUE - 51); // 최대 다른 문자의 개수가 50개 -> 오버플로우 방지를 위해 INT의 MAX값에서 51을 뺴주기

        dp[0] = 0;
        for (int i = 1; i <= str.length(); i++) {
            for (int j = 0; j < i; j++) {
                String splitWord = str.substring(j, i);
                for (int k = 0; k < N; k++) {
                    if (!compareAlphabet(splitWord, word[k])) continue;

                    int diffCnt = diff(splitWord, word[k]);

                    if (j == 0) {
                        dp[i] = Math.min(diffCnt, dp[i]);
                        continue;
                    }

                    dp[i] = Math.min(dp[i], dp[j] + diffCnt);
                }
            }
        }

        if (dp[str.length()] != Integer.MAX_VALUE - 51) {
            System.out.println(dp[str.length()]);
        } else {
            System.out.println(-1);
        }
    }


    private static int diff(String s1, String s2) {
        int cnt = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) cnt++;
        }
        return cnt;
    }

    static boolean compareAlphabet(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        int[] check = new int[26];
        for (int k = 0; k < s1.length(); k++) {
            check[s1.charAt(k) - 'a']++;
            check[s2.charAt(k) - 'a']--;
        }

        for (int k = 0; k < check.length; k++) {
            if (check[k] != 0) return false;
        }
        return true;
    }
}

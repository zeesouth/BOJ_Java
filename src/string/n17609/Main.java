package string.n17609;

import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String str = br.readLine();
            sb.append(check(str)).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    private static int check(String str) {
        int start = 0;
        int end = str.length()-1;
        // 1. 회문 검사
        if (palindrome(str, start, end)) return 0;
        // 2. 유사 회문 검사
        else if (nonePalindrome(str)) return 1;
        return 2;
    }

    private static boolean palindrome(String str, int start, int end) {

        while (start < end) {
            char a = str.charAt(start);
            char b = str.charAt(end);
            if (a == b) {
                start++;
                end--;
            } else return false;
        }
        return true;
    }

    private static boolean nonePalindrome(String str) {
        int start = 0;
        int end = str.length() - 1;
        while (start < end) {
            char a = str.charAt(start);
            char b = str.charAt(end);
            if(a != b) {
                boolean leftDelete = palindrome(str,start+1, end);
                boolean rightDelete = palindrome(str, start, end-1);
                if(leftDelete || rightDelete) return true;
                else return false;
            }
            start++;
            end--;
        }
        return true;
    }
}

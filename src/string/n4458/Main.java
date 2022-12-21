package string.n4458;

import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

//https://www.acmicpc.net/problem/4458
public class Main {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/n4458.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int n = 1; n <= N; n++) {
            StringBuilder sb = new StringBuilder(br.readLine());
            sb.setCharAt(0, sb.charAt(0) >= 'a' && sb.charAt(0) <= 'z' ? (char)(sb.charAt(0)-32) : sb.charAt(0));
            System.out.println(sb);
        }
    }
}

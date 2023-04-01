package kmp.n1305;

import java.io.*;
public class Main {
    static int L, pi[];
    static String s;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        L = Integer.parseInt(br.readLine());
        s = br.readLine();
        System.out.println(L-getLastPI());
    }

    // keypoint : 접두사 - 접미사가 같은 문자열의 최대 길이를 구하는 방법
    // 문자열 안에 똑같은 패턴이 있다면 가장 짧은 패턴을 찾아내는 것
    static int getLastPI() {
        int idx = 0;
        pi = new int[L];
        for(int i=1;i<L;i++) {
            while(idx > 0 && s.charAt(i) != s.charAt(idx)) idx = pi[idx-1];
            if(s.charAt(i) == s.charAt(idx)) pi[i] = ++idx;
        }
        return pi[L-1];
    }
}

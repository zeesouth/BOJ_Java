package kmp.n4354;

import java.io.*;

public class Main {
    static int N, pi[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while(true) {
            String s = br.readLine();
            N = s.length();
            int res = s.length();
            if(res != 0) {
                if(s.charAt(0) == '.') break;
                else {
                    int lastPI = getLastPI(s);
                    if(N%(N-lastPI) == 0) res = N / (N - getLastPI(s));
                    else res = 1;
                }
            }
            sb.append(res).append("\n");
        }
        System.out.println(sb);
    }

    static int getLastPI(String s) {
        int idx = 0;
        pi = new int[N];
        for(int i=1;i<N;i++) {
            while(idx > 0 && s.charAt(i) != s.charAt(idx)) idx = pi[idx-1];
            if(s.charAt(i) == s.charAt(idx)) pi[i] = ++idx;
        }
        return pi[N-1];
    }
}

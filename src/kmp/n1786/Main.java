package kmp.n1786;
// https://www.acmicpc.net/problem/1786

import java.io.*;
import java.util.*;

public class Main {
    static String T, P;
    static int table[];
    static List<Integer> list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = br.readLine();
        P = br.readLine();
        makeTable(P);
        list = new ArrayList<>();
        kmp(table, T, P);

        StringBuilder sb = new StringBuilder();
        sb.append(list.size()).append("\n");
        for(int e : list) sb.append(e).append(" ");
        System.out.println(sb);
    }

    static void makeTable(String pattern) {
        table = new int[pattern.length()];
        int idx = 0;
        for(int i=1;i<pattern.length();i++) {
            while(idx > 0 && pattern.charAt(i) != pattern.charAt(idx)) idx = table[idx-1];
            if(pattern.charAt(i) == pattern.charAt(idx)) table[i] = ++idx;
        }
    }

    // str문자열 내에 pattern이 있는지 없는지 나타내는 함수
    static void kmp(int[] table, String str, String pattern) {
        int idx = 0;
        for(int i=0;i<str.length();i++) {
            while(idx > 0 && str.charAt(i) != pattern.charAt(idx))
                idx = table[idx-1];

            if(str.charAt(i) == pattern.charAt(idx)) {
                if(idx == pattern.length()-1) {
                    list.add(i-pattern.length()+2);
                    idx = table[idx];
                } else idx++;
            }
        }
    }
}

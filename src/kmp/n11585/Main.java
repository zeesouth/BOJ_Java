package kmp.n11585;

import java.io.*;
import java.util.*;
public class Main {
    static int N, tbl[];
    static char r1[], r2[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        r1 = new char[N*2];
        r2 = new char[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            char num = st.nextToken().charAt(0);
            r1[i] = num;
            r1[i+N] = num;

        }
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            char num = st.nextToken().charAt(0);
            r2[i] = num;
        }
        int a = N;
        int b = search(r1, r2);
        int l = lcd(a, b);

        StringBuilder sb = new StringBuilder();
        if(b>a) sb.append("1/1");
        else sb.append(b/l).append("/").append(a/l);
        System.out.println(sb);
    }

    static int lcd(int a, int b) {
        while(b>0) {
            int r = a%b;
            a = b;
            b = r;
        }
        return a;
    }

    static int search(char[] str, char[] pattern) {
        tbl = makeTable(pattern);
        int idx = 0;
        int cnt = 0;
        for(int i=0;i<N*2-1;i++) {
            while(idx > 0 && str[i] != pattern[idx]) idx = tbl[idx-1];
            if(str[i] == pattern[idx]) {
                if (idx == N - 1) {
                    cnt++;          // str의 길이를 두배로 늘려 패턴이 반복되는 횟수 세어주기
                    idx = tbl[idx];
                }
                else idx++;
            }
        }
        return cnt;

    }

    static int[] makeTable(char[] r) {
        int idx = 0;
        int[] table = new int[N];
        for(int i=1;i<N;i++) {
            while(idx > 0 && r[i] != r[idx]) idx = table[idx-1];
            if(r[i] == r[idx]) table[i] = ++idx;
        }
        return table;
    }
}

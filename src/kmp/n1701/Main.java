package kmp.n1701;

import java.io.*;

public class Main {
    static String str;
    static int res = 0, table[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        for(int i=0;i<str.length();i++) res = Math.max(res, makeTable(i));
        System.out.println(res);
    }

    static int makeTable(int start) {
        int pLen = str.length()-start;
        table = new int[pLen];
        int idx = 0, max = 0;
        for(int i=1;i<pLen;i++) {
            while(idx > 0 && str.charAt(i+start) != str.charAt(idx+start)) idx = table[idx-1];
            if(str.charAt(i+start) == str.charAt(idx+start)) {
                max = Math.max(table[i] = idx += 1, max);
            }
        }
        return max;
    }
}

package string.n9935;
// https://www.acmicpc.net/problem/9935

import java.io.*;
import java.util.*;

public class Main {
    static int idx;
    static boolean flag;
    static char[] s, boom;
    static Stack<Character> newOne, temp;
    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        s = br.readLine().toCharArray();
        boom = br.readLine().toCharArray();

        newOne = new Stack<>();
        temp = new Stack<>();

        for(int i=0;i<s.length;i++) {
            if(s[i] == boom[idx]) {
                temp.push(s[i]);
                idx++;
            } else {
                if(temp.size() == boom.length) {
                    idx = 0;
                    temp = new Stack<>();
                } else {


                }
            }

        }


    }
}

package backTracking.n1759;
// https://www.acmicpc.net/problem/1759

import java.io.*;
import java.util.*;

public class Main {
    static int L, C;
    static char[] arr;
    static StringBuilder sb;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[C];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<C;i++) arr[i] = st.nextToken().charAt(0);
        Arrays.sort(arr);
        backTracking(0, 0, new StringBuilder(), 0, 0);
        System.out.println(sb);
    }

    static void backTracking(int idx, int cnt, StringBuilder s, int c, int v) {
        if(cnt == L) {
            if(c >= 2 && v >= 1) sb.append(s).append("\n");
            return;
        }

        for(int i=idx;i<C;i++) {
            s.append(arr[i]);
            if(arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u') backTracking(i+1, cnt+1, s, c, v+1);
            else backTracking(i+1, cnt+1, s, c+1, v);
            s.deleteCharAt(cnt);
        }
    }
}

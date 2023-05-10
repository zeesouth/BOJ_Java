package twoPointer.n16434;

import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long s_atk = Long.parseLong(st.nextToken());
        long s_hp_cur = 0, s_hp_max = 0;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            if (t == 1) {
                s_hp_cur += a * ((h / s_atk) - (h % s_atk != 0 ? 0 : 1));
                s_hp_max = Math.max(s_hp_max, s_hp_cur);
            } else {
                s_atk += a;
                s_hp_cur = Math.max(s_hp_cur - h, 0);
            }
        }
        System.out.println(++s_hp_max);
        br.close();
    }
}

package simulation.n17480;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static String s;
    static int N, select[] = new int['z' - 'a' + 1], total;
    static HashSet<String> set = new HashSet<>();

    public static void main(String[] args) throws Exception {
        init();
        for (int i = total; i <= s.length(); i++) {
            calculate(i);
        }
        System.out.println(set.size());
    }

    private static void calculate(int last) {
        int[] cnt = new int['z' - 'a' + 1];
        for (int i = last - total; i < last; i++) {
            cnt[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i <= 'z' - 'a'; i++) {
            if (cnt[i] != select[i]) return;
        }

        step1(s.substring(last - total, last));
    }

    static void init() throws Exception {
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int c, d;
        for (int i = 0; i < N; i++) {
            c = st.nextToken().charAt(0) - 'a';
            d = Integer.parseInt(st.nextToken());

            select[c] = d;
            total += d;
        }

        s = br.readLine();
    }


    static void step1(String s) {
        // 문자열을 반으로 나눈다
        String s1, s2;

        s1 = s.substring(0, s.length() / 2);
        s2 = s.substring(s.length() / 2);
        step2(s1, s2);

        if (s.length() % 2 == 1 && s.length() > 1) {
            s1 = s.substring(0, s.length() / 2 + 1);
            s2 = s.substring(s.length() / 2 + 1);
            step2(s1, s2);
        }
    }

    static void step2(String s1, String s2) {
        // 한쪽 문자열만 역순으로 바꾼다
        step3(new StringBuilder(s1).reverse().toString(), s2, true);
        step3(s1, new StringBuilder(s2).reverse().toString(), false);
    }

    static void step3(String s1, String s2, boolean flag) {
        // 역순으로 바꾸지 않은 다른 한 쪽의 문자열을 반으로 나눈다
        if (flag) {
            step4(s1, s2.substring(0, s2.length() / 2), s2.substring(s2.length() / 2), flag);

            if (s2.length() % 2 == 1 && s2.length() > 1) {
                step4(s1, s2.substring(0, s2.length() / 2 + 1), s2.substring(s2.length() / 2 + 1), flag);
            }
        } else {
            step4(s2, s1.substring(0, s1.length() / 2), s1.substring(s1.length() / 2), flag);

            if (s1.length() % 2 == 1 && s1.length() > 1) {
                step4(s2, s1.substring(0, s1.length() / 2 + 1), s1.substring(s1.length() / 2 + 1), flag);
            }
        }
    }

    static void step4(String s1, String s2, String s3, boolean flag) {
        // 반으로 나눈 두 개의 문자열 중 한 쪽 문자열만 역순으로 바꾼다.
        StringBuilder tmp;

        tmp = new StringBuilder(s2);
        tmp.reverse();
        if (flag) {
            tmp.insert(0, s1);
            tmp.append(s3);
        } else {
            tmp.append(s3);
            tmp.append(s1);
        }

        set.add(tmp.toString());

        tmp = new StringBuilder(s3);
        tmp.reverse();
        if (flag) {
            tmp.insert(0, s2);
            tmp.insert(0, s1);
        } else {
            tmp.insert(0, s2);
            tmp.append(s1);
        }
        set.add(tmp.toString());
    }
}

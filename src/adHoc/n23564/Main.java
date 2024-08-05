package adHoc.n23564;
// https://actually94.tistory.com/223 참고 ...

import java.util.*;

public class Main {
    static ArrayList<Character> S = new ArrayList<>();
    static ArrayList<Integer> A = new ArrayList<>();
    static char str[];
    static int L;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        str = new Scanner(System.in).nextLine().toCharArray();
        L = str.length;

        dfs(0, 1, 1);

        for (char c : S) sb.append(c);
        sb.append('\n');
        for (int a : A) sb.append(a).append(' ');

        System.out.println(sb);
    }

    private static void dfs(int start, int end, int n) {
        int len = end - start;

        char s;
        int a = 0, cur = len;

        if (n == 1) {
            for (int i = 0; i < L; i++) {
                if (str[i] == str[start]) a++;
                else break;
            }
            S.add(str[start]);
            A.add(a);

            if (a == L) return;

            dfs(0, a, n + 1);
        } else {
            s = str[len];
            while (cur < L) {
                if (s == str[cur]) a++;
                else {
                    S.add(s);
                    A.add(a);
                    dfs(0, cur, n + 1);
                    return;
                }
                cur += len + 1;
            }
            S.add(s);
            A.add(a);
        }
    }
}

package graphSearch.n1039;

import java.io.*;
import java.util.*;

public class Main {
    static char[] str;
    static int K, M;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        str = sc.next().toCharArray();
        K = sc.nextInt();
        M = str.length;
        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(str, 0));
        int ans = -1;
        boolean visited[][] = new boolean[1_000_000 + 1][K + 1];
        visited[Integer.parseInt(new String(str))][0] = true;

        while (!q.isEmpty()) {
            Pair p = q.poll();

            for (int i = 0; i < M - 1; i++) {
                char a = p.num[i];
                for (int j = i + 1; j < M; j++) {
                    char b = p.num[j];
                    if (i == 0 && b - '0' == 0) continue;

                    char[] newStr = Arrays.copyOfRange(p.num, 0, p.num.length);
                    newStr[i] = b;
                    newStr[j] = a;

                    int newNum = Integer.parseInt(new String(newStr));
                    if (visited[newNum][p.cnt + 1]) continue;
                    visited[newNum][p.cnt + 1] = true;

                    if (p.cnt + 1 == K) {
                        ans = Math.max(ans, Integer.parseInt(new String(newStr)));
                        continue;
                    }

                    q.add(new Pair(newStr, p.cnt + 1));
                }
            }
        }

        return ans;
    }

    static class Pair {
        char[] num;
        int cnt;

        Pair(char[] num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
}

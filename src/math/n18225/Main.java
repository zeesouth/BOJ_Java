package math.n18225;

import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        final int INF = 1_000_000;

        int A = sc.nextInt();
        int B = sc.nextInt();
        long x = sc.nextInt();
        long y = sc.nextInt();
        int p = sc.nextInt();
        int q = sc.nextInt();

        boolean flag = false;
        int t1 = 1;
        int t2 = 1;
        long d = 1;
        long ox = x;
        long oy = y;

        int gcd = gcd(A, p);

        while (t1 <= INF && t2 <= INF) {
            if (!flag && x % A == 0) {
                flag = true;
                d = A / gcd;
                oy = y;
                ox = x;
                continue;
            }

            if (flag) {
                if (y % B == 0) {
                    System.out.println(x / A + y / B - 1);
                    break;
                }

                x = ox + d * p;
                y = oy + d * q;
                d += A / gcd;
                t2++;
            } else {
                x = ox + d * p;
                y = oy + d * q;
                d++;
                t1++;
            }
        }

        if (t1 > INF || t2 > INF) System.out.println(-1);
    }

    static int gcd(int a, int b) {
        int tmp, n;

        if (a < b) {
            tmp = a;
            a = b;
            b = tmp;
        }

        while (b != 0) {
            n = a % b;
            a = b;
            b = n;
        }

        return a;
    }
}

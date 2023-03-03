package dp.n15572;
// https://www.acmicpc.net/problem/15572
// https://casterian.net/algo/kitamasa.html

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int N;
    static long M;
    static int[] v, c, a, t, b;
    static int[][] an;

    public static void main (String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextLong();

        v = new int[1001];
        v[0] = 0; v[1] = 1;
        c = new int[1001];
        an = new int[1001][1001];

        a = new int[1001];
        t = new int[1001];
        b = new int[1001];

        for(int i=2;i<=N;i++) v[i] = (2*v[i-1]) % 1999;
        v[N] = (2*v[N]-1)%1999;

        Arrays.fill(c, 1, N+1, 1);
        for(int i=1;i<N;i++) c[1] = (2*c[1]) % 1999;

        System.arraycopy(c, 1, an[1], 1, N);
        for (int j = 2; j <= N; j++)
            for (int i = 1; i <= N; i++)
                an[j][i] = (an[j][i] + an[j-1][i-1] + c[i] * an[j-1][N]) % 1999;

        kitamasa(M);

        int res = 0;
        for (int i = 1; i <= N; i++) res = (res + a[i] * v[i]) % 1999;
        System.out.println(res);
    }

    static void kitamasa(long p) {
        if (p<=N) {
            Arrays.fill(a, 1, N+1, 0);
            a[(int) p] = 1;
            return;
        }
        if(p%2 == 1){
            kitamasa(p-1);
            int last = a[N];
            for (int i=N; i>=1; i--) a[i] = (a[i-1] + c[i] * last) % 1999;
            return;
        }
        kitamasa(p/2);
        Arrays.fill(t, 1, N+1 ,0);
        Arrays.fill(b, 1, N+1, 0);
        for(int j=1;j<=N;j++)
            for(int k=j;k<=N;k++)
                b[j] = (b[j] + a[k] * a[j-k+N]) % 1999;
        for(int i=1;i<=N;i++) {
            for (int j = 1; j <= i - 1; j++)
                t[i] = (t[i] + a[j] * a[i - j]) % 1999;
            for (int j = 1;j <= N; j++)
                t[i] = (t[i] + b[j] * an[j][i]) % 1999;
        }
        System.arraycopy(t, 1, a, 1, N);
    }
}

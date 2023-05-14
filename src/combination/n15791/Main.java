package combination.n15791;
// https://www.acmicpc.net/problem/15791

import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int mod = 1000000007;

        long a = 1, b = 1;
        for(int i=0;i<M;i++) {
            a *= (N-i);
            b *= (M-i);
            a %= mod;
            b %= mod;
        }

        long b2 = 1;
        int exp = mod-2;
        while(exp > 0) {
            if(exp % 2 > 0) {
                b2 *= b;
                b2 %= mod;
            }

            b*=b;
            b %= mod;
            exp /=2;
        }
        long ans = (a * b2) % mod;
        System.out.println(ans);
    }
}
// nCm % mod

/*
페르마의 소정리
: 임의의 소수 p와 서로소인 한 수의 (p-1)승을 p로 나눈 나머지는 무조건 1이다.

*/

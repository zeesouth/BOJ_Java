package divideAndConquer.n1629;

import java.util.*;

public class Main {
    static long C;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        long A = sc.nextLong();
        long B = sc.nextLong();
        C = sc.nextLong();
        System.out.println(pow(A, B));
    }

    static long pow(long A, long exponent) {
        // 지수가 1일 경우 그대로 리턴
        if (exponent == 1) return A % C;

        // 지수의 절반에 해당하는 A^ (exponent/2)를 구한다.
        long temp = pow(A, exponent / 2);

        // 현재 지수가 홀수라면 A를 한번 더 곱해줌
        if (exponent % 2 == 1) return (temp * temp % C) * A % C;

        return temp * temp % C;
    }
}

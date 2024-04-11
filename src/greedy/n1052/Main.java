package greedy.n1052;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        int ans = 0;

        // Integer.bitCount(정수): 주어진 정수에서 true의 bit 개수를 찾는 함수
        while (Integer.bitCount(N) > K) {
            ans += N & (-N);
            N += N & (-N);
        }

        System.out.println(ans);
    }
}

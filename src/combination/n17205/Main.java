package combination.n17205;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();
        String answer = sc.nextLine();

        long[] cnt = new long[N + 1];
        cnt[0] = 1;
        for (int i = 1; i <= N; i++) cnt[i] = cnt[i - 1] * 26 + 1;

        long ans = 0;
        for (int i = 0; i < answer.length(); i++) ans += (answer.charAt(i) - 'a') * cnt[N - i - 1] + 1;

        System.out.println(ans);
    }
}

/*
 * 000 001 011 111 112 113 114 115 116 117 118 119 11,10
 *
 * aab, aac,
 *
 *
 * */
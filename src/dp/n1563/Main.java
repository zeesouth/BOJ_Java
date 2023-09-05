package dp.n1563;

import java.util.*;

public class Main {
    static final int MOD = 1000000;
    static int N;
    static int dp[][][];

    public static void main(String[] args) {
        init();
        System.out.println(dp(0, 0, 0));
    }

    static void init() {
        N = new Scanner(System.in).nextInt();

        // 날짜, 지금까지 지각, 지금까지 연속으로 결석한 횟수
        dp = new int[N][2][3];
    }

    static int dp(int day, int lCnt, int aCnt) {
        // 지각 누적 횟수가 2회가 넘어가거나, 연속으로 3회 결석한 경우
        if (lCnt >= 2 || aCnt >= 3) return 0;

        // 모든 날이 지난 경우: 정상적으로 개근상 받을 수 있는 경우
        if (day == N) return 1;

        if (dp[day][lCnt][aCnt] != 0) return dp[day][lCnt][aCnt];

        return dp[day][lCnt][aCnt] =
                (dp(day + 1, lCnt, 0) +
                        dp(day + 1, lCnt + 1, 0) +
                        dp(day + 1, lCnt, aCnt + 1)) % MOD;
    }
}

/*
- 지각의 횟수 누적
- 결석은 누적 X


 */
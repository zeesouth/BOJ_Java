package prefixSum.n2560;
// https://www.acmicpc.net/problem/2560
import java.util.*;
public class Main {
    static final int REMAIN = 1000;
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int d = sc.nextInt();
        int N = sc.nextInt();

        int dp[] = new int[N + 1];
        dp[0] = 1;
        for (int i = 1; i <= N; i++) {
            if (i < a) dp[i] = dp[i - 1] % REMAIN;
            else if (i < b) dp[i] = (dp[i - 1] + dp[i - a]) % REMAIN;
            else dp[i] = (dp[i - 1] + dp[i - a] - dp[i - b] + REMAIN) % REMAIN;
        }

        int ans = dp[N] % REMAIN;
        if (N - d >= 0) ans = (dp[N] - dp[N - d] + REMAIN) % REMAIN;
        System.out.println(ans);
    }
}
/*
- a일부터 b일 전까지는 무성생식이 가능하므로 a까지는 1마리로 유지
- b일 이후로는 더이상 생식을 하지 않음 -> 더이상 생식을 하지 않는 벌레 수를 빼주면서 더해감
 */

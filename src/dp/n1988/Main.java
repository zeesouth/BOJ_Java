package dp.n1988;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N, B, arr[], dp[][][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        B = Integer.parseInt(str[1]);
        arr = new int[N + 1];

        for (int i = 1; i <= N; i++) arr[i] = Integer.parseInt(br.readLine());

        dp = new int[N + 2][B + 2][2];
        for (int i = 0; i <= N + 1; i++) for (int j = 0; j <= B + 1; j++) dp[i][j][0] = dp[i][j][1] = -1;

        System.out.println(topdown(0, B + 1, 0));
    }

    private static int topdown(int x, int y, int z) {
        if (y < 0) return -1;
        if (y == 0 || x > N) return 0;

        if (dp[x][y][z] != -1) return dp[x][y][z];

        // 회복중
        if (z > 0) {
            return dp[x][y][z] = Math.max(
                    topdown(x + 1, y - 1, 1),       // 잠 - 잠 -> 기회 1번 차감
                    topdown(x + 1, y, 0))              // 잠 - 안잠 -> 기회 그대로
                    + arr[x];    // 두 경우 모두 피로 회복
        }
        // 회복 X
        else {
            return dp[x][y][z] = Math.max(
                    topdown(x + 2, y - 2, 1),       // 안잠 - 잠 -> 첫 번째에서는 피로 회복X, 1번 건너 뛰므로 기회 2번차감
                    topdown(x + 1, y, 0));             // 안잠 - 안잠 -> 기회 그대로
        }
    }
}

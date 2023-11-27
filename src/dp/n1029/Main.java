package dp.n1029;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, arr[][], dp[][][];

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(dfs(1 << (N - 1), 0, 0, 1));
    }

    private static void init() throws Exception {
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        dp = new int[N][1 << N][10];
        // arr[i][j]: j번 예술가가 i번 예술가에게 그림을 살 때 가격
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = s.charAt(j) - '0';
            }
        }
    }

    private static int dfs(int visited, int price, int from, int sum) {
        int res = sum;
        int compSum;

        // 이미 방문했다면
        if (dp[from][visited][price] != 0) return dp[from][visited][price];

        for (int i = 0; i < N; i++) {
            if ((visited & (1 << i)) != (1 << i) & price <= arr[from][N - 1 - i]) {
                if (res < (compSum = dfs(visited | (1 << i), arr[from][N - 1 - i], N - 1 - i, sum + 1))) {
                    res = compSum;
                }
            }
        }

        return dp[from][visited][price] = res;
    }
}

/*
그림의 가격은 0~9로 결정

1. 그림을 팔 떄, 그림을 산 가격보다 같거나 큰 가격으로 팔아야 함
2. 같은 그림을 두 번 이상 사는 것은 불가능합니다.

해법 : DFS + 비트마스킹 + DP를 이용한 문제해결
 */

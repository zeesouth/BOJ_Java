package dp.n12869;

import java.util.Scanner;

public class Main {
    static int N;
    static int arr[] = new int[3];
    static boolean visit[][][] = new boolean[61][61][61];
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        init();
        dfs(arr[0], arr[1], arr[2], 0);
        System.out.println(ans);
    }

    static void init() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
    }

    static void dfs(int scv1, int scv2, int scv3, int cnt) {
        scv1 = Math.max(0, scv1);
        scv2 = Math.max(0, scv2);
        scv3 = Math.max(0, scv3);

        int max = Math.max(Math.max(scv1, scv2), scv3);
        int min = Math.min(Math.min(scv1, scv2), scv3);
        int mid = scv1 + scv2 + scv3 - max - min;

        scv1 = max;
        scv2 = mid;
        scv3 = min;

        // 모든 scv 공격 완료
        if (scv1 == 0 && scv2 == 0 && scv3 == 0) {
            ans = Math.min(cnt, ans);
            return;
        }

        // 이미 계산된 경우의 scv 조합이라면 리턴
        if (visit[scv1][scv2][scv3]) return;
        else visit[scv1][scv2][scv3] = true;

        // 현재 cnt가 ans보다 크다면 리턴
        if (ans < cnt) return;

        dfs(scv1 - 9, scv2 - 3, scv3 - 1, cnt + 1);
        dfs(scv1 - 9, scv2 - 1, scv3 - 3, cnt + 1);
        dfs(scv1 - 3, scv2 - 9, scv3 - 1, cnt + 1);
        dfs(scv1 - 3, scv2 - 1, scv3 - 9, cnt + 1);
        dfs(scv1 - 1, scv2 - 3, scv3 - 9, cnt + 1);
        dfs(scv1 - 1, scv2 - 9, scv3 - 3, cnt + 1);
    }
}

package binarySearch.n15732;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static ArrayList<int[]> arr = new ArrayList<>();
    static int N, K, D, ans;

    public static void main(String[] args) throws Exception {
        init();
        binarySearch();
        System.out.println(ans);
    }

    private static void binarySearch() {
        int left = 0, right = N, mid;

        while (left <= right) {
            mid = (left + right) / 2;
            if (findIndex(mid) >= D) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
    }

    private static long findIndex(int mid) {
        // mid번째 박스에 몇 번째 도토리가 존재하는지 탐색
        long cnt = 0;

        for (int[] rule : arr) {
            if (mid < rule[0]) continue;

            int end = Math.min(mid, rule[1]);
            cnt += (end - rule[0]) / rule[2] + 1;
        }

        return cnt;
    }

    private static void init() throws Exception {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            arr.add(new int[]{A, B, C});
        }
    }
}

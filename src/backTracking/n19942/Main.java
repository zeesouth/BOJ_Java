package backTracking.n19942;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N;
    static int mp, mf, ms, mv;  // 단백질, 지방, 탄수화물, 비타민
    static int arr[][];
    static int ans = Integer.MAX_VALUE;
    static int selectCnt;
    static StringBuilder selected;

    public static void main(String[] args) throws Exception {
        init();
        dfs(0, 0, 0, 0, 0, 0, 0, new int[N]);

        if (ans == Integer.MAX_VALUE) sb.append(-1);
        else {
            sb.append(ans).append("\n");
            sb.append(selected);
        }
        System.out.println(sb);
    }

    private static void dfs(int p, int f, int s, int v, int c, int idx, int cnt, int[] select) {
        if (p >= mp && f >= mf && s >= ms && v >= mv) {
            if (ans > c) {
                ans = c;
                selectCnt = cnt;
                selected = new StringBuilder();
                for (int i = 0; i < cnt; i++) selected.append(select[i]).append(" ");
            } else if (ans == c) {
                StringBuilder newSelected = new StringBuilder();
                for (int i = 0; i < cnt; i++) newSelected.append(select[i]).append(" ");
                if (selected.compareTo(newSelected) > 0) {
                    selected = newSelected;
                }
            }
            return;
        }

        if (idx >= N) return;

        // 현재 인덱스를 포함
        for (int i = idx; i < N; i++) {
            select[cnt] = i + 1;
            dfs(p + arr[i][0],
                    f + arr[i][1],
                    s + arr[i][2],
                    v + arr[i][3],
                    c + arr[i][4],
                    i + 1,
                    cnt + 1,
                    select);
        }
    }

    private static void init() throws Exception {
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        mp = Integer.parseInt(st.nextToken());
        mf = Integer.parseInt(st.nextToken());
        ms = Integer.parseInt(st.nextToken());
        mv = Integer.parseInt(st.nextToken());

        arr = new int[N][5];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}

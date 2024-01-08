package permutationCycleDecomposition.n25577;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<int[]> arr = new ArrayList<>();
    static int adj[];
    static boolean[] visited;
    static int N, ans;

    public static void main(String[] args) throws Exception {
        init();
        pcd();
        System.out.println(ans);
    }

    private static void pcd() {
        // 순열 그래프 생성
        for (int i = 0; i < arr.size(); i++) {
            int bef_idx = arr.get(i)[1];
            int aft_idx = i;
            adj[bef_idx] = aft_idx;
        }

        // 순열 그래프에서 생기는 각각의 사이클들의 크기를 통해 문제를 해결
        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            ans += dfs(i, 0) - 1;
        }
    }

    private static int dfs(int curr, int cnt) {
        visited[curr] = true;
        cnt++;

        int next = adj[curr];
        if (visited[next]) return cnt;

        return dfs(next, cnt);
    }

    static void init() throws Exception {
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(st.nextToken());
            arr.add(new int[]{input, i});
        }

        Collections.sort(arr, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);

        adj = new int[N];
        visited = new boolean[N];
    }
}

// 순열 사이클 분할
// 정답은 각 (사이클의 크기-1)의 합이 된다.
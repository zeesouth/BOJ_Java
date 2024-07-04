package permutationCycleDecomposition.n27531;

import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int a, b, c;
        Node(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        List<Node> v = new ArrayList<>();
        v.add(new Node(0, 0, 0)); // Add a dummy node at index 0

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            v.add(new Node(a, b, c));
        }

        // a, b, c순 오름차순 정렬
        Collections.sort(v.subList(1, v.size()), (o1, o2) -> {
            if (o1.a != o2.a) return Integer.compare(o1.a, o2.a);
            if (o1.b != o2.b) return Integer.compare(o1.b, o2.b);
            return Integer.compare(o1.c, o2.c);
        });

        int result = 0;
        boolean[] visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            if (visited[i]) continue;
            List<Integer> list = new ArrayList<>();
            // 사이클 검사 + 계산 가능한 비용 계산
            dfs(v, visited, list, i);
            // 해당 사이클의 최소 비용 계산
            result += calc(list);
        }

        bw.write(result + "\n");
        bw.flush();
    }

    // 순열 사이클 검사 + 계산 가능한 비용들 계산
    static void dfs(List<Node> v, boolean[] visited, List<Integer> buc, int i) {
        visited[i] = true;
        buc.add(v.get(i).c);
        if (!visited[v.get(i).b]) dfs(v, visited, buc, v.get(i).b);
    }

    // 최소 비용 계산
    static int calc(List<Integer> v) {
        int[] dp = {0, Integer.MAX_VALUE, Integer.MAX_VALUE, v.get(0)};

        for (int i = 1; i < v.size(); i++) {
            int[] ndp = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
            ndp[0] = dp[1];
            ndp[1] = Math.min(dp[0], dp[1]) + v.get(i);
            ndp[2] = dp[3];
            ndp[3] = Math.min(dp[2], dp[3]) + v.get(i);
            dp = ndp;
        }

        return Math.min(Math.min(dp[1], dp[2]), dp[3]);
    }
}

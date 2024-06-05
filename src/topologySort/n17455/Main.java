package topologySort.n17455;

// 참고 : https://cyberflower.github.io/2019/09/10/icpc17455.html

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M;
    static int arr[], ind[], kdh[];
    static boolean nog[];
    static ArrayList<Integer> graph[];
    static String s;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(simulate());
    }

    private static int simulate() {
        kdh = new int[N + 1];
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (nog[i]) continue;
            if (ind[i] == 0) {
                q.offer(i);
                kdh[i] = 1;
            }
        }

        while (!q.isEmpty()) {
            int f = q.poll();

            for (int next : graph[f]) {
                if (nog[next]) continue;
                kdh[next] = Math.max(kdh[next], kdh[f] + 1);
                ind[next]--;
                if (ind[next] == 0) q.offer(next);
            }
        }

        int ans = -1;
        if (!isCycle(N)) {
            for (int i = 1; i <= N; i++) {
                ans = Math.max(ans, kdh[i] / 3);
            }

            ans = ans == 0 ? -1 : 3 * ans;
        }

        return ans;
    }

    private static void init() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        s = br.readLine();

        arr = new int[N + 1];
        ind = new int[N + 1];

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = ch2int(s.charAt(i - 1));
            graph[i] = new ArrayList<>();
        }

        int A, B;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            // B가 A다음 문자열
            if ((arr[A] + 1) % 3 == arr[B]) {
                graph[A].add(B);
                ind[B]++;
            }

            // A가 B다음 문자열
            if (arr[A] == (arr[B] + 1) % 3) {
                graph[B].add(A);
                ind[A]++;
            }
        }

        nog = new boolean[N + 1];

        for (int k = 0; k < 3; k++) {
            for (int i = 1; i <= N; i++) {
                if (nog[i]) continue;
                if (ind[i] == 0 && arr[i] != 0) {
                    nog[i] = true;
                    for (int next : graph[i]) ind[next]--;
                }
            }
        }
    }


    private static int ch2int(char c) {
        if (c == 'K') return 0;
        if (c == 'D') return 1;
        return 2;
    }

    private static boolean isCycle(int N) {
        for (int i = 1; i <= N; i++) {
            if (nog[i]) continue;
            if (kdh[i] == 0) return true;
        }
        return false;
    }
}

package bellmanFord.n1865;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static final int INF = 987654321;
    static int N, M, W;
    private static ArrayList<Pair> graph[];

    public static void main(String[] args) throws Exception {
        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            init();
            sb.append(bellmanFord() ? "YES" : "NO").append("\n");
        }

        System.out.println(sb);
    }

    private static boolean bellmanFord() {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        boolean update = false;

        // (정점의 개수-1) 동안 최단거리 초기화 작업을 반복함
        for (int i = 1; i < N; i++) {
            update = false;

            // 최단거리 초기화
            for (int j = 1; j <= N; j++) {
                for (Pair p : graph[j]) {
                    if (dist[p.e] > p.t + dist[j]) {
                        dist[p.e] = p.t + dist[j];
                        update = true;
                    }
                }
            }

            // 더 이상 최단거리 초기화가 일어나지 않을 경우 반복문 종료
            if (!update) break;
        }

        if (update) {
            for (int i = 1; i <= N; i++) {
                for (Pair p : graph[i]) {
                    // 음수 사이클이 발생하는 지 확인
                    if (dist[p.e] > dist[i] + p.t) return true;
                }
            }
        }

        return false;
    }

    static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            graph[s].add(new Pair(e, t));
            graph[e].add(new Pair(s, t));
        }


        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            graph[s].add(new Pair(e, -t));
        }
    }


    static class Pair {
        int e, t;

        Pair(int e, int t) {
            this.e = e;
            this.t = t;
        }
    }
}

/*
음의 사이클이 존재하기만 하면
출발 노드가 아니더라도 조건을 만족시킬 수 있음

풀이 방식은
벨만 포드
1. 모든 정점을 출발점으로 조사해서 음의 사이클이 발생하는지
2. 아무 정점을 출발점으로 조사해서 음의 사이클이 발생하는지
 */
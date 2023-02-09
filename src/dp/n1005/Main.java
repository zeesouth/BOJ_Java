package dp.n1005;
// https://www.acmicpc.net/problem/1005

import java.io.*;
import java.util.*;

public class Main {
    static int T, N, K, W;
    static int[] cost, inputCnt, remain;
    static ArrayList<Integer>[] graph;

    static Queue<Integer> q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            cost = new int[N+1];
            st = new StringTokenizer(br.readLine());
            for(int i=1;i<=N;i++) cost[i] = Integer.parseInt(st.nextToken());
            graph = new ArrayList[N+1];
            remain = new int[N+1];
            inputCnt = new int[N+1];
            for(int i=1;i<=N;i++) {
                graph[i] = new ArrayList<>();
                remain[i] = Integer.MIN_VALUE;
            }
            for(int i=0;i<K;i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
                inputCnt[b]++;  // 진입차수 증가
            }

            W = Integer.parseInt(br.readLine());

            q = new LinkedList<>();
            for(int i=1;i<=N;i++) if(inputCnt[i] == 0) q.add(i);
            while(!q.isEmpty()) {
                int curr = q.poll();
                for(int k : graph[curr]) {
                    inputCnt[k]--;
                    remain[k] = Math.max(remain[k], cost[curr]);
                    if(inputCnt[k] == 0) {
                        q.add(k);
                        cost[k] += remain[k];
                    }
                }
            }
            sb.append(cost[W]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        br.close();
    }
}

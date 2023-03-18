package dijkstra.n10282;
// https://www.acmicpc.net/problem/10282

import java.io.*;
import java.util.*;

class Node {
    int idx, second;
    Node(int idx, int second) {
        this.idx = idx;
        this.second = second;
    }
}

public class Main {
    static int T, N, D, C, cnt, ans;
    static ArrayList<Node>[] graph;
    static int[] dist;
    static boolean[] visit;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for(int t=0;t<T;t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            graph = new ArrayList[N+1];
            dist = new int[N+1];
            visit = new boolean[N+1];

            for(int i=1;i<=N;i++) {
                graph[i] = new ArrayList<>();
                dist[i] = Integer.MAX_VALUE;
            }

            for(int i=0;i<D;i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                graph[b].add(new Node(a, s));
            }

            Queue<Node> q = new LinkedList<>();
            q.add(new Node(C, 0));
            dist[C] = 0;
            cnt = 0;
            ans = 0;

            while(!q.isEmpty()) {
                Node curr = q.poll();

                if(!visit[curr.idx]) {
                    visit[curr.idx] = true;
                    cnt++;
                }

                for(Node next : graph[curr.idx]) {
                    if(dist[next.idx] > curr.second + next.second) {
                        dist[next.idx] = curr.second+next.second;
                        q.add(new Node(next.idx, dist[next.idx]));
                    }
                }
            }
            for(int i=1;i<=N;i++)
                if(dist[i] != Integer.MAX_VALUE)
                    ans = Math.max(dist[i], ans);
            sb.append(cnt).append(" ").append(ans).append("\n");
        }

        System.out.println(sb);
    }
}

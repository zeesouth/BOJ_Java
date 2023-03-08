package topologySort.n1516;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node {
    int idx;
    int value;
    Node(int idx, int value){
        this.idx = idx;
        this.value = value;
    }
}

public class Main {
    static int N;
    static ArrayList<Node>[] graph;
    static boolean[] visited;
    static int[] ans, indegree;


    static PriorityQueue<Node> pq;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N+1];
        visited = new boolean[N+1];
        ans = new int[N+1];
        indegree = new int[N+1];
        for(int i=1;i<=N;i++) graph[i] = new ArrayList<>();
        pq = new PriorityQueue<>((o1, o2)->o1.value-o2.value);

        for(int i=1;i<=N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = -1;
            boolean flag = false;
            while(st.hasMoreTokens()) {
                int curr = Integer.parseInt(st.nextToken());
                if(curr == -1) {
                    if(!flag) pq.add(new Node(i, time));
                    break;
                }
                if(time==-1) time = curr;
                else {
                    if(!flag) flag = true;
                    indegree[i]++;
                    graph[curr].add(new Node(i, time));
                }
            }
        }
        while(!pq.isEmpty()) {
            Node curr = pq.poll();
            visited[curr.idx] = true;
            ans[curr.idx] = curr.value;
            for(Node next : graph[curr.idx]) {
                indegree[next.idx]--;
                if(indegree[next.idx] == 0 && !visited[next.idx]) {
                    pq.add(new Node(next.idx, curr.value + next.value));
                }
            }
        }

        for(int i=1;i<=N;i++) bw.write(ans[i]+"\n");
        bw.flush();
        br.close();
    }
}

package graphSearch.n2606;

import java.io.*;
import java.util.*;

public class Main {

    static int C, N;
    static boolean[] visited;   // 방문한 컴퓨터 체킹
    static HashSet<Integer>[] nodes;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/n2606.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        C = Integer.parseInt(br.readLine());    // 컴퓨터의 수
        N = Integer.parseInt(br.readLine());    // 네트워크 수
        visited = new boolean[C+1];
        nodes = new HashSet[C+1];
        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(nodes[a] == null) nodes[a] = new HashSet<>();
            nodes[a].add(b);

            if(nodes[b] == null) nodes[b] = new HashSet<>();
            nodes[b].add(a);
        }

        int answer = 0;     // 바이러스에 걸린 컴퓨터 수 (1은 이미 감염)
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        visited[1] = true;

        while(!stack.isEmpty()) {
            int c = stack.pop();

            for(int child : nodes[c]) {
                if(visited[c] && !visited[child]) {
                    stack.push(child);
                    visited[child] = true;
                    answer++;
                }
            }
        }
        System.out.println(answer);

        br.close();
    }
}

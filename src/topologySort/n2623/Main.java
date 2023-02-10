package topologySort.n2623;
// https://www.acmicpc.net/problem/2623

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, cnt;
    static boolean[][] check;
    static int[] inDegree;
    static Queue<Integer> q;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        check = new boolean[N+1][N+1];
        inDegree = new int[N+1];

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            int a = -1;
            for(int j=0;j<cnt;j++){
                int curr = Integer.parseInt(st.nextToken());
                if(a == -1) a = curr;
                else {
                    if(!check[a][curr]) {
                        check[a][curr] = true;
                        inDegree[curr]++;
                    }
                    a = curr;
                }
            }
        }

        q = new LinkedList<>();
        for(int i=1;i<=N;i++) if(inDegree[i] == 0) q.add(i);

        cnt = 0;
        while(!q.isEmpty()) {
            int curr = q.poll();
            cnt++;
            sb.append(curr).append("\n");

            for(int i=1;i<=N;i++) {
                if(check[curr][i]) {
                    inDegree[i]--;
                    check[curr][i] = false;
                    if(inDegree[i] == 0) q.add(i);
                }
            }
        }

        if(cnt != N) bw.write(0+"");
        else bw.write(sb.toString());
        bw.flush();
        br.close();

    }
}

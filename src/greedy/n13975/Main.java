package greedy.n13975;
// https://www.acmicpc.net/problem/13975

import java.io.*;
import java.util.*;

public class Main {
    static int T, K;
    static long sum;
    static int[] arr;
    static PriorityQueue<Long> pq;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++) {
            K = Integer.parseInt(br.readLine());
            arr = new int[K];
            sum = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            pq = new PriorityQueue<Long>();
            for(int i=0;i<K;i++) pq.add(Long.parseLong(st.nextToken()));

            while(!pq.isEmpty()) {
                long a = pq.poll();
                if(pq.isEmpty()) break;
                long b = pq.poll();
                sum += (a+b);
                pq.add(a+b);
            }
            sb.append(sum).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        br.close();
    }
}
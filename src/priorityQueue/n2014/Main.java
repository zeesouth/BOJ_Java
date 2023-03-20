package priorityQueue.n2014;

import java.io.*;
import java.util.*;

public class Main {
    static int K, N;
    static long ans;
    static long[] arr;
    static PriorityQueue<Long> pq;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        arr = new long[K];
        N = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<K;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            pq.add(arr[i]);
        }

        ans = 0;
        while(N-- > 0) {
            ans = pq.poll();
            for(int i=0;i<K;i++) {
                // 정답이 2^31보다 작아야함
                if ((ans * arr[i]) >= ((long) 2 << 30)) break;

                pq.offer(ans * arr[i]);

                // 현재 소수로 나누어 떨어지는 수가 발견되면 종료
                // 중복된 값을 배제하기 위함
                if (ans % arr[i] == 0) break;
            }
        }
        System.out.println(ans);
    }
}
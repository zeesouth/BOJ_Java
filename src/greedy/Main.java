package greedy;

import java.util.*;
import java.io.*;

public class Main {
    static int N, answer;
    static int[] cal;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for(int i=0;i<N;i++) queue.add(Integer.parseInt(br.readLine()));
        cal = new int[N];
        cal[0] = queue.poll();

        for(int i=1;i<N;i++) {
            cal[i] = cal[i-1]+queue.poll();
            answer += cal[i];
        }

        System.out.println(answer);
        br.close();
    }
}

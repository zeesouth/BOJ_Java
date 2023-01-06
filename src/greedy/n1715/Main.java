package greedy.n1715;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    static int N, answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for(int i=0;i<N;i++) queue.add(Integer.parseInt(br.readLine()));

        while(N>=2 && !queue.isEmpty()) {
            int curr1 = queue.poll();
            int curr2 = queue.poll();
            answer += (curr1+curr2);

            if(queue.isEmpty()) break;
            else queue.add(curr1+curr2);
        }
        System.out.println(answer);
        br.close();
    }
}

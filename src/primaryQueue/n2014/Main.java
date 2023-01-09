package primaryQueue.n2014;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int K, N, count, answer;
    static int[] arr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        arr = new int[K];
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<K;i++) arr[i] = Integer.parseInt(st.nextToken());

        count = 0;
        answer = arr[0];

        while(count < N) {
            int curr = answer;
            for(int i=0;i<arr.length;i++) {
                while(curr%arr[i] == 0) curr /= arr[i];
                if(curr == 1) break;
            }
            if(curr == 1) count++;
            answer++;
        }
        System.out.println(answer-1);
    }
}

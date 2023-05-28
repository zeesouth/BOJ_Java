package greedy.n13164;
// https://www.acmicpc.net/problem/13164
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int height[] = new int[N];
        int gap[] = new int[N-1];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            height[i] = Integer.parseInt(st.nextToken());
            if(i >= 1) gap[i-1] = height[i]-height[i-1];
        }
        Arrays.sort(gap);

        int res = 0;
        for(int i=0;i<N-K;i++) res += gap[i];
        System.out.println(res);
        br.close();
    }
}
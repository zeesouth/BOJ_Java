package prefixSum.n2559;
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int arr[] = new int[N+1];
        for(int i=1;i<=N;i++) arr[i] = arr[i-1] + Integer.parseInt(st.nextToken());

        int ans = Integer.MIN_VALUE;
        for(int i=K;i<=N;i++) ans = Math.max(ans, arr[i]-arr[i-K]);
        System.out.println(ans);
        br.close();
    }
}

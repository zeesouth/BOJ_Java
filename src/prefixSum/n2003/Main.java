package prefixSum.n2003;
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int arr[] = new int[N+1];
        for(int i=1;i<=N;i++) arr[i] = arr[i-1] + Integer.parseInt(st.nextToken());

        int cnt = 0;
        for(int i=0;i<N;i++) {
            for(int j=i;j<=N;j++) {
                if(arr[j]-arr[i] >= M) {
                    if(arr[j]-arr[i] == M) cnt++;
                    break;
                }
            }
        }
        System.out.println(cnt);
        br.close();
    }
}

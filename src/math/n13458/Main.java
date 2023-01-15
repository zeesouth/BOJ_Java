package math.n13458;

import java.io.*;
import java.util.*;

public class Main {
    static int N, B, C;
    static long ans = 0;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) arr[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        br.close();
        ans+=N;
        for(int i=0;i<N;i++) {
            arr[i] -= B;
            if(arr[i] > 0) {
                if(arr[i]%C == 0) ans += (arr[i]/C);
                else ans += (arr[i]/C)+1;
            }
        }
        bw.write(ans+"\n");
        bw.flush();
    }
}

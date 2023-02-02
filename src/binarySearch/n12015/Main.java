package binarySearch.n12015;

import java.io.*;
import java.util.*;

public class Main {
    static int N, end;
    static int[] arr;
    static int[] LIS;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) arr[i] = Integer.parseInt(st.nextToken());

        LIS = new int[N];
        LIS[0] = arr[0];
        end = 1;

        for(int i=1;i<N;i++) {
            int curr = arr[i];
            if(curr > LIS[end-1]) LIS[end++] = curr;
            else {
                int s = 0;
                int e = end;
                while(s < e) {
                    int mid = (s+e) >>> 1;
                    if(LIS[mid] < curr) s = mid+1;
                    else e = mid;
                }
                LIS[s] = curr;
            }
        }

        bw.write(end+"");
        bw.flush();
        br.close();
    }
}

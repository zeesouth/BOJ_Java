package binarySearch.n20917;

import java.io.*;
import java.util.*;

public class Main {
    static int T, n, s, area[];
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            area = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) area[i] = Integer.parseInt(st.nextToken());
            Arrays.sort(area);

            int start = 1;
            int end = area[n-1];

            while(start <= end) {
                int mid = (start+end)/2;
                int left = area[0];
                int cnt = 1;
                for(int i=1;i<n;i++) {
                    int right = area[i];
                    if(Math.abs(right-left) >= mid) {
                        cnt++;
                        left = right;
                    }
                }
                if(cnt >= s) start = mid+1;
                else end = mid-1;
            }
            sb.append(end).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

}


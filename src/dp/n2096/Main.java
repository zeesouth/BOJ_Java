package dp.n2096;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] minDp, maxDp;
    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        minDp = new int[3];
        maxDp = new int[3];

        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(i==0) {
                maxDp[0] = minDp[0] = a;
                maxDp[1] = minDp[1] = b;
                maxDp[2] = minDp[2] = c;
            } else {
                // 최댓값
                int beforeMax0 = maxDp[0], beforeMax2 = maxDp[2];
                maxDp[0] = Math.max(maxDp[0], maxDp[1]) + a;
                maxDp[2] = Math.max(maxDp[1], maxDp[2]) + c;
                maxDp[1] = Math.max(Math.max(beforeMax0, maxDp[1]), beforeMax2) + b;

                int beforeMin0 = minDp[0], beforeMin2 = minDp[2];
                minDp[0] = Math.min(minDp[0], minDp[1]) + a;
                minDp[2] = Math.min(minDp[1], minDp[2]) + c;
                minDp[1] = Math.min(Math.min(beforeMin0, minDp[1]), beforeMin2) + b;
            }
        }
        bw.write(Math.max(maxDp[0], Math.max(maxDp[1], maxDp[2]))+" ");
        bw.write(Math.min(minDp[0], Math.min(minDp[1], minDp[2]))+"");
        bw.flush();
        bw.close();
        br.close();
    }
}

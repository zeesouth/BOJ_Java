package binarySearch.n1300;

import java.io.*;
public class Main {
    static int N, k;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        long left = 1;
        long right = k;

        while (left < right) {
            long mid = (left + right) / 2;
            long cnt = 0;

            for (int i = 1; i <= N; i++) cnt += Math.min(mid / i, N);

            if(k<=cnt) right = mid;
            else left = mid+1;
        }
        System.out.println(left);
        br.close();
    }
}

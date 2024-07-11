package bruteForce.n1954;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M;
    static int arr[][];

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        arr = new int[N][2];

        int max = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            max = Math.max(arr[i][1], max);
        }

        M = Integer.parseInt(br.readLine());

        int ans = 0;
        loop:
        for (int i = 1; i <= N * 10000; i++) {
            int sum = 0;
            for (int j = 0; j < N; j++) {
                if (i == arr[j][1]) continue loop;
                if ((i - arr[j][1]) % arr[j][0] != 0) continue loop;
                sum += (i - arr[j][1]) / arr[j][0];
            }

            if (sum == M) {
                ans = i;
                break;
            }
        }

        System.out.println(ans);
    }
}

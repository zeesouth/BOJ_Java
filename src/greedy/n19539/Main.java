package greedy.n19539;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        int arr[] = new int[N];

        st = new StringTokenizer(br.readLine());

        int sum = 0;
        for (int i = 0; i < N; i++) sum += (arr[i] = Integer.parseInt(st.nextToken()));

        int cnt = 0;
        if (sum % 3 == 0) {
            for (int node : arr) cnt += node / 2;

            if (cnt >= sum / 3) {
                System.out.println("YES");
                return;
            }
        }

        System.out.println("NO");
    }
}

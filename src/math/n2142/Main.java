package math.n2142;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M, arr[][];

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            arr = new int[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }


            int min = Math.min(N, M);
            boolean flag = true;
            for (int i = 0; i < min - 1; i++) {
                for (int j = 0; j < min - 1; j++) {
                    for (int l = j + 1; l < min; l++) {
                        if (arr[i][j] + arr[i + 1][l] > arr[i][l] + arr[i + 1][j]) {
                            flag = false;
                            break;
                        }
                    }
                }
            }

            if (flag) sb.append("YES\n");
            else sb.append("NO\n");
        }

        System.out.println(sb);
    }
}

/*
1 ≤ i < k ≤ m, 1 ≤ j < l ≤ n를 만족하는 모든 i, j, k, l 에 대해서
A[i][j] + A[k][l] ≤ A[i][l] + A[k][j]가 성립
A[i][j] - A[i][l] ≤ A[k][j] - A[k][l]가 성립
-> y값에 대해서는 y,y+1 관계만 비교해서 보면 된다.
 */
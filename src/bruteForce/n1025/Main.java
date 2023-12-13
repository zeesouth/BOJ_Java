package bruteForce.n1025;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int arr[][] = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                int n = s.charAt(j) - '0';
                arr[i][j] = n;
            }
        }

        int ans = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int di = -(N - 1); di < N; di++) {
                    for (int dj = -(M - 1); dj < M; dj++) {
                        if (di == 0 && dj == 0) {
                            if (isPowNum(arr[i][j])) ans = Math.max(ans, arr[i][j]);
                            continue;
                        }

                        int t = 0;
                        int newI = i;
                        int newJ = j;

                        while (newI >= 0 && newI < N && newJ >= 0 && newJ < M) // 범위 내에서만 제곱수 찾기 가능
                        {
                            t = 10 * t + arr[newI][newJ]; // 기존에 담긴 숫자가 있다면 *10해주고 더하기
                            if (isPowNum(t)) ans = Math.max(ans, t);    // 완전 제곱수인지 확인
                            newI += di;
                            newJ += dj;
                        }

                    }
                }
            }
        }
        System.out.println(ans);
    }

    static boolean isPowNum(int num) {
        int sqrt = (int) Math.sqrt(num);
        return sqrt * sqrt == num;
    }
}

/*
등차수열을 이룰 수 있는 형태
1. 왼쪽 아래에서 위로 올라가는 형태
2. 왼쪽 위에서 아래로 내려가는 형태
3. 오른쪽 아래에서 위로 올라가는 형태
4. 오른쪽 위에서 아래로 내려가는 형태
5. 행만 움직이는 형태
6. 열만 움직이는 형태
 */
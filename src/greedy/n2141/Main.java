package greedy.n2141;
// https://www.acmicpc.net/problem/2141

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static Village[] village;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        village = new Village[N];
        long res = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            res += a;
            village[i] = new Village(x, a);
        }
        Arrays.sort(village, (o1, o2) -> o1.x == o2.x ? o1.a - o2.a : o1.x - o2.x);

        long sum = 0;
        for (Village v : village) {
            sum += v.a;
            if (sum >= (res + 1) / 2) { // 전체 인구수의 반절이 넘어가는 위치에 설치
                System.out.println(v.x);
                break;
            }
        }
        br.close();
    }

    static class Village {
        int x, a;
        Village(int x, int a) {
            this.x = x;
            this.a = a;
        }
    }
}

// 우체국 위치 기준으로 "마을에 위치한 인구 수가 비슷해야 한다"
// 가능한 경우에는 "더 작은 위치를 출력하도록 한다".


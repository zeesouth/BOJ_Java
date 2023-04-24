package binarySearch.n1300;
// https://www.acmicpc.net/problem/1300
// https://st-lab.tistory.com/281
import java.io.*;
public class Main {
    static int N, k;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        long left = 1;
        long right = k;

        // B[K] = x에 대해 x는 left<= x < right 안의 수
        while (left < right) {
            long mid = (left + right) / 2;  // 임의의 x를 left와 right의 중간 값으로 잡는다.
            long cnt = 0;

            // 각 단 별 mid보다 작거나 같은 개수의 합을구한다
            for (int i = 1; i <= N; i++) cnt += Math.min(mid / i, N);

            // 임이의 x(mid)보다 작거나 같은 수의 개수 (count)와 K값을 비교
            if(k<=cnt) right = mid; // 하한 경계선(수의 범위 중 left)를 높인다.
            else left = mid+1;      // 상한 경계선(수의 범위 중 right)를 낮춘다.
        }
        System.out.println(left);
        br.close();
    }
}

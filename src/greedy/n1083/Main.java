package greedy.n1083;
// https://www.acmicpc.net/problem/1083

import java.io.*;
import java.util.*;
public class Main {
    static int N, arr[], S;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(br.readLine());

        for (int i = 0; i < N && 0 < S; i++) {
            // 최댓값, 최댓값 인덱스
            int max = -1, idx = -1;
            // 교환 한도 내에서 오른쪽으로 가장 큰 수 찾기
            for (int j = i; j < N && j <= i + S; j++) {
                if (max < arr[j]) { // 범위 안에서 최댓값과 그 인덱스를 찾기
                    max = arr[j];
                    idx = j;
                }
            }

            // 가장 큰 수를 범위 내 맨 앞에 놓고 나머지도 옮겨주기
            // 옮긴 횟수 만큼 교환 횟수 차감
            for (int j = idx; j > i; j--) {
                int tmp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = tmp;
                S--;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) sb.append(arr[i]).append(" ");
        System.out.println(sb);
        br.close();
    }
}

/*
1. 첫 번째 칸 부터 시작
2. 교환 한도 내에서 오른쪽으로 가장 큰 수 찾기
3. 가장 큰 수를 첫번째 칸에 옮겨놓고 나머지를 밀어주기.
4. 교환 횟수를 이동한 숫자만큼 제거하고 다음 칸으로 이동 후 두번째 칸부터 반복
 */

package twoPointer.n1253;

import java.io.*;
import java.util.*;

public class Main {

    static int N, goodNumber;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);
        goodNumber = 0;

        for (int i = 0; i < arr.length; i++) {
            int findNumber = arr[i];    // 오름차순 정렬된 배열을 하나씩 조회
            int start = 0;
            int end = arr.length - 1;
            int sum = 0;

            while (start < end) {
                sum = arr[start] + arr[end];
                if (sum == findNumber) {
                    // 자기 자신을 제외한 인덱스는 넘어가야 함
                    if (i == start) start++;
                    else if (i == end) end--;
                    else {
                        goodNumber++;
                        break;
                    }
                }
                // 두 수의 합이 크면 end인덱스 -1, 두 수의 합이 작으면 start인덱스 +1
                if (arr[start] + arr[end] > findNumber) end--;
                else if (arr[start] + arr[end] < findNumber) start++;
            }
        }

        bw.write(goodNumber + "");
        bw.flush();
    }
}

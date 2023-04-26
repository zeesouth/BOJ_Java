package binarySearch.n2632;
// https://www.acmicpc.net/problem/2632

import java.io.*;
import java.util.*;
public class Main {
    static int order, m, n, pizzaA[], pizzaB[], ans = 0;
    static ArrayList<Integer> AList = new ArrayList<>(), BList = new ArrayList<>();
    static boolean check[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        order = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        pizzaA = new int[m];
        for(int i=0;i<m;i++) pizzaA[i] = Integer.parseInt(br.readLine());
        pizzaB = new int[n];
        for(int i=0;i<n;i++) pizzaB[i] = Integer.parseInt(br.readLine());

        for(int i=0;i<m;i++) {
            // 체크 배열 초기화
            check = new boolean[m];
            // 첫 번째 조각 담기
            check[i] = true;
            getSum(pizzaA[i], i, i+1, pizzaA, AList);
        }

        for(int i=0;i<n;i++) {
            // 체크 배열 초기화
            check = new boolean[n];
            // 첫 번째 조각 담기
            check[i] = true;
            getSum(pizzaB[i], i, i+1, pizzaB, BList);
        }

        // 각 피자가 사용되지 않는 경우 추가
        AList.add(0);
        BList.add(0);

        Collections.sort(AList);
        Collections.sort(BList);

        int leftIdx = 0;
        int rightIdx = BList.size()-1;

        while(leftIdx < AList.size() && rightIdx >= 0) {
            int lv = AList.get(leftIdx);
            int rv = BList.get(rightIdx);

            if (lv + rv == order) {
                // 현재 lv, rc와 길이가 같은 조각 개수 구하기
                int lc = 0;
                int rc = 0;

                while (leftIdx < AList.size() && AList.get(leftIdx) == lv) {
                    lc++;
                    leftIdx++;
                }
                while (rightIdx >= 0 && BList.get(rightIdx) == rv) {
                    rc++;
                    rightIdx--;
                }
                ans += lc * rc;
            }

            if(lv + rv > order) rightIdx--;
            if(lv + rv < order) leftIdx++;
        }

        System.out.println(ans);
        br.close();
    }

    // 피자A, B의 부분 배열 합 구하기
    static void getSum(int sum, int startIdx, int idx, int[] arr, List list) {
        if(idx == arr.length) idx = 0;

        list.add(sum);

        // 아직 담지 않은 피자 조각일 경우
        if(check[idx] == false && sum <= order && idx != startIdx-1) {
            check[idx] = true;
            getSum(sum+arr[idx], startIdx, idx+1, arr, list);
        }
    }
}

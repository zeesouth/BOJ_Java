package binarySearch.n2143;

import java.io.*;
import java.util.*;

public class Main {
    static int T, N, M;
    static long ans = 0;
    static int[] A, B;

    static List<Integer> listA = new ArrayList<>(), listB = new ArrayList<>();  // 부분합 저장

    private static int upperBound(List<Integer> list, int target) {
        int min = 0;
        int max = list.size();
        while(min < max) {
            int mid = (min+max)/2;
            if(list.get(mid) <= target) min = mid+1;
            else max = mid;
        }
        return max;
    }

    private static int lowerBound(List<Integer> list, int target) {
        int min = 0;
        int max = list.size();
        while(min < max) {
            int mid = (min+max)/2;
            if(list.get(mid) >= target) max = mid;
            else min = mid+1;
        }
        return max;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) A[i] = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());
        B = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++) B[i] = Integer.parseInt(st.nextToken());

        for(int i=0;i<N;i++) {
            int sum = 0;
            for(int j=i;j<N;j++) {
                sum += A[j];
                listA.add(sum);
            }
        }

        for(int i=0;i<M;i++) {
            int sum = 0;
            for(int j=i;j<M;j++) {
                sum += B[j];
                listB.add(sum);
            }
        }

        Collections.sort(listA);
        Collections.sort(listB);

        for(int i=0;i<listA.size();i++) {
            ans += upperBound(listB, T-listA.get(i)) - lowerBound(listB, T - listA.get(i));
        }

        System.out.println(ans);
    }
}

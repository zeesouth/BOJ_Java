package prefixSum.n2143;

import java.io.*;
import java.util.*;

public class Main {
    static int T, N, M;
    static int[] A, B;
    static List<Integer> listA = new ArrayList<>(), listB = new ArrayList<>();  // 부분합 저장

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());
        B = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) B[i] = Integer.parseInt(st.nextToken());

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

        System.out.println(getCount());
    }

    private static long getCount() {
        int pa = 0;
        int pb = listB.size()-1;
        long cnt = 0;

        while(pa < listA.size() && pb >= 0) {
            long sum = listA.get(pa) + listB.get(pb);

            if(sum == T) {
                int a = listA.get(pa);
                int b = listB.get(pb);
                long aCnt = 0;
                long bCnt = 0;

                while(pa < listA.size() && listA.get(pa) == a) {
                    aCnt++; pa++;
                }
                while(pb >= 0 && listB.get(pb) == b) {
                    bCnt++;
                    pb--;
                }
                cnt += aCnt*bCnt;
            } else if (sum < T) pa++;
            else pb--;
        }
        return cnt;
    }
}

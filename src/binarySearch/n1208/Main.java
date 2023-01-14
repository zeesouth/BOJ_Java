package binarySearch.n1208;
// https://www.acmicpc.net/problem/1208

import java.io.*;
import java.util.*;

public class Main {
    static int N, S;
    static long ans;
    static long[] arr;
    static List<Long> left = new ArrayList<>(), right = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Long.parseLong(st.nextToken());

        makeSum(0, N / 2, 0, left);
        makeSum(N / 2, N, 0, right);
        Collections.sort(left);
        Collections.sort(right);

        ans = 0;
        getCnt();

        if (S == 0) ans-=1;
        bw.write(ans + "\n");
        bw.flush();
    }

    public static void makeSum(int idx, int end, long sum, List<Long> list) {
        if (idx == end) {
            list.add(sum);
            return;
        }
        makeSum(idx + 1, end, sum + arr[idx], list);
        makeSum(idx + 1, end, sum, list);
    }

    public static void getCnt() {
        int idxL = 0;
        int idxR = right.size() - 1;

        while (idxL < left.size() && idxR >= 0) {
            long valueL = left.get(idxL);
            long valueR = right.get(idxR);
            if (valueL + valueR == S) {
                long cntL = 0;
                while (idxL < left.size() && left.get(idxL) == valueL) {
                    idxL++; cntL++;
                }
                long cntR = 0;
                while (idxR >= 0 && right.get(idxR) == valueR) {
                    idxR--; cntR++;
                }
                ans += cntL * cntR;
            }
            else if (valueL + valueR < S) idxL++;
            else idxR--;
        }
    }
}

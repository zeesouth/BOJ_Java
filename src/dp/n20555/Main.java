package dp.n20555;
// https://blog.naver.com/raararaara/222761367307 너무 어려워서 참고..

import java.util.*;

public class Main {

    private static String[] words;
    private static int N;
    private static int[] d;     // 각 문자열이 입력된 상태 대한 최소 길이 저장
    private static int[] pw;    // 각 자릿수를 관리하기 위한 배열

    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = scanner.next();
        }

        pw = new int[N];
        pw[0] = 1;
        for (int i = 1; i < N; i++) {
            pw[i] = pw[i - 1] * 10;
        }

        int maxState = 1;
        for (int i = 0; i < N; i++) {
            maxState *= 10;
        }

        d = new int[maxState];
        Arrays.fill(d, INF);

        // d테이블 채우기 : 각 상태별로 최소 문자열 길이 구하기
        f(0);

        StringBuilder result = new StringBuilder();
        int st = 0;

        // 모든 단어의 부분 수열로 포함되는 결과 문자열 만들기
        while (!isEnd(st)) {
            // 각 상태에서 가능한 문자 시도
            for (char c = 'A'; c <= 'D'; c++) {
                List<Integer> lst = new ArrayList<>();
                for (int i = 0; i < N; i++) {
                    int digit = (st / pw[i]) % 10;
                    if (digit == words[i].length()) continue;
                    if (words[i].charAt(digit) == c) lst.add(i);
                }
                int newSt = st;
                for (int x : lst) newSt += pw[x];
                if (st == newSt) continue;

                // 새로운 상태로 전환이 가능하고
                // 그 상태가 최소 길이를 가지면 결과 문자열에 문자 추가
                if (d[newSt] == d[st] - 1) {
                    result.append(c);
                    st = newSt;
                    break;
                }
            }
        }
        System.out.println(result);
    }

    private static boolean isEnd(int st) {
        int cpySt = st;
        for (int i = 0; i < N; i++, cpySt /= 10) {
            int digit = cpySt % 10;
            if (digit != words[i].length()) return false;
        }
        return true;
    }

    private static int f(int st) {
        if (isEnd(st)) return d[st] = 0;
        int ret = d[st];
        if (ret != INF) return ret;

        for (char c = 'A'; c <= 'D'; c++) {
            List<Integer> lst = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                int digit = (st / pw[i]) % 10;
                if (digit == words[i].length()) continue;
                if (words[i].charAt(digit) == c) lst.add(i);
            }
            int newSt = st;
            for (int x : lst) newSt += pw[x];
            if (st == newSt) continue;

            // 새로 입력될 수 있는 상태라면 재귀함수 진행
            ret = Math.min(ret, f(newSt) + 1);
        }

        // 입력된 문자열의 최소 길이 반환
        return d[st] = ret;
    }
}

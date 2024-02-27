package dp.n16639;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, max[][], min[][];
    static String s;

    public static void main(String[] args) throws Exception {
        init();
        dp();
        System.out.println(max[0][N - 1]);
    }

    private static int cal(int operand1, int operand2, char operator) {
        if (operator == '+') return operand1 + operand2;
        else if (operator == '-') return operand1 - operand2;
        else if (operator == '*') return operand1 * operand2;
        return -1;
    }

    private static void dp() {
        for (int j = 2; j < N; j += 2) {
            for (int i = 0; i < N - j; i += 2) {
                for (int k = 2; k <= j; k += 2) {
                    int[] tmp = new int[4];
                    tmp[0] = cal(min[i][i + k - 2], min[i + k][i + j], s.charAt(i + k - 1));
                    tmp[1] = cal(min[i][i + k - 2], max[i + k][i + j], s.charAt(i + k - 1));
                    tmp[2] = cal(max[i][i + k - 2], min[i + k][i + j], s.charAt(i + k - 1));
                    tmp[3] = cal(max[i][i + k - 2], max[i + k][i + j], s.charAt(i + k - 1));

                    Arrays.sort(tmp);

                    min[i][i + j] = Math.min(min[i][i + j], tmp[0]);
                    max[i][i + j] = Math.max(max[i][i + j], tmp[3]);
                }
            }
        }
    }

    private static void init() throws Exception {
        N = Integer.parseInt(br.readLine());
        s = br.readLine();

        max = new int[N][N];
        min = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(max[i], Integer.MIN_VALUE);
            Arrays.fill(min[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < N; i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') { // 피연산자라면 일단 배열에 저장
                max[i][i] = min[i][i] = c - '0';
            }
        }
    }
}

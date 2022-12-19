package dp.n2839;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N;

    public static void main(String[] args) throws Exception {

        // System.setIn(new FileInputStream("res/n2839.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int answer = -1;
        N = Integer.parseInt(br.readLine());
        int five = N / 5; // 시작 : 최대 5봉지로 담는 수
        for (int i = five; i >= 0; i--) {
            if ((N - i * 5) % 3 == 0) {
                answer = i + ((N - i * 5) / 3);
                break;
            }
        }

        System.out.println(answer);
    }
}

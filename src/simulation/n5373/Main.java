package simulation.n5373;

import java.io.*;
import java.util.*;

public class Main {
    static final int N = 3;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static Map<Character, Integer> map = new HashMap<>();

    // 위 아래 앞 뒤 왼 오
    // U, D, F, B, L, R
    static char[][][] cube;

    public static void main(String[] args) throws Exception {

        map.put('U', 0);
        map.put('D', 1);
        map.put('F', 2);
        map.put('B', 3);
        map.put('L', 4);
        map.put('R', 5);

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            cube = init();
            int q = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            while (q-- > 0) {
                String order = st.nextToken();
                simulate(order.charAt(0), order.charAt(1));
            }
            print();
        }

        System.out.println(sb);
    }

    private static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) sb.append(cube[0][i][j]);
            sb.append("\n");
        }
    }

    private static void simulate(char direct, char rotate) {
        int d = map.get(direct);

        char[][] newMap = new char[N][N];

        // 시계, 반시계
        boolean flag = rotate == '+' ? false : true;

        // 반시계 시계
        int c = flag ? N - 1 : 0;
        for (int i = 0; i < N; i++) {
            int r = flag ? 0 : N - 1;
            for (int j = 0; j < N; j++) {
                newMap[i][j] = cube[d][r][c];
                if(flag) r++;
                else r--;
            }
            if (flag) c--;
            else c++;
        }

        cube[d] = newMap;

        // 위 아래 앞 뒤 왼 오
        // U, D, F, B, L, R
        // 0, 1, 2, 3, 4, 5

        // 위
        if (d == 0) {
            // 반시계
            // 뒤
            char[] temp = {cube[3][2][0], cube[3][2][1], cube[3][2][2]};
            if (flag) {
                // 뒤 <- 오
                cube[3][2][0] = cube[5][0][0];
                cube[3][2][1] = cube[5][1][0];
                cube[3][2][2] = cube[5][2][0];

                // 오 <- 앞
                cube[5][0][0] = cube[2][0][2];
                cube[5][1][0] = cube[2][0][1];
                cube[5][2][0] = cube[2][0][0];

                // 얖 <- 왼
                cube[2][0][2] = cube[4][2][2];
                cube[2][0][1] = cube[4][1][2];
                cube[2][0][0] = cube[4][0][2];

                // 왼 <- 뒤
                cube[4][2][2] = temp[0];
                cube[4][1][2] = temp[1];
                cube[4][0][2] = temp[2];
            }
            // 시계
            else {
                // 뒤 <- 왼
                cube[3][2][0] = cube[4][2][2];
                cube[3][2][1] = cube[4][1][2];
                cube[3][2][2] = cube[4][0][2];

                // 왼 <- 앞
                cube[4][2][2] = cube[2][0][2];
                cube[4][1][2] = cube[2][0][1];
                cube[4][0][2] = cube[2][0][0];

                // 앞 <- 오
                cube[2][0][2] = cube[5][0][0];
                cube[2][0][1] = cube[5][1][0];
                cube[2][0][0] = cube[5][2][0];

                // 오 <- 뒤
                cube[5][0][0] = temp[0];
                cube[5][1][0] = temp[1];
                cube[5][2][0] = temp[2];
            }

        } else if (d == 1) {
            char[] temp = {cube[2][2][0], cube[2][2][1], cube[2][2][2]};
            // 반시계
            if (flag) {
                cube[2][2][0] = cube[5][2][2];
                cube[2][2][1] = cube[5][1][2];
                cube[2][2][2] = cube[5][0][2];

                cube[5][2][2] = cube[3][0][2];
                cube[5][1][2] = cube[3][0][1];
                cube[5][0][2] = cube[3][0][0];

                cube[3][0][2] = cube[4][0][0];
                cube[3][0][1] = cube[4][1][0];
                cube[3][0][0] = cube[4][2][0];

                cube[4][0][0] = temp[0];
                cube[4][1][0] = temp[1];
                cube[4][2][0] = temp[2];
            }
            // 시계
            else {
                cube[2][2][0] = cube[4][0][0];
                cube[2][2][1] = cube[4][1][0];
                cube[2][2][2] = cube[4][2][0];

                cube[4][0][0] = cube[3][0][2];
                cube[4][1][0] = cube[3][0][1];
                cube[4][2][0] = cube[3][0][0];

                cube[3][0][2] = cube[5][2][2];
                cube[3][0][1] = cube[5][1][2];
                cube[3][0][0] = cube[5][0][2];

                cube[5][2][2] = temp[0];
                cube[5][1][2] = temp[1];
                cube[5][0][2] = temp[2];
            }
        } else if (d == 2) {
            char[] temp = {cube[0][2][0], cube[0][2][1], cube[0][2][2]};
            // 반시계
            if (flag) {
                cube[0][2][0] = cube[5][2][0];
                cube[0][2][1] = cube[5][2][1];
                cube[0][2][2] = cube[5][2][2];

                cube[5][2][0] = cube[1][0][2];
                cube[5][2][1] = cube[1][0][1];
                cube[5][2][2] = cube[1][0][0];

                cube[1][0][2] = cube[4][2][0];
                cube[1][0][1] = cube[4][2][1];
                cube[1][0][0] = cube[4][2][2];

                cube[4][2][0] = temp[0];
                cube[4][2][1] = temp[1];
                cube[4][2][2] = temp[2];
            }
            // 시계
            else {
                cube[0][2][0] = cube[4][2][0];
                cube[0][2][1] = cube[4][2][1];
                cube[0][2][2] = cube[4][2][2];

                cube[4][2][0] = cube[1][0][2];
                cube[4][2][1] = cube[1][0][1];
                cube[4][2][2] = cube[1][0][0];

                cube[1][0][2] = cube[5][2][0];
                cube[1][0][1] = cube[5][2][1];
                cube[1][0][0] = cube[5][2][2];

                cube[5][2][0] = temp[0];
                cube[5][2][1] = temp[1];
                cube[5][2][2] = temp[2];
            }

        } else if (d == 3) {
            char[] temp = {cube[1][2][0], cube[1][2][1], cube[1][2][2]};
            // 반시계
            if (flag) {
                cube[1][2][0] = cube[5][0][2];
                cube[1][2][1] = cube[5][0][1];
                cube[1][2][2] = cube[5][0][0];

                cube[5][0][2] = cube[0][0][2];
                cube[5][0][1] = cube[0][0][1];
                cube[5][0][0] = cube[0][0][0];

                cube[0][0][2] = cube[4][0][2];
                cube[0][0][1] = cube[4][0][1];
                cube[0][0][0] = cube[4][0][0];

                cube[4][0][2] = temp[0];
                cube[4][0][1] = temp[1];
                cube[4][0][0] = temp[2];
            }
            // 시계
            else {
                cube[1][2][0] = cube[4][0][2];
                cube[1][2][1] = cube[4][0][1];
                cube[1][2][2] = cube[4][0][0];

                cube[4][0][2] = cube[0][0][2];
                cube[4][0][1] = cube[0][0][1];
                cube[4][0][0] = cube[0][0][0];

                cube[0][0][2] = cube[5][0][2];
                cube[0][0][1] = cube[5][0][1];
                cube[0][0][0] = cube[5][0][0];

                cube[5][0][2] = temp[0];
                cube[5][0][1] = temp[1];
                cube[5][0][0] = temp[2];
            }
        } else if (d == 4) {
            char[] temp = {cube[3][0][0], cube[3][1][0], cube[3][2][0]};
            // 반시계
            if (flag) {
                cube[3][0][0] = cube[0][0][0];
                cube[3][1][0] = cube[0][1][0];
                cube[3][2][0] = cube[0][2][0];

                cube[0][0][0] = cube[2][0][0];
                cube[0][1][0] = cube[2][1][0];
                cube[0][2][0] = cube[2][2][0];

                cube[2][0][0] = cube[1][0][0];
                cube[2][1][0] = cube[1][1][0];
                cube[2][2][0] = cube[1][2][0];

                cube[1][0][0] = temp[0];
                cube[1][1][0] = temp[1];
                cube[1][2][0] = temp[2];
            }
            // 시계
            else {
                cube[3][0][0] = cube[1][0][0];
                cube[3][1][0] = cube[1][1][0];
                cube[3][2][0] = cube[1][2][0];

                cube[1][0][0] = cube[2][0][0];
                cube[1][1][0] = cube[2][1][0];
                cube[1][2][0] = cube[2][2][0];

                cube[2][0][0] = cube[0][0][0];
                cube[2][1][0] = cube[0][1][0];
                cube[2][2][0] = cube[0][2][0];

                cube[0][0][0] = temp[0];
                cube[0][1][0] = temp[1];
                cube[0][2][0] = temp[2];
            }
        } else if (d == 5){
            char[] temp = {cube[3][2][2], cube[3][1][2], cube[3][0][2]};
            // 반시계
            if (flag) {
                cube[3][2][2] = cube[1][2][2];
                cube[3][1][2] = cube[1][1][2];
                cube[3][0][2] = cube[1][0][2];

                cube[1][2][2] = cube[2][2][2];
                cube[1][1][2] = cube[2][1][2];
                cube[1][0][2] = cube[2][0][2];

                cube[2][2][2] = cube[0][2][2];
                cube[2][1][2] = cube[0][1][2];
                cube[2][0][2] = cube[0][0][2];

                cube[0][2][2] = temp[0];
                cube[0][1][2] = temp[1];
                cube[0][0][2] = temp[2];
            }
            // 시계
            else {
                cube[3][2][2] = cube[0][2][2];
                cube[3][1][2] = cube[0][1][2];
                cube[3][0][2] = cube[0][0][2];

                cube[0][2][2] = cube[2][2][2];
                cube[0][1][2] = cube[2][1][2];
                cube[0][0][2] = cube[2][0][2];

                cube[2][2][2] = cube[1][2][2];
                cube[2][1][2] = cube[1][1][2];
                cube[2][0][2] = cube[1][0][2];

                cube[1][2][2] = temp[0];
                cube[1][1][2] = temp[1];
                cube[1][0][2] = temp[2];
            }
        }

    }

    private static char[][][] init() {
        char[][][] cube = {
                {
                        {'w', 'w', 'w'},
                        {'w', 'w', 'w'},
                        {'w', 'w', 'w'}
                },
                {
                        {'y', 'y', 'y'},
                        {'y', 'y', 'y'},
                        {'y', 'y', 'y'}
                },
                {
                        {'r', 'r', 'r'},
                        {'r', 'r', 'r'},
                        {'r', 'r', 'r'}
                },
                {
                        {'o', 'o', 'o'},
                        {'o', 'o', 'o'},
                        {'o', 'o', 'o'}
                },
                {
                        {'g', 'g', 'g'},
                        {'g', 'g', 'g'},
                        {'g', 'g', 'g'}
                },
                {
                        {'b', 'b', 'b'},
                        {'b', 'b', 'b'},
                        {'b', 'b', 'b'}
                }
        };

        return cube;
    }
}

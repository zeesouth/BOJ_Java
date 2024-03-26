package implementation.n7682;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static HashSet<String> valid = new HashSet<>();
    static char map[][] = {
            {'.', '.', '.'},
            {'.', '.', '.'},
            {'.', '.', '.'}
    };

    public static void main(String[] args) throws Exception {
        findValidStr();

        String line;

        while ((line = br.readLine()).charAt(0) != 'e') {
            if (valid.contains(line)) sb.append("valid\n");
            else sb.append("invalid\n");
        }

        System.out.println(sb);
    }

    private static void findValidStr() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                dfs(i, j, 1, true);
                map[i][j] = '.';
            }
        }
    }

    private static void dfs(int y, int x, int cnt, boolean isFirst) {
        if (isFirst) map[y][x] = 'X';
        else map[y][x] = 'O';

        // 시험이 끝났는지 안끝났는지 판정
        // 1. 게임판이 가득 찼다면
        if (cnt == 9) {
            valid.add(getString(map));
            return;
        }

        // 2. 누가 가로/세로/대각선 한 줄을 만들었다면, 현재 플레이어 기준으로 계산
        // 가로
        if (map[y][x] == map[y][(x + 1) % 3] && map[y][x] == map[y][(x + 2) % 3]) {
            valid.add(getString(map));
            return;
        }

        // 세로
        if (map[y][x] == map[(y + 1) % 3][x] && map[y][x] == map[(y + 2) % 3][x]) {
            valid.add(getString(map));
            return;
        }

        // 대각선 1
        if (y == x && map[y][x] == map[(y + 1) % 3][(x + 1) % 3] && map[y][x] == map[(y + 2) % 3][(x + 2) % 3]) {
            valid.add(getString(map));
            return;
        }

        // 대각선 2
        if (((y == 1 && x == 1) || (y == 0 && x == 2) || (y == 2 && x == 0)) &&
                (map[y][x] == map[(y - 1 + 3) % 3][(x + 1) % 3] && map[y][x] == map[(y + 1) % 3][(x - 1 + 3) % 3])) {
            valid.add(getString(map));
            return;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[i][j] != '.') continue;
                dfs(i, j, cnt + 1, !isFirst);
                map[i][j] = '.';
            }
        }
    }

    static String getString(char[][] arr) {
        StringBuilder str = new StringBuilder();
        str.append(new String(arr[0]));
        str.append(new String(arr[1]));
        str.append(new String(arr[2]));
        return str.toString();
    }
}

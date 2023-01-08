package queue.n3190;

import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static int N, K, L, answer, index;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    static Queue<int[]> time;
    static Deque<int[]> snake;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
        }
        L = Integer.parseInt(br.readLine());
        time = new LinkedList<>();
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            time.add(new int[]{Integer.parseInt(st.nextToken()), st.nextToken().charAt(0)});
        }
        snake = new LinkedList<>();
        snake.add(new int[]{1, 1});
        map[1][1] = -1; // 뱀의 위치
        index = 0;
        answer = 0;
        while (true) {
            answer++;

            int[] curr = snake.peekFirst();
            int currY = curr[0] + dy[index];
            int currX = curr[1] + dx[index];
            if (isValid(currY, currX) && map[currY][currX] != -1) {
                if (map[currY][currX] != 1) {
                    int[] lastSnake = snake.peekLast();
                    map[lastSnake[0]][lastSnake[1]] = 0;
                    snake.pollLast();
                }
                snake.addFirst(new int[]{currY, currX});
                map[currY][currX] = -1;
            } else break;

            if (!time.isEmpty() && time.peek()[0] == answer) {
                if (time.peek()[1] == 'L') {
                    if(index-1 < 0) index = 3;
                    else index = (index-1) % 4;
                }
                else index = (index+1) % 4;
                time.poll();
            }
        }
        System.out.println(answer);
    }

    static boolean isValid(int Y, int X) {
        return Y >= 1 && Y <= N && X >= 1 && X <= N;
    }
}

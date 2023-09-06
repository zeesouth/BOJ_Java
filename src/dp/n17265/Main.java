package dp.n17265;

import java.io.*;
import java.util.*;

public class Main {
    static final int dy[] = {1, 0};
    static final int dx[] = {0, 1};
    static int N;
    static char map[][];
    static int cal[][][];
    static boolean visited[][];

    public static void main(String[] args) throws Exception {
        init();
        bfs();
        print();
    }

    private static void print() throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(cal[N-1][N-1][0] + " ");
        bw.write(cal[N-1][N-1][1] + " ");
        bw.close();
    }

    private static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0, map[0][0] - '0'});
        cal[0][0][0] = cal[0][0][1] = map[0][0] - '0';
        visited[0][0] = true;
        while (!q.isEmpty()) {
            int[] curr = q.poll();

            for (int i = 0; i < 2; i++) {
                int ody = curr[0] + dy[i];
                int odx = curr[1] + dx[i];

                if (!isRange(ody, odx)) continue;

                for (int j = 0; j < 2; j++) {
                    int ory = ody + dy[j];
                    int orx = odx + dx[j];
                    if (!isRange(ory, orx)) continue;

                    int currCal = curr[2];
                    if (map[ody][odx] == '+') {
                        currCal += map[ory][orx] - '0';
                    } else if (map[ody][odx] == '-') {
                        currCal -= map[ory][orx] - '0';
                    } else {
                        currCal *= map[ory][orx] - '0';
                    }

                    if(!visited[ory][orx]) {
                        visited[ory][orx] = true;
                        cal[ory][orx][0] = cal[ory][orx][1] = currCal;
                    } else {
                        cal[ory][orx][0] = Math.max(cal[ory][orx][0], currCal);
                        cal[ory][orx][1] = Math.min(cal[ory][orx][1], currCal);
                    }

                    q.add(new int[]{ory, orx, currCal});
                }
            }
        }
    }

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        cal = new int[N][N][2]; // max, min
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = st.nextToken().charAt(0);
            }
        }
        br.close();
    }

    private static boolean isRange(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < N;
    }
}

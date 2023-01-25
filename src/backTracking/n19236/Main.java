package backTracking.n19236;
// https://www.acmicpc.net/problem/19236
// 상어가 먹을 수 있는 물고기 번호 합의 최댓값ㅅ

import java.io.*;
import java.util.*;


public class Main {
    static int N = 4, ans;
    static int[][] map; // fishIdxInfo
    static int[][] fish;  // y, x, direct;

    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        map = new int[N][N];
        fish = new int[N*N+1][3];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                fish[map[i][j]] = new int[] {i, j, Integer.parseInt(st.nextToken())-1};
            }
        }
        ans = map[0][0];
        int direct = fish[map[0][0]][2];
        fish[map[0][0]] = new int[] {-1, -1, -1};
        map[0][0] = -1;
        dfs(0, 0, ans, direct);

        bw.write(ans+"");
        bw.flush();
        br.close();
    }

    static void dfs(int y, int x, int currAns, int shark) {

        int[][] originMap = new int[N][N];
        int[][] originFish = new int[N*N+1][3];

        for(int i=0;i<N;i++) originMap[i] = Arrays.copyOfRange(map[i], 0, N);
        for(int i=1;i<=N*N;i++) originFish[i] = Arrays.copyOfRange(fish[i], 0, 3);

        ans = Math.max(ans, currAns);

        // 물고기 이동
        for(int i=1;i<=N*N;i++) {
            int[] currFish = fish[i];
            if(currFish[0] != -1) {
                for(int j=0;j<8;j++) {
                    int nextY = currFish[0] + dy[fish[i][2]];
                    int nextX = currFish[1] + dx[fish[i][2]];
                    if(isValid(nextY, nextX) && map[nextY][nextX] != -1) {
                        if(map[nextY][nextX] == 0) {
                            map[currFish[0]][currFish[1]] = 0;
                            map[nextY][nextX] = i;
                            fish[i][0] = nextY;
                            fish[i][1] = nextX;
                        } else {
                            map[currFish[0]][currFish[1]] = map[nextY][nextX];
                            fish[map[nextY][nextX]][0] = currFish[0];
                            fish[map[nextY][nextX]][1] = currFish[1];
                            map[nextY][nextX] = i;
                            fish[i][0] = nextY;
                            fish[i][1] = nextX;
                        }
                        break;
                    } else fish[i][2] = (fish[i][2]+1)%8;
                }
            }
        }

        // 상어 이동
        int currY = y+dy[shark];
        int currX = x+dx[shark];

        while(isValid(currY, currX)) {
            if(map[currY][currX] != 0) {
                map[y][x] = 0;
                int currFishIndex = map[currY][currX];
                int currFishDirect = fish[currFishIndex][2];
                fish[currFishIndex][0] = -1;
                fish[currFishIndex][1] = -1;
                fish[currFishIndex][2] = -1;
                map[currY][currX] = -1;
                dfs(currY, currX, currAns + currFishIndex, currFishDirect);
                map[y][x] = -1;
                fish[currFishIndex][0] = currY;
                fish[currFishIndex][1] = currX;
                fish[currFishIndex][2] = currFishDirect;
                map[currY][currX] = currFishIndex;
            }
            currY += dy[shark];
            currX += dx[shark];
        }
        map = originMap;
        fish = originFish;
    }

    static boolean isValid(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < N;
    }

}

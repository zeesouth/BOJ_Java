package backTracking.n15686;
// https://www.acmicpc.net/problem/15686

import java.io.*;
import java.util.*;

class Point {
    int y, x;
    Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class Main {
    static int N, M, hSize, cSize, ans;
    static int[][] house, chicken;
    static boolean[] open;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        house = new int[2*N][2];
        chicken = new int[13][3];

        // 1. 집과 치킨집의 좌표를 각 house, chicken list에 저장
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                switch(Integer.parseInt(st.nextToken())) {
                    case 1:
                        house[hSize][0] = i;
                        house[hSize++][1] = j;
                        break;
                    case 2:
                        chicken[cSize][0] = i;
                        chicken[cSize++][1] = j;
                }
            }
        }

        ans = Integer.MAX_VALUE;
        open = new boolean[cSize];

        dfs(0, 0);
        bw.write(ans+"");
        bw.flush();
        bw.close();
        br.close();
    }

    static void dfs(int start, int cnt) {

        // 치킨 집이 오픈한 개수 M과 같다면, 모든 집에 대해 M개의 치킨집 중 최단 거리 계산
        if(cnt == M) {
            int res = 0;
            for(int i=0;i<hSize;i++) {
                int temp = Integer.MAX_VALUE;
                for(int j=0;j<cSize;j++) {
                    int dis = distance(chicken[j][0], chicken[j][1], house[i][0], house[i][1]);
                    if(open[j]) {
                        temp = Math.min(temp, dis);
                    }
                }
                res += temp;
            }
            ans = Math.min(ans, res);
            // 최대 오픈 가능한 치킨집이 M개 이므로 M개가 완료되면 return.
            return;
        }

        // Back Tracking
        for(int i=start; i<cSize; i++) {
            open[i] = true;
            dfs(i+1, cnt+1);
            open[i] = false; // 해제
        }
    }

    static int distance(int y1, int x1, int y2, int x2) {
        return Math.abs(y1-y2) + Math.abs(x1-x2);
    }
}
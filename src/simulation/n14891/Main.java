package simulation.n14891;

import java.io.*;
import java.util.*;

public class Main {
    static int K, gear[][], start[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        gear = new int[4][8];
        start = new int[4];
        for(int i=0;i<4;i++) {
            String g = br.readLine();
            for(int j=0;j<8;j++) gear[i][j] = g.charAt(j)-'0';
        }

        K = Integer.parseInt(br.readLine());

        for(int k=0;k<K;k++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int gNum = Integer.parseInt(st.nextToken())-1;
            int direct = Integer.parseInt(st.nextToken());
            int tmpL = gear[gNum][start[gNum]-2 < 0 ? start[gNum]+8-2 : start[gNum]-2];
            int tmpR = gear[gNum][(start[gNum]+2)%8];
            int tmpD = direct;
            start[gNum] = start[gNum]-direct < 0 ? 7 : (start[gNum]-direct)%8;

            // 선택된 톱니바퀴 왼쪽 방향
            for(int i=gNum-1;i>=0;i--) {
                if(tmpD != 0) {
                    if(tmpL != gear[i][(start[i]+2)%8]) {
                        tmpL = gear[i][start[i]-2 < 0 ? start[i]+8-2 : start[i]-2];
                        tmpD = -tmpD;
                        start[i] = start[i]-tmpD < 0 ? 7 : (start[i]-tmpD)%8;
                    } else tmpD = 0;
                }
            }

            tmpD = direct;

            // 선택된 톱니바퀴 오른쪽 방향
            for(int i=gNum+1;i<4;i++) {
                if(tmpD != 0) {
                    if(tmpR != gear[i][start[i]-2 < 0 ? start[i]+8-2 : start[i]-2]) {
                        tmpR = gear[i][(start[i]+2)%8];
                        tmpD = -tmpD;
                        start[i] = start[i]-tmpD < 0 ? 7 : (start[i]-tmpD)%8;
                    } else tmpD = 0;
                }
            }
        }

        int ans = 0;
        for(int i=0;i<4;i++) if(gear[i][(start[i])] == 1) ans += 1 << i;
        System.out.println(ans);
    }
}

/*
* N극 : 0, S극 : 1
* 맞닿은 극이 같음 : 회전 X (그 방향으로부터 회전을 아예 멈춤)
* 맞닿은 극이 다름 : 맞닿은 톱니바퀴와 반대 방향으로 회전
* 1 : 시계 방향, -1 반시계 방향
*
* 맞닿은 부분 체크 :
* - 1번 톱니 : 오른쪽 5번
* - 2번 톱니 : 왼쪽 1번, 오른쪽 5번
* - 3번 톱니 : 왼쪽 1번, 오른쪽 5번
* - 4번 톱니 : 왼쪽 1번
* */
package prefixSum.n3020;
// https://www.acmicpc.net/problem/3020
// https://loosie.tistory.com/557

import java.io.*;
import java.util.*;

public class Main {
    static int N, H, min = Integer.MAX_VALUE, cnt=1;
    static int[] bot, top;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        bot = new int[H+2];   // 석순
        top = new int[H+2];   // 종유석
        for(int i=0;i<N/2;i++) {
            int num1 = Integer.parseInt(br.readLine()); // 석순
            int num2 = Integer.parseInt(br.readLine()); // 종유석
            bot[num1]++;
            top[H-num2+1]++;
        }

        for(int i=1;i<=H;i++) {
            bot[i] += bot[i-1];
            top[H+1-i] += top[H+2-i];
        }

        min = N;
        cnt = 1;

        for(int i=1;i<=H;i++) {
            // (bot 장애물 최대 갯수 - bot[i-1]) + (top 장애물 최대 갯수 - top[i+1])
            int obs = (bot[H]-bot[i-1]) + (top[1]-top[i+1]);
            if(obs < min) {
                min = obs;
                cnt = 1;
            } else if (obs == min) cnt++;
        }


        // 짝수 : 아래, 홀수 : 위

        sb.append(min).append(" ").append(cnt);
        System.out.println(sb);
    }


}


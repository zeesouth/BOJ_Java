package greedy.n2457;

import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Flower flower[] = new Flower[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start_month = Integer.parseInt(st.nextToken());
            int start_day = Integer.parseInt(st.nextToken());
            int end_month = Integer.parseInt(st.nextToken());
            int end_day = Integer.parseInt(st.nextToken());

            int start = start_month * 100 + start_day;
            int end = end_month * 100 + end_day;
            flower[i] = new Flower(start, end);
        }
        Arrays.sort(flower, (o1, o2) -> o1.start == o2.start ? o1.end - o2.end : o1.start - o2.start);

        int max = 0, ans = 0;
        int start = 301;
        int idx = 0;
        while (start < 1201) {     // 시작일이 12월 이전인 것만 가능
            boolean find = false;  // 새 꽃을 찾은지 여부 확인
            for(int i=idx;i<N;i++) {
                // 종료일보다 시작일이 이후면 의미가 없음 (중간에 빈 날짜가 생기면 안됨)
                if(flower[i].start > start) break;

                // 현재 마지막 꽃이 피는 날짜보다 해당 꽃이 더 오래핀다면
                if(max < flower[i].end) {
                    max = flower[i].end;
                    idx++;
                    find = true;
                }
            }

            if(find) {
                start = max;
                ans++;
            } else break;
        }
        if(max < 1201) ans = 0;
        System.out.println(ans);
        br.close();
    }

    static class Flower {
        int start, end;
        Flower(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}

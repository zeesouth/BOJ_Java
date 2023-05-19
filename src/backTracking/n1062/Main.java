package backTracking.n1062;

import java.io.*;
import java.util.*;

public class Main {
    static boolean visited[];
    static int N, K, ans;
    static String voca[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (K < 5) ans = 0;
        else if (K == 26) ans = N;
        else {
            voca = new String[N];
            // anta, tica는 제거하고 단어 추가
            for (int i = 0; i < N; i++) {
                String v = br.readLine();
                v = v.replace("anta", "").replace("tica", "");
                voca[i] = v;
            }
            // a, c, i, n, t는 모든 단어에서 읽을 수 있는 숫자
            visited = new boolean[26];
            visited['a' - 'a'] = true;
            visited['c' - 'a'] = true;
            visited['i' - 'a'] = true;
            visited['n' - 'a'] = true;
            visited['t' - 'a'] = true;
            backTracking(0, 0);
        }
        System.out.println(ans);
        br.close();
    }

    static void backTracking(int alpha, int len) {
        if (len == K - 5) {
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                boolean read = true;
                for(int j=0;j<voca[i].length();j++) {
                    // 읽을 수 없는 글자가 나오면 이 단어는 읽을 수 없음
                    if(!visited[voca[i].charAt(j)-'a']) {
                        read = false;
                        break;
                    }
                }
                if(read) cnt++;
            }
            ans = Math.max(ans, cnt);
            return;
        }

        for (int i = alpha; i < 26; i++) {
            if (visited[i] == false) {
                visited[i] = true;
                backTracking(i, len + 1);
                visited[i] = false;
            }
        }
    }
}

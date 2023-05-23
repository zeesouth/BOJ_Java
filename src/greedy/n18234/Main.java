package greedy.n18234;
// https://www.acmicpc.net/problem/18234

import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        Carrot cList[] = new Carrot[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int taste = Integer.parseInt(st.nextToken());
            int plus = Integer.parseInt(st.nextToken());
            cList[i] = new Carrot(taste, plus);
        }
        Arrays.sort(cList, (o1, o2) -> o1.plus - o2.plus);

        long ans = 0;
        long wait = T - N;
        for (Carrot c : cList) {
            ans += ((long) c.taste + (long) c.plus * wait);
            wait++;
        }
        System.out.println(ans);
        br.close();
    }

    static class Carrot {
        int taste, plus;

        Carrot(int taste, int plus) {
            this.taste = taste;
            this.plus = plus;
        }
    }
}

package greedy.n12429;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static ArrayList<Food> list = new ArrayList<>();
    static int N, ans;

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            init();
            simulate();
            sb.append("Case #").append(t).append(": ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static void simulate() {
        // 허기 지는 타이밍 check
        boolean[] hungry = new boolean[200004];
        hungry[0] = true;

        for (int i = 0; i < N; i++) {
            // 해당 음식의 유통기한 내로 배고픈 시점이 있는지 체크
            for (int j = list.get(i).end; j >= 0; j--) {
                if (hungry[j]) {
                    hungry[j + list.get(i).con] = true;
                    ans = Math.max(ans, j + list.get(i).con);
                }
            }
        }
    }

    private static void init() throws Exception {
        list.clear();

        N = Integer.parseInt(br.readLine());
        int end, con;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            end = Integer.parseInt(st.nextToken());
            con = Integer.parseInt(st.nextToken());

            list.add(new Food(end, con));
        }

        Collections.sort(list);
        ans = 0;

    }

    static class Food implements Comparable<Food> {
        int end, con;

        Food(int end, int con) {
            this.end = end;
            this.con = con;
        }

        @Override
        public int compareTo(Food food) {
            return (this.end + this.con) - (food.end + food.con);
        }
    }
}

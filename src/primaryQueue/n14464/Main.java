package primaryQueue.n14464;
// https://www.acmicpc.net/problem/14464
// https://www.acmicpc.net/board/view/70045

import java.io.*;
import java.util.*;

class Pair implements Comparable<Pair> {
    int start, end;

    Pair(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override   // 시작 시기가 빠른 순
    public int compareTo(Pair o) {
        if (this.start == o.start)
            return this.end - o.end;
        return this.start - o.start;
    }
}

class SubPair implements Comparable<SubPair> {
    int start, end;

    SubPair(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override   // 시작 시기가 빠른 순
    public int compareTo(SubPair o) {
        if (this.end == o.end)
            return this.start - o.start;
        return this.end - o.end;
    }
}

public class Main {
    static int C, N, ans;
    static PriorityQueue<Integer> chicken;

    static PriorityQueue<Pair> cow;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        chicken = new PriorityQueue<>();
        cow = new PriorityQueue<>();
        for (int i = 0; i < C; i++) chicken.add(Integer.parseInt(br.readLine()));
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            cow.add(new Pair(s, e));
        }
        ans = 0;
        while (!cow.isEmpty()) {
            if (chicken.isEmpty()) break;
            int currChicken = chicken.peek();
            PriorityQueue<SubPair> availCow = new PriorityQueue<>();

            while (!cow.isEmpty()) {
                Pair p = cow.peek();
                if (p.start <= currChicken && currChicken <= p.end) {
                    availCow.add(new SubPair(p.start, p.end));
                    cow.poll();
                } else if(p.end < currChicken) cow.poll();
                else if(p.start > currChicken) chicken.poll();
            }

            if (availCow.isEmpty()) {
                chicken.poll();
            } else {
                availCow.poll();
                chicken.poll();
                ans++;
                while (!availCow.isEmpty()) {
                    SubPair a = availCow.poll();
                    cow.add(new Pair(a.start, a.end));
                }
            }
        }
        System.out.println(ans);
    }
}

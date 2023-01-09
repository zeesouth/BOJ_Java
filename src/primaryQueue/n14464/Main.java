package primaryQueue.n14464;
// https://www.acmicpc.net/problem/14464

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
        if(this.start == o.start)
            return this.end-o.end;
        return this.start-o.start;
    }
}

class SubPair implements Comparable<SubPair> {
    int start, end;
    SubPair(int start, int end) {
        this.start = start;
        this.end = end;
    }
    @Override   // 끝날 시기가 가장 빠른 순
    public int compareTo(SubPair o) {
        if(this.end == o.end)
            return this.start-o.start;
        return this.end-o.end;
    }
}

public class Main {
    static int C, N, ans;
    static boolean[] isCheckedChicken;
    static PriorityQueue<Integer> cow;

    static PriorityQueue<Pair> chicken;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        cow = new PriorityQueue<>();
        chicken = new PriorityQueue<>();
        for(int i=0;i<C;i++) cow.add(Integer.parseInt(br.readLine()));
        isCheckedChicken = new boolean[N];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            chicken.add(new Pair(s, e));
        }

        ans = 0;
        while(!cow.isEmpty()) {
            int currCow = cow.peek();
            PriorityQueue<SubPair> availChicken = new PriorityQueue<>();

            if(chicken.isEmpty()) break;

            while(!chicken.isEmpty()) {
                Pair p = chicken.peek();
                if(p.start <= currCow && currCow <= p.end) {
                    availChicken.add(new SubPair(p.start, p.end));
                    chicken.poll();
                } else break;
            }

            if (availChicken.isEmpty()) {
                if(currCow < chicken.peek().start) cow.poll();
                else if (currCow > chicken.peek().end) chicken.poll();
            } else {
                availChicken.poll();
                cow.poll();
                ans++;
                while(!availChicken.isEmpty()) {
                    SubPair a = availChicken.poll();
                    chicken.add(new Pair(a.start, a.end));
                }
            }
        }
        System.out.println(ans);
    }
}

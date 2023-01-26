package greedy.n1202;
// https://www.acmicpc.net/problem/1202

import java.io.*;
import java.util.*;

class Jewel implements Comparable<Jewel> {
    int m, v;
    Jewel(int m, int v) {
        this.m = m;
        this.v = v;
    }

    @Override
    public int compareTo(Jewel j) {
        if(this.m == j.m) return j.v-this.v;
        return this.m-j.m;
    }
}

public class Main {
    static int N, K;
    static long ans;
    static PriorityQueue<Jewel> jewels;
    static PriorityQueue<Integer> remain, bag;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        jewels = new PriorityQueue<>();
        remain = new PriorityQueue<>(Collections.reverseOrder());
        bag = new PriorityQueue<>();

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            jewels.add(new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        for(int i=0;i<K;i++) bag.add(Integer.parseInt(br.readLine()));

        ans = 0;
        while(!bag.isEmpty()) {

            int currBag = bag.peek();
            Jewel currJewel = jewels.peek();

            if(currJewel == null || currBag < currJewel.m) {
                bag.poll();
                if(!remain.isEmpty()) ans+=remain.poll();
            }
            else {
                remain.add(currJewel.v);
                jewels.poll();
            }
        }

        bw.write(ans+"");
        bw.flush();
        br.close();
    }
}

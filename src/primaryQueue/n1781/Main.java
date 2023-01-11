package primaryQueue.n1781;
// https://www.acmicpc.net/board/view/97635 반례
// https://www.acmicpc.net/problem/1781

import java.io.*;
import java.util.*;

//데드라인 내림차순 -> 라멘 내림차순
class Dead implements Comparable<Dead> {
    int dead, ramen;
    Dead(int dead, int ramen) {
        this.dead = dead;
        this.ramen = ramen;
    }
    @Override
    public int compareTo(Dead o) {
        if(this.dead == o.dead)
            return o.ramen-this.ramen;
        return o.dead-this.dead;
    }
}

// 라멘 내림차순 -> 데드라인 오름차순
class Ramen implements Comparable<Ramen> {
    int dead, ramen;
    Ramen(int dead, int ramen) {
        this.dead = dead;
        this.ramen = ramen;
    }

    @Override
    public int compareTo(Ramen o) {
        if(this.ramen == o.ramen)
            return o.dead-this.dead;
        return o.ramen-this.ramen;
    }
}


public class Main {
    static int N, ans, time;
    static PriorityQueue<Dead> dead;
    static PriorityQueue<Ramen> ramen;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        dead = new PriorityQueue<>();
        ramen = new PriorityQueue<>();
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            dead.add(new Dead(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        ans = 0;
        time = dead.peek().dead;
        while(time != 0) {
            while(!dead.isEmpty() && time == dead.peek().dead) {
                Dead currDead = dead.poll();
                ramen.add(new Ramen(currDead.dead, currDead.ramen));
            }
            if(!ramen.isEmpty()) {
                if(time <= ramen.peek().dead) ans += ramen.poll().ramen;
            }
            time--;
        }
        System.out.println(ans);
    }
}

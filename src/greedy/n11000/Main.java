package greedy.n11000;

import java.io.*;
import java.util.*;

class Pair implements Comparable<Pair> {
    int start, end;

    Pair(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Pair o) {
        if(this.start == o.start)
            return this.end-o.end;
        return this.start-o.start;
    }
}

public class Main {
    static int n;
    static PriorityQueue<Pair> wait;
    static PriorityQueue<Integer> end;
    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = stoi(br.readLine());
        wait = new PriorityQueue<>();
        for(int i=0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            wait.add(new Pair(stoi(st.nextToken()), stoi(st.nextToken())));
        }

        end = new PriorityQueue<>();

        do {
            Pair lecture = wait.poll();
            if(lecture != null) {
                if(end.isEmpty()) end.add(lecture.end);
                else {
                    Integer currEnd = end.peek();
                    if(currEnd != null) {
                        if(currEnd <= lecture.start) end.poll();
                        end.add(lecture.end);
                    }
                }
            }
        } while(!wait.isEmpty());
        System.out.println(end.size());
    }

    static int stoi(String s) {
        return Integer.parseInt(s);
    }
}

package greedy.n2437;
// https://www.acmicpc.net/problem/2437

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long ans = 0;
    static PriorityQueue<Integer> q;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        q = new PriorityQueue<>();
        for(int i=0;i<N;i++) q.add(Integer.parseInt(st.nextToken()));

        while(!q.isEmpty()) {
            int a = q.poll();
            int b = !q.isEmpty() ? q.poll() : -1;

            if(b!=-1) {
                if(a==b) {
                    if(a-ans > 1) {
                        ans=ans+1;
                        break;
                    } else {
                        q.add(a+b);
                        ans = a+b;
                    }
                } else if(a<b) {
                    if(a-ans > 1 || (a+1 != b && b-ans > 1)){
                        ans = ans+1;
                        break;
                    } else {
                        q.add(a+b);
                        ans = a+b;
                    }
                }
            } else {
                if(ans == 0 && a > 1) ans = 1;
                else ans = a+1;
                break;
            }
        }

        bw.write(ans+"");
        bw.flush();
        br.close();

    }
}

/*
* 1 1 2 3 6 7 30
* 1 2 3 4 5
*
* */
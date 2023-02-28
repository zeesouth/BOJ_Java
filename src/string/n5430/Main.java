package string.n5430;
// https://www.acmicpc.net/problem/5430

import java.io.*;
import java.util.*;

public class Main {
    static boolean flag, err;
    static int T, n;
    static String p;
    static Deque<Integer> dq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++) {
            flag = true;    // 첫 시작은 순행
            err = false;
            p = br.readLine();
            n = Integer.parseInt(br.readLine());
            dq = new LinkedList<>();
            StringTokenizer st = new StringTokenizer(br.readLine(), "[],");
            for(int i=0;i<n;i++) dq.add(Integer.parseInt(st.nextToken()));

            for(int i=0;i<p.length();i++) {
                char order = p.charAt(i);
                if(order == 'R') {
                    flag = !flag;
                } else {
                    if(dq.isEmpty()) {
                        sb.append("error\n");
                        err = true;
                        break;
                    }
                    if(flag) dq.pollFirst();
                    else dq.pollLast();
                }
            }
            if(err) continue;

            sb.append("[");
            while(!dq.isEmpty()) {
                sb.append(flag ? dq.pollFirst() : dq.pollLast());
                if(dq.size() != 0) sb.append(",");
            }
            sb.append("]\n");
        }
        bw.write(sb.toString());
        bw.flush();
        br.close();
    }
}

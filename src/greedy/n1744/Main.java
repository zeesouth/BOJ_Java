package greedy.n1744;
// https://www.acmicpc.net/problem/1744
import java.io.*;
import java.util.*;

public class Main {

    static int n, sum = 0, zero = 0;
    static PriorityQueue<Integer> plus, minus;
    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        plus = new PriorityQueue<>((o1,o2)->o2-o1); // 내림차순 정렬
        minus = new PriorityQueue<>();

        for(int i=0;i<n;i++) {
            int data = Integer.parseInt(br.readLine());
            if(data < 0) minus.add(data);
            else if(data > 0) plus.add(data);
            else zero++;
        }

        while(!plus.isEmpty()) {
            Integer a = plus.poll();
            Integer b = plus.poll();

            if(b==null) sum+=a;
            else {
                if(b==1) sum+=(a+b);
                else sum+=(a*b);
            }
        }

        while(!minus.isEmpty()) {
            Integer a = minus.poll();
            Integer b = minus.poll();

            if(b!=null) sum+=(a*b);
            else {
                if(zero > 0) zero--;
                else sum+=a;
            }
        }

        System.out.println(sum);
    }
}

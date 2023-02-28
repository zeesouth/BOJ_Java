package greedy.n1339;

import java.io.*;
import java.util.*;

public class Main {
    static int max = 0, m = 9;
    static int N, sum = 0;
    static int[] arr = new int[10];
    static Queue<Character>[] q = new Queue[9];

    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++) {
            String s = br.readLine();
            max = Math.max(max, s.length());
            for(int j=0;j<s.length();j++) {
                char a = s.charAt(j);
                if(q[s.length()-j] == null) q[s.length()-j] = new LinkedList<>();
                q[s.length()-j].add(a);
            }
        }

        for(int i=max;i>=1;i--) {
            while(!q[i].isEmpty()) {
                int c = q[i].poll()-'A';
                if(arr[c] == 0) arr[c] = m--;
                else {

                }
                sum += arr[c]*(Math.pow(10, i-1));
            }
        }
        System.out.println(sum);
    }
}

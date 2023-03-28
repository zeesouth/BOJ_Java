package dp_tree.n13325;
// https://www.acmicpc.net/problem/13325

import java.io.*;
import java.util.*;

public class Main {
    static int K, N;
    static int[] tree, path, sum;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        K = Integer.parseInt(br.readLine());
        N = 1<<(K+1);
        tree = new int[N];
        path = new int[N];
        sum = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=2;i<N;i++) tree[i] = Integer.parseInt(st.nextToken());

        for(int i=1<<K;--i>0;){
            int left = i<<1, right = left+1;
            path[i] = Math.max(path[left]+tree[left], path[right]+tree[right]);
            sum[i] = sum[left] + sum[right] + (path[i]-path[left]) + (path[i]-path[right]);
        }

        bw.write(sum[1]+"");
        bw.close();
        br.close();
    }
}

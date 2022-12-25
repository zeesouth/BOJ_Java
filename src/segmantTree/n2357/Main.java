package segmantTree.n2357;
// https://www.acmicpc.net/problem/2357

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static long[] arr;
    static long[][] tree;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/n2357.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new long[N+1];
        int size = (int) Math.pow(2, (int) Math.ceil(Math.log(N)/Math.log(2))+1);
        tree = new long[size][2];   // min, max

        for(int i=1;i<=N;i++) arr[i] = Long.parseLong(br.readLine());
        init(1, 1, N);
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            long[] res = minmax(1, 1,N,
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            System.out.println(res[0]+" "+res[1]);
        }
        br.close();

    }

    static long[] init(int node, int start, int end) {
        if(start == end)
            return tree[node] = new long[] {arr[start], arr[start]};

        int mid = (start+end)/2;
        long[] left = init(node*2, start, mid);
        long[] right = init(node*2+1, mid+1, end);

        return tree[node] = new long[] {
            Math.min(left[0], right[0]), Math.max(left[1], right[1])
        };
    }

    static long[] minmax(int node, int start, int end, int left, int right) {
        if(left > end || right < start)
            return new long[] {Long.MAX_VALUE, Long.MIN_VALUE};
        else if (left <= start && end <= right)
            return tree[node];

        int mid = (start + end) / 2;
        long[] L = minmax(node*2, start, mid, left, right);
        long[] R = minmax(node*2+1, mid+1, end, left, right);

        return new long[] {
                Math.min(L[0], R[0]), Math.max(L[1], R[1])
        };
    }
}

package segmantTree.n11505;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static long[] arr;
    static long[] tree;


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N+1];
        int h = (int) Math.ceil(Math.log(N)/Math.log(2));    // 트리 높이는 log2N
        int size = (int) Math.pow(2, h+1);          // +1 이유는 노드 번호를 1부터 시작하기 위함
        tree = new long[size];

        for(int i=1;i<=N;i++) arr[i] = Long.parseLong(br.readLine());
        // init
        init(1, 1, N);

        for(int i=0;i<M+K;i++) {
            st = new StringTokenizer(br.readLine());
            switch (Integer.parseInt(st.nextToken())){
                case 1:
                    modify(1, 1, N,
                            Integer.parseInt(st.nextToken()), Long.parseLong(st.nextToken()));
                    break;
                case 2:
                    System.out.println(multiply(1, 1, N,
                            Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())) % 1000000007);
                    break;
            }

        }

        br.close();
    }

    static long init(int node, int start, int end) {
        if(start == end)
            return tree[node] = arr[start]%1000000007;
        int mid = (start+end)/2;

        return tree[node] = (init(node*2, start, mid)*init(node*2+1, mid+1, end))%1000000007;
    }

    static long modify(int node, int start, int end, int index, long val) {
        if(index < start || end < index) return tree[node];
        else if (start == index && end == index) return tree[node] = val%1000000007;
        else return tree[node] = (modify(node * 2, start, (start + end) / 2, index, val) *
                    modify(node * 2 + 1, (start + end) / 2 + 1, end, index, val))%1000000007;
    }


    static long multiply(int node, int start, int end, int left, int right) {
        if (left > end || right < start)     // 구해야 하는 범위와 현재 범위가 겹치지 않는 경우
            return 1;
        else if (left <= start && end <= right)   // 구해야 하는 범위가 현재 범위를 완전히 포함하는 경우
            return tree[node] % 1000000007;

        int mid = (start + end) / 2;         // 현재 범위의 중간 범위
        return (multiply(node * 2, start, mid, left, right) * multiply(node * 2 + 1, mid + 1, end, left, right))%1000000007;
    }
}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long[] arr;
    static long[] tree;
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr = new long[N+1];
        int h = (int) Math.ceil(Math.log(N)/Math.log(2));   // 트리 높이는 log2N
        int size = (int) Math.pow(2, h+1);  // 트리 노드 번호를 1부터 시작하기 위함
        tree = new long[size];

        for(int i=1;i<=N;i++) arr[i] = Long.parseLong(br.readLine());
        init(1, 1, N);

        for(int i=0;i<M+K;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            switch(a) {
                case 1:
                    modify(1,1, N, b, c);
                    break;
                case 2:
                    System.out.println(sum(1,1, N, b, c));
                    break;
            }
        }

    }

    static long init(int node, int start, int end) {
        if(start == end)    // 리프노드 (단일 원솟값인 경우)
            return tree[node] = arr[start];

        int mid = (start+end)/2;
        return tree[node] = init(node*2, start, mid) + init(node*2+1, mid+1, end);
    }

    static long modify(int node, int start, int end, int index, long val) {
        if(index < start || end < index) return tree[node];
        else if (start == index && end == index) return tree[node] = val;
        else return tree[node] = modify(node * 2, start, (start + end) / 2, index, val) +
                    modify(node * 2 + 1, (start + end) / 2 + 1, end, index, val);
    }

    static long sum(int node, int start, int end, int left, long right) {
        if (left > end || right < start)     // 구해야 하는 범위와 현재 범위가 겹치지 않는 경우
            return 0;
        else if (left <= start && end <= right)   // 구해야 하는 범위가 현재 범위를 완전히 포함하는 경우
            return tree[node];

        int mid = (start + end) / 2;         // 현재 범위의 중간 범위
        return sum(node * 2, start, mid, left, right) + sum(node * 2 + 1, mid + 1, end, left, right);
    }


}
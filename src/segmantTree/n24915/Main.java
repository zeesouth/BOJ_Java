package segmantTree.n24915;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, Q;
    static long[] arr;

    static long[] tree;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/n24915.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        arr = new long[N+1];
        tree = new long[(int)Math.pow(2, (int)Math.ceil(Math.log(N)/Math.log(2))+1)];

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) arr[i] = Integer.parseInt(st.nextToken());
        init(1, 1, N);  // 구간 최소 트리

        // 무대의 매력 = b의 최댓값, a, c의 최솟값 (b - a - c)
        for(int i=0;i<Q;i++) {
            st = new StringTokenizer(br.readLine());
            switch (Integer.parseInt(st.nextToken())) {
                case 1:
                    int index = Integer.parseInt(st.nextToken());
                    int val = Integer.parseInt(st.nextToken());
                    arr[index] = val;
                    modify(1, 1, N, index, val);
                    break;
                case 2:
                    int left = Integer.parseInt(st.nextToken());
                    int right = Integer.parseInt(st.nextToken());
                    long answer = Long.MIN_VALUE;

                    for(int j=left+1;j<right;j++) {
                        long leftMin = Long.MIN_VALUE, rightMin = Long.MIN_VALUE;
                        if(j == left+1){
                            leftMin = arr[left];
                            rightMin = getMin(1, 1, N, j+1, right);
                        } else if (j == right-1) {
                            leftMin = getMin(1, 1, N, left, right-2);
                            rightMin = arr[right];
                        } else {
                            leftMin = getMin(1, 1, N, left, j-1);
                            rightMin = getMin(1, 1, N, j+1, right);
                        }
                        answer = Math.max(answer, arr[j]-leftMin-rightMin);
                    }

                    System.out.println(answer);

                    break;
            }
        }
    }

    static long init(int node, int start, int end) {
        if(start == end)
            return tree[node] = arr[start];
        int mid = (start+end)/2;
        return tree[node] = Math.min(init(node*2, start, mid), init(node*2+1, mid+1, end));
    }

    static long modify(int node, int start, int end, int index, long val) {
        int mid = (start+end)/2;
        if(index < start || end < index) return tree[node];
        else if (start == index && end == index) return tree[node] = val;
        else return tree[node] = Math.min(modify(node*2, start, mid, index, val), modify(node*2+1, mid+1, end, index, val));
    }

    static long getMin(int node, int start, int end, int left, int right) {
        if (left > end || right < start)     // 구해야 하는 범위와 현재 범위가 겹치지 않는 경우
            return Long.MAX_VALUE;
        else if (left <= start && end <= right)   // 구해야 하는 범위가 현재 범위를 완전히 포함하는 경우
            return tree[node];

        int mid = (start + end) / 2;         // 현재 범위의 중간 범위
        return Math.min(getMin(node*2, start, mid, left, right), getMin(node*2+1, mid+1, end, left, right));
    }
}

package test.segmantTree;

public class SegmantTree {

    // https://hongjuzzang.github.io/datastructure/segment_tree/
    // 배열 크기 지정
    static int input[] = { 5, 8, 7, 3, 1, 3, 2, 7, 9, 7 };
    static int N = input.length;
    static int h = (int) Math.ceil(Math.log(N) / Math.log(2)); // log2N
    static int size = (int) Math.pow(2, h+1);  // 1부터 시작하기 위해
    static long tree[] = new long[size];

    // 초기화
    /*
    최상단 인덱스(1)에서 재귀를 이용해 값을 채우는 방법
    init(1, 0, N-1)
    1: 세그먼트트리의 시작인덱스
    0: 입력할 배열의 시작인덱스
    N-1: 입력할 배열의 마지막 인덱스
    */
    private static long init(int node, int start, int end) {
        if (start == end)   // 리프노드 (단일 원소값인 경우)
            return tree[node] = input[start];

        int mid = (start + end) / 2;
        return tree[node] = init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end);
    }

    // 구간 합 찾기
    /*
    구해야 하는 범위를 [left, right]라고 하고, 노드가 담당하는 구간이 [start, end]라고 하자
    루트(최상단) 노드의 담당구간 [start, end] 는 [0, N-1]이다

    이 때 다음과 같이 4가지 경우로 나눌 수 있다

    1. [left,right]와 [start,end]가 겹치지 않는 경우
    - if( left > end || right < start )로 나타낼 수 있음.
    이때는 겹치지 않기 때문에 탐색을 이어나가고 0을 리턴해서 종료

    2. [left,right]가 [start,end]를 완전히 포함하는 경우
    - if( left <= start && end <= right )로 나타낼 수 있음.
    구해야 하는 범위는 left~right인데 start~end는 해당 범위에 완전 포함되고,
    start~end의 하위 노드들도 모두 포함이기 때문에 탐색을 진행할 필요가 없다. tree[node]를 리턴해서 종료.

    3. [start,end]가 [left,right]를 완전히 포함하는 경우
    4. [left,right]와 [start,end]가 겹쳐져 있는 경우 (1, 2, 3 제외한 나머지 경우)
    두 경우에는 왼쪽 자식노드와 오른쪽 자식노드를 루트로 하는 트리에서 다시 탐색 진행
    */

    private static long sum(int node, int start, int end, int left, int right) {
        if (left > end || right < start)     // 구해야 하는 범위와 현재 범위가 겹치지 않는 경우
            return 0;
        if (left <= start && end <= right)   // 구해야 하는 범위가 현재 범위를 완전히 포함하는 경우
            return tree[node];

        int mid = (start + end) / 2;         // 현재 범위의 중간 범위
        return sum(node * 2, start, mid, left, right) + sum(node * 2 + 1, mid + 1, end, left, right);
    }

    // 인덱스 값 변경
    /*
    중간에 어떤 수가 변경되면, 해당 수가 포함된 구간을 담당하는 모든 노드를 변경해야 한다.
    */
    private static void update(int node, int start, int end, int index, int diff) {
        if (index < start || index > end) // 인덱스 범위 조정
            return;
        tree[node] += diff; // diff 변경 적용
        if (start != end) {
            int mid = (start + end) / 2;
            update(node * 2, start, mid, index, diff);
            update(node * 2 + 1, mid + 1, end, index, diff);
        }
    }

    // 출력용
    private static void printTree() {
        System.out.println("========================");
        print(1, " /");
        System.out.println("========================");
    }

    private static void print(int idx, String sub) {
        System.out.println(sub + tree[idx]);
        if (idx * 2 < tree.length)
            print(idx * 2, "  " + sub);
        if (idx * 2 + 1 < tree.length)
            print(idx * 2 + 1, "  " + sub);
    }

    public static void main(String[] args) {
        int N = input.length;
        int h = (int) Math.ceil(Math.log(N) / Math.log(2)); // log2(N)
        int size = (int) Math.pow(2, h + 1);

        tree = new long[size];
        System.out.println(size);
        init(1, 0, N - 1);
        System.out.println("3 ~ 6의 구간합은 : " + sum(1, 0, N - 1, 3, 6));
        printTree();
        System.out.println("5번째 원소의 값 5를 3로 변환, diff = -2");
        update(1, 0, N - 1, 5, -2);
        printTree();
    }
}

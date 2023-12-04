package divideAndConquer.n2263;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringBuffer sb = new StringBuffer();
    static int[] inorder;
    static int[] inorderIndex;
    static int[] postorder;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        inorder = new int[n];
        postorder = new int[n];
        inorderIndex = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) inorder[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) postorder[i] = Integer.parseInt(st.nextToken());

        // 중위 순회
        for (int i = 0; i < n; i++) inorderIndex[inorder[i]] = i;


        getPreOrder(0, n - 1, 0, n - 1);
        System.out.println(sb);
    }

    public static void getPreOrder(int in_start, int in_end, int p_start, int p_end) {
        if (in_start > in_end || p_start > p_end) return;


        int rootNode = postorder[p_end];
        sb.append(rootNode).append(" ");

        // 중위 순회에서의 rootIndex
        int rootIndex = inorderIndex[rootNode];
        // left tree 길이
        int leftNodeLength = rootIndex - in_start;

        // left 트리
        getPreOrder(in_start, rootIndex - 1, p_start, p_start + leftNodeLength - 1);

        // right 트리
        getPreOrder(rootIndex + 1, in_end, p_start + leftNodeLength, p_end - 1);
    }
}

/*
전위 : 부모 -> 왼 -> 오
중위 : 왼 -> 부모 -> 오
후위 : 왼 -> 오 -> 부모
 */
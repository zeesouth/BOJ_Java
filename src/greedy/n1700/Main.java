package greedy.n1700;
// https://www.acmicpc.net/problem/1700

import java.io.*;
import java.util.*;

public class Main {

    static boolean use[];
    static int N, K, order[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        order = new int[K];
        use = new boolean[K+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) order[i] = Integer.parseInt(st.nextToken());

        int put = 0, ans = 0;
        for(int i=0;i<K;i++) {
            int o = order[i];

            if(!use[o]) {
                if(put < N) { // 콘센트 꽂을 공간 있음
                    use[o] = true;
                    put++;
                } else { // 공간 없음
                    // 현재 꽂힌 콘센트가 나중에 사용되는지 확인 (있다면 사용 순으로 저장)
                    ArrayList<Integer> arr = new ArrayList<>();
                    for(int j=i;j<K;j++)
                        if(use[order[j]] && !arr.contains(order[j])) arr.add(order[j]);

                    // 나중에도 사용되는 물품의 개수가 콘센트 구멍보다 작은 경우 (현재 꽂힌 물품 중 나중에 사용하지 않는 물품이 있는 경우)
                    if(arr.size() < N) {
                        for(int j=1;j<=K;j++) {
                            if(use[j] && !arr.contains(j)) { // 꽂힌 것 중 나중에 사용하지 않는 콘센트 1개 제거
                                use[j] = false;
                                break;
                            }
                        }
                    } else { // 현재 꽂힌 물품 모두가 나중에 사용되는 경우
                        int remove = arr.get(arr.size()-1); // 가장 마지막에 사용될 콘센트를 제거
                        use[remove] = false;
                    }
                    use[o] = true;
                    ans++;
                }
            }
        }
        System.out.println(ans);
    }
}
package greedy.n1092;
// https://www.acmicpc.net/problem/1092

import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> crane = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) crane.add(Integer.parseInt(st.nextToken()));

        int M = Integer.parseInt(br.readLine());
        ArrayList<Integer> box = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++) box.add(Integer.parseInt(st.nextToken()));

        // 배열 내림차순 정렬
        Collections.sort(crane, (o1, o2) -> o2-o1);
        Collections.sort(box, (o1, o2) -> o2-o1);

        int ans = 0;
        if(crane.get(0) < box.get(0)) ans = -1;         // 가장 큰 크레인보다 가장 큰 박스 크레인의 값이 작은 경우
        else {
            while(!box.isEmpty()) {
                int b = 0;
                for (int i = 0; i < N; ) {
                    if (b == box.size()) break;
                    else if (crane.get(i) >= box.get(b)) {
                        box.remove(b);
                        i++;
                    } else b++;
                }
                ans++;
            }
        }

        System.out.println(ans);
        br.close();
    }
}

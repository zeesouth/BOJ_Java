package backTracking.n17471;
import java.io.*;
import java.util.*;
public class Main {
    static int ans = Integer.MAX_VALUE, N, population[], graph[][];
    static boolean selected[], visited[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        population = new int[N];
        selected = new boolean[N];
        for(int i=0;i<N;i++) population[i] = Integer.parseInt(st.nextToken());

        graph = new int[N][];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            graph[i] = new int[n];
            for(int j=0;j<n;j++) graph[i][j] = Integer.parseInt(st.nextToken())-1;
        }

        divideArea(0);
        if(ans == Integer.MAX_VALUE) ans = -1;

        System.out.println(ans);
        br.close();
    }

    static void divideArea(int idx) {
        if(idx == N) {
            List<Integer> aList = new ArrayList<>();
            List<Integer> bList = new ArrayList<>();

            for(int i=0;i<N;i++) {
                if(selected[i]) aList.add(i);
                else bList.add(i);
            }

            if(aList.size() == 0 || bList.size() == 0) return;      // 한 지역에 모두 몰린 경우
            if(check(aList) && check(bList)) getPopulationDiff();   // 구역 내 지역들이 잘 연결되었는지 확인
            return;
        }

        selected[idx] = true;
        divideArea(idx+1);
        selected[idx] = false;
        divideArea(idx+1);
    }

    static boolean check(List<Integer> list) {
        Queue<Integer> q = new LinkedList<>();
        visited = new boolean[N];
        visited[list.get(0)] = true;
        q.offer(list.get(0));

        int cnt = 1;
        while(!q.isEmpty()) {
            int cur = q.poll();
            for(int i=0;i<graph[cur].length;i++) {
                int y = graph[cur][i];
                if(list.contains(y) && !visited[y]) {
                    q.offer(y);
                    visited[y] = true;
                    cnt++;
                }
            }
        }
        if(cnt == list.size()) return true; // 모든 선거구들이 다 연결된 경우
        else return false;
    }

    static void getPopulationDiff() {
        int pA = 0, pB = 0;
        for(int i=0;i<N;i++) {
            if(selected[i]) pA += population[i];
            else pB += population[i];
        }
        ans = Math.min(ans, Math.abs(pA-pB));
    }
}

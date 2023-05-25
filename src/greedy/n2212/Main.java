package greedy.n2212;
// https://www.acmicpc.net/problem/2212

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        int sensor[] = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) sensor[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(sensor);

        Integer dist[] = new Integer[N - 1];
        for (int i = 0; i < N - 1; i++) dist[i] = sensor[i + 1] - sensor[i];
        Arrays.sort(dist, (o1, o2) -> o2 - o1);

        int ans = 0;
        for(int i=K-1;i<N-1;i++) ans += dist[i];

        System.out.println(ans);
        br.close();
    }
}

// 1 3 6 6 7 9
// 5 3 3 1 2 3

// 10
// 4
// 3 6 7 8 10 12 14 15 18 20
// 7 4 5


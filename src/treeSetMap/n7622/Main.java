package treeSetMap.n7622;
// https://www.acmicpc.net/problem/7662

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            TreeMap<Integer, Integer> pq = new TreeMap<>();
            int k = Integer.parseInt(br.readLine());
            while (k-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                char order = st.nextToken().charAt(0);
                int data = Integer.parseInt(st.nextToken());
                if (order == 'I') {
                    if (!pq.containsKey(data)) pq.put(data, 0);
                    pq.replace(data, pq.get(data) + 1);
                } else if(!pq.isEmpty()){
                    if (data == 1) {
                        Integer last = pq.lastKey();
                        if (pq.get(last) == 1) pq.pollLastEntry();
                        else pq.replace(last, pq.get(last) - 1);
                    } else {
                        Integer first = pq.firstKey();
                        if (pq.get(first) == 1) pq.pollFirstEntry();
                        else pq.replace(first, pq.get(first) - 1);
                    }
                }
            }
            if (pq.isEmpty()) sb.append("EMPTY").append("\n");
            else sb.append(pq.lastKey()).append(" ").append(pq.firstKey()).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}
package twoPointer.n2842;
// https://www.acmicpc.net/problem/2842

import java.io.*;
import java.util.*;

public class Main {
    static int N, kNum, startY, startX, ans = 1_000_000;
    static char[][] village;
    static int[][] altitude;
    static boolean[][] visited;
    static List<Integer> altitudeList = new ArrayList<>();
    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, 1, -1, -1, 1, 1, -1};

    static class Node {
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        altitude = new int[N][N];
        village = new char[N][N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                village[i][j] = s.charAt(j);
                if (village[i][j] == 'K') kNum++;
                if (village[i][j] == 'P') {
                    startY = i;
                    startX = j;
                }
            }
        }

//      1. 투포인터 지정
//      1-1) 고도를 중복없이 저장하고 배열에 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                altitude[i][j] = Integer.parseInt(st.nextToken());
                if (!altitudeList.contains(altitude[i][j])) altitudeList.add(altitude[i][j]);
            }
        }

//      1-2) 오름차순으로 고도 리스트 정렬
        Collections.sort(altitudeList);

        bw.write(bfs() + "");
        bw.flush();
        br.close();
    }

    static int bfs() {
        // two pointer index
        int low = 0, high = 0;

        while (low < altitudeList.size()) {
            visited = new boolean[N][N];
            Queue<Node> q = new LinkedList<>();
            int val = altitude[startY][startX];

            if (altitudeList.get(low) <= val && val <= altitudeList.get(high)) {
                visited[startY][startX] = true;
                q.add(new Node(startY, startX));
            }

            int count = 0;
            while (!q.isEmpty()) {
                Node curr = q.poll();

                if (village[curr.y][curr.x] == 'K') count++;

                for (int d = 0; d < 8; d++) {
                    int currY = curr.y + dy[d];
                    int currX = curr.x + dx[d];

                    if (!isValid(currY, currX)) continue;
                    if (visited[currY][currX]) continue;
                    int nextVal = altitude[currY][currX];

                    // 2. 투포인터 범위 안에만 있는 고도만 BFS
                    if (altitudeList.get(low) <= nextVal && nextVal <= altitudeList.get(high)) {
                        visited[currY][currX] = true;
                        q.add(new Node(currY, currX));
                    }
                }

            }

            // 모든 배달 지역을 다 방문했다면 ans와 피로도를 비교
            if (kNum == count) {
                ans = Math.min(ans, altitudeList.get(high) - altitudeList.get(low));
                low++;
            } else if (high + 1 < altitudeList.size()) high++;
            else break;
        }
        return ans;
    }

    static boolean isValid(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < N;
    }
}
/*1) 투포인터 지정
 *    고도를 전부 중복 없이 오름차순으로 정렬 후 저장하여 탐색
 *    우체국 고도 중 제일 낮은곳, 높은 곳을 투포인터로 지정
 * 2) 투포인터 범위 안에만 있는 고도만 BFS
 * 3) 모든 집이 탐색되지 않으면 오른쪽 1증가 왼쪽 1증가
 * */
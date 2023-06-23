package prefixSum.n14846;
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int dp[][][] = new int[N+1][N+1][10+1];

        StringTokenizer st = null;
        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++) {
                int val = Integer.parseInt(st.nextToken());
                for(int k=1;k<=10;k++) {
                    dp[i][j][k] = dp[i-1][j][k] + dp[i][j-1][k] - dp[i-1][j-1][k] + (k == val ? 1 : 0);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());
        while(Q-->0) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            int answer = 0;
            for(int i=1;i<=10;i++) {
                int curr = dp[r2][c2][i] - dp[r2][c1-1][i] - dp[r1-1][c2][i] +  dp[r1-1][c1-1][i];
                if (curr != 0) answer++;
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}

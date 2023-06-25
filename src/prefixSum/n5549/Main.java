package prefixSum.n5549;
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());
        char info[][] = new char[M][N];
        for(int i=0;i<M;i++) info[i] = br.readLine().toCharArray();

        int dp[][][] = new int[M+1][N+1][3];    // 정글, 바다, 얼음

        for(int i=1;i<=M;i++) {
            for(int j=1;j<=N;j++) {
                char c = info[i-1][j-1];
                if(c == 'J') {
                    dp[i][j][0] = dp[i][j-1][0] + 1;
                    dp[i][j][1] = dp[i][j-1][1];
                    dp[i][j][2] = dp[i][j-1][2];
                } else if(c == 'O') {
                    dp[i][j][0] = dp[i][j-1][0];
                    dp[i][j][1] = dp[i][j-1][1] + 1;
                    dp[i][j][2] = dp[i][j-1][2];
                } else {
                    dp[i][j][0] = dp[i][j-1][0];
                    dp[i][j][1] = dp[i][j-1][1];
                    dp[i][j][2] = dp[i][j-1][2] + 1;
                }
            }
        }

        for(int j=1;j<=N;j++) {
            for(int i=1;i<=M;i++) {
                dp[i][j][0] += dp[i-1][j][0];
                dp[i][j][1] += dp[i-1][j][1];
                dp[i][j][2] += dp[i-1][j][2];
            }
        }

        StringBuilder sb = new StringBuilder();
        while(K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int ay = Integer.parseInt(st.nextToken());
            int ax = Integer.parseInt(st.nextToken());
            int by = Integer.parseInt(st.nextToken());
            int bx = Integer.parseInt(st.nextToken());
            sb.append(dp[by][bx][0]-dp[ay-1][bx][0]-dp[by][ax-1][0]+dp[ay-1][ax-1][0]).append(" ");
            sb.append(dp[by][bx][1]-dp[ay-1][bx][1]-dp[by][ax-1][1]+dp[ay-1][ax-1][1]).append(" ");
            sb.append(dp[by][bx][2]-dp[ay-1][bx][2]-dp[by][ax-1][2]+dp[ay-1][ax-1][2]).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}

/*
J : 정글
O : 바다
I : 얼음

 */
package prefixSum.n16139;
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int dp[][] = new int['z'-'a'+1][s.length()+1];

        for(int i=0;i<='z'-'a';i++) {
            for(int j=1;j<=s.length();j++) {
                int c = s.charAt(j-1)-'a';
                if(i == c) dp[i][j] = dp[i][j-1] + 1;
                else dp[i][j] = dp[i][j-1];
            }
        }

        StringBuilder sb = new StringBuilder();
        int q = Integer.parseInt(br.readLine());
        while(q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int c = st.nextToken().charAt(0)-'a';
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            sb.append(dp[c][r+1]-dp[c][l]).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}

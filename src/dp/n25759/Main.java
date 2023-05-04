package dp.n25759;
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[101];
        Arrays.fill(dp, -1000000007);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int tmp = Integer.parseInt(st.nextToken());
        dp[tmp] = 0;

        for(int i=1;i<N;i++) {
            int curr = Integer.parseInt(st.nextToken());
            for(int j=1;j<=100;j++) {
                if(dp[j] > -1)
                    dp[curr] = Math.max(dp[curr], dp[j]+(curr-j)*(curr-j));
            }
        }
        int ans = 0;
        for(int i=1;i<=100;i++) ans = Math.max(ans, dp[i]);
        System.out.println(ans);
        br.close();
    }
}

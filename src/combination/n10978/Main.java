package combination.n10978;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        long[] dp = new long[21];
        dp[2] = 1;
        for(int i=3;i<=20;i++) dp[i] = (i-1)*(dp[i-1]+dp[i-2]);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) sb.append(dp[Integer.parseInt(br.readLine())]).append("\n");

        System.out.println(sb);
        br.close();
    }
}
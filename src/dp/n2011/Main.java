package dp.n2011;

import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String pw = sc.nextLine();
        int L = pw.length();
        int[] dp = new int[L+1];
        dp[0] = 1;

        for(int i=1;i<=L;i++) {
            int num = pw.charAt(i-1)-'0';
            if(isInRange(2, num)) dp[i]=(dp[i-1]+dp[i])%1000000;

            if(i==1) continue;

            num += 10*(pw.charAt(i-2)-'0');
            if(isInRange(1, num)) dp[i]=(dp[i-2]+dp[i])%1000000;
        }
        System.out.println(dp[L]);
    }

    static boolean isInRange(int option, int num) {
        if(option == 1)
            return num >= 10 && num <= 26;
        else return num >= 1 && num <= 9;
    }
}
// % 1000000
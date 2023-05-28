package binarySearch.n3649;
import java.io.*;
import java.util.*;
public class Main {
    static int x, n, lego[], l2, l1;
    static String line = null;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while((line = br.readLine()) != null) {
            x = Integer.parseInt(br.readLine()) * 10000000;
            n = Integer.parseInt(br.readLine());
            lego = new int[n];
            for(int i=0;i<n;i++) lego[i] = Integer.parseInt(br.readLine());
            Arrays.sort(lego);
            binarySearch();

            if(l2 != -1) sb.append("yes ").append(l1).append(" ").append(l2).append("\n");
            else sb.append("danger\n");

            line = null;
        }
        System.out.println(sb);
        br.close();
    }

    static void binarySearch() {
        int left = 0;
        int right = n-1;

        l2 = l1 = -1;

        while(left < right) {
            int mid = (left+right)/2;
            int length = lego[left]+lego[right];
            if(length == x) {
                l2 = lego[right];
                l1 = lego[left];
                return;
            } else if (length < x) left++;
            else right--;
        }
    }
}

// 1 2 9999998 9999999


package tree.n28270;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int page[] = new int[C];
        int arr[] = new int[1000000 + 1];

        boolean flag = true;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < C; i++) {
            int num = Integer.parseInt(st.nextToken());
            page[i] = num;

            if (i == 0 && num != 1) {
                flag = false;
                break;
            }
            if (i > 0 && num - page[i - 1] > 1) {
                flag = false;
                break;
            }

            if (i == 0) arr[num]++;
            else {
                if (num - page[i - 1] >= 1) {
                    arr[num] = 0;
                }
                arr[num]++;
            }
            sb.append(arr[num]).append(" ");
        }

        if (!flag) System.out.println(-1);
        else System.out.println(sb);
    }
}

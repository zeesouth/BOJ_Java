package binarySearch.n5710;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        long A, B, sum;
        while (true) {
            st = new StringTokenizer(br.readLine());
            A = Long.parseLong(st.nextToken());
            B = Long.parseLong(st.nextToken());

            if (A == 0 && B == 0) break;

            sum = getWatt(A);

            long left = 0, right = sum / 2, mid, ans = left;

            while (left <= right) {
                mid = (left + right) / 2;

                long a = getPrice(mid);
                long b = getPrice(sum - mid);

                long diff = Math.abs(b - a);

                if (diff == B) {
                    ans = a;
                    break;
                }

                if (diff < B) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }


    private static long getWatt(long price) {
        long sum = 0;

        if (price > 100 * 2 + 9900 * 3 + 990000 * 5) {
            sum += (price - (100 * 2 + 9900 * 3 + 990000 * 5)) / 7;
            price = (100 * 2 + 9900 * 3 + 990000 * 5);
        }
        if (price > 100 * 2 + 9900 * 3) {
            sum += (price - (100 * 2 + 9900 * 3)) / 5;
            price = (100 * 2 + 9900 * 3);
        }
        if (price > 100 * 2) {
            sum += (price - (100 * 2)) / 3;
            price = (100 * 2);
        }
        if (price > 0) {
            sum += price / 2;
            price = 0;
        }
        return sum;
    }

    private static long getPrice(long watt) {
        long sum = 0;
        if (watt > 1000000) {
            sum += (watt - 1000000) * 7;
            watt = 1000000;
        }
        if (watt > 10000) {
            sum += (watt - 10000) * 5;
            watt = 10000;
        }
        if (watt > 100) {
            sum += (watt - 100) * 3;
            watt = 100;
        }
        if (watt > 0) {
            sum += watt * 2;
            watt = 0;
        }
        return sum;
    }
}

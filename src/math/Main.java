package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2417
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long num1 = Long.parseLong(br.readLine());
        long num2 = (long) Math.sqrt(num1);

        if(num1 > num2*num2) System.out.println(num2+1);
        else System.out.println(num2);
    }
}

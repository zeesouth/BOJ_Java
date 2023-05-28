package binarySearch.n2470;
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int arr[] = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);

        int ans1 = 0, ans2 = 0;
        int left = 0, right = N-1;
        int gap = Integer.MAX_VALUE;
        while(left < right) {
            int sum = arr[left] + arr[right];
            int temp = Math.abs(sum);
            if(temp < gap) {
                ans1 = arr[left];
                ans2 = arr[right];
                gap = temp;
            }

            if(sum == 0) break;
            else if(sum < 0) left++;
            else right--;
        }

        bw.write(ans1+" "+ans2);
        bw.flush();
        br.close();
    }
}

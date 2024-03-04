package string.n9369;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            ArrayList<String> encodes = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                encodes.add(br.readLine());
            }

            String decode = br.readLine();

            int[][] check1 = new int[100][150]; // encode -> decode
            int[][] check2 = new int[100][150]; // decode -> encode
            int cnt = 0;
            ArrayList<Integer> avail = new ArrayList<>();

            loop:
            for (int i = 0; i < encodes.size(); i++) {
                if (encodes.get(i).length() != decode.length()) continue;

                for (int j = 0; j < decode.length(); j++) {
                    int d = decode.charAt(j);
                    int e = encodes.get(i).charAt(j);

                    if (check1[i][e] == 0 && check2[i][d] == 0) {
                        check1[i][e] = d;
                        check2[i][d] = e;
                        cnt++;
                    } else if (check2[i][d] != e || check1[i][e] != d) continue loop;
                }

                avail.add(i);

                // 25개의 문자에 대한 정보를 알았다면 나머지 한 문자도 자동으로 알 수 있음 (1:1 대응)
                if (cnt == 25) {
                    int a = 0, b = 0;
                    for (int j = 'a'; j <= 'z'; j++) {
                        if (check1[i][j] == 0) a = j;
                        if (check2[i][j] == 0) b = j;
                    }

                    check1[i][a] = b;
                    check2[i][b] = a;
                }
            }


            String target = br.readLine();

            // 비교할 수 있는 문자열이 없다면
            if (avail.size() == 0) sb.append("IMPOSSIBLE");
            else {
                int idx = avail.get(0);

                // 비교할 수 있는 문자열이 1개
                if (avail.size() == 1) {
                    for (int i = 0; i < target.length(); i++) {
                        int v = target.charAt(i);

                        if (check1[idx][v] == 0) {  // 알 수 없는 문자 정보라면
                            sb.append("?");
                        } else {
                            sb.append((char) (check1[idx][v]));
                        }
                    }
                }
                // 비교할 수 있는 문자열이 2개 이상
                else {
                    for (int i = 0; i < target.length(); i++) {
                        boolean flag = false;
                        int v = target.charAt(i);
                        if (check1[idx][v] == 0) {
                            sb.append('?');
                            continue;
                        }

                        int num = check1[idx][v];
                        // 해독 문자가 서로 다르다면 -> 알 수 없는 문자
                        for (int j = 1; j < avail.size(); j++) {
                            if (num != check1[avail.get(j)][v]) {
                                flag = true;
                                break;
                            }
                        }

                        if (flag) {
                            sb.append('?');
                        } else {
                            sb.append((char) (num));
                        }
                    }
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}

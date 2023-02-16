package trie.n5052;
// https://www.acmicpc.net/problem/5052

import java.io.*;
import java.util.*;

class Trie {

    class Node {
        boolean last;
        int data;
        HashMap<Integer, Node> child;
        Node(int data) {
            this.data = data;
            this.child = new HashMap<>();
            this.last = false;
        }
    }

    Node root;

    Trie() {
        this.root = new Node(-1);
    }

    public boolean insert(String s) {
        Node curr = this.root;
        for(int i=0;i<s.length();i++) {
            int c = s.charAt(i)-'0';
            if(curr.child.containsKey(c)) {
                curr = curr.child.get(c);
                if(curr.last) return false;
                if(i == s.length()-1) return false;
            } else {
                Node newNode = new Node(c);
                if(i == s.length()-1) newNode.last = true;
                curr.child.put(c, newNode);
                curr = newNode;
            }
        }
        return true;
    }

}

public class Main {
    static boolean flag;
    static int T, N;
    static Trie trie;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++) {
            trie = new Trie();
            flag = true;
            N = Integer.parseInt(br.readLine());
            for(int i=0;i<N;i++) {
                String s = br.readLine();
                if(flag) flag = trie.insert(s);
            }
            sb.append(flag ? "YES\n" : "NO\n");
        }
        bw.write(sb.toString());
        bw.flush();
        br.close();
    }
}

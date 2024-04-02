package linkedList.n3217;
// 깃허브 참고

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static final int INF = 100000;
    static HashMap<String, Node> map = new HashMap<>();
    static LinkedList list = new LinkedList();

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        String q;
        while (N-- > 0) {
            q = br.readLine();
            if (q.charAt(5) == '(') print(q);
            else if (q.charAt(4) == '(') free(q);
            else malloc(q);
        }

        System.out.println(sb);
    }

    static void malloc(String q) {
        String str[] = q.split("=malloc");
        String name = str[0];
        int val = Integer.parseInt(str[1].substring(1, str[1].length() - 2));
        map.put(name, list.add(val));
    }

    static void free(String q) {
        String str[] = q.split("free");
        String name = str[1].substring(1, str[1].length() - 2);

        if (!map.containsKey(name)) return;

        Node node = map.get(name);
        if (node.start == 0) return;

        if (list.head.equals(list.tail)) {
            list.head = null;
            list.tail = null;
        } else if (node.prev == null) {
            list.head = node.next;
            list.head.prev = null;
        } else if (node.next == null) {
            list.tail = node.prev;
            list.tail.next = null;
        } else {
            Node prev = node.prev;
            Node next = node.next;
            prev.next = next;
            next.prev = prev;
        }
        node.start = 0;
    }

    static void print(String q) {
        String str[] = q.split("print");
        String name = str[1].substring(1, str[1].length() - 2);

        Node node = map.get(name);
        if (node == null) sb.append(0);
        else sb.append(node.start);

        sb.append("\n");
    }

    static class Node {
        Node next, prev;
        int start, size;

        public Node(int start, int size) {
            this.start = start;
            this.size = size;
        }
    }

    static class LinkedList {
        Node head, tail;

        Node add(int size) {
            Node newNode = new Node(1, size);

            // 빈 메모리 공간이라면
            if (head == null) {
                head = tail = newNode;
                return newNode;
            } else {
                // 맨 앞 메모리의 시작 인덱스 번호가 추가하려는 노드의 크기보다 크다면
                if (head.start > size) {
                    newNode.next = head;
                    head.prev = newNode;
                    head = newNode;
                    return newNode;
                }

                Node curr = head;
                while (curr != null) {
                    Node next = curr.next;
                    if (next == null) {
                        if (INF - (curr.start + curr.size - 1) < size) {
                            newNode.start = 0;
                        } else {
                            newNode.start = curr.start + curr.size;
                            curr.next = newNode;
                            newNode.prev = curr;
                            tail = newNode;
                        }
                        return newNode;
                    } else {
                        if (next.start - (curr.start + curr.size) >= size) {
                            newNode.start = curr.start + curr.size;
                            curr.next = newNode;
                            newNode.prev = curr;
                            newNode.next = next;
                            next.prev = newNode;
                            return newNode;
                        }
                    }
                    curr = curr.next;
                }
            }

            return newNode;
        }
    }

}

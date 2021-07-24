package cn.noncoder.algs4.string;

import edu.princeton.cs.algs4.Queue;

/**
 * 基于单词查找树的符号表
 */
public class TrieST<Value> {

    private static int r = 256;
    private Node root;

    private static class Node {
        Object value;
        Node[] next = new Node[r];
    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) {
            return null;
        }
        return (Value) x.value;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            return x;
        }
        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }

    public void put(String key, Value value) {
        root = put(root, key, value, 0);
    }

    private Node put(Node x, String key, Value value, int d) {
        if (x == null) {
            x = new Node();
        }
        if (d == key.length()) {
            x.value = value;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, value, d + 1);
        return x;
    }

    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> q = new Queue<>();
        collect(get(root, prefix, 0), prefix, q);
        return q;
    }

    private void collect(Node x, String prefix, Queue<String> q) {
        if (x == null) {
            return;
        }
        if (x.value != null) {
            q.enqueue(prefix);
        }
        for (char c = 0; c < r; c++) {
            collect(x.next[c], prefix + c, q);
        }
    }

    public Iterable<String> keysThatMatch(String pattern) {
        Queue<String> q = new Queue<>();
        collect(root, "", pattern, q);
        return q;
    }

    private void collect(Node x, String prefix, String pattern, Queue<String> q) {
        if (x == null) {
            return;
        }
        int d = prefix.length();
        if (pattern.length() == d) {
            if (x.value != null) {
                q.enqueue(prefix);
            }
            return;
        }
        char next = pattern.charAt(d);
        for (char c = 0; c < r; c++) {
            if (next == '.' || c == next) {
                collect(x.next[c], prefix + c, pattern, q);
            }
        }
    }

    public String longestPrefixOf(String s) {
        int length = search(root, s, 0, 0);
        return s.substring(0, length);
    }

    private int search(Node x, String s, int length, int d) {
        if (x == null) {
            return length;
        }
        if (x.value != null) {
            length = d;
        }
        if (d == s.length()) {
            return length;
        }
        char c = s.charAt(d);
        return search(x.next[c], s, length, d + 1);
    }

    public void delete(String key) {
        root = delete(root, key, 0);
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (key.length() == d) {
            x.value = null;
        } else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d + 1);
        }
        if (x.value != null) {
            return x;
        }
        for (char c = 0; c < r; c++) {
            if (x.next[c] != null) {
                return x;
            }
        }
        return null;
    }

}

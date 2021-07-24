package cn.noncoder.algs4.string;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * 基于三向查找树的符号表
 */
public class TST<Value> {

    private Node root;

    private class Node {
        char c;
        Node left, mid, right;
        Value value;
    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) {
            return null;
        }
        return x.value;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        char c = key.charAt(d);
        if (c < x.c) {
            return get(x.left, key, d);
        } else if (c > x.c) {
            return get(x.right, key, d);
        } else if (d < key.length() - 1) {
            return get(x.mid, key, d + 1);
        }
        return x;
    }

    public void put(String key, Value value) {
        root = put(root, key, value, 0);
    }

    private Node put(Node x, String key, Value value, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node();
            x.c = c;
        }
        if (c < x.c) {
            x.left = put(x.left, key, value, d);
        } else if (c > x.c) {
            x.right = put(x.right, key, value, d);
        } else if (d < key.length() - 1) {
            x.mid = put(x.mid, key, value, d + 1);
        } else {
            x.value = value;
        }
        return x;
    }

    public Iterable<String> keys() {
        Queue<String> q = new Queue<>();
        collect(root, "", q);
        return q;
    }

    private void collect(Node x, String prefix, Queue<String> q) {
        if (x == null) {
            return;
        }
        collect(x.left, prefix, q);
        if (x.value != null) {
            q.enqueue(prefix + x.c);
        }
        collect(x.mid, prefix + x.c, q);
        collect(x.right, prefix, q);
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> q = new Queue<>();
        Node x = get(root, prefix, 0);
        if (x == null) {
            return q;
        }
        if (x.value != null) {
            q.enqueue(prefix);
        }
        collect(x.mid, prefix, q);
        return q;
    }

    public Iterable<String> keysThatMatch(String pattern) {
        Queue<String> q = new Queue<>();
        collect(root, "", 0, pattern, q);
        return q;
    }

    private void collect(Node x, String prefix, int i, String pattern, Queue<String> q) {
        if (x == null) {
            return;
        }
        char c = pattern.charAt(i);
        if (c == '.' || c < x.c) {
            collect(x.left, prefix, i, pattern, q);
        }
        if (c == '.' || c == x.c) {
           if (i == pattern.length() - 1 && x.value != null) {
               q.enqueue(prefix + x.c);
           }
           if ( i < pattern.length() - 1 ) {
               collect(x.mid, prefix + x.c, i + 1, pattern, q);
           }
        }
        if (c == '.' || c > x.c) {
            collect(x.right, prefix, i, pattern, q);
        }
    }

    public String longestPrefixOf(String query) {
        if (query == null) {
            throw new IllegalArgumentException("calls longestPrefixOf() with null argument");
        }
        if (query.length() == 0) return null;
        int length = 0;
        Node x = root;
        int i = 0;
        while (x != null && i < query.length()) {
            char c = query.charAt(i);
            if      (c < x.c) x = x.left;
            else if (c > x.c) x = x.right;
            else {
                i++;
                if (x.value != null) length = i;
                x = x.mid;
            }
        }
        return query.substring(0, length);
    }

    public static void main(String[] args) {
        TST<Integer> tst = new TST<>();
        for (int i = 0; i < 1000000; i++) {
            tst.put("xxx" + i, i);
        }
        for (String key : tst.keys()) {
            StdOut.println(key);
        }

        String prefix = "xxx9";
        StdOut.println("----keysWithPrefix: " + prefix);
        for (String key : tst.keysWithPrefix(prefix)) {
            StdOut.println(key);
        }

        String pattern = "..x1.";
        StdOut.println("----keysThatMatch: " + pattern);
        for (String key : tst.keysThatMatch(pattern)) {
            StdOut.println(key);
        }
    }

}

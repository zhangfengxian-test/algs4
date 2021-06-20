package cn.noncoder.algs4.search;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.*;

/**
 * 顺序查找（基于链表）
 * @param <Key>
 * @param <Value>
 */
public class SequentialSearchST<Key, Value> implements Iterable<Key> {

    private int size;
    private Node first;

    private class Node {
        Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public void put(Key key, Value value) {
        if (value == null) {
            delete(key);
            return;
        }
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.value = value;
                return;
            }
        }
        first = new Node(key, value, first);
        size++;
    }

    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.value;
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    public void delete(Key key) {
        if (first == null) {
            return;
        }
        if (first.key.equals(key)) {
            Node next = first.next;
            first.next = null;
            first = next;
            size--;
            return;
        }
        for (Node p = first, x = first.next; x != null; p = x, x = x.next) {
            if (key.equals(x.key)) {
                p.next = x.next;
                x.next = null;
                size--;
            }
        }
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Iterable<Key> keys() {
        Deque<Key> queue = new ArrayDeque<>(size);
        for (Node x = first; x != null; x = x.next) {
            queue.addFirst(x.key);
        }
        return queue;
    }

    @Override
    public Iterator<Key> iterator() {
        return keys().iterator();
    }

    public static void main(String[] args) {
        SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }

}

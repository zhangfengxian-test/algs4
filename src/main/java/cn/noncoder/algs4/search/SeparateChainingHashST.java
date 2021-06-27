package cn.noncoder.algs4.search;

import edu.princeton.cs.algs4.Queue;

/**
 * 基于拉链法的符号表
 * @param <Key>
 * @param <Value>
 */
public class SeparateChainingHashST<Key, Value> {

    private int n;
    private int m;
    private SequentialSearchST<Key, Value>[] st;

    public SeparateChainingHashST() {
        this(997);
    }

    public SeparateChainingHashST(int m) {
        this.m = m;
        st = new SequentialSearchST[m];
        for (int i = 0; i < m; i++) {
            st[i] = new SequentialSearchST<>();
        }
    }

    public Value get(Key key) {
        return st[hash(key)].get(key);
    }

    public void put(Key key, Value value) {
        if (value == null) {
            delete(key);
            return;
        }

        if (n >= m / 2) {
            resize(2 * m);
        }
        int i = hash(key);
        if (!st[i].contains(key)) {
            n++;
        }
        st[i].put(key, value);
    }

    private void resize(int cap) {
        SeparateChainingHashST<Key, Value> t = new SeparateChainingHashST<>(cap);
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys()) {
                t.put(key, st[i].get(key));
            }
        }

        st = t.st;
        m = t.m;
        n = t.n;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys()) {
                queue.enqueue(key);
            }
        }
        return queue;
    }

    public int size() {
        return n;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public void delete(Key key) {
        int i = hash(key);
        if (st[i].contains(key)) {
            n--;
        }
        st[i].delete(key);

        if (n > 0 && n <= m / 8) {
            resize(m / 2);
        }
    }

}

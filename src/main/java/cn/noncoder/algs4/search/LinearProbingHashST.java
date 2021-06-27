package cn.noncoder.algs4.search;

import edu.princeton.cs.algs4.Queue;

/**
 * 基于线性探测的符号表
 * @param <Key>
 * @param <Value>
 */
public class LinearProbingHashST<Key, Value> {

    private int n;
    private int m;
    private Key[] keys;
    private Value[] values;

    public LinearProbingHashST() {
        this(16);
    }

    public LinearProbingHashST(int m) {
        this.m = m;

        keys = (Key[]) new Object[m];
        values = (Value[]) new Object[m];
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public void put(Key key, Value value) {

        if (value == null) {
            delete(key);
            return;
        }

        if (n >= m / 2) {
            resize(2 * m);
        }
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }
        }

        keys[i] = key;
        values[i] = value;
        n++;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                return values[i];
            }
        }
        return null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    private void resize(int cap) {
        LinearProbingHashST<Key, Value> st = new LinearProbingHashST<>(cap);
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                st.put(keys[i], values[i]);
            }
        }

        keys = st.keys;
        values = st.values;
        m = st.m;
    }

    public void delete(Key key) {
        if (!contains(key)) {
            return;
        }

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                keys[i] = null;
                values[i] = null;
                break;
            }
        }

        for (i = (i + 1) % m; keys[i] != null; i = (i + 1) % m) {
            Key keyToRedo = keys[i];
            Value valueToRedo = values[i];
            keys[i] = null;
            values[i] = null;
            put(keyToRedo, valueToRedo);
        }
        n--;
        if (n > 0 && n <= m / 8) {
            resize(m / 2);
        }
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < m; i++) {
            if (keys[i] != null)
                queue.enqueue(keys[i]);
        }
        return queue;
    }

}

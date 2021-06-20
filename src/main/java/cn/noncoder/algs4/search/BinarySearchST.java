package cn.noncoder.algs4.search;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * 基于有序数组二分查找的实现
 * @param <Key>
 * @param <Value>
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {

    private Key[] keys;
    private Value[] values;
    private int N;

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    public int size() {
        return N;
    }

    public Value get(Key key) {
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            return values[i];
        }
        return null;
    }

    public void put(Key key, Value value) {

        if (value == null) {
            delete(key);
            return;
        }

        int i = rank(key);
        // 找到，则直接更新值
        if (i < N && keys[i].compareTo(key) == 0) {
            values[i] = value;
            return;
        }

        if (N == keys.length) {
            resize(N * 2);
        }

        // 往后挪一格
        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        keys[i] = key;
        values[i] = value;
        N++;
    }

    public int rank(Key key) {
        int lo = 0;
        int hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }

        }
        return lo;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Key min() {
        return keys[0];
    }

    public Key max() {
        return keys[N - 1];
    }

    public Key ceiling(Key key) {
        int i = rank(key);
        return keys[i];
    }

    public Key floor(Key key) {
        int i = rank(key);
        if (i < N && key.compareTo(keys[i]) == 0) {
            return keys[i];
        }
        if (i == 0) {
            throw new NoSuchElementException("argument to floor() is too small");
        }
        return keys[i - 1];
    }

    public void delete(Key key) {
        if (isEmpty()) {
            return;
        }
        int i = rank(key);

        if (i == N || keys[i].compareTo(key) != 0) {
            return;
        }

        for (int j = i; j < N - 1; j++) {
            keys[j] = keys[j + 1];
            values[j] = values[j + 1];
        }
        N--;
        keys[N] = null;
        values[N] = null;

        if (N > 0 && N == keys.length/4) {
            resize(keys.length/2);
        }
    }

    private void resize(int capacity) {
        Key[]   tempk = (Key[])   new Comparable[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            tempk[i] = keys[i];
            tempv[i] = values[i];
        }
        values = tempv;
        keys = tempk;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Key select(int k) {
        return keys[k];
    }

    public void deleteMax() {
        delete(max());
    }

    public void deleteMin() {
        delete(min());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        int to = rank(hi);
        List<Key> list = new LinkedList<>();
        for (int i = rank(lo); i < to; i++) {
            list.add(keys[i]);
        }
        if (contains(hi)) {
            list.add(hi);
        }
        return list;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

}

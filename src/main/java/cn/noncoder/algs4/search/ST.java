package cn.noncoder.algs4.search;

import java.util.Iterator;

public class ST<Key extends Comparable<Key>, Value> implements Iterable<Key> {

    public void put(Key key, Value value) {

    }

    public Value get(Key key) {
        return null;
    }

    public void delete(Key key) {

    }

    public boolean contains(Key key) {
        return false;
    }

    public boolean isEmpty() {
        return true;
    }

    public int size() {
        return 0;
    }

    public Key max() {
        return null;
    }

    public Key min() {
        return null;
    }

    public Key floor(Key key) {
        return null;
    }

    public Key ceiling(Key key) {
        return null;
    }

    public int rank(Key key) {
        return 0;
    }

    public Key select(int k) {
        return null;
    }

    public void deleteMax() {
        delete(max());
    }

    public void deleteMin() {
        delete(min());
    }

    public int size(Key lo, Key hi) {
        if (lo.compareTo(hi) > 0) {
            return 0;
        } else if (contains(hi)){
            return rank(hi) - rank(lo) + 1;
        } else {
            return rank(hi) - rank(lo);
        }
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        return null;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    @Override
    public Iterator<Key> iterator() {
        return keys().iterator();
    }
}

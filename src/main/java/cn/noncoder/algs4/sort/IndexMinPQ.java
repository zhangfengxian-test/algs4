package cn.noncoder.algs4.sort;

/**
 *
 * 关联索引的优先级队列
 * @param <Item> 存储的元素
 * @see MaxPQ
 */
public class IndexMinPQ<Item extends Comparable<Item>> {

    private int[] pq;
    private int[] qp;
    private int n;
    private Item[] items;

    public IndexMinPQ(int maxN) {
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        items = (Item[]) new Comparable[maxN + 1];
        for (int i = 1; i <= maxN; i++) {
            pq[i] = -1;
        }
    }

    public void insert(int k, Item item) {
        n++;
        pq[n] = k;
        qp[k] = n;
        items[k] = item;
        swim(n);
    }

    public void change(int k, Item item) {
        items[k] = item;
        swim(qp[k]);
        sink(qp[k]);
    }

    public boolean contains(int k) {
        return qp[k] != -1;
    }

    public void delete(int k) {
        int index = qp[k];
        exch(index, n--);
        swim(index);
        sink(index);
        items[k] = null;
        qp[k] = -1;
    }

    public Item min() {
        return items[pq[1]];
    }

    public int minIndex() {
        return pq[1];
    }

    public int delMin() {
        int min = pq[1];
        exch(1, n--);
        sink(1);
        qp[min] = -1;
        items[min] = null;
        pq[n + 1] = -1;
        return min;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private boolean greater(int i, int j) {
        return items[pq[i]].compareTo(items[pq[j]]) > 0;
    }

    private void swim(int k) {
        while (k > 1 && greater(k/2, k)) {
            exch(k/2, k);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2 * k < n) {
            int j = k * 2;
            if (j < n && greater(j, j + 1)) {
                j++;
            }
            if (!greater(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    private void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;

        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

}

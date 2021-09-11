package cn.noncoder.algs4.sort;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 优先队列的多项归并
 */
public class Multiway {

    public static void main(String[] args) {
        int n = args.length;
        In[] streams = new In[n];
        for (int i = 0; i < n; i++) {
            streams[i] = new In(args[i]);
        }
        merge(streams);
    }

    private static void merge(In[] streams) {
        int n = streams.length;
        IndexMinPQ<String> pq = new IndexMinPQ<>(n);
        for (int i = 0; i < n; i++) {
            if (!streams[i].isEmpty()) {
                pq.insert(i, streams[i].readString());
            }
        }

        while (!pq.isEmpty()) {
            StdOut.print(pq.min() + " ");
            int i = pq.delMin();
            if (!streams[i].isEmpty()) {
                pq.insert(i, streams[i].readString());
            }
        }
    }

}

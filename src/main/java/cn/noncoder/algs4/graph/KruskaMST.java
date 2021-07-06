package cn.noncoder.algs4.graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.UF;

import java.util.Arrays;

/**
 * kruska算法实现最小生成树：每一次取权重最小的边，加入的边不会与树中已有的边构成环，直到树中含有V-1边为止
 */
public class KruskaMST {

    private Queue<Edge> mst;
    private double weight;

    public KruskaMST(EdgeWeightedGraph g) {
        mst = new Queue<>();
        Edge[] edges = new Edge[g.e()];
        int i = 0;
        for (Edge e : g.edges()) {
            edges[i++] = e;
        }
        Arrays.sort(edges);

        UF uf = new UF(g.v());
        for (i = 0; i < edges.length && mst.size() < g.v() - 1; i++) {
            Edge e = edges[i];
            int v = e.either();
            int w = e.other(v);

            if (uf.connected(v, w)) {
                continue;
            }

            weight += e.weight();
            mst.enqueue(e);
            uf.union(v, w);
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        return weight;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);

        EdgeWeightedGraph g = new EdgeWeightedGraph(in);
        KruskaMST mst = new KruskaMST(g);

        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.println(mst.weight());
    }

}

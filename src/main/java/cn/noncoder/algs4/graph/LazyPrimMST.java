package cn.noncoder.algs4.graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * 最小生成树的Prim算法实现（延时实现）
 */
public class LazyPrimMST {

    private boolean[] marked;
    private Queue<Edge> mst;
    private MinPQ<Edge> pq;
    private double weight;

    public LazyPrimMST(EdgeWeightedGraph g) {
        marked = new boolean[g.v()];
        mst = new Queue<>();
        pq = new MinPQ<>();

        visit(g, 0);
        while (!pq.isEmpty()) {
            Edge e = pq.delMin();

            int v = e.either();
            int w = e.other(v);
            if (marked[v] && marked[w]) {
                continue;
            }
            weight += e.weight();
            mst.enqueue(e);
            if (!marked[v]) {
                visit(g, v);
            }
            if (!marked[w]) {
                visit(g, w);
            }
        }
    }

    private void visit(EdgeWeightedGraph g, int v) {
        marked[v] = true;
        for (Edge e : g.adj(v)) {
            if (!marked[e.other(v)]) {
                pq.insert(e);
            }
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
        LazyPrimMST mst = new LazyPrimMST(g);

        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.println(mst.weight());
    }

}

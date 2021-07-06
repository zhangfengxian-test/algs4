package cn.noncoder.algs4.graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 最短路径算法（dijkstra算法）
 */
public class DijkstraSP {

    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;

    public DijkstraSP(EdgeWeightedDigraph g, int s) {
        edgeTo = new DirectedEdge[g.v()];
        distTo = new double[g.v()];
        pq = new IndexMinPQ<>(g.v());

        for (int v = 0; v < g.v(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        pq.insert(s, 0.0);
        distTo[s] = 0.0;
        while (!pq.isEmpty()) {
            relax(g, pq.delMin());
        }
    }

    private void relax(EdgeWeightedDigraph g, int v) {
        for (DirectedEdge e : g.adj(v)) {
            int w = e.to();

            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (pq.contains(w)) {
                    pq.changeKey(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge x = edgeTo[v]; x != null; x = edgeTo[x.from()]) {
            path.push(x);
        }
        return path;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int s = Integer.parseInt(args[1]);

        EdgeWeightedDigraph g = new EdgeWeightedDigraph(in);
        DijkstraSP sp = new DijkstraSP(g, s);

        for (int t = 0; t < g.v(); t++) {
            StdOut.print(s + " to " + t);
            StdOut.printf(" (%4.2f): ", sp.distTo(t));
            if (sp.hasPathTo(t)) {
                for (DirectedEdge e : sp.pathTo(t)) {
                    StdOut.print(e + " ");
                }
            }
            StdOut.println();
        }

    }

}

package cn.noncoder.algs4.graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 无环加权有向图的最短路径算法
 */
public class AcyclicSP {

    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public AcyclicSP(EdgeWeightedDigraph g, int s) {
        edgeTo = new DirectedEdge[g.v()];
        distTo = new double[g.v()];

        for (int v = 0; v < g.v(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        Topological top = new Topological(g);
        for (int v : top.order()) {
            relax(g, v);
        }
    }

    private void relax(EdgeWeightedDigraph g, int v) {
        for (DirectedEdge e : g.adj(v)) {
            int w = e.to();

            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
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
        AcyclicSP sp = new AcyclicSP(g, s);

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

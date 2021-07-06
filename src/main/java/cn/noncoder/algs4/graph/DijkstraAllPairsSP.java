package cn.noncoder.algs4.graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 任意顶点对之间的最短路径
 */
public class DijkstraAllPairsSP {

    private DijkstraSP[] all;

    public DijkstraAllPairsSP(EdgeWeightedDigraph g) {
        all = new DijkstraSP[g.v()];
        for (int v = 0; v < g.v(); v++) {
            all[v] = new DijkstraSP(g, v);
        }
    }

    public Iterable<DirectedEdge> path(int s, int t) {
        return all[s].pathTo(t);
    }

    public double weight(int s, int t) {
        return all[s].distTo(t);
    }

    public static void main(String[] args) {
        In in = new In(args[0]);

        EdgeWeightedDigraph g = new EdgeWeightedDigraph(in);
        DijkstraAllPairsSP all = new DijkstraAllPairsSP(g);
        while (!StdIn.isEmpty()) {
            int s = StdIn.readInt();
            int t = StdIn.readInt();
            StdOut.print(s + " to " + t);
            StdOut.printf(" (%4.2f): ", all.weight(s, t));
            for (DirectedEdge e : all.path(s, t)) {
                StdOut.print(e + " ");
            }
            StdOut.println();
        }
    }

}

package cn.noncoder.algs4.graph;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 货币中的套汇
 */
public class Arbitrage {

    public static void main(String[] args) {
        int n = StdIn.readInt();
        String[] name = new String[n];
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(n);
        for (int v = 0; v < n; v++) {
            name[v] = StdIn.readString();
            for (int w = 0; w < n; w++) {
                double rate = StdIn.readDouble();
                g.addEdge(new DirectedEdge(v, w, -Math.log(rate)));
            }
        }

        BellmanFordSP sp = new BellmanFordSP(g, 0);
        // find negative cycle
        if (sp.hasNegativeCycle()) {
            double stake = 1000.0;
            for (DirectedEdge e : sp.negativeCycle()) {
                StdOut.printf("%10.5f %s ", stake, name[e.from()]);
                stake *= Math.exp(-e.weight());
                StdOut.printf("= %10.5f %s\n", stake, name[e.to()]);
            }
        }
        else {
            StdOut.println("No arbitrage opportunity");
        }
    }

}

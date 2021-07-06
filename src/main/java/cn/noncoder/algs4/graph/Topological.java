package cn.noncoder.algs4.graph;

import edu.princeton.cs.algs4.StdOut;

/**
 * 拓扑排序
 */
public class Topological {

    private Iterable<Integer> order;

    public Topological(Digraph g) {
        DirectedCycle cycleFinder = new DirectedCycle(g);
        if (!cycleFinder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(g);
            order = dfs.reversePost();
        }
    }

    public Topological(EdgeWeightedDigraph g) {
        EdgeWeightedDirectedCycle cycleFinder = new EdgeWeightedDirectedCycle(g);
        if (!cycleFinder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(g);
            order = dfs.reversePost();
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean isDAG() {
        return order != null;
    }

    public static void main(String[] args) {
        String filename = args[0];
        String separator = args[1];
        SymbolDigraph g = new SymbolDigraph(filename, separator);

        Topological top = new Topological(g.g());
        for (int v : top.order()) {
            StdOut.println(g.name(v));
        }
    }

}

package cn.noncoder.algs4.graph;

import edu.princeton.cs.algs4.Stack;

public class EdgeWeightedDirectedCycle {

    private boolean[] marked;
    private DirectedEdge[] edgeTo;
    private boolean[] onStack;
    private Stack<DirectedEdge> cycle;

    public EdgeWeightedDirectedCycle(EdgeWeightedDigraph g) {
        onStack = new boolean[g.v()];
        marked = new boolean[g.v()];
        edgeTo = new DirectedEdge[g.v()];
        for (int v = 0; v < g.v(); v++) {
            if (!marked[v]) {
                dfs(g, v);
            }
        }
    }

    private void dfs(EdgeWeightedDigraph g, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (DirectedEdge e : g.adj(v)) {
            int w = e.to();
            if (hasCycle()) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = e;
                dfs(g, w);
            } else if (onStack[w]) {
                cycle = new Stack<>();

                DirectedEdge f = e;
                while (f.from() != w) {
                    cycle.push(f);
                    f = edgeTo[f.from()];
                }
                cycle.push(f);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<DirectedEdge> cycle() {
        return cycle;
    }
}

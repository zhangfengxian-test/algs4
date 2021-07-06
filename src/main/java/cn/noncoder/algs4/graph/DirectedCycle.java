package cn.noncoder.algs4.graph;

import edu.princeton.cs.algs4.Stack;

/**
 * 有向环
 */
public class DirectedCycle {

    private boolean[] marked;
    private int[] edgeTo;
    private boolean[] onStack;
    private Stack<Integer> cycle;

    public DirectedCycle(Digraph g) {
        onStack = new boolean[g.v()];
        marked = new boolean[g.v()];
        edgeTo = new int[g.v()];
        for (int v = 0; v < g.v(); v++) {
            if (!marked[v]) {
                dfs(g, v);
            }
        }
    }

    private void dfs(Digraph g, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w :g.adj(v)) {
            if (hasCycle()) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            } else if (onStack[w]) {
                cycle = new Stack<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

}

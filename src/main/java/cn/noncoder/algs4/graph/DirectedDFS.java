package cn.noncoder.algs4.graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 有向图的深度优先搜索
 */
public class DirectedDFS {

    private boolean[] marked;

    public DirectedDFS(Digraph g, int s) {
        marked = new boolean[g.v()];
        dfs(g, s);
    }

    public DirectedDFS(Digraph g, Iterable<Integer> sources) {
        marked = new boolean[g.v()];
        for (int s : sources) {
            if (!marked[s]) {
                dfs(g, s);
            }
        }
    }

    private void dfs(Digraph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public static void main(String[] args) {
        Digraph g = new Digraph(new In(args[0]));

        Bag<Integer> sources = new Bag<>();
        for (int i = 1; i < args.length; i++) {
            sources.add(Integer.parseInt(args[i]));
        }

        DirectedDFS reachable = new DirectedDFS(g, sources);

        for (int v = 0; v < g.v(); v++) {
            if (reachable.marked(v)) {
                StdOut.print(v + "  ");
            }
        }
        StdOut.println();
    }

}

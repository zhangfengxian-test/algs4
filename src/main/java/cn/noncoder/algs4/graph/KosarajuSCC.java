package cn.noncoder.algs4.graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 强连通分量（Kosaraju算法）
 */
public class KosarajuSCC {

    private boolean[] marked;
    private int[] id;
    private int count;

    public KosarajuSCC(Digraph g) {
        marked = new boolean[g.v()];
        id = new int[g.v()];
        DepthFirstOrder order = new DepthFirstOrder(g.reverse());
        for (int s : order.reversePost()) {
            if (!marked[s]) {
                dfs(g, s);
                count++;
            }
        }
    }

    private void dfs(Digraph g, int v) {
        marked[v] = true;
        id[v] = count;
        for(int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
    }

    public boolean isStronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    public int count() {
        return count;
    }

    public int id(int v) {
        return id[v];
    }

    public static void main(String[] args) {
        Digraph g = new Digraph(new In(args[0]));
        KosarajuSCC scc = new KosarajuSCC(g);

        int m = scc.count();
        StdOut.println(m + " components");
        Bag<Integer>[] components = new Bag[m];
        for (int i = 0; i < m; i++) {
            components[i] = new Bag<>();
        }
        for (int v = 0; v < g.v(); v++) {
            components[scc.id(v)].add(v);
        }
        for (int i = 0; i < m; i++) {
            for (int v : components[i]){
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
    }

}

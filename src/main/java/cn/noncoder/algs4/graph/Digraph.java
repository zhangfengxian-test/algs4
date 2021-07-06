package cn.noncoder.algs4.graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * 有向图
 */
public class Digraph {

    private final int v;
    private int e;
    private Bag<Integer>[] adj;

    public Digraph(int v) {
        this.v = v;
        adj = new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<>();
        }
    }

    public Digraph(In in) {
        this(in.readInt());
        int e = in.readInt();
        for (int i = 0; i < e; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    public int v() {
        return v;
    }

    public int e() {
        return e;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        e++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public Digraph reverse() {
        Digraph digraph = new Digraph(this.v);
        for (int v = 0; v < this.v; v++) {
            for (int w : adj(v)) {
                digraph.addEdge(w, v);
            }
        }
        return digraph;
    }

}

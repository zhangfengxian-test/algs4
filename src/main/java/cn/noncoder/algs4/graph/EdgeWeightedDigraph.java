package cn.noncoder.algs4.graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * 加权有向图
 */
public class EdgeWeightedDigraph {

    private final int v;
    private int e;
    private Bag<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int v) {
        this.v = v;
        adj = new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<>();
        }
    }

    public EdgeWeightedDigraph(In in) {
        this(in.readInt());
        int e = in.readInt();
        for (int i = 0; i < e; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            addEdge(new DirectedEdge(v, w, weight));
        }
    }

    public int v() {
        return v;
    }

    public int e() {
        return e;
    }

    public void addEdge(DirectedEdge e) {
        adj[e.from()].add(e);
        this.e++;
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> edges = new Bag<>();
        for (int v = 0; v < this.v; v++) {
            for (DirectedEdge e : adj(v)) {
                edges.add(e);
            }
        }
        return edges;
    }

    @Override
    public String toString() {
        StringBuilder g = new StringBuilder();
        g.append(String.format("%d%n%d", v, e));
        for (DirectedEdge e : edges()) {
            g.append(String.format("%n%d %d %.5f", e.from(), e.to(), e.weight()));
        }
        return g.toString();
    }
}

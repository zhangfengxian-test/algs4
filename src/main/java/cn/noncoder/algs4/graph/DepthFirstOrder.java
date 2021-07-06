package cn.noncoder.algs4.graph;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

/**
 * 有向图中基于深度优先搜索的顶点排序
 */
public class DepthFirstOrder {

    private boolean[] marked;
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost;

    public DepthFirstOrder(Digraph g) {
        marked = new boolean[g.v()];
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();
        for (int v = 0; v < g.v(); v++) {
            if (!marked[v]) {
                dfs(g, v);
            }
        }
    }

    public DepthFirstOrder(EdgeWeightedDigraph g) {
        marked = new boolean[g.v()];
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();
        for (int v = 0; v < g.v(); v++) {
            if (!marked[v]) {
                dfs(g, v);
            }
        }
    }

    private void dfs(EdgeWeightedDigraph g, int v) {
        pre.enqueue(v);

        marked[v] = true;
        for (DirectedEdge e : g.adj(v)) {
            int w = e.to();
            if (!marked[w]) {
                dfs(g, w);
            }
        }

        post.enqueue(v);
        reversePost.push(v);
    }

    private void dfs(Digraph g, int v) {
        pre.enqueue(v);

        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }

        post.enqueue(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }

}

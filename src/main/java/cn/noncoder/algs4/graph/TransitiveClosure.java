package cn.noncoder.algs4.graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 传递性闭包
 */
public class TransitiveClosure {

    private DirectedDFS[] all;

    public TransitiveClosure(Digraph g) {
        all = new DirectedDFS[g.v()];
        for (int v = 0; v < g.v(); v++) {
            all[v] = new DirectedDFS(g, v);
        }
    }

    public boolean reachable(int v, int w) {
        return all[v].marked(w);
    }

    public static void main(String[] args) {
        Digraph g = new Digraph(new In(args[0]));

        TransitiveClosure tc = new TransitiveClosure(g);
        while (!StdIn.isEmpty()) {
            String line = StdIn.readLine();
            String[] arr = line.split(" ");
            int v = Integer.parseInt(arr[0]);
            int w = Integer.parseInt(arr[1]);
            StdOut.println(v + " -> " + w + ": " + tc.reachable(v, 2));
        }
    }

}

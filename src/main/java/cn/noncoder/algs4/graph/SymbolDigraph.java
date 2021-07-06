package cn.noncoder.algs4.graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 符号图
 */
public class SymbolDigraph {

    private ST<String, Integer> st;
    private String[] keys;
    Digraph g;

    public SymbolDigraph(String stream, String sp) {
        st = new ST<>();
        In in = new In(stream);
        while (in.hasNextLine()) {
            String[] s = in.readLine().split(sp);
            for (int i = 0; i < s.length; i++) {
                if (!st.contains(s[i])) {
                    st.put(s[i], st.size());
                }
            }
        }

        keys = new String[st.size()];
        for (String name : st.keys()){
            keys[st.get(name)] = name;
        }

        g = new Digraph(st.size());
        in = new In(stream);
        while (in.hasNextLine()) {
            String[] s = in.readLine().split(sp);
            int v = st.get(s[0]);
            for (int i = 1; i < s.length; i++) {
                g.addEdge(v, st.get(s[i]));
            }
        }
    }

    public boolean contains(String s) {
        return st.contains(s);
    }

    public int index(String s) {
        return st.get(s);
    }

    public String name(int v) {
        return keys[v];
    }

    public Digraph g() {
        return g;
    }

    public static void main(String[] args) {
        String filename = args[0];
        String delim = args[1];
        SymbolDigraph sg = new SymbolDigraph(filename, delim);

        Digraph g = sg.g();
        while (StdIn.hasNextLine()) {
            String source = StdIn.readLine();
            for (int w : g.adj(sg.index(source))) {
                StdOut.println("    " + sg.name(w));
            }
        }
    }

}

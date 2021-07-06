package cn.noncoder.algs4.graph;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 间隔的度数
 */
public class DegreesOfSeparation {

    public static void main(String[] args) {
        SymbolGraph sg = new SymbolGraph(args[0], args[1]);

        Graph g = sg.g();

        String source = args[2];
        if (!sg.contains(source)) {
            StdOut.println(source + " not in database.");
            return;
        }

        int s = sg.index(source);
        BreadthFirstPaths bfs = new BreadthFirstPaths(g, s);

        while (!StdIn.isEmpty()) {
            String link = StdIn.readLine();
            if (!sg.contains(link)) {
                StdOut.println(link + " not in database.");
                continue;
            }
            int t = sg.index(link);
            if (!bfs.hasPathTo(t)) {
                StdOut.println("Not connected");
            }

            for (int v : bfs.pathTo(t)) {
                StdOut.println("  " + sg.name(v));
            }
        }
    }

}

package cn.noncoder.algs4.graph;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 优先级限定下的并行任务调度问题的关键路径方法
 */
public class CPM {

    public static void main(String[] args) {
        int n = StdIn.readInt();
        StdIn.readLine();
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(n * 2 + 2);
        int s = n * 2;
        int t = n * 2 + 1;
        for (int i = 0; i < n; i++) {
            String[] a = StdIn.readLine().split("\\s+");
            double duration = Double.parseDouble(a[0]);
            g.addEdge(new DirectedEdge(i, n + i, duration));
            // 起始顶点，权重为0
            g.addEdge(new DirectedEdge(s, i, 0.0));
            // 结束顶点，权重为0
            g.addEdge(new DirectedEdge(n + i, t, 0.0));

            // 依赖的任务节点
            for (int j = 1; j < a.length; j++) {
                int successor = Integer.parseInt(a[j]);
                g.addEdge(new DirectedEdge(n + i, successor, 0.0));
            }
        }

        AcyclicLP lp = new AcyclicLP(g, s);
        StdOut.println("Start times:");
        for (int i = 0; i < n; i++) {
            StdOut.printf("%4d: %5.1f\n", i, lp.distTo(i));
        }
        StdOut.printf("Finish time: %5.1f\n", lp.distTo(t));
    }

}

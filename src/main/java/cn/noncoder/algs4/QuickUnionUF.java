package cn.noncoder.algs4;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * quick-union实现，基础思想就是每个触点所对应的id[]是同一个分量中的另外一个触点的名称（可能是自己），
 * 这种是采用链接的方式。可以看做是quick-find的改良版。
 * 极端情况下，构造的树的深度可能太深
 * @see QuickFindUF
 */
public class QuickUnionUF {

    private int[] id; // 分量id（以触点作为索引）
    private int count; // 分量的数量

    public QuickUnionUF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
           id[i] = i;
        }
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }

        id[pRoot] = qRoot;
        count--;
    }

    private int find(int p) {
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int count() {
        return count;
    }

    public void printId() {
        StdOut.println(Arrays.toString(id));
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        QuickUnionUF uf = new QuickUnionUF(N);

        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();

            if (uf.connected(p, q)) {
                continue;
            }
            uf.union(p, q);
        }

        StdOut.println(uf.count + "components");
    }

}

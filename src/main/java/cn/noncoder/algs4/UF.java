package cn.noncoder.algs4;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * 路径压缩的加权的quick-union实现
 * @see QuickFindUF
 * @see QuickUnionUF
 * @see WeightedQuickUnionUF
 */
public class UF {

    private int[] id; // 分量id（以触点作为索引）
    private int[] sz; // 各根节点所对应的分量的大小
    private int count; // 分量的数量

    public UF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
           id[i] = i;
        }

        sz = new int[N];
        for (int i = 0; i < N; i++) {
            sz[i] = 1;
        }
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }

        if (sz[pRoot] < sz[qRoot]) {
            id[pRoot] = qRoot;
        } else if (sz[pRoot] > sz[qRoot]) {
            id[qRoot] = pRoot;
        } else {
            id[qRoot] = pRoot;
            sz[pRoot]++;
        }

        count--;
    }

    private int find(int p) {
        while (p != id[p]) {
            id[p] = id[id[p]];
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
        UF uf = new UF(N);

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

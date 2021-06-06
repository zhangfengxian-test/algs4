package cn.noncoder.algs4.ex1;

import cn.noncoder.algs4.WeightedQuickUnionUF;

/**
 *  使用加权quick-union算 法 （请见算法1 . 5 ) 完成练习1.5.1。
 */
public class Ex1_5_3 {

    public static void main(String[] args) {
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(10);
        uf.union(9, 0);
        uf.printId();
        // [9, 1, 2, 3, 4, 5, 6, 7, 8, 9]

        uf.union(3, 4);
        uf.printId();
        // [9, 1, 2, 3, 3, 5, 6, 7, 8, 9]

        uf.union(5, 8);
        uf.printId();
        // [9, 1, 2, 3, 3, 5, 6, 7, 5, 9]

        uf.union(7, 2);
        uf.printId();
        // [9, 1, 7, 3, 3, 5, 6, 7, 5, 9]

        uf.union(2, 1);
        uf.printId();
        // [9, 7, 7, 3, 3, 5, 6, 7, 5, 9]

        uf.union(5, 7);
        uf.printId();
        // [9, 7, 7, 3, 3, 7, 6, 7, 5, 9]

        uf.union(0, 3);
        uf.printId();
        // [9, 7, 7, 9, 3, 7, 6, 7, 5, 9]

        uf.union(4, 2);
        uf.printId();
        // [9, 7, 7, 9, 3, 7, 6, 7, 5, 7]
    }

}

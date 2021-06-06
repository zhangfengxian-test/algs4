package cn.noncoder.algs4.ex1;

import cn.noncoder.algs4.QuickFindUF;

/**
 * 1.5.1用quick-find算法处理序列9-0 3-4 5-8 7-2 2-1 5-7 0-3 4-2。
 * 对于输入的每一对整数，给出id []* 数组的内容和访问数组的次数。
 */
public class Ex1_5_1 {

    public static void main(String[] args) {
        QuickFindUF uf = new QuickFindUF(10);
        uf.union(9, 0);
        uf.printId();
        // [0, 1, 2, 3, 4, 5, 6, 7, 8, 0]
        // N + 2 + 1

        uf.union(3, 4);
        uf.printId();
        // [0, 1, 2, 4, 4, 5, 6, 7, 8, 0]
        // N + 2 + 1

        uf.union(5, 8);
        uf.printId();
        // [0, 1, 2, 4, 4, 8, 6, 7, 8, 0]
        // N + 2 + 1

        uf.union(7, 2);
        uf.printId();
        // [0, 1, 2, 4, 4, 8, 6, 2, 8, 0]
        // N + 2 + 1


        uf.union(2, 1);
        uf.printId();
        // [0, 1, 1, 4, 4, 8, 6, 1, 8, 0]
        // N + 2 + 2

        uf.union(5, 7);
        uf.printId();
        // [0, 1, 1, 4, 4, 1, 6, 1, 1, 0]
        // 2

        uf.union(0, 3);
        uf.printId();
        // [4, 1, 1, 4, 4, 1, 6, 1, 1, 4]
        // N + 2 + 2

        uf.union(4, 2);
        uf.printId();
        // [1, 1, 1, 1, 1, 1, 6, 1, 1, 1]
        // N + 2 + 4
    }

}

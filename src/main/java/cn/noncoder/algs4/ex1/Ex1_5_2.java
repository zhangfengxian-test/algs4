package cn.noncoder.algs4.ex1;

import cn.noncoder.algs4.QuickFindUF;
import cn.noncoder.algs4.QuickUnionUF;

/**
 * 1.5.2 使用quick-union算 法 （请 见 1.5.2.3节代码框）完成练习1.5.1。另外，在处理完输入的每对整数
 * 之后画出id[]数组表示的森林。
 */
public class Ex1_5_2 {

    public static void main(String[] args) {
        QuickUnionUF uf = new QuickUnionUF(10);
        uf.union(9, 0);
        uf.printId();
        // [0, 1, 2, 3, 4, 5, 6, 7, 8, 0]

        uf.union(3, 4);
        uf.printId();
        // [0, 1, 2, 4, 4, 5, 6, 7, 8, 0]

        uf.union(5, 8);
        uf.printId();
        // [0, 1, 2, 4, 4, 8, 6, 2, 8, 0]

        uf.union(7, 2);
        uf.printId();
        // [0, 1, 1, 4, 4, 8, 6, 2, 8, 0]

        uf.union(2, 1);
        uf.printId();
        // [0, 1, 1, 4, 4, 8, 6, 2, 8, 0]

        uf.union(5, 7);
        uf.printId();
        // [0, 1, 1, 4, 4, 8, 6, 2, 1, 0]

        uf.union(0, 3);
        uf.printId();
        // [4, 1, 1, 4, 4, 8, 6, 2, 1, 0]

        uf.union(4, 2);
        uf.printId();
        // [4, 1, 1, 4, 1, 8, 6, 2, 1, 0]
    }

}

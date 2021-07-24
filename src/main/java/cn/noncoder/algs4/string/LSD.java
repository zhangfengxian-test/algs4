package cn.noncoder.algs4.string;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 低位优先的字符串排序
 */
public class LSD {

    public static void sort(String[] a, int w) {
        int n = a.length;
        int r = 256;
        String[] aux = new String[n];

        for (int d = w - 1; d >= 0; d--) {
            // 计算字符出现频率
            int[] count = new int[r + 1];
            for (int i = 0; i < n; i++) {
                count[a[i].charAt(d) + 1]++;
            }

            // 将频率转换成索引
            for (int i = 0; i < r; i++) {
                count[i + 1] += count[i];
            }

            // 将元素分类
            for (int i = 0; i < n; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }

            // 回写
            for (int i = 0; i < n; i++) {
                a[i] = aux[i];
            }
        }
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        int n = a.length;

        // check that strings have fixed length
        int w = a[0].length();
        for (int i = 0; i < n; i++) {
            assert a[i].length() == w : "Strings must have fixed length";
        }

        // sort the strings
        sort(a, w);

        // print results
        for (int i = 0; i < n; i++) {
            StdOut.println(a[i]);
        }
    }

}

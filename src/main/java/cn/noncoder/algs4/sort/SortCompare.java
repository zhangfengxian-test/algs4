package cn.noncoder.algs4.sort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

/**
 * 比较各自排序算法的性能
 */
public class SortCompare {

    public static double time(String alg, Double[] a) {
        Stopwatch timer = new Stopwatch();
        switch (alg) {
            case "Insertion": {
                Insertion.sort(a);
                break;
            }
            case "Selection": {
                Selection.sort(a);
                break;
            }
            case "Shell": {
                Shell.sort(a);
                break;
            }
            case "Merge": {
                Merge.sort(a);
                break;
            }
            case "MergeBU": {
                MergeBU.sort(a);
                break;
            }
            case "Quick": {
                Quick.sort(a);
                break;
            }
            case "Quick3way": {
                Quick3way.sort(a);
                break;
            }
            case "Java": {
                Arrays.sort(a);
                break;
            }
        }
        return timer.elapsedTime();
    }

    public static double timeRandomInput(String alg, int N, int T) {
        double total = 0.0;
        Double[] a = new Double[N];
        for (int t = 0; t < T; t++) {
            for (int i = 0; i < N; i++) {
                a[i] = StdRandom.uniform();
            }
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        String alg1 = args[0];
        String alg2 = args[1];
        int N = Integer.parseInt(args[2]);
        int T = Integer.parseInt(args[2]);
        double t1 = timeRandomInput(alg1, N, T);
        StdOut.printf("%s elapsed time %.1f seconds\n", alg1, t1);
        double t2 = timeRandomInput(alg2, N, T);
        StdOut.printf("%s elapsed time %.1f seconds\n", alg2, t2);
        StdOut.printf("For %d random Doubles\n  %s is", N, alg1);
        StdOut.printf(" %.1f times faster than %s\n", t2 / t1, alg2);
    }

}

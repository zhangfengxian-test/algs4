package cn.noncoder.algs4.search;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 单词频率统计
 */
public class FrequencyCounter {

    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
        int minLength = Integer.parseInt(args[0]);

        BST<String, Integer> st = new BST<>();
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (word.length() < minLength) {
                continue;
            }
            if (!st.contains(word)) {
                st.put(word, 1);
            } else {
                st.put(word, st.get(word) + 1);
            }
        }

        String max = null;
        for (String word : st.keys()) {
            if (max == null || st.get(word) > st.get(max)) {
                max = word;
            }
        }
        StdOut.println("elapsed time: " + stopwatch.elapsedTime() + "s");
        StdOut.println("size: " + st.size());
        StdOut.println(max + " " + st.get(max));
    }

}

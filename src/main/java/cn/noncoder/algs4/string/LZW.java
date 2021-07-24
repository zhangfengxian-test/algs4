package cn.noncoder.algs4.string;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

/**
 * LZW压缩
 */
public class LZW {

    private static final int r = 256;
    private static final int l = 4096; // 2^12
    private static final int w = 12;

    public static void compress() {
        String input = BinaryStdIn.readString();
        TST<Integer> st = new TST<>();

        for (int i = 0; i < r; i++) {
            st.put("" + (char) i, i);
        }
        int code = r + 1;

        while (input.length() > 0) {
            String s = st.longestPrefixOf(input);
            BinaryStdOut.write(st.get(s), w);

            int t = s.length();
            if (t < input.length() && code < l) {
                st.put(input.substring(0, t + 1), code++);
            }
            input = input.substring(t);
        }

        BinaryStdOut.write(r, w);
        BinaryStdOut.close();
    }

    public static void expand() {
        String[] st = new String[l];
        int i;
        for (i = 0; i < r; i++) {
            st[i] = "" + (char) i;
        }
        st[i++] = " ";

        int codeword = BinaryStdIn.readInt(w);
        String value = st[codeword];
        while (true) {
            BinaryStdOut.write(value);
            codeword = BinaryStdIn.readInt(w);
            if (codeword == r) {
                break;
            }
            String s = st[codeword];
            if (i == codeword) {
                s = value + value.charAt(0);
            }
            if (i < l) {
                st[i++] = value + s.charAt(0);
            }
            value = s;
        }
        BinaryStdOut.close();
    }

    public static void main(String[] args) {
        if (args[0].equals("-")) {
            compress();
        } else if (args[0].equals("+")) {
            expand();
        }
    }

}

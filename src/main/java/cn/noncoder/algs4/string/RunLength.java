package cn.noncoder.algs4.string;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class RunLength {

    public static void expand() {
        boolean b = false;
        while (!BinaryStdIn.isEmpty()) {
            char run = BinaryStdIn.readChar();
            for (int i = 0; i < run; i++) {
                BinaryStdOut.write(b);
            }
            b = !b;
        }
        BinaryStdIn.close();
    }

    public static void compress() {
        char run = 0;
        boolean b;
        boolean old = false;
        while (!BinaryStdIn.isEmpty()) {
            b = BinaryStdIn.readBoolean();
            if (b != old) {
                BinaryStdOut.write(run);
                run = 1;
                old = !old;
            } else {
                if (run == 255) {
                    BinaryStdOut.write(run);
                    run = 0;
                    BinaryStdOut.write(run);
                }
                run++;
            }
        }
        BinaryStdOut.write(run);
        BinaryStdIn.close();
    }

    public static void main(String[] args) {
        if (args[0].equals("-")) {
            compress();
        } else if (args[0].equals("+")) {
            expand();
        }
    }

}

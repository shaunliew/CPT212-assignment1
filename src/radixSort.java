// a) sorting words instead of numbers
// b) words of different length
// based on lecturer start from back to front


/******************************************************************************
 *  Compilation:  javac LSD.java
 *  Execution:    java LSD < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/51radix/words3.txt
 *
 *  LSD radix sort
 *
 *    - Sort a String[] array of n extended ASCII strings (R = 256), each of length w.
 *
 *    - Sort an int[] array of n 32-bit integers, treating each integer as
 *      a sequence of w = 4 bytes (R = 256).
 *
 *  Uses extra space proportional to n + R.
 *
 *
 *  % java LSD < words3.txt
 *  all
 *  bad
 *  bed
 *  bug
 *  dad
 *  ...
 *  yes
 *  yet
 *  zoo
 *
 ******************************************************************************/

import java.io.IOException;
import java.util.Collections;
import java.util.Vector;

/**
 *  The {@code LSD} class provides static methods for sorting an
 *  array of <em>w</em>-character strings or 32-bit integers using LSD radix sort.
 *  <p>
 *  For additional documentation,
 *  see <a href="https://algs4.cs.princeton.edu/51radix">Section 5.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class radixSort {
    private static final int BITS_PER_BYTE = 8;

    // do not instantiate
    private radixSort() { }

    /**
     * Rearranges the array of w-character strings in ascending order.
     *
     * @param a the array to be sorted
     * @param w the number of characters per string
     */
    public static void sort(String[] a, int w) {
        int n = a.length;
        int R = 256;   // extend ASCII alphabet size
        String[] aux = new String[n];

        for (int d = w-1; d >= 0; d--) {
            // sort by key-indexed counting on dth character

            // compute frequency counts
            int[] count = new int[R+1];
            for (int i = 0; i < n; i++)
                count[a[i].charAt(d) + 1]++;

            // compute cumulates
            for (int r = 0; r < R; r++)
                count[r+1] += count[r];

            // move data
            for (int i = 0; i < n; i++)
                aux[count[a[i].charAt(d)]++] = a[i];

            // copy back
            for (int i = 0; i < n; i++)
                a[i] = aux[i];
        }
    }

    /**
     * Rearranges the array of 32-bit integers in ascending order.
     * This is about 2-3x faster than Arrays.sort().
     *
     * @param a the array to be sorted
     */
    public static void sort(int[] a) {
        final int BITS = 32;                 // each int is 32 bits
        final int R = 1 << BITS_PER_BYTE;    // each bytes is between 0 and 255
        final int MASK = R - 1;              // 0xFF
        final int w = BITS / BITS_PER_BYTE;  // each int is 4 bytes

        int n = a.length;
        int[] aux = new int[n];

        for (int d = 0; d < w; d++) {

            // compute frequency counts
            int[] count = new int[R+1];
            for (int i = 0; i < n; i++) {
                int c = (a[i] >> BITS_PER_BYTE*d) & MASK;
                count[c + 1]++;
            }

            // compute cumulates
            for (int r = 0; r < R; r++)
                count[r+1] += count[r];

            // for most significant byte, 0x80-0xFF comes before 0x00-0x7F
            if (d == w-1) {
                int shift1 = count[R] - count[R/2];
                int shift2 = count[R/2];
                for (int r = 0; r < R/2; r++)
                    count[r] += shift1;
                for (int r = R/2; r < R; r++)
                    count[r] -= shift2;
            }

            // move data
            for (int i = 0; i < n; i++) {
                int c = (a[i] >> BITS_PER_BYTE*d) & MASK;
                aux[count[c]++] = a[i];
            }

            // copy back
            for (int i = 0; i < n; i++)
                a[i] = aux[i];
        }
    }

    /**
     * Reads in a sequence of fixed-length strings from standard input;
     * LSD radix sorts them;
     * and prints them to standard output in ascending order.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        // set file path
        String file_name = "src/wordList.txt";

        // declare string vector to store input
        Vector<String> string_vector = new Vector<>();

        // import the words from .txt file
        try{
            importWords.import_words(file_name, string_vector);
            String[] a = string_vector.toArray(new String[0]);
            int n = a.length;

            // check that strings have fixed length
            int w = a[0].length();
            for (int i = 0; i < n; i++)
                assert a[i].length() == w : "Strings must have fixed length";

            // sort the strings
            sort(a, w);

            // print results
            for (int i = 0; i < n; i++)
                System.out.println(a[i]);
        }catch(IOException e){
            System.out.println("Something went wrong when reading a file");
        }

        // display total words read from .txt file
        System.out.println("Total length after reading .txt file is :" + string_vector.size());

    }
}



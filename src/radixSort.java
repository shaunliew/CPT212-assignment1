import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

public class radixSort {

    private radixSort() {
    }

    // find the longest string length in wordList.txt
    static int getLongestString(Vector<String> string_vector) {
        int maxLength = 0;
        for (String s : string_vector) {
            if (s.length() > maxLength) {
                maxLength = s.length();
            }
        }
        return maxLength;
    }

    // starting radix sort
    static long radix_sort(Vector<String> string_vector, char smallest, char largest) {

        /*
        System.out.println((int)'\''); //smallest ascii -39
        System.out.println((int)'™'); //largest ascii - 8483
         */

        int n = string_vector.size();
        int count_size = (int) largest - smallest + 2;
        Vector<String> aux = new Vector<>();
        aux.setSize(n);

        long counter = 7L; // int n = ..., .size(), - smallest, + 2, int count_size = ..., aux = .., .setSize(n)

        // find the longest string length in wordList.txt
        int z = getLongestString(string_vector);

        counter += 4; // getLongestString(), int z = ..., z - 1, int index = ...

        // start sorting for from back to front LSD
        for (int index = z - 1; index >= 0; index--) {

            counter += 3; // index >= 0, index--

            // compute frequency counts
            int[] count_array = new int[count_size];

            // need to initialise 0 first
            Arrays.fill(count_array, 0);

            counter += 3; // int[]count_array = ..., .fill(), int i = 0

            for (int i = 0; i < n; i++) {

                counter += 3; // i<n, i++

                int char_index;

                counter += 4; // .get(), .length(), -1, < index

                //if word length is lesser than the current position index
                if (string_vector.get(i).length() - 1 < index) {
                    char_index = 0;

                    counter++; // char_index = 0
                } else {
                    char_index = string_vector.get(i).charAt(index) - (int) smallest + 1;

                    counter += 5; // char_index = ..., .get(), charAt(), - smallest, +1
                }
                count_array[char_index]++;

                counter += 3; // count_array[char_index], ++
            }

            counter += 2; //final comparison before exiting the loop, int i = 0

            // compute cumulates
            for (int i = 0; i < count_size-1; i++) {
                counter += 4; // i < , count_size-1, i++

                count_array[i+1] += count_array[i];

                counter += 5; // count_array[...], i+1, +=, count_array[i]
            }

            counter += 3; //final comparison before exiting the loop, int i = ..., n-1

            // move data
            for (int i = n - 1; i >= 0; i--) {
                counter += 3; // i >= 0, i--

                int char_index;

                counter += 4; // .get(), .length(), -1, < index

                //if word length is lesser than the current position index
                if (string_vector.get(i).length() - 1 < index) {
                    char_index = 0;

                    counter++; // char_index = 0
                } else {
                    char_index = string_vector.get(i).charAt(index) - (int) smallest + 1;

                    counter += 5; // char_index = ..., .get(), charAt(), - smallest, +1
                }

                aux.set(count_array[char_index] - 1, string_vector.get(i));
                count_array[char_index]--;

                counter += 7; // .set(), count_array[char_index], -1, .get(), count_array[char_index], --
            }

            counter += 2; //final comparison before exiting the loop, int i = 0

            // copy back
            for (int i = 0; i < n; i++) {
                counter += 3; // i < n, i++

                string_vector.set(i, aux.get(i));

                counter += 2; // .set(), .get()
            }

            counter++; //final comparison before exiting the loop
        }

        counter += 2; //final comparison before exiting the loop, return

        return counter;
    }

    public static void main(String[] args) {
        // set file path
        String file_name = "src/wordList.txt";

        // declare string vector to store input
        Vector<String> string_vector = new Vector<>();

        // import the words from .txt file
        try {
            importWords.import_words(file_name, string_vector);

            int n = string_vector.size();

        } catch (IOException e) {
            System.out.println("Something went wrong when reading a file");
        }

        // display total words read from .txt file
        System.out.println("Total length after reading .txt file is :" + string_vector.size());

        radix_sort(string_vector, '\'', '™');

        /*
        // test radix sort
        radix_sort(string_vector, '\'', '™');
        // check the first x words of the sorted vector
        System.out.println("After Sorting: ");
        for (int i = 0; i < 20; i++) {
            System.out.println(string_vector.get(i));
        }
         */

    }
}

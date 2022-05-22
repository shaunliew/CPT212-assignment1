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

    // perform left padding by inserting a char 'ch' to the left of strings which length < maxLength
    static void leftPadding(Vector<String> string_vector, char ch, int L) {
        // Loop over vector
        for (int i = 0; i < string_vector.size(); i++) {
            String result = String
                            // left pad the string with space up to length L
                            .format("%" + L + "s", string_vector.get(i))
                            // replace all the spaces with the given character ch
                            .replace(' ', ch);
            // update the string in the vector
            string_vector.set(i,result);
        }
    }

    // remove "!" that has been inserted before
    static void replaceAll(Vector<String> string_vector, String ch){
        for (int i = 0; i < string_vector.size(); i++) {
            // replace all '!' with ''
            String result = string_vector.get(i).replaceAll(ch,"");
            // update the string in the vector
            string_vector.set(i,result);
        }
    }

    // starting radix sort
    static long radix_sort(Vector<String> string_vector) {

        long counter = 0L;

        int n = string_vector.size();
        int R = 8483;   // largest char available in text file
        Vector<String> aux = new Vector<>();
        aux.setSize(n);

        // find the longest string length in wordList.txt
        // System.out.println((int)'™');
        int z = getLongestString(string_vector);

        // perform left padding
        leftPadding(string_vector, '!', z);

        // check that strings have fixed length
        for (int i = 0; i < n; i++)
            assert string_vector.get(i).length() == z : "Strings must have fixed length of "+z;

        // start sorting for from back to front LSD
        for (int index = z - 1; index >= 0; index--) {

            // compute frequency counts, can directly map char index
            int[] count_array = new int[R + 1];

            // need to initialise 0 first
            Arrays.fill(count_array, 0);

            // can switch to no need padding, just if the length is smaller, count_array++ at index 0
            for (int i = 0; i < n; i++){
                int char_index = string_vector.get(i).charAt(index) + 1;
                count_array[char_index]++;
            }

            // compute cumulates
            for (int i = 0; i < R; i++){
                count_array[i + 1] += count_array[i];
            }

            // !! here differ
            // move data
            for (int i = 0; i < n; i++){
                aux.set(count_array[string_vector.get(i).charAt(index)]++, string_vector.get(i));
            }

            // copy back
            for (int i = 0; i < n; i++){
                string_vector.set(i, aux.get(i));
            }
        }

        // remove all the placeholder '!'
        replaceAll(string_vector,"!");

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

        }
        catch(IOException e){
            System.out.println("Something went wrong when reading a file");
        }



        // sort the strings
        // !! change the function to get the length of string inside the radix_sort
        // !! radix_sort can put outside the try catch as the try catch is for reading the file je
        radix_sort(string_vector);

        // display total words read from .txt file
        System.out.println("Total length after reading .txt file is :" + string_vector.size());

        // check the first x words of the sorted vector
        System.out.println("After Sorting: ");
        for(int i = 50; i < 70; i++) {
            System.out.println(string_vector.get(i));
        }
        // run 10 times and find the best, average, worst case
        // !! your 3 cases is slightly diff then us https://iq.opengenus.org/time-and-space-complexity-of-radix-sort/#:~:text=The%20worst%20case%20in%20radix%20sort%20occurs%20when,running%20time%20of%20Counting%20sort%20is%20O%20%28n%2Bb%29.
        /*Vector<Long> time_complexity_tracker = new Vector<>();
        for(int i=0; i<10; i++){
            // shuffle the word list before sorting
            Collections.shuffle(string_vector);

            // track the time complexity for each sorting
            time_complexity_tracker.add(radix_sort(string_vector,0));
        }
        long worst_case = Collections.max(time_complexity_tracker);
        long best_case = Collections.min(time_complexity_tracker);
        long average_case, sum = 0L;

        for(long time: time_complexity_tracker){
            sum += time;
        }
        average_case = (long)Math.ceil((double)sum / time_complexity_tracker.size()); // maybe need to do %10e9

        System.out.println();
        System.out.println("Worst Case: " + worst_case);
        System.out.println("Average Case: " + average_case);
        System.out.println("Best Case: " + best_case);
    */}
}

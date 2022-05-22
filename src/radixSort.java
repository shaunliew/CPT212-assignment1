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

            // count number of each char based on each string index
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

            // compute cumulative
            for (int i = 0; i < count_size-1; i++) {
                counter += 4; // i < , count_size-1, i++

                count_array[i+1] += count_array[i];

                counter += 5; // count_array[...], i+1, +=, count_array[i]
            }

            counter += 3; //final comparison before exiting the loop, int i = ..., n-1

            // move data based on the count_array indexing
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

            // copy back the string vector
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

    static long best_radix_sort(Vector<String> string_vector){
        long best_case;

        // best case for radix sort is sorted list
        Collections.sort(string_vector);

        // store best_case primitive operations
        best_case = radix_sort(string_vector, '\'', '™');

        System.out.println("Best Case: " + best_case);

        return best_case;
    }

    static long average_radix_sort(Vector<String> string_vector){
        long average_case, sum = 0L;

        // store primitive operation for each time
        Vector<Long> primitive_operation_tracker = new Vector<>();

        // run 10 times and find the average
        for(int i=0; i<10; i++){
            // shuffle the word list before sorting
            Collections.shuffle(string_vector);

            // track the time complexity for each sorting
            primitive_operation_tracker.add(radix_sort(string_vector, '\'', '™'));
        }

        for(long time: primitive_operation_tracker){
            sum += time;
        }

        // store average_case primitive operations
        average_case = (long)Math.ceil((double)sum / primitive_operation_tracker.size());

        System.out.println("Average Case: " + average_case);

        return average_case;
    }

    static long worst_radix_sort(Vector<String> string_vector){
        long worst_case;

        // worst case for insertion sort is reverse list
        Collections.sort(string_vector, Collections.reverseOrder());

        // store worst_case primitive operations
        worst_case = radix_sort(string_vector, '\'', '™');

        System.out.println("Worst Case: " + worst_case);

        return worst_case;
    }

    public static void main(String[] args) {

        // declare variables
        Vector<Vector<Long>> primitive_operation_tracker = new Vector<>();
        primitive_operation_tracker.add(new Vector<>()); // to store best case
        primitive_operation_tracker.add(new Vector<>()); // to store average case
        primitive_operation_tracker.add(new Vector<>()); // to store worst case
        Vector<Integer> inputs = new Vector<>(); // to store the inputs(n)

        // set file path
        String file_name = "src/wordList.txt";

        // declare string vector to store input
        Vector<String> string_vector = new Vector<>();

        // import the words from .txt file
        try {
            importWords.import_words(file_name, string_vector);
        } catch (IOException e) {
            System.out.println("Something went wrong when reading a file");
        }

        // display total words read from .txt file
        int original_size = string_vector.size();
        System.out.println("Total length after reading .txt file is :" + original_size);

        // Generate inputs(n)
        inputs.add(1);
        for(int i=5000; i<142000; i+=5000){
            inputs.add(i+1);
        }
        inputs.add(original_size);
        //System.out.println(inputs);
        //System.out.println();

        // finding primitive operations for 3 cases for different inputs(n)
        for(int n: inputs){

            // slice the vector
            Vector<String> input_vector = new Vector<>();
            input_vector.addAll(new Vector<>(string_vector.subList(0,n)));

            // output current inputs
            System.out.println("Current input(n): "+n);

            // Best Case scenario
            primitive_operation_tracker.get(0).add(best_radix_sort(input_vector));

            // Average Case scenario -
            primitive_operation_tracker.get(1).add(average_radix_sort(input_vector));

            // Worst Case scenario
            primitive_operation_tracker.get(2).add(worst_radix_sort(input_vector));

            System.out.println("-------------------------------");
        }

        // store all results into a csv file
        recordOperationvsN.writeToCSV("radixSort", primitive_operation_tracker, inputs);

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

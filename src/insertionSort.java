import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Vector;


public class insertionSort {
    private insertionSort() {
    }

    static long insertion_sort(Vector<String> string_vector) {

        long counter = 2L; // int j = 1, j;

        // start from index 1, check each words and loop from right to left
        for(int i = 1, j; i < string_vector.size(); i++) {

            counter += 3; // i < ... and i++

            // store the current word into a String variable
            String temp = string_vector.get(i);
            counter += 2; // .get method and assigning value

            counter++; // j=i

            // start comparing each words from current location to the right
            for(j=i; j>0; j--){
                counter += 3; // j>0 and j--

                counter += 4; // j-1 and .get and .compareTo and >0

                // if the word on the left (index j-1) is larger than the current word, continue to shift the word (to right), else break loop
                if (string_vector.get(j-1).compareTo(temp)>0){
                    string_vector.set(j, string_vector.get(j-1));

                    counter += 3; // j-1 and .get and .set

                }else{
                    counter--; // to minus 1 for the final comparison as no comparison is done when directly breaking the loop

                    break;
                }
            }

            counter++; // final comparison before breaking the loop

            // after breaking the loop, assign the last checked location as the current word
            string_vector.set(j, temp);

            counter++; // .set

        }

        counter += 2; // final comparison of for loop and return

        return counter;
    }

    static long best_insertion_sort(Vector<String> string_vector){
        long best_case;

        // best case for insertion sort is sorted list
        Collections.sort(string_vector);

        // store best_case primitive operations
        best_case = insertion_sort(string_vector);

        System.out.println("Best Case: " + best_case);

        return best_case;
    }

    static long average_insertion_sort(Vector<String> string_vector){
        long average_case, sum = 0L;

        // store primitive operation for each time
        Vector<Long> primitive_operation_tracker = new Vector<>();

        // run 10 times and find the average
        for(int i=0; i<10; i++){
            // shuffle the word list before sorting
            Collections.shuffle(string_vector);

            // track the time complexity for each sorting
            primitive_operation_tracker.add(insertion_sort(string_vector));
        }

        for(long time: primitive_operation_tracker){
            sum += time;
        }

        // store average_case primitive operations
        average_case = (long)Math.ceil((double)sum / primitive_operation_tracker.size());

        System.out.println("Average Case: " + average_case);

        return average_case;
    }

    static long worst_insertion_sort(Vector<String> string_vector){
        long worst_case;

        // worst case for insertion sort is reverse list
        Collections.sort(string_vector, Collections.reverseOrder());

        // store worst_case primitive operations
        worst_case = insertion_sort(string_vector);

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
        try{
            importWords.import_words(file_name, string_vector);
        }catch(IOException e){
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
            primitive_operation_tracker.get(0).add(best_insertion_sort(input_vector));

            // Average Case scenario -
            primitive_operation_tracker.get(1).add(average_insertion_sort(input_vector));

            // Worst Case scenario
            primitive_operation_tracker.get(2).add(worst_insertion_sort(input_vector));

            System.out.println("-------------------------------");
        }

        // store all results into a csv file
        recordOperationvsN.writeToCSV("insertionSort", primitive_operation_tracker, inputs);
    }
}
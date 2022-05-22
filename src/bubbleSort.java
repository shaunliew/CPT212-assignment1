import java.io.IOException;
import java.util.Collections;
import java.util.Vector;

public class bubbleSort {
    // assume that calling vector method is 1 primitive operation
    static long bubble_sort(Vector<String> string_vector){
        boolean swapped;
        long primitiveOperations = 0; // 1 assignment
        int n = string_vector.size(); // 1  assignment and 1 function call
        //assign times = 0 for the outer loop, 1 assignment
        primitiveOperations+= 4;
        for(int times=0;times<n-1;times++) // n-1 additions and n-1 assignments
        {
            primitiveOperations +=3;//compare times and times++
            //reset the tracker for every new loop
            swapped = false; // 1 assignment
            primitiveOperations++;

            //repeated swapping
            //assign j = 0, 1 operation
            primitiveOperations++;
            for(int j=0; j < n - times - 1; j++ )
            {
                // compare j and j++
                primitiveOperations+=3;
                //3 method call and 1 comparison (for check the swapping value)
                primitiveOperations+=4;
                if(string_vector.get(j).compareTo(string_vector.get(j+1))>0) //
                {
                    String temp = string_vector.get(j); // 1 assignment and 1 function call
                    string_vector.set(j, string_vector.get(j + 1)); // 2 functions call and 1 assignment
                    string_vector.set(j + 1, temp); // 1 function call and 1 assignment
                    swapped = true; // 1 assignment
                    primitiveOperations+=7;

                }
            }
            //exit the inner loop, final comparison, 1 comparison
            primitiveOperations++;
            // IF no two elements were swapped by inner loop, then break
            //1 comparison in the if statement.
            primitiveOperations++;
            if(!swapped)
            {
                // if break the loop, then it doesn't have final comparison for outer loop
                primitiveOperations--;
                break;
            }
        }
        //exit outer loop, final comparison and return value
        primitiveOperations+=2;
        return primitiveOperations;
    }

    static long best_bubble_sort(Vector<String> string_vector){
        long best_case;

        // best case for insertion sort is sorted list
        Collections.sort(string_vector);

        // store best_case primitive operations
        best_case = bubble_sort(string_vector);

        System.out.println("Best Case: " + best_case);

        return best_case;
    }

    static long average_bubble_sort(Vector<String> string_vector){
        long average_case, sum = 0L;

        // store primitive operation for each time
        Vector<Long> primitive_operation_tracker = new Vector<>();

        // run 10 times and find the average
        for(int i=0; i<10; i++){
            // shuffle the word list before sorting
            Collections.shuffle(string_vector);

            // track the time complexity for each sorting
            primitive_operation_tracker.add(bubble_sort(string_vector));
        }

        for(long time: primitive_operation_tracker){
            sum += time;
        }

        // store average_case primitive operations
        average_case = (long)Math.ceil((double)sum / primitive_operation_tracker.size());

        System.out.println("Average Case: " + average_case);

        return average_case;
    }

    static long worst_bubble_sort(Vector<String> string_vector){
        long worst_case;

        // worst case for insertion sort is reverse list
        Collections.sort(string_vector, Collections.reverseOrder());

        // store worst_case primitive operations
        worst_case = bubble_sort(string_vector);

        System.out.println("Worst Case: " + worst_case);

        return worst_case;
    }
    //Time complexity for bubble sort
    // best case is O(n)
    // average case is O(n^2)
    // worst case is O(n^2)

    public static void main(String[] args){
        // Name of algorithm
        System.out.println("Bubble Sort:");
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

        // finding primitive operations for 3 cases for different inputs(n)
        for(int n: inputs){

            // slice the vector
            Vector<String> input_vector = new Vector<>();
            input_vector.addAll(new Vector<>(string_vector.subList(0,n)));

            // output current inputs
            System.out.println("Current input(n): "+n);

            // Best Case scenario
            primitive_operation_tracker.get(0).add(best_bubble_sort(input_vector));

            // Average Case scenario -
            primitive_operation_tracker.get(1).add(average_bubble_sort(input_vector));

            // Worst Case scenario
            primitive_operation_tracker.get(2).add(worst_bubble_sort(input_vector));

            System.out.println("-------------------------------");
        }

        // store all results into a csv file
        recordOperationvsN.writeToCSV("bubbleSort", primitive_operation_tracker, inputs);

    }

}
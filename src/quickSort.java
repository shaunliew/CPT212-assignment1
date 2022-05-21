import java.io.IOException;
import java.util.Collections;
import java.util.Vector;
import java.util.Random;

// Java program for implementation of QuickSort
public class quickSort
{
    //Declare counter to store the count of primitive operations
    static long counter = 0;

    //Method for pivot to be chosen and placed at its correct position
    public static int partition(Vector<String> string_vector, int low, int high)
    {
        //Using random method to assign a random pivot index
        int pivotIndex = new Random().nextInt(high - low) + low;
        //Store the string of pivotIndex
        String pivot = string_vector.get(pivotIndex);
        //Swap the position between pivotIndex and high
        Collections.swap(string_vector, pivotIndex, high);

        //Index of smaller element
        int i = (low - 1);

        /*
        Assignment = 3
        Addition = 1
        Subtraction = 2
        Method calling = 3
        */
        counter += 9;

        //Assignment of j = 1
        counter++;

        //Start from index 0, check the condition j <= high - 1 and loop to index (high - 1)
        for (int j = low; j <= high - 1; j++)
        {
            /*
            Comparison = 1
            Assignment = 1
            Addition = 1
            Subtraction = 1
             */
            counter += 4;

            /*
            Comparison = 1
            Method calling = 1
            */
            counter += 2;

            //If current string is smaller than pivot, swap their position
            if (string_vector.get(j).compareTo(pivot) < 0)
            {
                i++;
                Collections.swap(string_vector, i, j);

                /*
                Assignment = 1
                Addition = 1
                Method calling = 1
                 */
                counter += 3;
            }
        }

        /*
        Method calling = 1
        Addition = 2
        Return = 1
         */
        counter += 4;

        //Swap the position between i+1 and high
        Collections.swap(string_vector, i + 1, high);
        return i+1;
    }

    //Main method that implement quickSort algorithm
    public static long qckSort(Vector<String> string_vector, int low, int high)
    {
        //Comparison = 1
        counter++;

        if (low < high)
        {
            //Declare pi (partition index) for recursively calling qckSort method
            int pi = partition(string_vector, low, high);
            //Sort the strings before and after partition
            qckSort(string_vector, low, pi - 1);
            qckSort(string_vector, pi + 1, high);

            /*
            Assignment = 1
            Method calling = 3
            Addition = 1
            Subtraction = 1
             */
            counter += 6;
        }
        return counter;
    }

    static long best_qckSort(Vector<String> string_vector, int n)
    {
        long best_case;

        //Best case scenario
        Collections.sort(string_vector);

        //Store best_case primitive operations
        best_case = qckSort(string_vector, 0, n - 1);

        System.out.println("Best Case: " + best_case);

        return best_case;
    }

    static long average_qckSort(Vector<String> string_vector, int n){
        long average_case, sum = 0L;

        //Store primitive operation for each time
        Vector<Long> primitive_operation_tracker = new Vector<>();

        //Run 10 times and find the average
        for(int i = 0; i < 10; i++)
        {
            //Shuffle the word list before sorting
            Collections.shuffle(string_vector);

            //Track the time complexity for each sorting
            primitive_operation_tracker.add(qckSort(string_vector, i, n-1));
        }

        for(long time: primitive_operation_tracker){
            sum += time;
        }

        //Store average_case primitive operations
        average_case = (long)Math.ceil((double)sum / primitive_operation_tracker.size());

        System.out.println("Average Case: " + average_case);

        return average_case;
    }

    static long worst_qckSort(Vector<String> string_vector, int n)
    {
        long worst_case;

        //Worst case scenario
        Collections.reverse(string_vector);

        //Store worst_case primitive operations
        worst_case = qckSort(string_vector, 0, n-1);

        System.out.println("Worst Case: " + worst_case);

        return worst_case;
    }
         
    /*
    Method for printing string_vector after sorting

    static void printResult(Vector<String> string_vector)
    {
        int n = string_vector.size();
        for (int i = 0; i < n; ++i)
        {
            System.out.println(string_vector.get(i));
        }
    }
    */

    //Main program
    public static void main(String[] args)
    {
        //Set file path
        String file_name = "src/wordList.txt";

        // declare variables
        Vector<Vector<Long>> primitive_operation_tracker = new Vector<>();
        primitive_operation_tracker.add(new Vector<>()); // to store best case
        primitive_operation_tracker.add(new Vector<>()); // to store average case
        primitive_operation_tracker.add(new Vector<>()); // to store worst case
        Vector<Integer> inputs = new Vector<>(); // to store the inputs(n)

        //Declare string vector to store input
        Vector<String> string_vector = new Vector<>();
        
        //Import the words from .txt file
        try{
            importWords.import_words(file_name, string_vector);
        }catch(IOException e){
            System.out.println("Something went wrong when reading a file");
        }

        //Display total words read from .txt file
        System.out.println("Total length after reading .txt file is :" + string_vector.size());

        //Declare variable to the size of string_vector
        int original_size = string_vector.size();

        //Generate inputs(n)
        inputs.add(1);
        for(int i=5000; i<142000; i+=5000){
            inputs.add(i+1);
        }
        inputs.add(original_size);

        //Finding primitive operations for 3 cases for different inputs(n)
        for(int n: inputs){

            // slice the vector
            Vector<String> input_vector = new Vector<>();
            input_vector.addAll(new Vector<>(string_vector.subList(0,n)));

            // output current inputs
            System.out.println("Current input(n): " + n);

            // Best Case scenario
            primitive_operation_tracker.get(0).add(best_qckSort(input_vector, n));

            // Average Case scenario -
            primitive_operation_tracker.get(1).add(average_qckSort(input_vector, n));

            // Worst Case scenario
            primitive_operation_tracker.get(2).add(worst_qckSort(input_vector, n));

            System.out.println("-------------------------------");
        }

        // store all results into a csv file
        recordOperationvsN.writeToCSV("quickSort", primitive_operation_tracker, inputs);
        
        /*
        //Store 3 cases primitive operations in .txt file to avoid rerun of the code
        try{
            //Declare data to be stored
            String data = "Best Case: " + best_case + "\nAverage Case: " + average_case + "\nWorst Case: " + worst_case;

            //Creates a FileWriter
            FileWriter file = new FileWriter("src/quickSortCases.txt");

            //Creates a BufferedWriter
            BufferedWriter buffer = new BufferedWriter(file);

            //Writes the string to the file
            buffer.write(data);

            //Close the writer
            buffer.close();
        }catch (Exception e){
            System.out.println("Something went wrong when writing a file");
        }
        */

        /*
        Testing for the quickSort function and display the sorted string
        int n = string_vector.size();
        qckSort(string_vector, 0, n-1);
        System.out.println("Sorted String:");
        printResult(string_vector);
         */
    }
}


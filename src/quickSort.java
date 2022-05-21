import java.io.BufferedWriter;
import java.io.FileWriter;
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
        //
        long normal_case, worst_case, best_case, average_case = 0, sum = 0;

        //Set file path
        String file_name = "src/wordList.txt";

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
        int n = string_vector.size();

        //Best Case scenario
        Collections.sort(string_vector);
        best_case = qckSort(string_vector, 0, n-1);
        System.out.println("Best Case: " + best_case);

        // Average Case scenario - run 10 times and find the average
        Vector<Long> time_complexity_tracker = new Vector<>();
        for(int i = 0; i < 10; i++)
        {
            //Shuffle the word list before sorting
            Collections.shuffle(string_vector);

            //Track the time complexity for each sorting
            time_complexity_tracker.add(qckSort(string_vector, i, n-1));
        }

        for(long time: time_complexity_tracker){
            sum += time;
        }
        average_case = (long)Math.ceil((double)sum / time_complexity_tracker.size());
        System.out.println("Average Case: " + average_case);

        //Worst Case scenario
        Collections.reverse(string_vector);
        worst_case = qckSort(string_vector,0, n-1);
        System.out.println("Worst Case: " + worst_case);


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

        /*
        Testing for the quickSort function and display the sorted string
        int n = string_vector.size();
        qckSort(string_vector, 0, n-1);
        System.out.println("Sorted String:");
        printResult(string_vector);
         */
    }
}


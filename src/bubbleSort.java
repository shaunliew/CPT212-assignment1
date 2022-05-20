import java.io.IOException;
import java.util.Collections;
import java.util.Vector;

public class bubbleSort {
    // assume that calling vector method is 1 primitive operation
    static int bubble_sort(Vector<String> string_vector){
        boolean swapped;
        int primitiveOperations = 0; // 1 assignment
        int n = string_vector.size(); // 1  assignment and 1 function call
        //assign times = 0 for the outer loop, 1 assignment
        primitiveOperations+= 4;
        for(int times=0;times<n-1;times++) // n-1 additions and n-1 assignments
        {
            //System.out.println("Current loop: "+ times);
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
                //System.out.println("now comparing "+ string_vector.get(j) + " and "+ string_vector.get(j + 1));
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
                //System.out.println("since the array is sorted, break the outer loop");
                // 1 method call
                primitiveOperations++;
                // if break the loop, then it doesn't have final comparison for outer loop
                primitiveOperations--;
                break;
            }
        }
        //exit outer loop, final comparison and return value
        primitiveOperations+=2;
        return primitiveOperations;
    }

    static void showResult(String caseName, Vector<String> string_vector)
    {
        System.out.println(caseName+":");
//        System.out.println("The array before sorting");
//        for(String i: string_vector)
//        {
//            System.out.println(i);
//        }
        int PrimitiveOperations = bubble_sort(string_vector);
//        System.out.println("\nAfter the bubble sort, the sorted array is");
//        for(String i: string_vector)
//        {
//            System.out.println(i);
//        }
        System.out.println("The number of primitive operations used for "+caseName+ " is " + PrimitiveOperations);
        System.out.println();
    }

    static void showAverageCaseResult(Vector<String> string_vector, int numberOfShuffle)
    {
        // shuffle the word list before sorting
        System.out.println("Average Case:");
        int TotalPrimitiveOperations = 0;
        for(int i = 0; i<numberOfShuffle;i++)
        {
            Collections.shuffle(string_vector);
            int temp = bubble_sort(string_vector);
            //System.out.println("The number of primitive operations used are for loop "+ i + " are " + temp);
            TotalPrimitiveOperations += temp;
        }
        int averagePrimitiveOperations = TotalPrimitiveOperations/numberOfShuffle;
        System.out.println("The number of shuffle we used for average case is "+ numberOfShuffle);
        System.out.println("The number of primitive operations used for average case is " + averagePrimitiveOperations);
        System.out.println();
    }

    // worst case is O(n^2)
    // best case is O(n)
    // average case is O(n^2)
    public static void main(String[] args){
        // Name of algorithm
        System.out.println("Bubble Sort:");
        // set file path
        String averageCase_file_name = "src/averageCaseWordList.txt";
        String bestCase_file_name = "src/bestCaseWordList.txt";
        String worstCase_file_name = "src/worstCaseWordList.txt";
        String test_file_name = "src/test.txt";
        // declare string vector to store input
        Vector<String> averageCase_string_vector = new Vector<>();
        Vector<String> bestCase_string_vector = new Vector<>();
        Vector<String> worstCase_string_vector = new Vector<>();
        Vector<String> test_string_vector = new Vector<>();
        // import the words from .txt file
        try{
            importWords.import_words(averageCase_file_name, averageCase_string_vector);
            importWords.import_words(bestCase_file_name, bestCase_string_vector);
            importWords.import_words(worstCase_file_name, worstCase_string_vector);
            importWords.import_words(test_file_name, test_string_vector);
        }catch(IOException e){
            System.out.println("Something went wrong when reading the files");
        }

        // display total words read from .txt file
        System.out.println("Total length after reading each .txt file are:" );
        System.out.println("Average case file is "+ averageCase_string_vector.size());
        System.out.println("Best case file is "+ bestCase_string_vector.size());
        System.out.println("Worst case file is "+ worstCase_string_vector.size());
        System.out.println("Test file is "+ test_string_vector.size());
        System.out.println();

        //For Testing
        showResult("Test Case",test_string_vector);
       for(String i: test_string_vector)
       {
           System.out.println(i);
       }

        //For Average case
        showAverageCaseResult(averageCase_string_vector,100);
        //for best case
        showResult("Best Case",bestCase_string_vector);
        //for worst case
        showResult("Worst Case",worstCase_string_vector);
    }

}
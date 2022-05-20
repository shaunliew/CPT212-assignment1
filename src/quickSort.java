import java.io.IOException;
import java.util.Collections;
import java.util.Vector;
import java.util.Random;

// Java program for implementation of QuickSort
public class quickSort
{
    public static int partition(Vector<String> string_vector, int low, int high)
    {
        int pivotIndex = new Random().nextInt(high - low) + low;
        String pivot = string_vector.get(pivotIndex);
        Collections.swap(string_vector, pivotIndex, high);
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++)
        {
            if (string_vector.get(j).compareTo(pivot) < 0)
            {
                i++;
                Collections.swap(string_vector, i, j);
            }
        }
        Collections.swap(string_vector, i + 1, high);
        return i+1;
    }

    public static void qckSort(Vector<String> string_vector, int low, int high)
    {
        if (low < high)
        {
            int pi = partition(string_vector, low, high);
            qckSort(string_vector, low, pi - 1);
            qckSort(string_vector, pi + 1, high);
        }
    }

    static void printResult(Vector<String> string_vector)
    {
        int n = string_vector.size();
        for (int i = 0; i < n; ++i)
        {
            System.out.println(string_vector.get(i));
        }
    }


    // Driver program
    public static void main(String[] args)
    {
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
        System.out.println("Total length after reading .txt file is :" + string_vector.size());
        int n = string_vector.size();
        qckSort(string_vector, 0, n-1);
        System.out.println("Sorted String:");
        printResult(string_vector);
    }
}


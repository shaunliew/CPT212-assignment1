import java.io.IOException;
import java.util.Collections;
import java.util.Vector;

// Java program for implementation of QuickSort
public class quickSort
{
    static int partition(Vector<String> string_vector, int start, int end)
    {
        int pivot = Integer.parseInt(string_vector.get(start + (end - start) / 2));
        int i = start;
        for (int j = start+1; j < end; j++)
        {
            if (string_vector.get(j).compareTo(string_vector.get(pivot)) < 0)
            {
                i++;
                Collections.swap(string_vector, i, j);
            }
        }
        Collections.swap(string_vector, i, pivot);
        return pivot;
    }

    static void sort(Vector<String> string_vector, int start, int end)
    {
        if (start > end)
            return;
        else
        {
            int pivot = partition(string_vector, start, end);
            sort(string_vector, start, pivot-1);
            sort(string_vector, pivot+1, end);
        }
    }


    /*static void printResult(Vector<String> string_vector)
    {
        int n = string_vector.size();
        sort(string_vector, 0, n);
        for (int i=0; i<n; i++)
            System.out.println(string_vector.get(i));
    }*/


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
    }
}


import java.io.IOException;
import java.util.Collections;
import java.util.Vector;
import java.util.Collection;

public class insertionSort {
    public insertionSort() {
    }

    // Doesn't work
    static void insertion_sort(Vector<String> string_vector) {
        for(int i = 1; i < string_vector.size(); i++) {
            String temp = string_vector.get(i);

            int j=i;
            while(j>0 && string_vector.get(j-1).compareTo(temp) > 1){
                string_vector.set(j, string_vector.get(j-1));
                j--;
            }
            string_vector.set(j,temp);
        }

    }

    public static void main(String[] args) {
        String file_name = "src/wordList.txt";
        Vector<String> string_vector = new Vector<>();

        try{
            importWords.import_words(file_name, string_vector);
        }catch(IOException e){
            System.out.println("Something went wrong when reading a file");
        }


        System.out.println("Total length after reading .txt file is :" + string_vector.size());

        // To shuffle the word list
        //Collections.shuffle(string_vector);

        insertion_sort(string_vector);

        System.out.println("After Sorting: ");
        for(int i = 0; i < 20; ++i) {
            System.out.println(string_vector.get(i));
        }

        System.out.println("Total length after insertion sort is :" + string_vector.size());

        // check if contain single quote in line
        //int counter = 0;
        //for(String word: string_vector){
        //    if(word.contains("'")){
        //        System.out.print(word + " * ");
        //        counter++;
        //    }
        //}
        //System.out.println("");
        //System.out.println(counter); //Should have 359
    }
}
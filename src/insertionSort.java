import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Vector;


public class insertionSort {
    public insertionSort() {
    }

    // Need to ask Dr whether vector method consider as +1 or act like array
    static long insertion_sort(Vector<String> string_vector, int iteration) {

        long counter = 2L; // int j = 1, j;

        // start from index 1, check each words and loop from right to left
        for(int i = 1, j; i < string_vector.size(); i++) {

            counter += 3; // i < ... and i++

            // print out the progress of the insertion sort algorithm
            //System.out.println("Iteration " + iteration +  ": Currently at index "+i);

            // store the current word into a String variable
            String temp = string_vector.get(i);
            counter += 2; // .get method and assigning value and (excluded println now)

            counter++; // j=i

            // start comparing each words from current location to the right
            for(j=i; j>0; j--){
                counter += 3; // j>0 and j--

                counter += 4; // j-1 and .get and .compareTo and >1

                // if the word on the left (index j-1) is larger than the current word, continue to shift the word (to right), else break loop
                if (string_vector.get(j-1).compareTo(temp)>0){ //0 for actual, 1 for testing as it will take too long time for actual one
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

    public static void main(String[] args) {

        // declare variables
        long worst_case, best_case, average_case, sum = 0L;

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

        // Worst Case scenario
        Collections.sort(string_vector, Collections.reverseOrder());
        worst_case = insertion_sort(string_vector, 1);
        System.out.println();
        System.out.println("Worst Case: " + worst_case);

        // Best Case scenario
        Collections.sort(string_vector);
        best_case = insertion_sort(string_vector, 1);
        System.out.println();
        System.out.println("Best Case: " + best_case);

        // Average Case scenario - run 10 times and find the average
        Vector<Long> time_complexity_tracker = new Vector<>();
        for(int i=0; i<10; i++){
            // shuffle the word list before sorting
            Collections.shuffle(string_vector);

            // track the time complexity for each sorting
            time_complexity_tracker.add(insertion_sort(string_vector, i+1));
        }

        for(long time: time_complexity_tracker){
            sum += time;
        }
        average_case = (long)Math.ceil((double)sum / time_complexity_tracker.size());

        System.out.println();
        System.out.println("Average Case: " + average_case);


        // Store 3 cases primitive operations in .txt file to avoid rerun of the code
        try{
            // data to stored
            String data = "Best Case: " + best_case + "\nAverage Case: " + average_case + "\nWorst Case: " + worst_case;

            // creates a FileWriter
            FileWriter file = new FileWriter("src/insertionSortCases.txt");

            // creates a BufferedWriter
            BufferedWriter buffer = new BufferedWriter(file);

            // writes the string to the file
            buffer.write(data);

            // close the writer
            buffer.close();
        }catch (Exception e){
            System.out.println("Something went wrong when writing a file");
        }


        // do we need to output sorted words into a txt file?
        // later draft time complexity against number of inputs code block

        // Self check algorithm

        /*
        // check if contain non ascii in string
        int counter = 0;
        for(String word: string_vector){

            // !word.matches("[a-zA-Z]+" //true if contain non alphabet
            // !word.matches("\\A\\p{ASCII}*\\z") // true if contain non ascii value - ASCII means 0 - 127 only
            // word.contains("'") // true if contain ' char

            if(!word.matches("\\A\\p{ASCII}*\\z")){
                System.out.print(word + "  |  ");
                counter++;
            }
        }
        System.out.println("");
        System.out.println(counter);
        // for example: naÃ¯vetÃ© , danaÃ« , geneviÃ¨ve , cÃ³rdoba , bjÃ¶rn
         */

        /*
        // test insertion sort
        insertion_sort(string_vector);
        // check the first x words of the sorted vector
        System.out.println("After Sorting: ");
        for(int i = 0; i < 20; i++) {
            System.out.println(string_vector.get(i));
        }
        */

        // To double-check the sort algorithm
        /*
        Collections.sort(string_vector);
        System.out.println("After Java Sorting: ");
        for(int i = 0; i < 20; ++i) {
            System.out.println(string_vector.get(i));
        }
        */

    }
}
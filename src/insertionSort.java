import java.io.IOException;
import java.util.Collections;
import java.util.Vector;


public class insertionSort {
    public insertionSort() {
    }

    // Need to ask Dr whether vector method consider as +1 or act like array
    static long insertion_sort(Vector<String> string_vector) {

        long counter = 2L; // int j = 1, j;

        // start from index 1, check each words and loop from right to left
        for(int i = 1, j; i < string_vector.size(); i++) {

            counter += 3; // i < ... and i++

            // print out the progress of the insertion sort algorithm
            System.out.println("Currently at index "+i);

            // store the current word into a String variable
            String temp = string_vector.get(i);
            counter += 2; // .get method and assigning value

            counter++; // j=i

            // start comparing each words from current location to the right
            for(j=i; j>0; j--){
                counter += 3; // j>0 and j--

                counter += 4; // j-1 and .get and .compareTo and >1

                // if the word on the left (index j-1) is larger than the current word, continue to shift the word (to right), else break loop
                if (string_vector.get(j-1).compareTo(temp)>0){ //0 for actual, 1 for testing as will took too long time for 1
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

        // run 10 times and find the best, average, worst case
        Vector<Long> time_complexity_tracker = new Vector<>();
        for(int i=0; i<10; i++){
            // shuffle the word list before sorting
            Collections.shuffle(string_vector);

            // track the time complexity for each sorting
            time_complexity_tracker.add(insertion_sort(string_vector));
        }
        long worst_case = Collections.max(time_complexity_tracker);
        long best_case = Collections.min(time_complexity_tracker);
        long average_case, sum = 0L;

        for(long time: time_complexity_tracker){
            sum += time;
        }
        average_case = (long)Math.ceil((double)sum / time_complexity_tracker.size()); // maybe need to do %10e9

        System.out.println();
        System.out.println("Worst Case: " + worst_case);
        System.out.println("Average Case: " + average_case);
        System.out.println("Best Case: " + best_case);

        // Self check algorithm

        // check if contain single quote in line (check if correct input line)
        //int counter = 0;
        //for(String word: string_vector){
        //    if(word.contains("'")){
        //        System.out.print(word + " * ");
        //        counter++;
        //    }
        //}
        //System.out.println("");
        //System.out.println(counter); //Should have 359


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
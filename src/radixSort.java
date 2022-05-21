// a) sorting words instead of numbers
// b) words of different length
// based on lecturer start from back to front


import java.io.IOException;
import java.util.Collections;
import java.util.Vector;

public class radixSort {

    private radixSort() {
    }

    // find the longest string in wordList.txt
    // !! better to return the maxLength(int) instead hehe
    public static String getLongestString(Vector<String> string_vector) {
        int maxLength = 0;
        String longestString = null;
        for (String s : string_vector) {
            if (s.length() > maxLength) {
                maxLength = s.length();
                longestString = s;
            }
        }
        return longestString;
    }

    // perform left padding by inserting "!" to the left of strings which length < maxLength
    // !! need to change this function to update the whole vector or single line je? can think whether to include counter in this part later
    public static String leftPadding(Vector<String> string_vector, char ch, int L)
    {
        String result = String

        // First left pad the string with space up to length L
        // var.format ah hahahaa
        .format("%" + L + "s", string_vector)

        // Then replace all the spaces with the given character ch
        .replace(' ', ch);

        // Return the resultant string
         return result;
    }

    // find "!" that has been inserted before
    // !! you can change the function to directly manipulate the whole string vector, the current one only check one char
    public static char isFound(char ch){
        char[] replaceChar = {'!'};
        for (int i = 0; i < replaceChar.length; i++) {
            if(ch==replaceChar[i]){
                return '\0';
            }
        }
        return ch;
    }

    // remove "!" that has been inserted before
    // !! similar can think of manipulate the whole string, becareful whether to add counter inside or not, as it's involved in the radix_sort function already
    public static String removeChar(String ch){
        char[] srcArr = ch.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < srcArr.length; i++) {
            char foundChar = isFound(srcArr[i]);
            if(foundChar!='\0')
                sb.append(foundChar);
        }
        return sb.toString();
    }

    // starting radix sort
    // !! need to remove z as parameter as the length will be determined inside
    public static long radix_sort(Vector<String> string_vector, int z) {

        int n = string_vector.size();
        int R = 256;   // alphabet size of extended ASCII
        Vector<String> aux = new Vector<String>(n);

        // find the longest string in wordList.txt
        // !! can store the maxLength into a variable named z(put inside the function instead of parameter)
        getLongestString(string_vector);

        // perform left padding
        leftPadding(string_vector, '!', 30);

        // !! once done adding padding, can put in the assert length code here to check the length again

        for (int d = z - 1; d >= 0; d--) {
            // sort by key-indexed counting on dth character

            // compute frequency counts
            int[] count = new int[R + 1];
            for (int i = 0; i < n; i++)
                count[string_vector.get(i).charAt(d) + 1]++;

            // compute cumulates
            for (int r = 0; r < R; r++)
                count[r + 1] += count[r];

            // move data
            for (int i = 0; i < n; i++)
                aux.set(count[string_vector.get(i).charAt(d)]++, string_vector.get(i));

            // copy back
            for (int i = 0; i < n; i++)
                string_vector.set(i, aux.get(i));
        }

        isFound('!');
        removeChar("!");

        return 0;
    }

    public static void main(String[] args) {
        // set file path
        String file_name = "src/wordList.txt";

        // declare string vector to store input
        Vector<String> string_vector = new Vector<>();


        // import the words from .txt file
        try {
            importWords.import_words(file_name, string_vector);
            Vector<String> a = string_vector;

            int n = a.size();

            // check that strings have fixed length
            // !! need to put this after manipulating string
            //int w = a.get(0).length();
            //for (int i = 0; i < n; i++)
            //    assert a.get(i).length() == w : "Strings must have fixed length";

            // sort the strings
            // !! change the function to get the length of string inside the radix_sort
            // !! radix_sort can put outside the try catch as the try catch is for reading the file je
            //radix_sort(a, w);

            // print results
            for (int i = 0; i < n; i++)
                System.out.println(a.get(i));

        }
        catch(IOException e){
            System.out.println("Something went wrong when reading a file");
        }

        // display total words read from .txt file
        System.out.println("Total length after reading .txt file is :" + string_vector.size());

        // run 10 times and find the best, average, worst case
        // !! your 3 cases is slightly diff then us https://iq.opengenus.org/time-and-space-complexity-of-radix-sort/#:~:text=The%20worst%20case%20in%20radix%20sort%20occurs%20when,running%20time%20of%20Counting%20sort%20is%20O%20%28n%2Bb%29.
        Vector<Long> time_complexity_tracker = new Vector<>();
        for(int i=0; i<10; i++){
            // shuffle the word list before sorting
            Collections.shuffle(string_vector);

            // track the time complexity for each sorting
            time_complexity_tracker.add(radix_sort(string_vector,0));
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
    }
}


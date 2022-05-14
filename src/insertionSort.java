import java.util.Vector;

public class insertionSort {
    public insertionSort() {
    }

    static void insertion_sort(int[] a, int n) {
        for(int i = 1; i <= n - 1; ++i) {
            int current = a[i];

            int prev;
            //loop to find the right index where the element current should be inserted
            for(prev = i - 1; prev >= 0 && a[prev] > current; --prev) {
                a[prev + 1] = a[prev];
            }

            a[prev + 1] = current;
        }

    }

    public static void main(String[] args) {
        String file_name = "src/wordList.txt";
        Vector<String> string_vector = new Vector<>();

        try {
            importWords.import_words(file_name, string_vector);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 10; ++i) {
            System.out.println(string_vector.get(i));
        }

        System.out.println("Total length of string vector is :" + string_vector.size());
    }
}
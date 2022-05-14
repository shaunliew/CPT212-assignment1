import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class importWords {
    public importWords() {
    }

    static void import_words(String file_path, Vector<String> string_vector) throws IOException {
        FileReader file = new FileReader(file_path);
        Scanner f_scan = new Scanner(file);

        while(f_scan.hasNext()) {
            String line = f_scan.nextLine();
            string_vector.add(line);
        }

        f_scan.close();
    }
}
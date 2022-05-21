import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Vector;


public class recordOperationvsN {
    private recordOperationvsN(){
    }


    static void writeToCSV(String sort_type, Vector<Vector<Long>> primitive_operation_tracker, Vector<Integer> index){
        try{
            // creates a FileWriter
            FileWriter file = new FileWriter("src/"+sort_type+"GraphData.csv");

            // creates a BufferedWriter
            BufferedWriter buffer = new BufferedWriter(file);

            // writes the header to the file
            buffer.write("n,best,average,worst");
            buffer.newLine();

            // writes all data into file
            for(int i=0; i<index.size(); i++){
                buffer.write(index.get(i)+","
                                +primitive_operation_tracker.get(0).get(i)+","
                                +primitive_operation_tracker.get(1).get(i)+","
                                +primitive_operation_tracker.get(2).get(i));
                if(i!=index.size()-1){
                    buffer.newLine();
                }
            }

            System.out.println(sort_type+"GraphData.csv created successfully");

            // close the writer
            buffer.close();
        }catch (Exception e){
            System.out.println("Something went wrong when writing a file");
        }
    }

    public static void main(String[] args){
        Vector<Vector<Long>> primitive_operation_tracker = new Vector<>();
        primitive_operation_tracker.add(new Vector<>());
        primitive_operation_tracker.add(new Vector<>());
        primitive_operation_tracker.add(new Vector<>());
        // 3 rows of time complexity
        primitive_operation_tracker.get(0).add(100L);
        primitive_operation_tracker.get(1).add(200L);
        primitive_operation_tracker.get(2).add(300L);

        Vector<Integer> index = new Vector<>();
        index.add(1);


        //writeToCSV("testsort", primitive_operation_tracker, index);
    }
}

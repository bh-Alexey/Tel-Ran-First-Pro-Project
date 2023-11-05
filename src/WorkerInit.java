import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class WorkerInit {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("db/workers.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        List<Worker> workers = bufferedReader.lines()
                .map(Worker::new)
                .toList();

//        for (Worker worker: workers) {
//            System.out.println(worker);
//            System.out.println(worker.getName());
//            System.out.println(worker.getSurname());
//            System.out.println(worker.getMinSalary());
//            System.out.println(worker.getMaxSalary());
//            System.out.println(worker.getGender());
//        }
    }
}

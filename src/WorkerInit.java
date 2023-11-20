import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Objects;

public class WorkerInit {
    public static List<Worker> createWorker() throws FileNotFoundException {
        File file = new File("db/workers.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        List<Worker> workers = bufferedReader.lines()
                .filter(Objects::nonNull)
                .map(Worker::new)
                .toList();
        return workers;
    }
}

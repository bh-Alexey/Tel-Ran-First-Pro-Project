import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Objects;

public class WorkerInit {

    private final static String WORKERS_FILE = "data/workers.txt";
    public static List<Worker> createWorkers() throws FileNotFoundException {

        File file = new File(WORKERS_FILE);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        return bufferedReader.lines()
                .filter(Objects::nonNull)
                .map(Worker::new)
                .toList();
    }
}

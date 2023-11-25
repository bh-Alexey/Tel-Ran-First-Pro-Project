import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Objects;

public class WorkerInit {

    private static final File FILE = new File("workers.txt");
    public List<Worker> createWorker(String workersFile) throws FileNotFoundException {

        File file = new File(String.valueOf(FILE));
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        return bufferedReader.lines()
                .filter(Objects::nonNull)
                .map(Worker::new)
                .toList();
    }
}

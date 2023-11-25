import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Objects;

public class FundInit {

    private static final File FILE = new File("pension-funds.txt");

    public List<PensionFund> createFund() throws FileNotFoundException {

        FileReader fileReader = new FileReader(String.valueOf(FILE));
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        return bufferedReader.lines()
                .filter(Objects::nonNull)
                .map(fund -> {
                    try {
                        return new PensionFund(fund);
                    } catch (FileNotFoundException exception) {
                        throw new RuntimeException(exception);
                    }
                })
                .toList();
    }
}

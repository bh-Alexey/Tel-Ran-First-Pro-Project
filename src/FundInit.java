import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Objects;

public class FundInit {

    private static final File file = new File("db/pension-funds.txt");

    public static List<PensionFund> createFund() throws FileNotFoundException {

        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        List<PensionFund> pensionFunds = bufferedReader.lines()
                .filter(Objects::nonNull)
                .map(fund -> {
                    try {
                        return new PensionFund(fund);
                    } catch (FileNotFoundException exception) {
                        throw new RuntimeException(exception);
                    }
                })
                .toList();
        return pensionFunds;
    }
}

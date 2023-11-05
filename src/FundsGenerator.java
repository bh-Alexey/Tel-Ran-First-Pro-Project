import java.io.*;
import java.util.List;
import java.util.Random;

public class FundsGenerator {
    public static void main(String[] args) throws IOException {
        Random random = new Random();

        File fundsNames = new File("db/retirement-funds.txt");
        File retirementFunds = new File("db/pension-funds.txt");

        FileReader fileReaderForFundsNames = new FileReader(fundsNames);
        FileWriter fileWriter = new FileWriter(retirementFunds);

        BufferedReader bufferedReaderForFundsNames = new BufferedReader(fileReaderForFundsNames);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        List<String> pensionFunds = bufferedReaderForFundsNames.lines()
                .toList();

        for (int i = 0; i < 100; i++) {
            boolean isState = random.nextBoolean();
            String fundName = pensionFunds.get(i);

            String generatedString = fundName + ", " + isState;
            bufferedWriter.append(generatedString);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
    }
}

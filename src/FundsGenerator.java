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
            int nameRandomNumber = random.nextInt(0, 1000);
            String fundName = pensionFunds.get(nameRandomNumber);
            int yearRandomNumber = random.nextInt(1980, 2022);
            int monthRandomNumber = random.nextInt(1, 12);
            int dayRandomNumber = random.nextInt(1, 28);
            String creationDate = monthRandomNumber + "/" + dayRandomNumber + "/" + yearRandomNumber;


            String generatedString = fundName + " - " + isState + " - " + creationDate;
            bufferedWriter.append(generatedString);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
    }
}

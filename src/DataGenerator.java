import java.io.*;
import java.util.List;
import java.util.Random;

public class DataGenerator {
    private final static int PEOPLE_LIST_COUNT = 1000;
    private final static int START_AGE = 16;
    private final static int END_AGE = 120;
    private final static int SALARY_LOW_RANGE = 550;
    private final static int SALARY_HIGH_RANGE = 5500;
    private final static int COUNT_OF_NAMES = 2000;
    private final static int COUNT_OF_SURNAMES = 1000;
    private final static int FUNDS_LIST_COUNT = 100;
    private final static int COUNT_OF_FUNDS = 1000;
    private final static int START_YEAR = 1980;
    private final static int LAST_YEAR = 2022;
    private final static int MOUNTHS_IN_YEAR = 12;
    private final static int DAYS_IN_MONTH = 28;

    public void generateWorkersFile(String firstInputFile, String secondInputFile, String outputFile) throws IOException {
        Random random = new Random();

        File firstNames = new File(firstInputFile);
        File lastNames = new File(secondInputFile);
        File workers = new File(outputFile);

        FileReader fileReaderForNames = new FileReader(firstNames);
        FileReader fileReaderForLastNames = new FileReader(lastNames);
        FileWriter fileWriter = new FileWriter(workers);

        BufferedReader bufferedReaderForNames = new BufferedReader(fileReaderForNames);
        BufferedReader bufferedReaderForLastNames = new BufferedReader(fileReaderForLastNames);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        List<String> names = bufferedReaderForNames.lines()
                .toList();
        List<String> surnames = bufferedReaderForLastNames.lines()
                .toList();
        Gender gender;

        for (int i = 0; i < PEOPLE_LIST_COUNT; i++) {
            int age = random.nextInt(START_AGE, END_AGE);
            int minSalary = random.nextInt(SALARY_LOW_RANGE, SALARY_LOW_RANGE * 2);
            int maxSalary = random.nextInt(SALARY_LOW_RANGE * 2, SALARY_HIGH_RANGE);
            int nameRandomNumber = random.nextInt(0, COUNT_OF_NAMES);
            if (nameRandomNumber > COUNT_OF_NAMES / 2) {
                gender = Gender.FEMALE;
            }
            else {
                gender = Gender.MALE;
            }
            String name = names.get(nameRandomNumber);
            int lastNameRandomNumber = random.nextInt(0, COUNT_OF_SURNAMES);
            String lastName = surnames.get(lastNameRandomNumber);

            String generatedString = name + " " + lastName + " " + minSalary + " " + maxSalary + " " + gender + " " + age;
            bufferedWriter.append(generatedString);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
    }

    public void generateFundsFile(String inputFile, String outputFile) throws IOException {
        Random random = new Random();

        File fundsNames = new File(inputFile);
        File retirementFunds = new File(outputFile);

        FileReader fileReaderForFundsNames = new FileReader(fundsNames);
        FileWriter fileWriter = new FileWriter(retirementFunds);

        BufferedReader bufferedReaderForFundsNames = new BufferedReader(fileReaderForFundsNames);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        List<String> pensionFunds = bufferedReaderForFundsNames.lines()
                .toList();

        for (int i = 0; i < FUNDS_LIST_COUNT; i++) {
            boolean isState = random.nextBoolean();
            int nameRandomNumber = random.nextInt(0, COUNT_OF_FUNDS);
            String fundName = pensionFunds.get(nameRandomNumber);
            int yearRandomNumber = random.nextInt(START_YEAR, LAST_YEAR);
            int monthRandomNumber = random.nextInt(1, MOUNTHS_IN_YEAR);
            int dayRandomNumber = random.nextInt(1, DAYS_IN_MONTH);
            String creationDate = monthRandomNumber + "/" + dayRandomNumber + "/" + yearRandomNumber;


            String generatedString = fundName + " - " + isState + " - " + creationDate;
            bufferedWriter.append(generatedString);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
    }
}

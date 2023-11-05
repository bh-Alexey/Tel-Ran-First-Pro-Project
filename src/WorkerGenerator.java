import java.io.*;
import java.util.List;
import java.util.Random;

public class WorkerGenerator {
    public static void main(String[] args) throws IOException {
        Random random = new Random();

        File firstNames = new File("db/names.txt");
        File lastNames = new File("db/surnames.txt");
        File people = new File("db/workers.txt");

        FileReader fileReaderForNames = new FileReader(firstNames);
        FileReader fileReaderForLastNames = new FileReader(lastNames);
        FileWriter fileWriter = new FileWriter(people);

        BufferedReader bufferedReaderForNames = new BufferedReader(fileReaderForNames);
        BufferedReader bufferedReaderForLastNames = new BufferedReader(fileReaderForLastNames);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        List<String> names = bufferedReaderForNames.lines()
                .toList();
        List<String> surnames = bufferedReaderForLastNames.lines()
                .toList();
            Gender sex;

        for (int i = 0; i < 1000; i++) {
            int minSalary = random.nextInt(550, 1000);
            int maxSalary = random.nextInt(1100, 6000);
            int nameRandomNumber = random.nextInt(0, 2000);
            if (nameRandomNumber > 1000) {
                sex = Gender.FEMALE;
            }
            else {
                sex = Gender.MALE;
            }
            String name = names.get(nameRandomNumber);
            int lastNameRandomNumber = random.nextInt(0, 1000);
            String lastName = surnames.get(lastNameRandomNumber);

            String generatedString = name + " " + lastName + " " + minSalary + " " + maxSalary + " " + sex;
            bufferedWriter.append(generatedString);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
    }
}

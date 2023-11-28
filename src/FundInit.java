import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.DayOfWeek;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FundInit {
    private final static int MAX_OF_MEMBERS = 100;
    private final static String FUNDS_FILE = "data/pension-funds.txt";
    public static List<PensionFund> createFunds() throws FileNotFoundException {

        FileReader fileReader = new FileReader(FUNDS_FILE);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        return bufferedReader.lines()
                .filter(Objects::nonNull)
                .map(PensionFund::new)
                .toList();
    }

    public static List<Worker> addMembers(boolean isState) throws FileNotFoundException {
        List<Worker> depositors = WorkerInit.createWorkers();
        Random random = new Random();
        List<Worker> members = new ArrayList<>();
        if (isState) {
            members = IntStream.range(0, random.nextInt(MAX_OF_MEMBERS))
                    .mapToObj(i -> depositors.get(random.nextInt(depositors.size())))
                    .collect(Collectors.toList());
        }
        else {
            members = IntStream.range(0, random.nextInt(MAX_OF_MEMBERS / 2))
                    .mapToObj(i -> depositors.get(random.nextInt(depositors.size())))
                    .collect(Collectors.toList());
        }

        return members;
    }

    public static HashMap<DayOfWeek, Boolean> fillWorkDays() {
        HashMap<DayOfWeek, Boolean> workingDays = new HashMap<>();
        Random random = new Random();

        DayOfWeek[] days = DayOfWeek.values();
        for (DayOfWeek day : days) {
            workingDays.put(day, random.nextInt() > 0.1);
        }
        return workingDays;
    }

}

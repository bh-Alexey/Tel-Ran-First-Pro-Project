import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class CalculatorUtils {

    public static double calculateAverage(int first, int second) {
        return (first + second) / 2.0;
    }

    public static double calculateAverage(int first, int second, int third) {
        return (first + second + third) / 3.0;
    }

    public static PensionFund mostPopularFund() throws FileNotFoundException {
        List<PensionFund> pensionFunds = FundInit.createFund();
        return pensionFunds.stream()
                .filter(Objects::nonNull)
                .max(Comparator.comparingInt(fund -> fund.getPersons().size()))
                .orElse(null);
    }

    public static String mostHigherPension() throws FileNotFoundException {
        List<Worker> pensioners = WorkerInit.createWorker();
        Worker pensioner = pensioners.stream()
                .filter(Objects::nonNull)
                .max(Comparator.comparingDouble(Worker::calculatePension))
                .orElse(null);
        assert pensioner != null;
        return pensioner.getName() + " " + pensioner.getSurname();
    }
}

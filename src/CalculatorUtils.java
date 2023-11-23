import java.io.FileNotFoundException;
import java.util.*;

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
                .max(Comparator.comparingInt(fund -> fund.getDepositors().size()))
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

    public static double avgPensionFundsPaid() throws FileNotFoundException {
        List<PensionFund> pensionFunds = FundInit.createFund();
        return (double) pensionFunds.stream()
                .filter(Objects::nonNull)
                .map(PensionFund::calculateMedianPension)
                .count();
    }

    public static List<Worker> getFraudVictims() throws FileNotFoundException {
        List<PensionFund> pensionFunds = FundInit.createFund();
        return pensionFunds.stream()
                .filter(funds -> !funds.isState())
                .map(PensionFund::getDepositors)
                .flatMap(Collection::stream)
                .toList();
    }

    public static double fundsAvgPension() throws FileNotFoundException {
        List<PensionFund> pensionFunds = FundInit.createFund();
        int size = pensionFunds.size();
        Optional<Double> paid = pensionFunds.stream().map(PensionFund::calculateMedianPension).reduce(Double::sum);
        return paid.map(aDouble -> aDouble / size).orElse(0.0);
    }
}

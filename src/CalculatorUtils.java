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

    public static double mostHigherPension(int age) throws FileNotFoundException {
        List<Worker> pensioners = WorkerInit.createWorker();
        Worker pensioner = pensioners.stream()
                .filter(Objects::nonNull)
                .filter(worker -> worker.getAge() < age)
                .max(Comparator.comparingDouble(Worker::calculatePension))
                .orElse(null);
        assert pensioner != null;
        return pensioner.calculatePension();
    }

    public static List<Worker> getFraudVictims() throws FileNotFoundException {
        List<PensionFund> pensionFunds = FundInit.createFund();
        return pensionFunds.stream()
                .filter(funds -> !funds.isState())
                .map(PensionFund::getDepositors)
                .flatMap(Collection::stream)
                .toList();
    }

    public static double avgPensionFundsPaid() throws FileNotFoundException {
        List<PensionFund> pensionFunds = FundInit.createFund();
        int size = pensionFunds.size();
        Optional<Double> paid = pensionFunds.stream()
                .map(PensionFund::calculateMedianPension)
                .reduce(Double::sum);
        return paid.map(aDouble -> aDouble / size).orElse(0.0);
    }

    public static double workersAvgPension() throws FileNotFoundException {
        List<Worker> workers = WorkerInit.createWorker();
        int size = workers.size();
        Optional<Double> pension = workers.stream()
                .map(Worker::calculatePension)
                .reduce(Double::sum);
        return pension.map(aDouble -> aDouble / size).orElse(0.0);
    }

    public static String mostYoungerDepositor() throws FileNotFoundException {
        List<PensionFund> pensionFunds = FundInit.createFund();
        Worker depositors = pensionFunds.stream()
                .filter(Objects::nonNull)
                .map(PensionFund::getDepositors)
                .flatMap(Collection::stream)
                .min(Comparator.comparingInt(Worker::getAge))
                .orElse(null);
        assert depositors != null;
        return depositors.getName() + " " + depositors.getSurname();
    }
}

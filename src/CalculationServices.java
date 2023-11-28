import java.io.FileNotFoundException;
import java.util.*;

public class CalculationServices {

    public PensionFund mostPopularFund(List<PensionFund> pensionFunds) {
        return pensionFunds.stream()
                .filter(Objects::nonNull)
                .max(Comparator.comparingInt(fund -> fund.getDepositors().size()))
                .orElse(null);
    }

    public String workerHigherPension(List<Worker> workers) {
        Worker pensioner = workers.stream()
                .filter(Objects::nonNull)
                .max(Comparator.comparingDouble(Worker::calculatePension))
                .orElse(null);
        assert pensioner != null;
        return pensioner.getName() + " " + pensioner.getSurname() + " - " + Math.floor(pensioner.calculatePension()) + "$";
    }

    public double mostHigherPensionAmong(List<Worker> pensioners, int age) {
        Worker pensioner = pensioners.stream()
                .filter(Objects::nonNull)
                .filter(worker -> worker.getAge() < age)
                .max(Comparator.comparingDouble(Worker::calculatePension))
                .orElse(null);
        assert pensioner != null;
        return pensioner.calculatePension();
    }

    public List<Worker> getFraudVictims(List<PensionFund> pensionFunds) {
        return pensionFunds.stream()
                .filter(funds -> !funds.isState())
                .map(PensionFund::getDepositors)
                .flatMap(Collection::stream)
                .toList();
    }

    public double avgPensionFundsPaid(List<PensionFund> pensionFunds) {
        int size = pensionFunds.size();
        Optional<Double> paid = pensionFunds.stream()
                .map(PensionFund::calculateMedianPension)
                .reduce(Double::sum);
        return paid.map(aDouble -> aDouble / size).orElse(0.0);
    }

    public double workersAvgPension(List<Worker> pensioners) {
        int size = pensioners.size();
        Optional<Double> pension = pensioners.stream()
                .map(Worker::calculatePension)
                .reduce(Double::sum);
        return pension.map(aDouble -> aDouble / size).orElse(0.0);
    }

    public String mostYoungerDepositor(List<PensionFund> pensionFunds) {
        Worker depositors = pensionFunds.stream()
                .filter(Objects::nonNull)
                .map(PensionFund::getDepositors)
                .flatMap(Collection::stream)
                .min(Comparator.comparingInt(Worker::getAge))
                .orElse(null);
        assert depositors != null;
        return depositors.getName() + " " + depositors.getSurname() + " " + depositors.getAge();
    }
}

import java.io.FileNotFoundException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;


public class PensionFund {

    private String name;

    private boolean isState;

    private final String dateOfCreation;

    private List<Worker> depositors;

    private static Map<DayOfWeek, Boolean> workDays = FundInit.fillWorkDays();

    public PensionFund(String name, boolean isState, String dateOfCreation, List<Worker> depositors) {
        this.name = name;
        this.isState = isState;
        this.dateOfCreation = dateOfCreation;
        this.depositors = depositors;
        workDays = new HashMap<>();
    }
    public PensionFund(String string) {
        String[] array = string.split(" - ");
        this.name = array[0];
        this.isState = Boolean.parseBoolean(array[1]);
        this.dateOfCreation = array[2];
        try {
            this.depositors = FundInit.addMembers(isState);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<DayOfWeek, Boolean> getWorkDays() {
        return workDays;
    }

    public void setWorkDays(Map<DayOfWeek, Boolean> workDays) {
        PensionFund.workDays = workDays;
    }

    public List<Worker> getDepositors() {
        return depositors;
    }

    public void setDepositors(List<Worker> depositors) {
        this.depositors = depositors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isState() {
        return isState;
    }

    public void setState(boolean state) {
        isState = state;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PensionFund that = (PensionFund) o;
        return isState == that.isState && Objects.equals(name, that.name) && Objects.equals(dateOfCreation, that.dateOfCreation) && Objects.equals(depositors, that.depositors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isState, dateOfCreation, depositors);
    }

    @Override
    public String toString() {
        return "Pension Fund - " +
                "Name: " + name +
                ", isState - " + isState +
                ", Since Date: " + dateOfCreation  +
                ", Number of members: " + depositors.size() + "\n" +
                " Members: " + depositors +
                ']';
    }


    public void info() {
        System.out.println("Fund's name " + name);

        int count = (depositors != null) ? depositors.size() : 0;

        if (isState) {
            System.out.println("People invested in the fund: " + count / 1000 + " thousand");
        }
        else {
            System.out.println("People invested in the fund: " + count);
        }
    }

    public double calculatePensionFor(AbleToCalculatePension object) {
        if (isState && isWorkDayToday()) {
            return object.calculatePension();
        }
        else {
            return 0.0;
        }
    }

    private boolean isWorkDayToday() {
        LocalDate localDate = LocalDate.now();
        DayOfWeek dayOfWeekNow = localDate.getDayOfWeek();

        if (workDays == null) {
            return false;
        }

        return workDays.get(dayOfWeekNow);
    }

    public double calculateMedianPension() {
        if (depositors == null || depositors.isEmpty()) {
            return 0.0;
        }

        double pensionPaid = 0.0;

        for (Worker worker : depositors) {
            pensionPaid += calculatePensionFor(worker);
        }

        return pensionPaid / depositors.size();
    }
}
